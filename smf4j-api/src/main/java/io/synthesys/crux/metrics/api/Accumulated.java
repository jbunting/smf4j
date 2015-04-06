package io.synthesys.crux.metrics.api;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * Indicates that results of the annotated method should be added to an accumulator.
 */
@Retention(RUNTIME)
@Target(METHOD)
@Inherited
public @interface Accumulated {
}
