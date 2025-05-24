package com.example.off;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class GhostAdapter extends RecyclerView.Adapter<GhostAdapter.MyViewHolder> {
    private List<Ghost> ghostList;

    public GhostAdapter(List<Ghost> ghostList) {
        this.ghostList = ghostList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ghost_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ghost ghost = ghostList.get(position);

        holder.name.setText(ghost.getName());
        holder.img.setImageResource(ghost.getImg());
    }

    @Override
    public int getItemCount() {
        return ghostList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);

        }
    }
}