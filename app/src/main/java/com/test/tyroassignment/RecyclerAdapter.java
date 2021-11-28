package com.test.tyroassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.tyroassignment.API.Data;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    Context context;
    List<Data> dataList;

    public RecyclerAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void filterData(List<Data> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        holder.name.setText(dataList.get(position).getName());
        holder.instructor.setText("With " + dataList.get(position).getTrainerName());
        holder.difficulty.setText(dataList.get(position).getDifficultyLevelName());
        String duration = dataList.get(position).getDuration().toString();
        duration = duration.substring(0, duration.length() - 2) + " Min";
        holder.duration.setText(duration);

        if (position % 3 == 2 || position % 3 == 0) {
            Picasso.get().load(R.drawable.image_1).into(holder.imageView);
        } else {
            Picasso.get().load(R.drawable.image_2).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView name, duration, difficulty, instructor;
        ImageView imageView;
        ImageButton checkButton;
        LinearLayout layout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.recyclerView_name);
            duration = itemView.findViewById(R.id.recyclerView_duration);
            difficulty = itemView.findViewById(R.id.recyclerView_difficulty);
            instructor = itemView.findViewById(R.id.recyclerView_instructor);

            imageView = itemView.findViewById(R.id.recyclerView_image);

            checkButton = itemView.findViewById(R.id.recyclerView_check);

            layout = itemView.findViewById(R.id.recyclerView_layout);
        }
    }
}
