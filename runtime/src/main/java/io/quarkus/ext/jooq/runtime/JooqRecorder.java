package io.quarkus.ext.jooq.runtime;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.runtime.DataSources;
import io.quarkus.runtime.annotations.Recorder;
import org.jboss.logging.Logger;

import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.arc.runtime.BeanContainerListener;

/**
 * Quarkus Template class (runtime)
 *
 * @author <a href="mailto:leo.tu.taipei@gmail.com">Leo Tu</a>
 */
@Recorder
public class JooqRecorder {
    private static final Logger log = Logger.getLogger(JooqRecorder.class);

    /**
     * Build Time
     */
    public BeanContainerListener addContainerCreatedListener(Class<? extends AbstractDslContextProducer> dslContextProducerClass) {
        return new BeanContainerListener() {
            /**
             * Runtime Time
             */
            @Override
            public void created(BeanContainer beanContainer)  { // Arc.container()
                AbstractDslContextProducer dslContextProducer = beanContainer.instance(dslContextProducerClass);
                if (dslContextProducer == null) {
                    log.warn("(dslContextProducer == null)");
                } else {
                    log.debugv("dslContextProducer.class: {0}", dslContextProducer.getClass().getName());
                }
            }
        };
    }
}
