import java.util.Random;
public class ChanceCard {
/**
 * This Class provides the skeleton for the creation of chance cards.
 */

	private String name;
	private String action;
	private int amount;
	private int movedPosition;

    ChanceCard(String aName, String anAction, int aValue, int aMovedPosition) {
        this.name = aName;
        this.action = anAction;
        this.amount= aValue;
		this.movedPosition = aMovedPosition;
    }
	ChanceCard(String aName, String anAction, int aValue) {
		this(aName, anAction, aValue, 0);

	}

	public void ChanceCardAction(Player player, Square current, MonopolyConfiguration config, ChanceCard card){
		if (card.action.equals("money")) {
			player.changeBalance(amount);
		} 
		
		else if (card.action.equals("move")) {
			current.getOccupants().remove(player.getIcon());
			player.changePosition(movedPosition);
			current = config.getBoard().get(player.getPosition());
			current.getOccupants().add(player.getIcon());
			System.out.println("You drew the [" + name + "] card so I moved you to " + current.getName());
		} 
		
		else if (card.action.equals("prison")) {
			current.getOccupants().remove(player.getIcon());
			player.setJail(true);
			player.changePosition(10);
			current = config.getBoard().get(player.getPosition());
			current.getOccupants().add(player.getIcon());
			System.out.println("You drew the [jail] card Go To Jail ! You are now moved to jail. Next turn you will start in in jail " +
				" you must roll double or pay bail to get out.");
		} 

    }
}
