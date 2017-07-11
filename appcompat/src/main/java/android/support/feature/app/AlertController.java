package android.support.feature.app;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.feature.annotation.Negative;
import android.support.feature.annotation.Neutral;
import android.support.feature.annotation.Positive;
import android.support.feature.property.Tools;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hongyang on 17-7-6.
 */

class AlertController {

    private static final int PARAME = 20;
    private CharSequence mId;
    private CharSequence mTitle;
    private CharSequence mMessage;
    private CharSequence mButtonPositive;
    private CharSequence mButtonNegative;
    private CharSequence mButtonNeutral;

    private ListAdapter mAdapter;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mAlertDialog;
    private Alertlistener mAlertlistener;

    private CharSequence mButtonPositiveId;
    private CharSequence mButtonNegativeId;
    private CharSequence mButtonNeutralId;

    private Context mContext;
    private View view;
    private android.widget.ListView listView;

    AlertController(Context mContext) {
        this.mContext = mContext;
        mBuilder = new AlertDialog.Builder(mContext);
    }

    void setTitle(CharSequence mTitle) {
        this.mTitle = mTitle;
        mBuilder.setTitle(mTitle);
    }

    void setMessage(CharSequence mMessage) {
        this.mMessage = mMessage;
        mBuilder.setMessage(mMessage);
    }

    void setButtonPositive(CharSequence mButtonPositive) {
        this.mButtonPositive = mButtonPositive;
        mBuilder.setPositiveButton(mButtonPositive, mAlertlistener);
    }

    void setButtonNegative(CharSequence mButtonNegative) {
        this.mButtonNegative = mButtonNegative;
        mBuilder.setNegativeButton(mButtonNegative, mAlertlistener);
    }

    void setButtonNeutral(CharSequence mButtonNeutral) {
        this.mButtonNeutral = mButtonNeutral;
        mBuilder.setNeutralButton(mButtonNeutral, mAlertlistener);
    }

    public void setButtonPositiveId(CharSequence mButtonPositiveId) {
        this.mButtonPositiveId = mButtonPositiveId;
    }

    public void setButtonNegativeId(CharSequence mButtonNegativeId) {
        this.mButtonNegativeId = mButtonNegativeId;
    }

    public void setButtonNeutralId(CharSequence mButtonNeutralId) {
        this.mButtonNeutralId = mButtonNeutralId;
    }

    public void setFlag(CharSequence mId) {
        Tools.NullPointerException(mId);
        this.mId = mId;
        mAlertlistener = new Alertlistener(mContext, mId.toString());
    }

    void show() {
        Tools.NullPointerException(mBuilder);
        this.mAlertDialog = mBuilder.show();
    }

    boolean isShowing() {
        return mAlertDialog != null && mAlertDialog.isShowing();
    }

    public void setLayoutResID(@LayoutRes int layoutResID) {
        Tools.NullPointerException(layoutResID);
        Tools.NullPointerException(mBuilder);
        LayoutInflater factory = LayoutInflater.from(mContext);
        View view = factory.inflate(layoutResID, null);
        this.view = view;
        mBuilder.setView(view);
    }

    public void setItems(CharSequence[] mItems, DialogInterface.OnClickListener listener) {
        Tools.NullPointerException(mItems, listener);
        mBuilder.setItems(mItems, listener);
    }

    public void setItems(List<CharSequence> mItems, DialogInterface.OnClickListener listener) {
        Tools.NullPointerException(mItems, listener);
        CharSequence[] mItem = mItems.toArray(new CharSequence[0]);
        setItems(mItem, listener);
    }

    public void setAdapter(BaseAdapter baseAdapter, AdapterView.OnItemClickListener itemClickListener) {
        Tools.NullPointerException(baseAdapter, itemClickListener);
        listView = new android.widget.ListView(mContext);
        listView.setPadding(PARAME, PARAME, PARAME, PARAME);
        listView.setDivider(null);
        listView.setAdapter(baseAdapter);
        listView.setOnItemClickListener(itemClickListener);
        mBuilder.setView(listView);
    }

    public android.widget.ListView getListView() {
        Tools.NullPointerException(listView);
        return listView;
    }

    public View getLayoutView() {
        Tools.NullPointerException(view);
        return view;
    }

    private class Alertlistener implements DialogInterface.OnClickListener {

        private Context context;
        private Method[] methods;
        private String mFlag;

        public Alertlistener(Context context, String mFlag) {
            this.context = context;
            this.mFlag = mFlag;
            this.methods = context.getClass().getDeclaredMethods();
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            traversalCall(which);
        }

        public void traversalCall(int which) {
            for (Method method : methods) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        findPositiveMethod(method);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        findNegativeMethod(method);
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        findNeutralMethod(method);
                        break;
                }
            }
        }

        private void methodCall(Method method) {
            try {
                method.setAccessible(true);
                method.invoke(context);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        private void findNeutralMethod(Method method) {
            Neutral annotations = method.getAnnotation(Neutral.class);
            if (annotations != null) {
                if (annotations.call().equals(mFlag)) {
                    methodCall(method);
                }
            }
        }

        private void findNegativeMethod(Method method) {
            Negative annotations = method.getAnnotation(Negative.class);
            if (annotations != null) {
                if (annotations.call().equals(mFlag)) {
                    methodCall(method);
                }
            }
        }

        private void findPositiveMethod(Method method) {
            Positive annotations = method.getAnnotation(Positive.class);
            if (annotations != null) {
                if (annotations.call().equals(mFlag)) {
                    methodCall(method);
                }
            }
        }
    }

    static class AlertParams {
        final Context mContext;
        private static final String POSITIVE = "positive";
        private static final String NEGATIVE = "negative";
        private static final String NEUTRAL = "neutral";
        private static final String POSITIVE_NAME = "取消";
        private static final String NEUTRAL_NAME = "确认";
        CharSequence mTitle;
        CharSequence mMessage;
        CharSequence mButtonPositive;
        CharSequence mButtonNegative;
        CharSequence mButtonNeutral;
        CharSequence mFlag;
        CharSequence[] mItems;
        @LayoutRes
        int mLayoutResID;
        DialogInterface.OnClickListener mOnClickListener;

        AlertParams(Context mContext) {
            this.mContext = mContext;
        }

        public void apply(AlertController dialog) {

            if (mFlag != null) {
                dialog.setFlag(mFlag);
            }

            if (mTitle != null) {
                dialog.setTitle(mTitle);
            }
            if (mMessage != null) {
                dialog.setMessage(mMessage);
            }

            if (mLayoutResID != 0&&mLayoutResID!=-1) {
                dialog.setLayoutResID(mLayoutResID);
            }
            if (mItems != null && mOnClickListener != null) {
                dialog.setItems(mItems, mOnClickListener);
            }
            if (mButtonPositive != null &&
                    !mButtonPositive.equals(POSITIVE)) {
                dialog.setButtonPositive(mButtonPositive);
            }
            if (mButtonNegative != null && !
                    mButtonNegative.equals(NEGATIVE)) {
                dialog.setButtonNegative(mButtonNegative);
            }
            if (mButtonNeutral != null &&
                    !mButtonNeutral.equals(NEUTRAL)) {
                dialog.setButtonNeutral(mButtonNeutral);
            }
            if (mButtonPositive == null &&
                    mButtonNegative == null
                    && mButtonNeutral == null
                    && mLayoutResID == 0) {
                dialog.setButtonPositive(NEUTRAL_NAME);
                dialog.setButtonNegative(POSITIVE_NAME);
            }
        }
    }
}
