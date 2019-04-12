import java.util.*;

/**
 * This class deals with any interaction the player has with the board throughout the game.
 * @author Group 6
 * @version 1.0
 * @since 2019-04-11
 */
public class MonopolyActions {
	private Banker banker = new Banker();
	private DeckMaker deckMaker = new DeckMaker();
	public ArrayList<Card> chanceDeck = deckMaker.chanceCardDeck();
	public ArrayList<Card> communityDeck = deckMaker.communityDeck();
	private Random drawer = new Random();

	/**
	 * This method checks if a player has gone full circle around the board, and gives them $200
	 * each time.
	 * @param player The player that has just passed the GO! square
	 * @return A message stating a plyer has passed the GO! square and their new balance after +$200
	 */
	public String passGoMoney(Player player) {
		if (player.getPassedGo()) {
			player.setPassedGo(false);
			player.changeBalance(200);
			return (player.getName() + " passed the GO! square, they will recieve $200\nand their new balance is $" +
				player.getBalance() +".\n");
		}
		else {
			return "";
		}
	}

	/**
	 * This method deals with the actions that occur with each special square that players land on and executes the actions
	 * @param player The Player who has landed on a squ
	 * @param board The current board
	 * @param confirm Confirmation of whether the playe can buy the property
	 * @return A message
	 */
	public String actionConfirmation(Player player, List<Square> board, String confirm) {
		Square sq = board.get(player.getPosition());

		// Checks if a bot player can buy the property it landed on
		if (player.getName().equals("bot")) {
			if (player.getBalance() <= sq.getCost()) {
				confirm = "no";
			}
		}

		// The player lands on a chance card square
		if (sq.getType().equals("?")) {
			return (dealer(player, chanceDeck));
		}

		// The player has passed Go, $200 is added to their balance
		else if (sq.getType().equals("Go")) {
			player.changeBalance(200);
			return (player.getName() + " gets an extra $200 their new balance is $" + player.getBalance() );
		}
		// The player has landed on a property, if it is their's, nothing happens. If not, player pays rent to owner
		// If it is owned by no one, they're prompted to buy or not
		// The appropriate message is returned according to whether its owned or not
		else if (sq.getType().equals("property")) {
			if (sq.getOwned()) {
				if (sq.getOwner() == player) {
					return (player.getName() + " spends the night at their own property.");
				}
				else {
					banker.payRent(player, sq);
					return (player.getName() + " spent $" + sq.getRent() + " to spend the night at " +
						sq.getName() + "\nand their new balance is $" + player.getBalance() + ".\n" +
						"Meanwhile " + sq.getOwner().getName() + " recieved $" + sq.getRent() + " as rent\nfrom " +
						player.getName() +". Their new balance is $" + sq.getOwner().getBalance() + ".\n");
				}
			}
			else {
				if (confirm.toUpperCase().equals("Y") || confirm.toUpperCase().equals("YES")) {
				banker.buyProperty(player, sq);
					return (player.getName() + " has purchased the property " + sq.getName()+ " for\n$" + sq.getCost() + ". " +
						player.getName() + " now has $" + player.getBalance() + " in their account.");
				}
				else {
					return (player.getName() + " decided not to purchase " + sq.getName());
				}
			}
		}

		// The player has landed on a railroad, if it is their's, nothing happens. If not, player pays rent to owner
		// If it is owned by no one, they're prompted to buy or not
		// The appropriate message is returned according to whether its owned or not
		else if (sq.getType().equals("rail")) {
			if (sq.getOwned()) {
				if (sq.getOwner() == player) {
					return (player.getName() + " spends the night at their own rail.");
				}
				else {
					banker.payRent(player, sq);
					return (player.getName() + " spent $" + sq.getRent() + " to spend the night at " +
						sq.getName() + "\nand their new balance is $" + player.getBalance() + ".\n" +
						"Meanwhile " + sq.getOwner().getName() + " recieved $" + sq.getRent() + " as rent\nfrom " +
						player.getName() +". Their new balance is $" + sq.getOwner().getBalance() + ".\n");
				}
			}
			else {
				if (confirm.toUpperCase().equals("Y") || confirm.toUpperCase().equals("YES")) {
				banker.buyProperty(player, sq);
					return (player.getName() + " has purchased the rail " + sq.getName() + " for \n$" + sq.getCost() + ". " +
						player.getName() + " now has $" + player.getBalance() + " in their account.");
				}
				else {
					return (player.getName() + " decided not to purchase " + sq.getName());
				}
			}
		}

		// The player lands on a community chest square
		else if (sq.getType().equals("chest")) {
			return (dealer(player, communityDeck));
		}

		// The player has landed on tax square, if it is luxury tax, a message of their $75 payment is returned
		// If it isn't owned, they can purchase the square. A message with their new balance is returned
		// otherwise, they pay tax to the owner. A message stating how much they paid is returned
		else if (sq.getType().equals("tax")) {
			if (sq.getName().equals("Luxury Tax")) {
				player.changeBalance(-75);
				return (player.getName() + " paid $75 and their new balance is $" + player.getBalance());
			}
			else {
				if (confirm.toUpperCase().equals("Y") || confirm.toUpperCase().equals("YES")) {
					player.changeBalance(-200);
					return (player.getName() + " paid $200, their new balance is $" + player.getBalance());
				}
				else {
					player.changeBalance(-(int)(player.getBalance()*0.1));
					return (player.getName() + " paid 10% of their balance, their new balance is $" + player.getBalance());
				}
			}
		}

		// The player has landed on the goToJail square, they are moved to Jail. Jail message is returned
		else if (sq.getType().equals("goToJail")) {
			player.changePosition(10);
			player.setJail(true);
			sq.getOccupants().remove(player.getIcon());
			board.get(10).getOccupants().add(player.getIcon());
			return (player.getName() + " is now in jail, roll doubles or pay bail next turn to get out.");
		}

		// If they land on jail itself, they simply pass by, nothing happens. Visit message is returned
		else if (sq.getType().equals("jail")) {
			return (player.getName() + " had fun visiting jail today");
		}

		// If none of the above applies, an empty string is returned
		else {
			return "";
		}
	}

