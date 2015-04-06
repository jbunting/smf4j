package io.synthesys.crux.metrics.dropwizard;

import com.codahale.metrics.MetricRegistry;

/**
 * Implementations of this interface must be registered with the {@link java.util.ServiceLoader ServiceLoader}. Only one
 * may be registered at a time. If one is registered, it will be used to configure the global {@link MetricRegistry}.
 */
public interface Configurator {
  MetricRegistry configure();
}
