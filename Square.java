import java.util.ArrayList;

public class Square {

	// initializes a name, type, color, cost, rent, and a list occupants of each square on the board
	private String type;
	private String name;
	private String color;
	private Integer cost;
	private Integer rent;
	private ArrayList<String> occupants = new ArrayList<String>();
	private boolean owned = false;
	private Player owner;
	//private int houseMultiplier = 1;

	/**
	 * This constructs a new square with some name, type, color, cost, and rent
	 * @param n The name of a square/property
	 * @param t The type of a square/property
	 * @param c The color of a square/property
	 * @param aCost The cost of a square/property
	 * @param r The rent of a square/property
	 */
	public Square(String n, String t, String c, int aCost , int r) {
		name = n;
		type = t;
		color = c;
		cost = aCost;
		rent = r;
	}

	/**
	* This method allows you to get the type of a specific square/property
	* @return type The type of the square/property
	*/
	public String getType() {
		return type;
	}

	/**
	 * This method allows you to get the name of a specific square/property
	 * @return name The name of the square/property
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method allows you to get the color of a specific square/property
	 * @return color The color of the square/property
	 */
	public String getColor() {
		return color;
	}

	/**
	 * This method allows you to get the cost of a specific square/property
	 * @return cost The cost of the square/property
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * This method allows you to get the rent of a specific square/property
	 * @return rent The rent of the square/property
	 */
	public int getRent() {
		return rent;
	}

	/**
	 * This method allows you to get the list of occupants of a specific square/property
	 * @return occupants The list of occupants of the square/property
	 */
	public ArrayList<String> getOccupants() {
		return occupants;
	}

	/**
	 * This method allows you to get a string name from a list of occupants on a square
	 * @return color The color of the square/property
	 */
	public String listedOccupants() {
		String temp = "";
		for (String s: occupants) {
			temp = temp + " " + s;
		}
		return temp;
	}

	/**
	 * This method makes sure each square is equal lengths/width
	 * @return temp Lenght of square in string
	 */
	public String toString() {
		String temp = name;
		while (temp.length() < 13) {
			temp = " " + temp + " ";
			if (temp.length() == 13) {
				temp = temp +" ";
			}
		}
		return temp;
	}

	/**
	 * This method checks if a square/property is owned by some player
	 * @return owned True if owned or False if not
	 */
	public boolean getOwned() {
		return owned;
	}

	/**
	 * This method sets a property to a owned status
	 * @param b True if owned or False if not
	 */
	public void setOwned(boolean b) {
		owned = b;
	}

	/**
	 * This method allows you to get the name of the owner of the property/square
	 * @return owner The player that owns the square
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * This method sets a player as the owner of square/property
	 * @param h The player buying the square 
	 */
	public void setOwner (Player h) {
		owner = h;
	}
}
