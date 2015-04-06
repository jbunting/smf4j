package io.synthesys.crux.metrics.api.nop;

import io.synthesys.crux.metrics.api.Timer;

class NopTimer implements Timer {
  static final Timer INSTANCE = new NopTimer();

  @Override
  public Run start() {
    return new Run();
  }

  private static class Run extends Timer.Run {
    @Override
    public void stop() {
      // do nothing
    }
  }
}
