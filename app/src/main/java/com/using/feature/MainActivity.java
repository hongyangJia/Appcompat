package com.using.feature;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.feature.annotation.Layout;
import android.support.feature.annotation.Message;
import android.support.feature.annotation.Positive;
import android.support.feature.app.FeatureDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG= MainActivity.class.getSimpleName();
    private static final String FEATURE_TAG="featureDialog";
    private static final String LISTVIEW_TAG="listDialog";

    @Layout
    private FeatureDialog featureDialog;

    @Message(title = R.string.app_name,message = R.string.app_name)
    private FeatureDialog listDialog;

    @Layout
    private FeatureDialog itemsDialog;

    @Layout(id = R.layout.item)
    private FeatureDialog customizeDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.usingFeatureDialog();
        //this.usingListDialog();;
    }

    private void usingFeatureDialog(){
        featureDialog.show();
    }

    private void usingListDialog(){
        listDialog.setAdapter(new Adapter(), this);
        listDialog.show();
    }

    private void usingItemsDialog(){
        List<CharSequence> list = new ArrayList<>();
        itemsDialog.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    private void usingCustomizeView(){
        //ListView listView= (ListView) customizeDialog.getView().findViewById(R.id.listview);
        customizeDialog.show();
    }

    @Positive(call =FEATURE_TAG)
    private void call() {
        Log.e(TAG, "call");
    }

    @Positive(call =LISTVIEW_TAG)
    private void call2() {
        Log.e(TAG, "call");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, parent.getAdapter().getItem(position).toString());
    }

}

