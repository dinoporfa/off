package com.example.off;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PuntuacionesAdapter extends RecyclerView.Adapter<PuntuacionesAdapter.MyViewHolder> {
    private List<Puntuaciones> ptsList;
    public PuntuacionesAdapter(List<Puntuaciones> ptsList) {
        this.ptsList = ptsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.puntuaciones_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Puntuaciones puntuaciones = ptsList.get(position);

        holder.pts.setText(puntuaciones.getPts());
        holder.date.setText(puntuaciones.getDate());
    }

    @Override
    public int getItemCount() {
        return ptsList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pts, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pts = itemView.findViewById(R.id.ppts);
            date = itemView.findViewById(R.id.pdate);
        }
    }
}