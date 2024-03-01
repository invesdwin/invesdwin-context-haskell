package de.invesdwin.context.haskell.runtime.etlas;

import javax.annotation.concurrent.ThreadSafe;

import de.invesdwin.context.system.properties.SystemProperties;

@ThreadSafe
public final class EtlasProperties {

    public static final String ETLAS_COMMAND;

    static {

        final SystemProperties systemProperties = new SystemProperties(EtlasProperties.class);
        if (systemProperties.containsValue("ETLAS_COMMAND")) {
            ETLAS_COMMAND = systemProperties.getString("ETLAS_COMMAND");
        } else {
            ETLAS_COMMAND = null;
        }
    }

    private EtlasProperties() {}

}
