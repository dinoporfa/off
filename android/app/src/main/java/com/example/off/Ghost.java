package com.example.off;

import android.widget.ImageView;

public class Ghost {
    private static String name;
    private static ImageView img;
    private int pts;
    private int hp;
    private int atk;
    private int def;
    private int esp;
    private int agl;

    public Ghost(String name, ImageView img, int pts, int hp, int atk, int def, int esp, int agl){
        this.name=name;
        this.img=img;
        this.pts=pts;
        this.hp=hp;
        this.atk=atk;
        this.def=def;
        this.esp=esp;
        this.agl=agl;
    }
    public static String getName() {
        return name;
    }
    public static ImageView getImg() {
        return img;
    }
    public int getPts(){
        return pts;
    }
    public int getHp() {
        return hp;
    }
    public int getAtk() {
        return atk;
    }
    public int getDef() {
        return def;
    }
    public int getEsp() {
        return esp;
    }
    public int getAgl() {
        return agl;
    }
}