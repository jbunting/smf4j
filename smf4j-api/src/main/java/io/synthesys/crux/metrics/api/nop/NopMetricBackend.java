package io.synthesys.crux.metrics.api.nop;

import io.synthesys.crux.metrics.api.*;

/**
 * Used by default if no registered adapter is found. Does absolutely nothing except implement the API.
 */
public class NopMetricBackend implements MetricBackend {
  @Override
  public Accumulator getAccumulator(String key) {
    return NopAccumulator.INSTANCE;
  }

  @Override
  public Counter getCounter(String key) {
    return NopCounter.INSTANCE;
  }

  @Override
  public Meter getMeter(String key) {
    return NopMeter.INSTANCE;
  }

  @Override
  public Timer getTimer(String key) {
    return NopTimer.INSTANCE;
  }
}
