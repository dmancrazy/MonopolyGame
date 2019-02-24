import java.util.*;

public class HumanPlayer{
//This is the Human player in the Monopoly game.

//initializes player's name, balance and properties owned
private String name;
private int balance = 1500;
private ArrayList<Square> propertiesOwned = new ArrayList<Square>();


  /**
   * This method takes some name and sets it as a certain player's name
   * @param name The name of the player
   */
  public HumanPlayer(String aName){
  this.name = aName;

}
  /**
   * This method returns a specific player's given name
   * @return name The player's name
   */
    public String getName() {
        return name;
    }

    /**
     * This method takes some name and sets it as a specific player's name
     * @param name The name of the specific player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns a specific player's balance
     * @return balance The specific player's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * This method takes a certain integer and sets it as a specific player's balance
     * @param balance The number set to a specific player's balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * This method returns the list of properties owned by a specific player
     * @return propertiesOwned The list of properties owned by a specific player
     */
    public ArrayList<Square> getPropertiesOwned() {
        return propertiesOwned;
    }

    public void move(int i) {

    }
}
