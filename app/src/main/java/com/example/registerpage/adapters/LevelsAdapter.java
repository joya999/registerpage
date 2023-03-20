package com.example.registerpage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registerpage.R;
import com.example.registerpage.models.Level;

import java.util.ArrayList;

public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.RCViewHolder> {
    ArrayList<Level> levels;

    public LevelsAdapter(ArrayList<Level> modelArrayList) {
        this.levels = modelArrayList;
    }

    @NonNull
    @Override
    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_level, parent, false);
        return new RCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCViewHolder holder, int position) {
        Level level = levels.get(position);
        holder.rc_title.setText(level.getName());
        //holder.rc_image.setImageResource(rcModel.image);
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public void notifyDataSetChange() {
    }

    public void addItem(Level level){
        levels.add(level);
        notifyItemInserted(levels.size() - 1);
    }
    public class RCViewHolder extends RecyclerView.ViewHolder {
        ImageView rc_image;
        TextView rc_title;

        public RCViewHolder(@NonNull View itemView) {
            super(itemView);

            rc_title = itemView.findViewById(R.id.rc_title);


        }
    }
}

