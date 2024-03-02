package de.invesdwin.context.haskell.runtime.frege;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.haskell.runtime.contract.AScriptTaskInputsHaskellToExpression;

@NotThreadSafe
public class FregeScriptTaskInputsHaskell extends AScriptTaskInputsHaskellToExpression {

    private final FregeScriptTaskEngineHaskell engine;

    public FregeScriptTaskInputsHaskell(final FregeScriptTaskEngineHaskell engine) {
        this.engine = engine;
    }

    /**
     * frege does not really support Nothing/null, causes exceptions when "show" is called on it, so we use empty string
     * as a workaround
     */
    @Override
    public void putNull(final String variable) {
        putExpression(variable, "\"\"");
    }

    @Override
    public FregeScriptTaskEngineHaskell getEngine() {
        return engine;
    }

    @Override
    protected String booleanToString(final boolean value) {
        return String.valueOf(value);
    }

}
