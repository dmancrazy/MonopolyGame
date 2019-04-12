package Actions;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

/**
 * This class is a sub-class of Card class
 */
public class CommunityChest extends Card {

	/**
	 * This method constructs a Community Chest card that gives a player money
	 * @param aName The name of the Community Chest card
	 * @param anAmount The amount of money the card gives a player
	 */
	public CommunityChest(String aName, int anAmount) {
		super(aName, "money", anAmount);
	}
}
