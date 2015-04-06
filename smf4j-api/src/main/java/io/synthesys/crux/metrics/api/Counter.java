package io.synthesys.crux.metrics.api;

/**
 * TODO: Document this class
 */
public interface Counter extends Metric {
  void inc();
  void dec();
  void inc(long amount);
  void dec(long amount);
}
