import java.util.ArrayList;

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
	public void printBoard() {

//////////Printing out the first row////////////////////////////////////////////////////////////////////////
		for (int i = 0; i < 178; i++) {			//Top line of dollarsigns
			System.out.print("$");
		}
		System.out.println("");


		System.out.print("$");					//Titles of the squares
		for (int i = 0; i < 11; i++) {
			System.out.print("|" + board.get(i).toString() + "|");
		}
		System.out.println("$");


		System.out.print("$");					//The player's positions
		String temp;
		for (int i = 0; i < 11; i++) {
			temp = board.get(i).listedOccupants();
			while (temp.length() < 13) {
				temp = " " + temp + " ";
				if (temp.length() == 13) {
					temp = temp +" ";
				}
			}
			System.out.print("|" + temp + "|");
		}
		System.out.println("$");


		System.out.print("$");					//White space for cleanness
		for (int j = 0; j < 11; j++) {
			System.out.print("|              |");
		}
		System.out.println("$");

		for (int i = 0; i < 178; i++) {
			System.out.print("$");
		}
		System.out.println("");
//////////////////////////Printing The First Row/////////////////////////////////////////////////////////////////////////////


/////////////////Printing the Middle Columns//////////////////////////////////////////////////////////////////////////////////
		for (int i = 1; i < 10; i++) {												//titles of the left squares for 2 collumns
			System.out.print("$|" + board.get(40-i).toString() + "||");


			for (int k = 0; k < 142; k++) {											//white space between left and right square
				System.out.print(" ");
			}


			System.out.print("||" + board.get(10+i).toString() + "|");				//titles of right squares for 2 collumns
			System.out.println("$");



			temp = board.get(40-i).listedOccupants();								//occupants of the left squares
			while (temp.length() < 13) {
				temp = " " + temp + " ";
				if (temp.length() == 13) {
					temp = temp +" ";
				}
			}
			System.out.print("$|" + temp + "||");


			for (int k = 0; k < 142; k++) {											//white space in between 2 collumns
				System.out.print(" ");
			}


			temp = board.get(10+i).listedOccupants();								//occupants of right squares
			while (temp.length() < 13) {
				temp = " " + temp + " ";
				if (temp.length() == 13) {
					temp = temp +" ";
				}
			}
			System.out.println("||" + temp + "|$");


			System.out.print("$|              ||");									//White space in the 2 collumns squares for cleanliness
			for (int k = 0; k < 142; k++) {
				System.out.print(" ");
			}
			System.out.println("||              |$");								//second last row of dollar signs
			if (i == 9) {
				for (int q = 0; q < 178; q++) {
					System.out.print("$");
				}
				System.out.println("");
			}


			else {																	//dollar sign border around the bottom of collumn squares
				System.out.print("$$$$$$$$$$$$$$$$$$");
					for (int j = 0; j < 142; j++) {
						System.out.print(" ");
					}
				System.out.println("$$$$$$$$$$$$$$$$$$");
			}
		}
////////////////////////////Printing Middle Collumns////////////////////////////////////////////////////////////////////



////////////////////////Printing out the bottom Row//////////////////////////////////////////////////////////////////////
		System.out.print("$");														//printing the bottom row backwards like it should be
		for (int f = 30; f>19; f--) {
			System.out.print("|" + board.get(f).toString() + "|");
		}
		System.out.println("$");


		System.out.print("$");														//Printing out theoccupants of the bottom row
		for (int f = 30; f>19; f--) {
			temp = board.get(f).listedOccupants();
			while (temp.length() < 13) {
				temp = " " + temp + " ";
				if (temp.length() == 13) {
					temp = temp +" ";
				}
			}
			System.out.print("|" + temp + "|");
		}
		System.out.println("$");


		System.out.print("$");														//White space onbottom row for cleanness
		for (int j = 0; j < 11; j++) {
			System.out.print("|              |");
		}
		System.out.println("$");


		for (int q = 0; q < 178; q++) {												//Last line of dollar signs for border
				System.out.print("$");
		}
		System.out.println("\n");
	}
/////////////////////////Printing Bottom Row////////////////////////////////////////////////////////////


}
