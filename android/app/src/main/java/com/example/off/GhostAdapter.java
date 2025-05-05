package com.example.off;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GhostAdapter extends RecyclerView.Adapter<GhostAdapter.GhostViewHolder> {

    private List<Ghost> ghostList;

    public GhostAdapter(List<Ghost> examList) {
        this.ghostList = examList;
    }

    @NonNull
    @Override
    public GhostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ghost_card, parent, false);
        return new GhostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GhostViewHolder holder, int position) {
        Ghost ghost = ghostList.get(position);

        holder.name.setText(Ghost.getName());
        holder.image.setImageResource(Ghost.getImg());
    }

    @Override
    public int getItemCount() {
        return ghostList.size();
    }

    static class GhostViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public GhostViewHolder(@NonNull View itemView) {

            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}