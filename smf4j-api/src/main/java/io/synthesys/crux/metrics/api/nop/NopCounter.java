package io.synthesys.crux.metrics.api.nop;

import io.synthesys.crux.metrics.api.Counter;

class NopCounter implements Counter {
  static final Counter INSTANCE = new NopCounter();

  @Override
  public void inc() {
    // do nothing
  }

  @Override
  public void dec() {
    // do nothing
  }

  @Override
  public void inc(long amount) {
    // do nothing
  }

  @Override
  public void dec(long amount) {
    // do nothing
  }
}
