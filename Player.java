import java.util.*;

public class Player{
    private String name;
    private int balance = 1500;
    private int position = 0;
    private ArrayList<Square> propertiesOwned = new ArrayList<Square>();
    private boolean jail = false;
    private int jailCount = 0;
    private String occupantValue;


    public Player(String aName, String ov){
    this.name = aName;
    this.occupantValue = ov;
    }

    /**
     * @return the name
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

    public void setBalance(int newB) {
        if (newB >= 0) {
            balance = newB;
        }
        else {
            balance = 0;
        }
    }

    public boolean getJail() {
        return jail;
    }

    public void setJail(boolean b) {
        jail = b;
    }

    public int getJailCount() {
        return jailCount;
    }

    public void addJailCount(int i) {
        jailCount += i;
        if (jailCount == 3) {
            jail = false;
        }
    }


    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @return the propertiesOwned
     */
    public ArrayList<Square> getPropertiesOwned() {
        return propertiesOwned;
    }

    public void changePosition(int newSpot) {
        position = newSpot;
            if (position > 39) {
                position -= 40;
            }
    }

    public String getOccupantValue() {
        return occupantValue;
    }
}