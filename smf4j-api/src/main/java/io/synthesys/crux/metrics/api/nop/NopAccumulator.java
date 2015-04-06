package io.synthesys.crux.metrics.api.nop;

import io.synthesys.crux.metrics.api.Accumulator;

class NopAccumulator implements Accumulator {
  static final Accumulator INSTANCE = new NopAccumulator();

  @Override
  public void accumulate(long value) {
    // do nothing
  }
}
