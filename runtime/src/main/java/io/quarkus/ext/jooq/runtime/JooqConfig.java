package io.quarkus.ext.jooq.runtime;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

import java.util.Map;

/**
 * Read from application.properties file
 *
 * @author <a href="mailto:leo.tu.taipei@gmail.com">Leo Tu</a>
 */
@ConfigRoot(name = "jooq", phase = ConfigPhase.BUILD_TIME)
public class JooqConfig {

    /**
     * The default config.
     */
    @ConfigItem(name = ConfigItem.PARENT)
    public JooqItemConfig defaultConfig;

    /**
     * Additional configs.
     */
    @ConfigItem(name = ConfigItem.PARENT)
    public Map<String, JooqItemConfig> namedConfig;

}
