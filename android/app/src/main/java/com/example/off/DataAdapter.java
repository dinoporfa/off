package com.example.off;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder>{
    private ArrayList<Ghost> ghostlist;

    public DataAdapter(ArrayList<Ghost> ghostlist) {
        this.ghostlist = ghostlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_dashboard, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return ghostlist.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Ghost currentGhost = ghostlist.get(position);
        holder.img.setImage(currentGhost.getImg());
        holder.name.setText(currentGhost.getName());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.txtName);
        }
    }
}
