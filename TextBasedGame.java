
/*
 * This class runs the text based version of the Monopoly game
 */
public class TextBasedGame {

	/*
 	* This is the main method that creates a new instance of the monoply game and allows the user to start playing
 	*/
	public static void main(String[] args) {
    	MonopolyGame monopolyGame = new MonopolyGame();
    	monopolyGame.setup();
    	monopolyGame.play();
	}
}
