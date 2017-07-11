package com.using.feature;

import android.content.Context;
import android.support.feature.annotation.Positive;
import android.util.Log;

import java.io.PipedOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by hongyang on 17-7-11.
 */

public class Test {

     public  static void main(Context context){
         Method[] methods = context.getClass().getDeclaredMethods();

         for (Method method:methods){
             Positive positive = method.getAnnotation(Positive.class);
             if (positive!=null){
                 Log.e("Test",method.getName());
             }
         }
     }

}