	/**
	 * This method deals with identifying what actions occur with certain squares but do not perform the actions
	 * @param player The Player who has landed on a square
	 * @param sq The square that the player has landed on
	 *@return A message
	 */
	public String actionType(Player player, Square sq) {

		// If the player lands on chance card, a message stating that will draw a chance card is returned
		if (sq.getType().equals("?")) {
			return (player.getName() + " has landed on the chance card space and will draw a chance card.");
		}

		// If the player lands on Go, a message stating they have recieved $200 is returnd
		else if (sq.getType().equals("Go")) {
			return (player.getName() + " has landed exactly on GO! and will recieve\nan additional $200 on top of the 200 passing go.");
		}

		// Player lands on property, if it is their's, a message stating nothings happens is returned
		// If it isn't their's, a message stating the owner's name and rent payment is returned
		// If they don't have enough to buy the property, a message stating the lack of funds and possibility of losing is returned
		else if (sq.getType().equals("property")) {
			if (sq.getOwned()) {
				if (sq.getOwner() == player) {
					return (player.getName() + " has landed on their own property " + sq.getName() +", so nothing will happen.");
				}
				else {
					return (player.getName() + " has landed on " + sq.getName() + " which is owned by\n" + sq.getOwner().getName() + " and must now pay them $" + sq.getRent() + " for rent.");
				}
			}
			else if (player.getBalance() <= sq.getCost()) {
				return ("Player " + player.getName() + " only has $" + player.getBalance() + " in their account so\nif they purchase this property costing " +
					sq.getCost() + " they will lose. Say yes if you want to lose");
			}
			else {
				return (player.getName() + " has landed on the property " + sq.getName() + ". " +
					player.getName() + ",\nwould you like to purchase this property\nfor $" + sq.getCost() +
					"? Say yes if you would like to.");
			}
		}

		// Player lands on railraod, if it is their's, a message stating nothings happens is returned
		// If it isn't their's, a message stating the owner's name and rent payment is returned
		// If they don't have enough to buy the property, a message stating the lack of funds and possibility of losing is returned
		else if (sq.getType().equals("rail")) {
			if (sq.getOwned()) {
				if (sq.getOwner() == player) {
					return (player.getName() + " has landed on their own rail " + sq.getName() +", so nothing will happen.");
				}
				else {
					return (player.getName() + " has landed on " + sq.getName() + " which is owned by " + sq.getOwner().getName() + " and\nmust now pay them $" + sq.getRent() + " for rent.");
				}
			}
			else if (player.getBalance() <= sq.getCost()) {
				return ("Player " + player.getName() + " only has $" + player.getBalance() + " in their account so if they purchase this rail\ncosting " +
					sq.getCost() + " they will lose. Say yes if you want to lose");
			}
			else {
				return (player.getName() + " has landed on the rail " + sq.getName() + ".\n" +
					player.getName() + ",\nwould you like to purchase this rail for\n$" + sq.getCost() +
					"? Say yes if you would like to.");
			}
		}

		// If the player lands on community chest card, a message stating that will draw a community chest card is returned
		else if (sq.getType().equals("chest")) {
			return (player.getName() + " has landed on the community chest\nand will now draw a community chest card.");
		}

		// If the player lands on jail, a visit message is returned
		else if (sq.getType().equals("jail")) {
			return (player.getName() + " is just visiting jail.");
		}

		// The player lands on tax square, if it is luxury tax, a message stating their $75 is returned
		// If it is owned by another player, a message stating they must pay 10% of their balance is returned
		else if (sq.getType().equals("tax")) {
			if (sq.getName().equals("Luxury Tax")) {
				return (player.getName() + " must pay their " + sq.getName() + ", which is $75.");
			}
			else {
				return (player.getName() + " must pay their " + sq.getName() + ", which is\n10% of their money or $200." +
					" Say yes for $200.");
			}
		}

		// If a player lands on parking square, a message stating their turn is over is returned
		else if (sq.getType().equals("parking")) {
			return (player.getName() + " gets free parking today and their turn is now over.");
		}

		// If none of the above conditions apply, a message stating the player is being sent to jail is returned
		else {
			return (player.getName() + " has done illegal activities and will now be sent to jail.");
		}
	}

	/**
	 * This method deals with what happens when a player lands on either a Chance card or Community Chest
	 * An amount specified on the card is added to the player's balance
	 * @param player The player that landed on the Chance card or community chest square
	 * @param deck The deck with the Chance/Community Chest Cards
	 * @return A message stating what card they got, and how much was added to their balance
	 */
	public String dealer(Player player, ArrayList<Card> deck) {
		Card cardDrawn = deck.get(drawer.nextInt(deck.size()));
		player.changeBalance(cardDrawn.getAmount());
		return player.getName() + " drew the " + cardDrawn.getName() + " card.\nTheir new balance is $" + player.getBalance();
	}
}
