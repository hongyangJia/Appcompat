package com.using.feature;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.feature.app.AppcompatFeature;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hongyang on 17-7-4.
 */

public class BaseCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppcompatFeature.init(this);
    }
}