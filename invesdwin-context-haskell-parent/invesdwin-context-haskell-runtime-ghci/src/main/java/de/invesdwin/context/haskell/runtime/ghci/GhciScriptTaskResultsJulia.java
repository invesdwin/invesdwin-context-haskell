package de.invesdwin.context.haskell.runtime.ghci;

import javax.annotation.concurrent.NotThreadSafe;

import com.fasterxml.jackson.databind.JsonNode;

import de.invesdwin.context.haskell.runtime.contract.AScriptTaskResultsHaskellFromJson;

@NotThreadSafe
public class GhciScriptTaskResultsJulia extends AScriptTaskResultsHaskellFromJson {

    private final GhciScriptTaskEngineJulia engine;

    public GhciScriptTaskResultsJulia(final GhciScriptTaskEngineJulia engine) {
        this.engine = engine;
    }

    @Override
    public GhciScriptTaskEngineJulia getEngine() {
        return engine;
    }

    @Override
    protected JsonNode getAsJsonNode(final String variable) {
        return engine.unwrap().getAsJsonNode(variable);
    }
}