package fr.campus.player;

import fr.campus.board.Cell;
import java.util.Random;
import fr.campus.main.Game;

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
