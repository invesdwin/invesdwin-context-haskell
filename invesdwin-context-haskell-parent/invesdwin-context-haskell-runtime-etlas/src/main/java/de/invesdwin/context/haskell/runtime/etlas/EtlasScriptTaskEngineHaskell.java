package de.invesdwin.context.haskell.runtime.etlas;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.haskell.runtime.etlas.pool.ExtendedEtlasBridge;
import de.invesdwin.context.haskell.runtime.etlas.pool.EtlasObjectPool;
import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.util.concurrent.WrappedExecutorService;
import de.invesdwin.util.concurrent.lock.ILock;
import de.invesdwin.util.concurrent.lock.disabled.DisabledLock;

@NotThreadSafe
public class EtlasScriptTaskEngineHaskell implements IScriptTaskEngine {

    private ExtendedEtlasBridge juliaCaller;
    private final EtlasScriptTaskInputsHaskell inputs;
    private final EtlasScriptTaskResultsHaskell results;

    public EtlasScriptTaskEngineHaskell(final ExtendedEtlasBridge juliaCaller) {
        this.juliaCaller = juliaCaller;
        this.inputs = new EtlasScriptTaskInputsHaskell(this);
        this.results = new EtlasScriptTaskResultsHaskell(this);
    }

    @Override
    public void eval(final String expression) {
        juliaCaller.eval(expression);
    }

    @Override
    public EtlasScriptTaskInputsHaskell getInputs() {
        return inputs;
    }

    @Override
    public EtlasScriptTaskResultsHaskell getResults() {
        return results;
    }

    @Override
    public void close() {
        juliaCaller = null;
    }

    @Override
    public ExtendedEtlasBridge unwrap() {
        return juliaCaller;
    }

    /**
     * Each instance has its own engine, so no shared locking required.
     */
    @Override
    public ILock getSharedLock() {
        return DisabledLock.INSTANCE;
    }

    /**
     * No executor needed.
     */
    @Override
    public WrappedExecutorService getSharedExecutor() {
        return null;
    }

    public static EtlasScriptTaskEngineHaskell newInstance() {
        return new EtlasScriptTaskEngineHaskell(EtlasObjectPool.INSTANCE.borrowObject()) {
            @Override
            public void close() {
                final ExtendedEtlasBridge unwrap = unwrap();
                if (unwrap != null) {
                    EtlasObjectPool.INSTANCE.returnObject(unwrap);
                }
                super.close();
            }
        };
    }

}
