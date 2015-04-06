package io.synthesys.crux.metrics.dropwizard;

import com.codahale.metrics.MetricRegistry;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Loads the resource passed in the constructor as a groovy script. It adds a {@code registry} property to the binding
 * that may be modified or replaced. It is expected that the groovy script will setup whatever listeners or reporters
 * are necessary. The value of {@code registry} at the end of the script will be used as the system
 * {@link MetricRegistry}. If it is **NOT** an instance of {@link MetricRegistry} then a {@link ClassCastException} will
 * be thrown.
 */
class GroovyConfigurator implements Configurator {
  private final URL resource;

  public GroovyConfigurator(final URL resource) {
    this.resource = resource;
  }

  @Override
  public MetricRegistry configure() {
    final MetricRegistry registry = new MetricRegistry();

    final CompilerConfiguration compilerConfiguration = new CompilerConfiguration();

    final Binding binding = new Binding();
    binding.setProperty("registry", registry);
    final GroovyShell shell = new GroovyShell(GroovyConfigurator.class.getClassLoader(), binding, compilerConfiguration);
    try {
      shell.evaluate(resource.toURI());
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException("Failed to load metrics configuration resource " + resource, e);
    }
    return (MetricRegistry) binding.getProperty("registry");
  }
}
