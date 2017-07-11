package android.support.feature.app;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by hongyang on 17-7-4.
 */

public class FeatureDialog {

    final AlertController mAlert;

    public FeatureDialog(Context getContext) {
        mAlert = new AlertController(getContext);
    }

    public void setTitle(String title) {
        mAlert.setTitle(title);
    }

    public void setMessage(String message) {
        mAlert.setMessage(message);
    }

    public void setButtonPositive(String buttonPositive) {
        mAlert.setButtonPositive(buttonPositive);
    }

    public void setButtonNegative(String buttonNegative) {
        mAlert.setButtonNegative(buttonNegative);
    }

    public void setButtonNeutral(String buttonNeutral) {
        mAlert.setButtonNeutral(buttonNeutral);
    }

    public void setItems(CharSequence[] mItems, final DialogInterface.OnClickListener listener) {
        mAlert.setItems(mItems, listener);
    }

    public void setItems(List<CharSequence> mItems, final DialogInterface.OnClickListener listener) {
        mAlert.setItems(mItems, listener);
    }

    public void setAdapter(final BaseAdapter adapter, final AdapterView.OnItemClickListener onItemClickListener) {
        mAlert.setAdapter(adapter,onItemClickListener);
    }

    public void show() {
        mAlert.show();
    }

    public boolean isShowing() {
        return mAlert.isShowing();
    }

    public View getView() {
        return mAlert.getLayoutView();
    }

    public ListView getListView(){
        return mAlert.getListView();
    }

    public static class Builder {

        private AlertController.AlertParams P;
        private Context getContext;

        public Builder(Context getContext) {
            this.builder(getContext, null);
        }

        public Builder(Context getContext, AlertController.AlertParams P) {
            this.builder(getContext, P);
        }

        private void builder(Context getContext, AlertController.AlertParams P) {
            this.getContext = getContext;
            this.P = P;
            if (this.P == null) {
                this.P = new AlertController.AlertParams(getContext);
            }
        }

        public Builder setId(CharSequence mFlag) {
            P.mFlag = mFlag;
            return this;
        }

        public Builder setTitle(int title) {
            P.mTitle = P.mContext.getText(title);
            return this;
        }

        public Builder setMessage(int message) {
            P.mMessage = P.mContext.getText(message);
            return this;
        }

        public Builder setButtonPositive(int buttonPositive) {
            P.mButtonPositive = P.mContext.getText(buttonPositive);
            return this;
        }

        public Builder setButtonNegative(int buttonNegative) {
            P.mButtonNegative = P.mContext.getText(buttonNegative);
            return this;
        }

        public Builder setButtonNeutral(int buttonNeutral) {
            P.mButtonNeutral = P.mContext.getText(buttonNeutral);
            return this;
        }

        public Builder setItems(CharSequence[] items, final DialogInterface.OnClickListener listener) {
            P.mItems = items;
            P.mOnClickListener = listener;
            return this;
        }

        public FeatureDialog create() {
            FeatureDialog featureDialog = new FeatureDialog(getContext);
            P.apply(featureDialog.mAlert);
            return featureDialog;
        }

        public FeatureDialog show() {
            FeatureDialog featureDialog = create();
            featureDialog.show();
            return featureDialog;
        }
    }
}
