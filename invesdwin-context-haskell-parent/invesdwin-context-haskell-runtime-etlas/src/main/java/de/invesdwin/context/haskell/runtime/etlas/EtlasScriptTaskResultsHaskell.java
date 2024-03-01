package de.invesdwin.context.haskell.runtime.etlas;

import javax.annotation.concurrent.NotThreadSafe;

import com.fasterxml.jackson.databind.JsonNode;

import de.invesdwin.context.haskell.runtime.contract.AScriptTaskResultsHaskellFromJson;

@NotThreadSafe
public class EtlasScriptTaskResultsHaskell extends AScriptTaskResultsHaskellFromJson {

    private final EtlasScriptTaskEngineHaskell engine;

    public EtlasScriptTaskResultsHaskell(final EtlasScriptTaskEngineHaskell engine) {
        this.engine = engine;
    }

    @Override
    public EtlasScriptTaskEngineHaskell getEngine() {
        return engine;
    }

    @Override
    protected JsonNode getAsJsonNode(final String variable) {
        return engine.unwrap().getAsJsonNode(variable);
    }
}