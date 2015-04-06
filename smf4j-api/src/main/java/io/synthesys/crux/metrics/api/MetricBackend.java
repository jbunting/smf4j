package io.synthesys.crux.metrics.api;

/**
 * Implementation point for a metric adapter. If you have a metrics library that you wish to hook into crux-metrics,
 * implement this interface and add a service loader registration.
 *
 * It is IMPERATIVE that only one registered implementation of this interface be on the classpath at a time. Otherwise
 * instantiation will fail.
 */
public interface MetricBackend {

  Accumulator getAccumulator(final String key);

  Counter getCounter(final String key);

  Meter getMeter(final String key);

  Timer getTimer(final String key);
}
