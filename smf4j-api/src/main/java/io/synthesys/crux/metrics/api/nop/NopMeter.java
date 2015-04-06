package io.synthesys.crux.metrics.api.nop;

import io.synthesys.crux.metrics.api.Meter;

class NopMeter implements Meter {
  static final Meter INSTANCE = new NopMeter();

  @Override
  public void mark() {
    // do nothing
  }
}
