package Actions;

import java.util.Random;

/**
 * This class generates a random value from 1 to 6
 */
public class Dice {
    private Random rand = new Random();

    /**
     * This method return a random value from 1 to 6
     * @return int A random integer from 1 to 6
     */
    public int roll() {
    	return rand.nextInt(6)+1;
    }
}
