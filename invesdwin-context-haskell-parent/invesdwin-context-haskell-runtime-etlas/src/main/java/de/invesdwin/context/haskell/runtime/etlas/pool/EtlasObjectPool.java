package de.invesdwin.context.haskell.runtime.etlas.pool;

import java.io.IOException;

import javax.annotation.concurrent.ThreadSafe;

import org.springframework.beans.factory.FactoryBean;

import de.invesdwin.util.concurrent.pool.timeout.ATimeoutObjectPool;
import de.invesdwin.util.time.date.FTimeUnit;
import de.invesdwin.util.time.duration.Duration;
import jakarta.inject.Named;

@ThreadSafe
@Named
public final class EtlasObjectPool extends ATimeoutObjectPool<ExtendedEtlasBridge>
        implements FactoryBean<EtlasObjectPool> {

    public static final EtlasObjectPool INSTANCE = new EtlasObjectPool();

    private EtlasObjectPool() {
        //julia compilation is a lot of overhead, thus keep instances open longer
        super(new Duration(10, FTimeUnit.MINUTES), new Duration(10, FTimeUnit.SECONDS));
    }

    @Override
    public void invalidateObject(final ExtendedEtlasBridge element) {
        element.close();
    }

    @Override
    protected ExtendedEtlasBridge newObject() {
        final ExtendedEtlasBridge session = new ExtendedEtlasBridge();
        try {
            session.open();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return session;
    }

    @Override
    protected boolean passivateObject(final ExtendedEtlasBridge element) {
        try {
            element.reset();
            return true;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EtlasObjectPool getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return EtlasObjectPool.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
