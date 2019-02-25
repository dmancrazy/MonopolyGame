import java.util.*;

public class Player{

    // initializes player name, balance, position/square, number of jail visits, and token name
    private String name;
    private int balance = 1500;
    private int position = 0;
    private ArrayList<Square> propertiesOwned = new ArrayList<Square>();
    private boolean jail = false;
    private int jailCount = 0;
    private String occupantValue;

    /**
     * This method constructs a new player with a specific name and token that shows on the board
     * @param aName This is the name set to a player
     * @param ov This is the token name that shows up on the board
     */
    public Player(String aName, String ov){
    this.name = aName;
    this.occupantValue = ov;
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
    public void setBalance(int newB) {
        if (newB >= 0) {
            balance = newB;
        }
        else {
            balance = 0;
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
    public void addJailCount(int i) {
        jailCount += i;
        if (jailCount == 3) {
            jail = false;
        }
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
     * This method ensures the player goes in cycle around the board (30 -> 1)
     * @param newSpot The new current position of a player
     */
    public void changePosition(int newSpot) {
        position = newSpot;
            if (position > 39) {
                position -= 40;
            }
    }

    /**
     * This method allows you to get the token of a player on the board
     * @return occupantValue A token of a player
     */
    public String getOccupantValue() {
        return occupantValue;
    }
}
