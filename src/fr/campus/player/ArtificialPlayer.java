package fr.campus.player;

import java.util.Random;
import fr.campus.game.Game;

public class ArtificialPlayer extends Player {


    private Random random;

    public ArtificialPlayer(String representation) {

        super(representation, null, null);
        this.random = new Random();
    }

    public int[] getMove () {

        int i = random.nextInt(Game.SIZE);
        int j = random.nextInt(Game.SIZE);
        return new int[]{i, j};

    }


}
