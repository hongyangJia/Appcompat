package com.using.feature;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.feature.annotation.Layout;
import android.support.feature.annotation.Positive;
import android.support.feature.app.FeatureDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

public class MainActivity extends BaseCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG= MainActivity.class.getSimpleName();
    @Layout
    private FeatureDialog featureDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        featureDialog.setAdapter(new Adapter(), this);
        featureDialog.show();
    }

    @Positive()
    private void call() {
        Log.e(TAG, "call");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, parent.getAdapter().getItem(position).toString());
    }

}

