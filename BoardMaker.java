import java.util.ArrayList;


public class BoardMaker {
	
	public static ArrayList<Square> DefaultBoard() {
		ArrayList<Square> board = new ArrayList<Square>();
		board.add(new Square("      GO", "Go", "" , 0 , 0));
		board.add(new Square("    Med Ave" , "property" , "brown" , 60 , 30));
		board.add(new Square(" Comm Chest 1" , "chest" , "", 0 , 0)); 				//figure out cost
		board.add(new Square("  Baltic Ave" , "property", "brown" , 60 , 30));
		board.add(new Square("  Income Tax" , "Tax" , "" , 200 , 0));
		board.add(new Square(" Reading Rail" , "rail" , "" , 200 , 50));
		board.add(new Square(" Oriental Ave" , "property", "blue" , 100 , 50));
		board.add(new Square("    Chance" , "?" , "" , 0 , 0));								//figure out cost
		board.add(new Square("    Vermont" , "property", "blue" , 100 , 50));
		board.add(new Square("   Conn Ave" , "property" , "blue" , 120 , 60));
		board.add(new Square("     Jail" , "jail" , "" , 0 , 0)); 									//Jail or Just visiting
		board.add(new Square(" St. C Place" , "property" , "purple" , 140 , 70));
		board.add(new Square("Electric Co" , "property" , "" , 150 , 75));
		board.add(new Square("States Ave" , "property" , "purple" , 150 , 75));
		board.add(new Square("Virginia Ave" , "property" , "purple" , 160 , 80));
		board.add(new Square("Penn Rail" , "rail" , "" , 200 , 50));
		board.add(new Square("St. J Place" , "property" , "orange" , 160 , 80));
		board.add(new Square("Comm Chest 2" , "chest" , "" , 0 , 0));				//figure out cost
		board.add(new Square("Tennessee Ave" , "property" , "orange" , 180 , 90));
		board.add(new Square("New York Ave" , "property" , "orange" , 200 , 100));
		board.add(new Square("Free Parking" , "parking" , "" , 0 , 0));
		board.add(new Square("Kentucky Ave" , "property" , "red" , 220 , 110));
		board.add(new Square("Chance" , "?" , "" , 0 , 0));								//figure out cost
		board.add(new Square("Indiana Ave" , "property" , "red" , 220 , 110));
		board.add(new Square("Illinois Ave" , "property" , "red" , 240 , 120));
		board.add(new Square("B & O Rail" , "rail" , "" , 200 , 50));
		board.add(new Square("Atlantic Ave" , "property" , "yellow" , 260 , 130));
		board.add(new Square("Ventnor Ave" , "property" , "yellow" , 260 , 130));
		board.add(new Square("Water Works" , "property" , "" , 150 , 75));
		board.add(new Square("Marvin Gard" , "property" , "yellow" , 280 , 140));
		board.add(new Square("Go To Jail" , "Specialty" , "" , 0 , 0)); 						//Go to jail & figure out cost?
		board.add(new Square("Pacific Ave" , "property" , "green" , 300 , 150));
		board.add(new Square("NC Ave" , "property" , "green" , 300 , 150));
		board.add(new Square("Comm Chest 3" , "chest" , "" , 0 , 0));				//figure out cost
		board.add(new Square("Penn Ave" , "property" , "green" , 320 , 160));
		board.add(new Square("Short Line" , "rail" , "" , 200 , 50));
		board.add(new Square("Chance" , "?" , "" , 0 , 0));								//figure out cost
		board.add(new Square("Park Place" , "property" , "blue" , 350 , 175));
		board.add(new Square("Luxury Tax" , "Tax" , "" , 0 , 100));
		board.add(new Square("Boardwalk" , "property" , "blue" , 400 , 200));



		return board;


	}
}