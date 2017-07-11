package android.support.feature.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hongyang on 17-7-7.
 */

@Target(FIELD)
@Retention(RUNTIME)
public @interface Button {
    int mButtonPositive() default 0;

    int mButtonNegative() default 0;

    int mButtonNeutral()  default 0;
}
