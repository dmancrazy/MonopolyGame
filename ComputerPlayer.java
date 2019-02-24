import java.util.*;

public class ComputerPlayer{
//This is the AI computer controlled player for the Monopoly game.

//initializes computer player name, balance and owned properties
private String name;
private int balance = 1500;
private ArrayList<Square> propertiesOwned = new ArrayList<Square>();
    public ComputerPlayer(String aName){
    this.name = aName;
    }


    /**
     * This method takes some name and sets it as a computer player's name
     * @param name The name of the computer player
     */
    public void setName(String aName) {
        name = aName;
    }


    /**
     * This method returns a computer player's given name
     * @return name The player's name
     */
    public String getName() {
        return name;
    }


    /**
     * This method returns a computer player's balance
     * @return balance The computer player's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * This method takes a certain integer and sets it as a computer player's balance
     * @param balance The number set to a computer player's balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * This method returns the list of properties owned by a computer player
     * @return propertiesOwned The list of properties owned by a computer player
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
