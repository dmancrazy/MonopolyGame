import java.util.ArrayList;

/**
 * This class generates a monopoly board with a list of 39 unique squares
 * @author Group 6
 * @version 1.0
 * @since 2019-02-25
 */
public class BoardMaker {

	/**
	* This method initializes a new board with each individual squares.
	* Each squares contain a specific name, type, color, price, and rent
	* @return board This is the list with each individual squares added.
	*/
	public static ArrayList<Square> DefaultBoard() {
		ArrayList<Square> board = new ArrayList<Square>();
		board.add(new Square("GO", "Go", "go" , 0 , 0,0,0,0));
		board.add(new Square("Med Ave" , "property" , "brown" , 60 , 2, 50 , 30,1));
		board.add(new Square("Comm Chest 1" , "chest" , "cc-1", 0 , 0, 0, 0,2)); 				//figure out cost
		board.add(new Square("Baltic Ave" , "property", "brown" , 60 , 2, 50, 30,3));
		board.add(new Square("Income Tax" , "tax" , "tax-1" , 200 ,  0, 0, 0,4));
		board.add(new Square("Reading Rail" , "rail" , "r-1" , 200 , 25, 0, 100,5));
		board.add(new Square("Oriental Ave" , "property", "light-blue" , 100 , 6, 50, 50,6));
		board.add(new Square("Chance" , "?" , "c-1" , 0 , 0, 0, 0,7));								//figure out cost
		board.add(new Square("Vermont" , "property", "light-blue" , 100 , 6, 50, 50,8));
		board.add(new Square("Conn Ave" , "property" , "light-blue" , 120 , 8, 50, 60,9));
		board.add(new Square("Jail" , "jail" , "j-1" , 0 , 0, 0, 0,10)); 									//Jail or Just visiting
		board.add(new Square("St. C Place" , "property" , "purple" , 140 , 10, 100, 70,11));
		board.add(new Square("Electric Co" , "property" , "elec" , 150 , 75, 0, 0,12));
		board.add(new Square("States Ave" , "property" , "purple" , 150 , 10, 100, 70,13));
		board.add(new Square("Virginia Ave" , "property" , "purple" , 160 , 12, 100, 80,14));
		board.add(new Square("Penn Rail" , "rail" , "r-2" , 200 , 25, 0, 0,15));
		board.add(new Square("St. J Place" , "property" , "orange" , 180 , 14, 100, 90,16));
		board.add(new Square("Comm Chest 2" , "chest" , "cc-2" , 0 , 0, 0, 0,17));				//figure out cost
		board.add(new Square("Tennessee Ave " , "property" , "orange" , 180 , 14, 100, 90,18));
		board.add(new Square("New York Ave" , "property" , "orange" , 200 , 16, 100, 100,19));
		board.add(new Square("Free Parking" , "parking" , "park" , 0 , 0, 0, 0,20));
		board.add(new Square("Kentucky Ave" , "property" , "red" , 220 , 18, 150, 110,21));
		board.add(new Square("Chance" , "?" , "c-2" , 0 , 0, 0, 0,22));								//figure out cost
		board.add(new Square("Indiana Ave" , "property" , "red" , 220 , 18, 150, 110,23));
		board.add(new Square("Illinois Ave" , "property" , "red" , 240 , 20, 150, 120,24));
		board.add(new Square("B & O Rail" , "rail" , "r-3" , 200 , 25, 0, 0 , 25));
		board.add(new Square("Atlantic Ave" , "property" , "yellow" , 260 , 22, 150, 130 ,26));
		board.add(new Square("Ventnor Ave" , "property" , "yellow" , 260 , 22, 150, 130,27));
		board.add(new Square("Water Works" , "property" , "water" , 150 , 75, 0, 0 ,28));
		board.add(new Square("Marvin Gard" , "property" , "yellow" , 280 , 24, 150, 140,29));
		board.add(new Square("Go To Jail" , "goToJail" , "jail-send" , 0 , 0, 0, 0,30)); 						//Go to jail & figure out cost?
		board.add(new Square("Pacific Ave" , "property" , "green" , 300 , 26, 200, 150,31));
		board.add(new Square("NC Ave" , "property" , "green" , 300 , 26, 200, 150,32));
		board.add(new Square("Comm Chest 3" , "chest" , "cc-3" , 0 , 0, 0, 0,33));				//figure out cost
		board.add(new Square("Penn Ave" , "property" , "green" , 320 , 28, 200, 160,34));
		board.add(new Square("Short Line" , "rail" , "r-4" , 200 , 50, 0 ,0,35));
		board.add(new Square("Chance" , "?" , "c-3" , 0 , 0, 0, 0,36));								//figure out cost
		board.add(new Square("Park Place" , "property" , "dark-blue" , 350 , 35, 200, 175,37));
		board.add(new Square("Luxury Tax" , "tax" , "tax-2" , 0 , 100, 0, 0,38));
		board.add(new Square("Boardwalk" , "property" , "dark-blue" , 400 , 50, 200,200,39));



		return board;


	}
}
