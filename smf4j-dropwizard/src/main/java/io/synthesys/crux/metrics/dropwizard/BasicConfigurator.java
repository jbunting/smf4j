package io.synthesys.crux.metrics.dropwizard;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * Creates a new {@link MetricRegistry} with no special configuration. Adds a ConsoleReporter to it that dumps metrics
 * to {@link System#err} every 5 minutes.
 */
class BasicConfigurator implements Configurator {
  @Override
  public MetricRegistry configure() {
    final MetricRegistry registry = new MetricRegistry();

    ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(registry)
            .outputTo(System.err)
            .build();
    consoleReporter.start(5, TimeUnit.MINUTES);
    return registry;
  }
}
