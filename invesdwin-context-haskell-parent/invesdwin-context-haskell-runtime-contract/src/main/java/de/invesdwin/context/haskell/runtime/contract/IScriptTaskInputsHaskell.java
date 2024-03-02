package de.invesdwin.context.haskell.runtime.contract;

import de.invesdwin.context.integration.script.IScriptTaskInputs;

public interface IScriptTaskInputsHaskell extends IScriptTaskInputs {

    @Override
    default void putExpression(final String variable, final String expression) {
        getEngine().eval(variable + " = " + expression);
    }

    @Override
    default void putNull(final String variable) {
        putExpression(variable, "Nothing");
    }

    @Override
    default void remove(final String variable) {
        putNull(variable);
    }

}
