package io.synthesys.crux.metrics.dropwizard;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer.Context;
import io.synthesys.crux.metrics.api.Timer;

class DropwizardTimer implements Timer {

  private final com.codahale.metrics.Timer timer;

  public DropwizardTimer(String key, MetricRegistry metricRegistry) {
    timer = metricRegistry.timer(key);
  }

  @Override
  public Run start() {
    final Context timerContext = timer.time();
    return new Run() {
      @Override
      public void stop() {
        timerContext.stop();
      }
    };
  }
}
