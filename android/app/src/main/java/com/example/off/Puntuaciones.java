package com.example.off;

public class Puntuaciones {
    private int pts;
    private String date;

    public Puntuaciones(int pts, String date){
        this.pts = pts;
        this.date = date;
    }

    public int getPts(){
        return this.pts;
    }
    public String getDate(){
        return this.date;
    }
}
