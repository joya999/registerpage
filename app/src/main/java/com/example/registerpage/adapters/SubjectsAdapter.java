package com.example.registerpage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registerpage.OnItemClickListener;
import com.example.registerpage.R;
import com.example.registerpage.models.Level;
import com.example.registerpage.models.Subject;

import java.util.ArrayList;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.RCViewHolder> {
    ArrayList<Subject> subjects;

    private OnItemClickListener<Subject> listener;
    public void setOnItemClickListener(OnItemClickListener<Subject> listener){
        this.listener = listener;
    }
    public SubjectsAdapter(ArrayList<Subject> modelArrayList) {
        this.subjects = modelArrayList;
    }

    @NonNull
    @Override
    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_subject, parent, false);
        return new RCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.tvTitle.setText(subject.name);
        holder.tvCode.setText(subject.code);

        holder.itemView.setOnClickListener(view -> {
          listener.onItemClick(subject);
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void addItem(Subject subject){
        subjects.add(subject);
        notifyItemInserted(subjects.size() - 1);
    }
    public class RCViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvCode;

        public RCViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_name);
            tvCode = itemView.findViewById(R.id.tv_code);

        }
    }
}

