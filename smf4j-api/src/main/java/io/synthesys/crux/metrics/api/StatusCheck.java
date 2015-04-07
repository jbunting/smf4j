package io.synthesys.crux.metrics.api;

/**
 * TODO: Document this class
 */
public interface StatusCheck<E extends Enum> {
  E check();

  enum UpDown
  {
    UP,DOWN
  }

  /**
   * Meant to provide a functional interface that could easily be satisfied by a preexisting method.
   */
  interface Simple {
    boolean isUp();
  }
}
