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

    private ExtendedEtlasBridge bridge;
    private final EtlasScriptTaskInputsHaskell inputs;
    private final EtlasScriptTaskResultsHaskell results;

    public EtlasScriptTaskEngineHaskell(final ExtendedEtlasBridge bridge) {
        this.bridge = bridge;
        this.inputs = new EtlasScriptTaskInputsHaskell(this);
        this.results = new EtlasScriptTaskResultsHaskell(this);
    }

    @Override
    public void eval(final String expression) {
        bridge.eval(expression);
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
        bridge = null;
    }

    @Override
    public ExtendedEtlasBridge unwrap() {
        return bridge;
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
