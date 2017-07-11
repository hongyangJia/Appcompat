package android.support.feature.app;

import android.content.Context;
import android.content.res.Resources;
import android.support.feature.annotation.Button;
import android.support.feature.annotation.Layout;
import android.support.feature.annotation.Message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @English
 * @Function: use reflection, annotation technology to create a simple and efficient dialog box
 * @Description: the use of reflection to create a statement object, resolve the value of the Notes and assigned to the dialog box
 * @Mode: warning dialog box, prompt dialog box, custom dialog box, template dialog box
 * @Chinese
 * @功能: 利用反射，注解技术 简洁,高效地创建对话框
 * @说明: 利用反射创建声明对象, 解析注解值.并且赋值给对话框
 * @模式: 警告型对话框, 提示型对话框, 自定义对话框, 模板对话框
 * -----------------------------------------------------------------------------------
 * Created by hongyang on 17-7-4.
 */
public class AppcompatFeature {

    /**
     * @English Filter the specified object
     * @Chinese 过滤指定条件的对象
     */
    private static final String TAG = "android.support.feature.app.FeatureDialog";

    /**
     * @English Determines whether the comment is assigned or not
     * @Chinese 判断注解是否赋值.默认为0未赋值
     */
    private static final int DEFULT = 0;

    /**
     * @English Traverse all objects to see if the object is a FeatureDialog type
     * @Chinese 遍历所有对象, 查看对象是否是FeatureDialog类型
     */
    public static void init(Context context) {
        Field[] fields = context.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (TAG.equals(field.getType().getName())) {
                findField(field, context);
            }
        }
    }

    /*
     * @English
     * If the FeatureDialog type object is found, the object is created
     * If you add annotations, assign an assignment to the analysis value
     * If no annotation is added, the program starts the default value
     * ---------------------------------------------------------------
     * @Chinese
     * 如果找到FeatureDialog类型对象,便创建对象
     * 如果添加注解就解析注解值给予赋值
     * 如果未添加注解，程序会启动默认值
     */
    private static void findField(Field field, Context context) {
        try {
            AlertController.AlertParams alertParams = findAnnotations(field.getAnnotations(), context);
            FeatureDialog.Builder builder = new FeatureDialog.Builder(context, alertParams);
            builder.setId(field.getName());
            field.setAccessible(true);
            field.set(context, builder.create());
        } catch (IllegalAccessException e) {
            throw new Resources.NotFoundException(e.getMessage());
        }
    }

    /**
     * @English 1.Traverse the object class all annotations
     * 2.Create a dialog box property class
     * @Chinese 1.遍历对象类所有注解
     * 2.创建对话框属性类
     */
    private static AlertController.AlertParams findAnnotations(Annotation[] annotations, Context context) {
        AlertController.AlertParams alertParams = new AlertController.AlertParams(context);
        for (Annotation annotation : annotations) {
            parsingAnnotations(alertParams, annotation);
        }
        return alertParams;
    }

    /**
     * @English Parse the annotation value and add it to the dialog box properties class
     * @Chinese 解析注解值, 并且添加到对话框属性类
     */
    private static void parsingAnnotations(AlertController.AlertParams alertParams, Annotation annotation) {

        if (annotation instanceof Message) {
            int title = ((Message) annotation).title();
            int message = ((Message) annotation).message();
            if (title != DEFULT) {
                alertParams.mTitle = alertParams.mContext.getText(title);
            }
            if (message != DEFULT) {
                alertParams.mMessage = alertParams.mContext.getText(message);
            }
        }

        if (annotation instanceof Button) {
            int positive = ((Button) annotation).mButtonPositive();
            int negative = ((Button) annotation).mButtonNegative();
            int neutral = ((Button) annotation).mButtonNeutral();
            if (positive != DEFULT) {
                alertParams.mButtonPositive = alertParams.mContext.getText(positive);
            }
            if (negative != DEFULT) {
                alertParams.mButtonNegative = alertParams.mContext.getText(negative);
            }
            if (neutral != DEFULT) {
                alertParams.mButtonNeutral = alertParams.mContext.getText(neutral);
            }
        }

        if (annotation instanceof Layout) {
            alertParams.mLayoutResID = ((Layout) annotation).id();
        }

    }
}
