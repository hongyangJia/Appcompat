package android.support.feature.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hongyang on 17-7-3.
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Message {
    int  title() default 0;
    int  message() default 0;
}
