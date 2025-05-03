package com.example.off;

import java.util.ArrayList;

public class Ghosts {
    public static ArrayList<Ghost> getGhostData() {
        ArrayList<Ghost> ghostList = new ArrayList<Ghost>();

        Ghost ghost1 = new Ghost("Espectro común", 10, 40, 1, 115, 35);
        ghostList.add(ghost1);
        Ghost ghost2 = new Ghost("Xaneiro", 90, 75, 70, 40, 35);
        ghostList.add(ghost2);
        Ghost ghost3 = new Ghost("Espectro de un ollo", 65, 70, 70, 70, 35);
        ghostList.add(ghost3);

        return ghostList;
    }
}
