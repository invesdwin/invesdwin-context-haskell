package de.invesdwin.context.haskell.runtime.etlas;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.jupiter.api.Test;

import de.invesdwin.context.haskell.runtime.contract.InputsAndResultsTests;
import de.invesdwin.context.haskell.runtime.contract.callback.ParametersAndReturnsTests;
import de.invesdwin.context.haskell.runtime.contract.callback.SimpleCallbackTest;
import de.invesdwin.context.haskell.runtime.etlas.EtlasScriptTaskRunnerHaskell;
import de.invesdwin.context.test.ATest;
import jakarta.inject.Inject;

@NotThreadSafe
public class EtlasScriptTaskRunnerHaskellTest extends ATest {

    @Inject
    private EtlasScriptTaskRunnerHaskell runner;

    @Test
    public void test() {
        new InputsAndResultsTests(runner).test();
    }

    @Test
    public void testParallel() {
        new InputsAndResultsTests(runner).testParallel();
    }

    @Test
    public void testCallback() {
        new ParametersAndReturnsTests(runner).test();
    }

    @Test
    public void testCallbackParallel() {
        new ParametersAndReturnsTests(runner).testParallel();
    }

    @Test
    public void testSimpleCallback() {
        new SimpleCallbackTest(runner).testSimpleCallback();
    }

}
