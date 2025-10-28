package fr.campus.player;

import java.util.Random;
import fr.campus.controller.GameController;

public class ArtificialPlayer extends Player {


    private Random random;

    public ArtificialPlayer(String representation) {

        super(representation, null, null);
        this.random = new Random();
    }

    public int[] getMove () {

        int i = random.nextInt(GameController.SIZE);
        int j = random.nextInt(GameController.SIZE);
        return new int[]{i, j};

    }


}
