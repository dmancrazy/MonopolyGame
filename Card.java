import java.util.Random;
public class Card {
/**
 * This Class provides the skeleton for the creation of chance cards.
 */

	private String name;
	private String action;
	private int amount;
	private int movedPosition;

    Card(String aName, String anAction, int aValue, int aMovedPosition) {
        this.name = aName;
        this.action = anAction;
        this.amount= aValue;
		this.movedPosition = aMovedPosition;
    }

	Card(String aName, String anAction, int aValue) {
		this(aName, anAction, aValue, 0);
	}

	public String getName() {
		return this.name;
	}

	public int getAmount() {
		return amount;
	}

}

