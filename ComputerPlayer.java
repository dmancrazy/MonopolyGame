import java.util.*;

public class ComputerPlayer{
//This is the AI computer controlled player for the Monopoly game.
private String name;
private int balance = 1500;
private ArrayList<Square> propertiesOwned = new ArrayList<Square>();
    public ComputerPlayer(String aName){
    this.name = aName;   
    }
    

    /**
     * @param name the name to set
     */
    public void setName(String aName) {
        name = aName;
    }


    /**
     * @return the getname
     */
    public String getName() {
        return name;
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