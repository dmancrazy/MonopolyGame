import java.util.ArrayList;

	public class MonopolyConfiguration {
		private static ArrayList<Square> board;

	public MonopolyConfiguration() {
		board = BoardMaker.DefaultBoard();
		}

	public ArrayList<Square> getBoard() {
		return board;
	}

	public static void printBoard() {

		//Printing out the first row//
		for (int i = 0; i < 178; i++) {
			System.out.print("$");
		}

		System.out.println("");
		System.out.print("$");
		for (int i = 0; i < 11; i++) {
			System.out.print("|" + board.get(i).toString() + "|");
		}
		System.out.print("$");
		System.out.println("");
		for (int l = 0; l < 2; l++) {
			System.out.print("$");
			for (int j = 0; j < 11; j++) {
				System.out.print("|              |");
			}
			System.out.print("$");
			System.out.println("");
		}
		for (int i = 0; i < 178; i++) {
			System.out.print("$");
		}


		//Printing out the middle 2 collumns//
		for (int i = 1; i < 10; i++) {
			System.out.println("");
			System.out.print("$|" + board.get(40-i).toString() + "||");
			for (int k = 0; k < 142; k++) {
				System.out.print(" ");
			}
			System.out.print("||" + board.get(10+i).toString() + "|");
			System.out.println("$");
			for (int h = 0; h < 2; h++) {
				System.out.print("$|              ||");
				for (int y = 0; y < 142; y++) {
					System.out.print(" ");
				}
				System.out.println("||              |$");
			}
			if (i == 9) {
				for (int q = 0; q < 178; q++) {
					System.out.print("$");
				}
			}
			else {
				System.out.print("$$$$$$$$$$$$$$$$$$");
				for (int j = 0; j < 142; j++) {
					System.out.print(" ");
				}
			System.out.print("$$$$$$$$$$$$$$$$$$");
			}
			
		}
		System.out.println("");



		//Printing out the bottom Row//
		System.out.print("$");
		for (int f = 30; f>19; f--){
			System.out.print("|" + board.get(f).toString() + "|");
		}
		System.out.println("$");
		for (int l = 0; l < 2; l++) {
			System.out.print("$");
			for (int j = 0; j < 11; j++) {
				System.out.print("|              |");
			}
			System.out.println("$");
		}
		for (int q = 0; q < 178; q++) {
				System.out.print("$");
		}
		System.out.println("\n");


	}	
}


		



