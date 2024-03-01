package de.invesdwin.context.haskell.runtime.etlas;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.haskell.runtime.contract.AScriptTaskInputsHaskellToExpression;

@NotThreadSafe
public class EtlasScriptTaskInputsHaskell extends AScriptTaskInputsHaskellToExpression {

    private final EtlasScriptTaskEngineHaskell engine;

    public EtlasScriptTaskInputsHaskell(final EtlasScriptTaskEngineHaskell engine) {
        this.engine = engine;
    }

    @Override
    public EtlasScriptTaskEngineHaskell getEngine() {
        return engine;
    }

}
