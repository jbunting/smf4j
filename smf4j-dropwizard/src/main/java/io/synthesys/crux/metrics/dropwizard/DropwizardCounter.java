package io.synthesys.crux.metrics.dropwizard;

import com.codahale.metrics.MetricRegistry;
import io.synthesys.crux.metrics.api.Counter;

class DropwizardCounter implements Counter {

  private final com.codahale.metrics.Counter counter;

  public DropwizardCounter(String key, MetricRegistry metricRegistry) {
    counter = metricRegistry.counter(key);
  }

  @Override
  public void inc() {
    this.counter.inc();
  }

  @Override
  public void dec() {
    this.counter.dec();
  }

  @Override
  public void inc(long amount) {
    this.counter.inc(amount);
  }

  @Override
  public void dec(long amount) {
    this.counter.dec(amount);
  }
}
