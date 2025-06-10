package com.example.off;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GhostAdapter extends RecyclerView.Adapter<GhostAdapter.MyViewHolder> {
    private Context context;
    private List<Ghost> ghostList;

    public GhostAdapter(List<Ghost> ghostList, Context context) {
        this.context = context;
        this.ghostList = ghostList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ghost_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ghost ghost = ghostList.get(position);

        holder.name.setText(ghost.getName());
        Picasso.get().load(ghost.getImg()).into(holder.img);

        holder.detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("name", ghostList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("img", ghostList.get(holder.getAdapterPosition()).getImg());
                intent.putExtra("pts", ghostList.get(holder.getAdapterPosition()).getPts());
                intent.putExtra("hp", ghostList.get(holder.getAdapterPosition()).getHp());
                intent.putExtra("atk", ghostList.get(holder.getAdapterPosition()).getAtk());
                intent.putExtra("def", ghostList.get(holder.getAdapterPosition()).getDef());
                intent.putExtra("esp", ghostList.get(holder.getAdapterPosition()).getEsp());
                intent.putExtra("agl", ghostList.get(holder.getAdapterPosition()).getAgl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ghostList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;
        Button detail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}