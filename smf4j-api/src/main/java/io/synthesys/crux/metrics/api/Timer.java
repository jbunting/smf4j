package io.synthesys.crux.metrics.api;

/**
 * TODO: Document this class
 */
public interface Timer extends Metric {
  /**
   * Starts the timer.
   * @return an instance of this run
   */
  Run start();

  abstract class Run implements AutoCloseable {
    /**
     * Stops the timer.
     */
    public abstract void stop();

    /**
     * Invokes {@link #stop()}, allowing for use in a try-with-resources block.
     */
    public final void close() {
      this.stop();
    }
  }
}
