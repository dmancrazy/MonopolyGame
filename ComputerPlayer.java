import java.util.*;

public class ComputerPlayer{
//This is the AI computer controlled player for the Monopoly game.

//initializes player name, balance and owned properties
private String name;
private int balance = 1500;
private ArrayList<Square> propertiesOwned = new ArrayList<Square>();
    public ComputerPlayer(String aName){
    this.name = aName;
    }


    /**
     * This method takes some name and sets it as a certain player's name
     * @param name The name of the player
     */
    public void setName(String aName) {
        name = aName;
    }


    /**
     * This method returns a specific player's given name
     * @return name The player's name
     */
    public String getName() {
        return name;
    }


    /**
     * This method returns a certain player's balance
     * @return balance The player's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * This method takes a certain integer and sets it as a specific player's balance
     * @param balance The number set to a player's balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * This method returns the list of properties owned by a player
     * @return the propertiesOwned
     */
    public ArrayList<Square> getPropertiesOwned() {
        return propertiesOwned;
    }

    /**
     * @param i 
     */
    public void move(int i) {

    }


}
