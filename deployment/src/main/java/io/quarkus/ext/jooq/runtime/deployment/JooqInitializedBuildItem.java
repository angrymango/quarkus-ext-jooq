package io.quarkus.ext.jooq.runtime.deployment;

import io.quarkus.builder.item.SimpleBuildItem;

/**
 * Marker build item indicating the QuerySQL has been fully initialized.
 */
public final class JooqInitializedBuildItem extends SimpleBuildItem {

    public JooqInitializedBuildItem() {
    }
}
