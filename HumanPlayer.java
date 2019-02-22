import java.util.*;

public class HumanPlayer{
//This is the Human player in the Monopoly game.

private String name;
private int balance = 1500;
private ArrayList<Square> propertiesOwned = new ArrayList<Square>();



public HumanPlayer(String aName){
this.name = aName;

}



    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * @return the propertiesOwned
     */
    public ArrayList<Square> getPropertiesOwned() {
        return propertiesOwned;
    }

    public void move(int i) {
        
    }
}