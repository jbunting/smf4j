package io.synthesys.crux.metrics.dropwizard;

import com.codahale.metrics.MetricRegistry;
import io.synthesys.crux.metrics.api.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * 1. Metrics tries to find a file called metrics-test.groovy in the classpath.
 *
 * 2. If no such file is found, metrics tries to find a file called metrics.groovy in the classpath.
 *
 * 3. If no such file is found the ServiceLoader will be
 *    used to resolve an implementation of io.synthesys.crux.metrics.dropwizard.Configurator. The only implementation
 *    found will be used. If multiple instances are found, initialization will error out. See ServiceLoader
 *    documentation for more details.
 *
 * 4. If none of the above succeeds, metrics configures itself automatically using the BasicConfigurator which will
 *    cause metric output to be directed to the console.
 */
public class DropwizardMetricBackend implements MetricBackend {
  private final MetricRegistry metricRegistry = new MetricRegistry();

  @Override
  public Accumulator getAccumulator(String key) {
    return new DropwizardAccumulator(key, metricRegistry);
  }

  @Override
  public Counter getCounter(String key) {
    return new DropwizardCounter(key, metricRegistry);
  }

  @Override
  public Meter getMeter(String key) {
    return new DropwizardMeter(key, metricRegistry);
  }

  @Override
  public Timer getTimer(String key) {
    return new DropwizardTimer(key, metricRegistry);
  }

  static MetricRegistry init() {
    final Configurator configurator = loadConfigurator();
    return configurator.configure();
  }

  private static Configurator loadConfigurator() {
    final ClassLoader classLoader = DropwizardMetricBackend.class.getClassLoader();
    final URL testResource = classLoader.getResource("metrics-test.groovy");
    if (testResource != null)
    {
      return new GroovyConfigurator(testResource);
    }
    final URL resource = classLoader.getResource("metrics.groovy");
    if (resource != null)
    {
      return new GroovyConfigurator(resource);
    }
    final ServiceLoader<Configurator> serviceLoader = ServiceLoader.load(Configurator.class, classLoader);
    final List<Configurator> allConfigurators = new ArrayList<>();
    for (Configurator configurator: serviceLoader)
    {
      allConfigurators.add(configurator);
    }
    if (allConfigurators.size() > 1)
    {
      throw new IllegalStateException("More than one configurator was located. Please remove all but one " +
              "configuration from your classpath." + allConfigurators);
    }
    else if (allConfigurators.size() < 1)
    {
      return new BasicConfigurator();
    }
    else
    {
      return allConfigurators.get(0);
    }
  }
}
