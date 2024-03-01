package de.invesdwin.context.haskell.runtime.ghci.pool;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class ExtendedGhciBridge extends ModifiedGhciBridge {

    public ExtendedGhciBridge() {
        super();
    }

    public void reset() throws IOException {
        getErrWatcher().clearLog();
        eval(":load");
        getErrWatcher().clearLog();
    }

}
