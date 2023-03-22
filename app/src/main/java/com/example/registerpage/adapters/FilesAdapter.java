package com.example.registerpage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registerpage.R;
import com.example.registerpage.models.File;
import com.example.registerpage.models.FilesGroup;

import java.util.ArrayList;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.RCViewHolder> {
    ArrayList<File> files;

    public FilesAdapter(ArrayList<File> modelArrayList) {
        this.files = modelArrayList;
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
        File file = files.get(position);

    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public void addItem(File file){
        files.add(file);
        notifyItemInserted(files.size() - 1);
    }
    public class RCViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;


        public RCViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_name);

        }
    }
}

