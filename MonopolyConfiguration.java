import java.util.ArrayList;

/**
 * This class sets up the exact dimensions of the entire board and placement for each unique square/property
 * @author Group 6
 * @version 1.0
 * @since 2019-02-25
 */
	public class MonopolyConfiguration {
		// initializes the board
		private ArrayList<Square> board;

	/**
	 * This constructs a default board
	 */
	public MonopolyConfiguration() {
		board = BoardMaker.DefaultBoard();
		}

	/**
	 * This method returns the board
	 * @return board The board
	 */
	public ArrayList<Square> getBoard() {
		return board;
	}
	/**
	 * This manually draws the board in a rectangular form onto the console
	 */

	 
}
