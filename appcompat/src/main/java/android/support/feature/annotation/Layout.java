package android.support.feature.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hongyang on 17-7-10.
 */
@Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE})
@Retention(RUNTIME)
public @interface Layout {
    @LayoutRes int id() default -1 ;
}
