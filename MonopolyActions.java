import java.util.ArrayList;

public class MonopolyActions {
	private Banker banker = new Banker();

	public String passGoMoney(Player player) {
		if (player.getPassedGo()) {
			player.setPassedGo(false);
			player.changeBalance(200);
			return (player.getName() + " passed the GO! square, they will recieve $200 and their new balance is $" +
				player.getBalance() +".\n");
		}
		else {
			return "";
		}
	}

	public String actionConfirmation(Player player, ArrayList<Square> board, String confirm) {
		Square sq = board.get(player.getPosition());
		if (player.getName().equals("bot")) {
			if (player.getBalance() <= sq.getCost()) {
				confirm = "no";
			}
		}
		if (sq.getType().equals("?")) {
			return ("Chance cards not done yet");
		}
		else if (sq.getType().equals("Go")) {
			player.changeBalance(200);
			return (player.getName() + " gets an extra $200 their new balance is $" + player.getBalance() );	
		}
		else if (sq.getType().equals("property")) {
			if (sq.getOwned()) {
				if (sq.getOwner() == player) {
					return (player.getName() + " spends the night at their own property.");
				}
				else {
					banker.payRent(player, sq);
					return (player.getName() + " spent $" + sq.getRent() + " to spend the night at " +
						sq.getName() + " and their new balance is $" + player.getBalance() + ".\n" +
						"Meanwhile " + sq.getOwner().getName() + " recieved $" + sq.getRent() + " as rent from " +
						player.getName() +". Their new balance is $" + sq.getOwner().getBalance() + ".");
				}
			}
			else {
				if (confirm.toUpperCase().equals("Y")) {
				banker.buyProperty(player, sq);			
					return (player.getName() + " has purchased the property " + sq.getName()+ " for $" + sq.getCost() + ". " + 
						player.getName() + " now has $" + player.getBalance() + " in their account.");
				}
				else {
					return (player.getName() + " decided not to purchase " + sq.getName());
				}
			}
		}

		else if (sq.getType().equals("rail")) {
			if (sq.getOwned()) {
				if (sq.getOwner() == player) {
					return (player.getName() + " spends the night at their own rail.");
				}
				else {
					banker.payRent(player, sq);
					return (player.getName() + " spent $" + sq.getRent() + " to spend the night at " +
						sq.getName() + " and their new balance is $" + player.getBalance() + ".\n" +
						"Meanwhile " + sq.getOwner().getName() + " recieved $" + sq.getRent() + " as rent from " +
						player.getName() +". Their new balance is $" + sq.getOwner().getBalance() + ".");
				}
			}
			else {
				if (confirm.toUpperCase().equals("Y")) {
				banker.buyProperty(player, sq);		
					return (player.getName() + " has purchased the rail " + sq.getName() + " for $" + sq.getCost() + ". " + 
						player.getName() + " now has $" + player.getBalance() + " in their account.");
				}
				else {
					return (player.getName() + " decided not to purchase " + sq.getName());
				}
			}
		}
		else if (sq.getType().equals("chest")) {
			return ("Community Chests haven't been made yet");
		}
		else if (sq.getType().equals("tax")) {
			if (sq.getName().equals("Luxury Tax")) {
				player.changeBalance(-75);
				return (player.getName() + " paid $75 and their new balance is $" + player.getBalance());
			}
			else {
				if (confirm.toUpperCase().equals("Y")) {
					player.changeBalance(-200);
					return (player.getName() + " paid $200, their new balance is $" + player.getBalance());
				}
				else {
					player.changeBalance(-(int)(player.getBalance()*0.1));
					return (player.getName() + " paid 10% of their balance, their new balance is $" + player.getBalance());
				}
			}
		}

		else if (sq.getType().equals("goToJail")) {
			player.changePosition(10);
			player.setJail(true);
			sq.getOccupants().remove(player.getIcon());
			board.get(10).getOccupants().add(player.getIcon());
			return (player.getName() + " is now in jail, roll doubles or pay bail next turn to get out.");
		}
		else if (sq.getType().equals("jail")) {
			return (player.getName() + " had fun visiting jail today");
		}

		else {
			return "";
		}
	}

	public String actionType(Player player, Square sq) {
		if (sq.getType().equals("?")) {
			return (player.getName() + " has landed on the chance card space and will draw a chance card.");
		}
		else if (sq.getType().equals("Go")) {
			return (player.getName() + " has landed exactly on GO! and will recieve an additional $200 on top of the 200 passing go.");	
		}
		else if (sq.getType().equals("property")) {
			if (sq.getOwned()) {
				if (sq.getOwner() == player) {
					return (player.getName() + " has landed on their own property " + sq.getName() +", so nothing will happen.");
				}
				else {
					return (player.getName() + " has landed on " + sq.getName() + " which is owned by " + sq.getOwner().getName() + " and must now pay them $" + sq.getRent() + " for rent.");
				}
			}
			else if (player.getBalance() <= sq.getCost()) {
				return ("Player " + player.getName() + " only has $" + player.getBalance() + " in their account so if they purchase this property costing " +
					sq.getCost() + " they will lose. Type y if you want to lose");
			}
			else {		
				return (player.getName() + " has landed on the property " + sq.getName() + ". " + 
					player.getName() + ", would you like to purchase this property for $" + sq.getCost() + 
					"? Type y if you would like to.");
			}
		}

		else if (sq.getType().equals("rail")) {
			if (sq.getOwned()) {
				if (sq.getOwner() == player) {
					return (player.getName() + " has landed on their own rail " + sq.getName() +", so nothing will happen.");
				}
				else {
					return (player.getName() + " has landed on " + sq.getName() + " which is owned by " + sq.getOwner().getName() + " and must now pay them $" + sq.getRent() + " for rent.");
				}
			}
			else if (player.getBalance() <= sq.getCost()) {
				return ("Player " + player.getName() + " only has $" + player.getBalance() + " in their account so if they purchase this rail costing " +
					sq.getCost() + " they will lose. Type y if you want to lose");
			}
			else {		
				return (player.getName() + " has landed on the rail " + sq.getName() + ". " + 
					player.getName() + ", would you like to purchase this rail for $" + sq.getCost() + 
					"? Type y if you would like to.");
			}
		}

		else if (sq.getType().equals("chest")) {
			return (player.getName() + " has landed on the community chest and will now draw a community chest card.");
		}
		else if (sq.getType().equals("jail")) {
			return (player.getName() + " is just visiting jail.");
		}
		else if (sq.getType().equals("tax")) {
			if (sq.getName().equals("Luxury Tax")) {
				return (player.getName() + " must pay their " + sq.getName() + ", which is $75.");
			}
			else {
				return (player.getName() + " must pay their " + sq.getName() + ", which is 10% 0f their money or $200." +
					" Type y for $200.");
			}
		}
		else if (sq.getType().equals("parking")) {
			return (player.getName() + " gets free parking today and their turn is now over.");
		}
		else {
			return (player.getName() + " has been caught with mescaline and will now be sent to jail.");
		}
	}
}