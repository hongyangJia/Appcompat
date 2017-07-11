package android.support.feature.property;

import android.support.feature.app.FeatureDialog;

/**
 * Created by hongyang on 17-7-7.
 */

public class Tools {

    public static <T> void NullPointerException(T t1) {
        if (t1 == null) {
            throw new NullPointerException();
        }
    }

    public static <T> void NullPointerException(T t1, T t2) {
        if (t1 == null) {
            throw new NullPointerException();
        }
        if (t2 == null) {
            throw new NullPointerException();
        }
    }

}
