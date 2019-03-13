import java.util.Scanner;

public class Banker {
	private Scanner kb = new Scanner(System.in);
    private String check;

    public void viewNewProperty(Player player, Square current) {
    	if (player.getBalance() <= current.getCost()) {
			System.out.println(player.getName() +  " does not have enough money to purchase this property, " +
				"if they do purchase it, they lose the game.");
			check = kb.next();
		}
		if (player.getName().equals("bot")) {
			if (player.getBalance() > current.getCost()) {
				buyProperty(player, current);
				check = kb.next();
			}
		}
		else {
			System.out.println("would " + player.getName() + " like to buy " + current.getName() + " for $" +
				current.getCost() + ". They have $" + player.getBalance() + " in their account.");
			System.out.println("press y for yes");
			check = kb.next();
			if (check.toUpperCase().equals("Y")){
				buyProperty(player, current);
				check = kb.next();
			}
		}
    }


    public void stayAtRivalProperty(Player player, Square current) {
    	System.out.print(player.getName() + " has landed on the " + current.getName() +
			" property and must pay $" + current.getRent() + " to the property owner, which is " +
			current.getOwner().getName() + ".\n" + player.getName() + " has $" +
			player.getBalance() + " in their account." );
		check = kb.next();
		payRent(player, current);
		check = kb.next();
    }

    /**
  	* This method gives ownership of a property to a player and tells the user their new balance
   	* @param p A player
   	* @param property A property being bought by a player
   	*/
	public void buyProperty(Player p, Square property) {
		p.changeBalance(-1*property.getCost());
		property.setOwned(true);
		property.setOwner(p);
		p.addPropertiesOwned(property);
		System.out.println(p.getName() + " has purchased " + property.getName() + " for $" + property.getCost() +
		". Their new balance is $" + p.getBalance());
	}

	/**
   	* When a player stays on another player's property, this method transfers rent from the player to that another player
   	* @param p A player paying the rent or the payer
   	* @param property Property owned by the payee (p1)
   	*/
	public void payRent(Player p, Square property) {
		p.changeBalance(-1*property.getRent());
		System.out.println("\n" + p.getName() + " spent $" + property.getRent() + " to spend the night at " +
			property.getName() + " and their new balance is $" + p.getBalance() + ".\n");
		property.getOwner().changeBalance(property.getRent());
		System.out.println(property.getOwner().getName() + " recieved $" + property.getRent() + " as rent from " +
			p.getName() +". Their new balance is $" + property.getOwner().getBalance() + ".");
	}
}