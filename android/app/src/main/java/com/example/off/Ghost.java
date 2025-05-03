package com.example.off;
public class Ghost {
    private String name;
    private String img;
    private int hp;
    private int atk;
    private int def;
    private int esp;
    private int agl;

    public Ghost(String name, String img, int hp, int atk, int def, int esp, int agl){
        this.name=name;
        this.img=img;
        this.hp=hp;
        this.atk=atk;
        this.def=def;
        this.esp=esp;
        this.agl=agl;
    }
    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
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
