package de.invesdwin.context.haskell.runtime.etlas;

import javax.annotation.concurrent.Immutable;

import org.springframework.beans.factory.FactoryBean;

import de.invesdwin.context.haskell.runtime.contract.AScriptTaskHaskell;
import de.invesdwin.context.haskell.runtime.contract.IScriptTaskRunnerHaskell;
import de.invesdwin.context.haskell.runtime.contract.callback.socket.SocketScriptTaskCallbackContext;
import de.invesdwin.context.haskell.runtime.etlas.pool.ExtendedEtlasBridge;
import de.invesdwin.context.haskell.runtime.etlas.pool.EtlasObjectPool;
import de.invesdwin.context.integration.script.callback.IScriptTaskCallback;
import de.invesdwin.context.integration.script.callback.LoggingDelegateScriptTaskCallback;
import de.invesdwin.util.error.Throwables;
import jakarta.inject.Named;

@Immutable
@Named
public final class EtlasScriptTaskRunnerHaskell
        implements IScriptTaskRunnerHaskell, FactoryBean<EtlasScriptTaskRunnerHaskell> {

    public static final EtlasScriptTaskRunnerHaskell INSTANCE = new EtlasScriptTaskRunnerHaskell();

    /**
     * public for ServiceLoader support
     */
    public EtlasScriptTaskRunnerHaskell() {}

    @Override
    public <T> T run(final AScriptTaskHaskell<T> scriptTask) {
        //get session
        final ExtendedEtlasBridge bridge = EtlasObjectPool.INSTANCE.borrowObject();
        final IScriptTaskCallback callback = scriptTask.getCallback();
        final SocketScriptTaskCallbackContext context;
        if (callback != null) {
            context = new SocketScriptTaskCallbackContext(LoggingDelegateScriptTaskCallback.maybeWrap(LOG, callback));
        } else {
            context = null;
        }
        try {
            //inputs
            final EtlasScriptTaskEngineHaskell engine = new EtlasScriptTaskEngineHaskell(bridge);
            if (context != null) {
                context.init(engine);
            }
            scriptTask.populateInputs(engine.getInputs());

            //execute
            scriptTask.executeScript(engine);

            //results
            final T result = scriptTask.extractResults(engine.getResults());
            if (context != null) {
                context.deinit(engine);
            }
            engine.close();

            //return
            EtlasObjectPool.INSTANCE.returnObject(bridge);
            return result;
        } catch (final Throwable t) {
            //we have to destroy instances on exceptions, otherwise e.g. SFrontiers.jl might get stuck with some inconsistent state
            EtlasObjectPool.INSTANCE.invalidateObject(bridge);
            throw Throwables.propagate(t);
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }

    @Override
    public EtlasScriptTaskRunnerHaskell getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return EtlasScriptTaskRunnerHaskell.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
