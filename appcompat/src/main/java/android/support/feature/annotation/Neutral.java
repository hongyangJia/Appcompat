package android.support.feature.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hongyang on 17-7-7.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Neutral {
    String call() default "neutral";
}
