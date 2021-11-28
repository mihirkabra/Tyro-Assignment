package com.test.tyroassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import hiennguyen.me.circleseekbar.CircleSeekBar;

public class SliderAdapter extends ArrayAdapter<String> {
    private final String[] items;
    private final Context context;
    float DURATION;

    public SliderAdapter(@NonNull Context context, int resource, String[] arr, float duration) {
        super(context, resource, arr);
        this.context = context;
        items = arr;
        DURATION = duration;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(context);
            convertView = layoutInflator.inflate(R.layout.duration_layout, null);

            holder = new ViewHolder();
            holder.circleSeekBar = convertView.findViewById(R.id.slider);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.circleSeekBar.setTag(position);
        holder.circleSeekBar.setProgressDisplay((int) DURATION);
        holder.circleSeekBar.setSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangedListener() {
            @Override
            public void onPointsChanged(CircleSeekBar circleSeekBar, int points, boolean fromUser) {
                DURATION = holder.circleSeekBar.getCurrentProgress();
            }

            @Override
            public void onStartTrackingTouch(CircleSeekBar circleSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(CircleSeekBar circleSeekBar) {
            }
        });
        return convertView;
    }

    public float getDURATION() {
        return DURATION;
    }

    private static class ViewHolder {
        private CircleSeekBar circleSeekBar;
    }

}
