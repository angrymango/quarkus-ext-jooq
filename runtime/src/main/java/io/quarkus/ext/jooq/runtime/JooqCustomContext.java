package io.quarkus.ext.jooq.runtime;

import io.quarkus.ext.jooq.sql.SqlLoggerListener;
import org.jboss.logging.Logger;
import org.jooq.Configuration;
import org.jooq.ExecuteListenerProvider;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Custom Configuration
 *
 * @author <a href="mailto:leo.tu.taipei@gmail.com">Leo Tu</a>
 */
public interface JooqCustomContext {
    static final Logger LOGGER = Logger.getLogger(JooqCustomContext.class);

    /**
     * File "jooq-settings.xml"
     */
    default public void apply(Configuration configuration) {
        Settings settings = configuration.settings();
        settings.setRenderCatalog(false);
        settings.setRenderSchema(false);
        settings.setExecuteLogging(SqlLoggerListener.sqlLog.isTraceEnabled()); // LoggerListener
        settings.setRenderFormatted(false);
        settings.setRenderNameStyle(RenderNameStyle.AS_IS);
        settings.setQueryTimeout(60); // seconds

        if (settings.isExecuteLogging() && configuration instanceof DefaultConfiguration) {
            DefaultConfiguration defaultConfig = (DefaultConfiguration) configuration;
            if (configuration instanceof DefaultConfiguration && configuration.executeListenerProviders() != null) {
                List<ExecuteListenerProvider> providers = new ArrayList<>(Arrays.asList(configuration.executeListenerProviders()));
                providers.add(SqlLoggerListener::new);
                defaultConfig.setExecuteListenerProvider(providers.toArray(new ExecuteListenerProvider[0]));
            } else {
                defaultConfig.setExecuteListenerProvider(SqlLoggerListener::new);
            }
            Stream.of(configuration.executeListenerProviders()).forEach(p -> {
                LOGGER.debugv("executeListenerProvider: {0}", p);
            });
        }
    }
}
