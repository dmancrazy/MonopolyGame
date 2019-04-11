import java.util.*;

/**
 * This class outlines the specifications of each players playing the game.
 * These specifications affect the interactions a player may have with each squares
 * @author Group 6
 * @version 1.0
 * @since 2019-02-25
 */
public abstract class Player{

    // initializes player name, balance, position/square, number of jail visits, and token name
    private String name;
    private int balance = 1500;
    private int position = 0;
    private ArrayList<Square> propertiesOwned = new ArrayList<Square>();
    private boolean jail = false;
    private int jailCount = 0;
    private String icon;
    private boolean passedGo = false;
	private ArrayList<String> playerColorList = new ArrayList<String>();

    /**
     * This method constructs a new player with a specific name and token that shows on the board
     * @param aName This is the name set to a player
     * @param ov This is the token name that shows up on the board
     */
    public Player(String aName, String ov){
    this.name = aName;
    this.icon = ov;
    }

    /**
     * This method allows you to get the name of a player
     * @return name The name of a player
     */
    public String getName() {
        return name;
    }

    /**
     * This method allows you to get the balance of a player
     * @return balance The balance of a player
     */
    public int getBalance() {
        return balance;
    }

    /**
     * This method takes a certain number and sets it as a player's balance
     * If no value is provided, the balance is set to 0
     * @param newB The value being set to a player's balance
     */
    public void changeBalance(int amount) {
        if (amount > 0) {
            balance += amount;
        }
        else if (amount < 0) {
            if ( balance < (-1*amount) ) {
                balance = 0;
            }
            else {
                balance += amount;
            }
        }
    }
    
    /**
     * This method checks if a player is in jail or not
     * @return jail True or False
     */
    public boolean getJail() {
        return jail;
    }

    /**
     * This method puts a player in and out of jail
     * @param b True or False
     */
    public void setJail(boolean b) {
        jail = b;
    }

    /**
     * This method allows you to determine how many times a player has been in jail
     */
    public int getJailCount() {
        return jailCount;
    }

    /**
     * This method adds 1 to a player's number of jail visits
     * If a player has been in jail for 3 turns, they are forced out
     */
	 // this should be handled in the main program
    public void addJailCount(int i) {
        jailCount += i;
    }


    /**
     * This method allows you to get the current position of a player
     * @return position Current position of a player
     */
    public int getPosition() {
        return position;
    }

    /**
     * This method allows you to get a list of properties owned by a player
     * @return propertiesOwned The properties owned by a player
     */
    public ArrayList<Square> getPropertiesOwned() {
        return propertiesOwned;
    }

    /**
     * This method adds a property to a players list of owned properties
     * @param s The square the property is on
     */
    public void addPropertiesOwned(Square s) {
        propertiesOwned.add(s);
    }

    /**
     * This method ensures the player goes in cycle around the board (30 -> 1)
     * @param newSpot The new current position of a player
     */
    public void changePosition(int newSpot) {
        position = newSpot;
            if (position > 39) {
                position -= 40;
                passedGo = true;
            }
    }

    /**
     * This method checks if a player has passed through the first "Go" square
     * @return passedGo True if the player has or False if not
     */
    public boolean getPassedGo() {
        return passedGo;
    }

    /**
     * This method determines if a player has just passed through the first "Go" square
     * @param b True if the player has or False if not
     */
    public void setPassedGo(boolean b) {
        passedGo = b;
    }
	
    /**
     * This method allows you to get the token of a player on the board
     * @return icon A token of a player
     */
    public String getIcon() {
        return icon;
    }
	public void addColorToList(String aColor){
		playerColorList.add(aColor);
	}
	public ArrayList<String>getPlayerColorList(){
		return playerColorList;
	}
}