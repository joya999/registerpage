package com.example.registerpage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registerpage.R;
import com.example.registerpage.models.FilesGroup;
import com.example.registerpage.models.Subject;

import java.util.ArrayList;

public class FilesGroupAdapter extends RecyclerView.Adapter<FilesGroupAdapter.RCViewHolder> {
    ArrayList<FilesGroup> groups;

    public FilesGroupAdapter(ArrayList<FilesGroup> modelArrayList) {
        this.groups = modelArrayList;
    }

    @NonNull
    @Override
    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_file, parent, false);
        return new RCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCViewHolder holder, int position) {
        FilesGroup subject = groups.get(position);

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public void addItem(FilesGroup subject){
        groups.add(subject);
        notifyItemInserted(groups.size() - 1);
    }
    public class RCViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;


        public RCViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_name);

        }
    }
}

