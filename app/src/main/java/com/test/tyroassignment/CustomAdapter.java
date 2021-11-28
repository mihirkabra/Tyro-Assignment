package com.test.tyroassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Model> {
    private final Context mContext;
    private final ArrayList<Model> listState;
    private final CustomAdapter customAdapter;
    private final boolean isImage;
    private boolean isFromView = false;

    public CustomAdapter(Context context, int resource, List<Model> objects, boolean isImage) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<Model>) objects;
        this.customAdapter = this;
        this.isImage = isImage;
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
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.list_layout, null);

            holder = new ViewHolder();

            holder.mTextView = (TextView) convertView.findViewById(R.id.list_text);
            holder.imageView = convertView.findViewById(R.id.list_imageView);
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.list_checkBox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (isImage) {
            holder.imageView.setVisibility(View.VISIBLE);
        } else {
            holder.imageView.setVisibility(View.GONE);
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    listState.get(getPosition).setSelected(isChecked);
                }
            }
        });
        return convertView;
    }

    public List<Model> getList() {
        List<Model> checkedList = new ArrayList<>(listState);
        for (Model item : listState) {
            if (!item.isSelected()) {
                checkedList.remove(item);
            }
        }
        return checkedList;
    }

    private static class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
        private ImageView imageView;
    }
}