package fr.campus.player;

import fr.campus.player.Player;


public class ArtificialPlayer extends Player {

    public ArtificialPlayer(String representation) {

        super(representation);
    }

    public int[] getMove () {

        return new int[]{0,0};

    }

}
