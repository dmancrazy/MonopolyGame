package GamePieces;

import java.util.ArrayList;
/**
 * The Square class generates a square with unique name, type, color, cost, rent, and owner.
 * Through this class, information about each square can be obtained and a square can be set to a player/new owner.
 * @author Group 6
 * @version 1.0
 * @since 2019-02-25
 */
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
	private int housePrice;
	private int mortgageValue;
	private int houseAmount;
	private int id;

	/**
	 * This constructs a new square with some name, type, color, cost, and rent
	 * @param n The name of a square/property
	 * @param t The type of a square/property
	 * @param c The color of a square/property
	 * @param aCost The cost of a square/property
	 * @param r The rent of a square/property
	 * @param hp price to buy a house at this square
	 * @param mv The mortgae value of the house
	 * @param anID integer from 0 to 39 representing the squares identity
	 */
	public Square(String n, String t, String c, int aCost , int r, int hp, int mv,int anID) {
		name = n;
		type = t;
		color = c;
		cost = aCost;
		rent = r;
		housePrice= hp;
		mortgageValue=mv;
		id=anID;
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
		// setting rent to be on a constant mutliplier, it is not 100% accurate to real game prices
		if( houseAmount==0){
			return rent;
		}
		else if(houseAmount==1){
			return rent*5;
		}
		else if(houseAmount==2){
			return rent*10;
		}
		else if(houseAmount==3){
			return rent*20;

		}
		else if(houseAmount==4){
			return rent*30;
		}
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
	 * This method allows you to get player that owns the square
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
	/**
	 * This getter method returns price of building a house at this square
	 * @return housePrice, which is the price of the house 
	 */
	public int getHousePrice(){
		return housePrice;
	}
	/**
	 * This setter method sets the amount of houses on a property
	 * @param aHouseAmount, an integer amount of houses 
	 */
	public void setHouseAmount(int aHouseAmount){
		houseAmount=aHouseAmount;
	}
	/**
	 * This getter method returns the ID of this square
	 * @return id, integer value from 0 to 39 representing the square
	 */
	public int getId(){
		return id;
	}
	
}
