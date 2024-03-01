package de.invesdwin.context.haskell.runtime.etlas.pool;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class ExtendedEtlasBridge extends ModifiedEtlasBridge {

    public ExtendedEtlasBridge() {
        super();
    }

    public void reset() throws IOException {
        getErrWatcher().clearLog();
        eval(":load");
        getErrWatcher().clearLog();
    }

}
