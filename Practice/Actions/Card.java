package Actions;

import java.util.Random;

/**
 * This class provides the skeleton for the creation of chance cards.
 */
public class Card {

	private String name;
	private String action;
	private int amount;
	private int movedPosition;

	/**
	 * This method constructs a card with some name, action, money amount, and position moved amount
	 * @param aName The card name
	 * @param anAction The action associated with the card
	 * @param aValue A money amount that the card can give you
	 * @param aMovedPosition An amount that the card allows you to move
	 */
  Card(String aName, String anAction, int aValue, int aMovedPosition) {
      this.name = aName;
      this.action = anAction;
      this.amount= aValue;
			this.movedPosition = aMovedPosition;
  }

	/**
	 * This method constructs a default card with move position amount 0
	 * @param aName The card name
	 * @param anAction The action associated with the card
	 * @param aValue A money amount that the card can give you
	 */
	Card(String aName, String anAction, int aValue) {
		this(aName, anAction, aValue, 0);
	}

	/**
	 * This getter method allows you to get the name of a card
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This getter method allows you to get the money value of the card
	 */
	public int getAmount() {
		return amount;
	}

}
