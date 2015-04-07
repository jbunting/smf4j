package io.synthesys.crux.metrics.api;

import io.synthesys.crux.metrics.api.nop.NopMetricBackend;

import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Primary entry point for metric creation.
 */
public final class MetricFactory {
  private MetricFactory() {}

  private static MetricBackend backend = locateAndLoadAdapter();

  public static Timer getTimer(final Class<?> anchor, final String ... context)
  {
    return backend.getTimer(contextToName(anchor.getName(), context));
  }

  public static Timer getTimer(final String ... context)
  {
    return backend.getTimer(contextToName(null, context));
  }

  public static Counter getCounter(final Class<?> anchor, final String ... context)
  {
    return backend.getCounter(contextToName(anchor.getName(), context));
  }

  public static Counter getCounter(final String ... context)
  {
    return backend.getCounter(contextToName(null, context));
  }

  public static Meter getMeter(final Class<?> anchor, final String ... context)
  {
    return backend.getMeter(contextToName(anchor.getName(), context));
  }

  public static Meter getMeter(final String ... context)
  {
    return backend.getMeter(contextToName(null, context));
  }

  public static Accumulator getAccumulator(final Class<?> anchor, final String ... context)
  {
    return backend.getAccumulator(contextToName(anchor.getName(), context));
  }

  public static Accumulator getAccumulator(final String ... context)
  {
    return backend.getAccumulator(contextToName(null, context));
  }

  public static void registerStatusCheck(final StatusCheck<?> check, final Class<?> anchor, final String ... context)
  {
    backend.registerCheck(check, contextToName(anchor.getName(), context));
  }

  public static void registerStatusCheck(final StatusCheck<?> check, final String ... context)
  {
    backend.registerCheck(check, contextToName(null, context));
  }

  private static String contextToName(final String prefix, final String[] context) {
    final StringBuilder sb = new StringBuilder();
    if (prefix != null)
    {
      sb.append(prefix);
    }
    for (int i = 0; i < context.length; i++)
    {
      if (prefix != null || i > 0)
      {
        sb.append('.');
      }
      sb.append(context[i]);
    }
    return sb.toString();

  }

  private static MetricBackend locateAndLoadAdapter() {
    final ServiceLoader<MetricBackend> serviceLoader = ServiceLoader.load(MetricBackend.class);
    final List<MetricBackend> adapters = new ArrayList<>();
    for (final MetricBackend adapter: serviceLoader)
    {
      adapters.add(adapter);
    }
    if (adapters.size() > 1)
    {
      throw new IllegalStateException("No more than one MetricFactoryAdapter may be registered at a time. Found: " + adapters);
    }
    else if (adapters.isEmpty())
    {
      return new NopMetricBackend();
    }
    else
    {
      return adapters.get(0);
    }
  }
}
