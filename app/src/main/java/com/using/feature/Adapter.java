package com.using.feature;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by hongyang on 17-7-11.
 */

public class Adapter extends BaseAdapter {

    private static final int CONUNT = 20;

    @Override
    public int getCount() {
        return CONUNT;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        textView.setPadding(CONUNT, CONUNT, CONUNT, CONUNT);
        textView.setTextSize(CONUNT);
        textView.setText("position" + position);
        return textView;
    }

}
