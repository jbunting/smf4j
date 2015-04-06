package io.synthesys.crux.metrics.dropwizard;

import com.codahale.metrics.MetricRegistry;
import io.synthesys.crux.metrics.api.Meter;

class DropwizardMeter implements Meter {

  private final com.codahale.metrics.Meter meter;

  public DropwizardMeter(String key, MetricRegistry metricRegistry) {
    meter = metricRegistry.meter(key);
  }

  @Override
  public void mark() {
    meter.mark();
  }
}
