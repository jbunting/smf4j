package io.synthesys.crux.metrics.dropwizard;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import io.synthesys.crux.metrics.api.Accumulator;

class DropwizardAccumulator implements Accumulator {

  private final Histogram histogram;

  public DropwizardAccumulator(String key, MetricRegistry metricRegistry) {
    histogram = metricRegistry.histogram(key);
  }

  @Override
  public void accumulate(long value) {
    histogram.update(value);
  }
}
