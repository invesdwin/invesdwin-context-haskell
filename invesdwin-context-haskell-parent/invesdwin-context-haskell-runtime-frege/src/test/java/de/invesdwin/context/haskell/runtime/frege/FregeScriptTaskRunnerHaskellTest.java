package de.invesdwin.context.haskell.runtime.frege;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.jupiter.api.Test;

import de.invesdwin.context.haskell.runtime.frege.tests.InputsAndResultsTests;
import de.invesdwin.context.haskell.runtime.frege.tests.callback.ParametersAndReturnsTests;
import de.invesdwin.context.haskell.runtime.frege.tests.callback.SimpleCallbackTest;
import de.invesdwin.context.test.ATest;
import jakarta.inject.Inject;

@NotThreadSafe
public class FregeScriptTaskRunnerHaskellTest extends ATest {

    @Inject
    private FregeScriptTaskRunnerHaskell runner;

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
