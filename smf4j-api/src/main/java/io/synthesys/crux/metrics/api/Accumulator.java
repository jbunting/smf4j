package io.synthesys.crux.metrics.api;

/**
 * TODO: Document this class
 */
public interface Accumulator extends Metric {
  void accumulate(final long value);
}
