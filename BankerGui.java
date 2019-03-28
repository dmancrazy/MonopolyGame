import java.util.Scanner;

public class BankerGui {
	
   

    public int viewNewProperty(Player player, Square current) {
		int toReturn=0;
    	if (player.getBalance() <= current.getCost()) {
		/*	System.out.println(player.getName() +  " does not have enough money to purchase this property, " +
				"if they do purchase it, they lose the game.");
			*/
		}
		else if (player.getName().equals("bot")) {
			if (player.getBalance() > current.getCost()) {
				buyProperty(player, current);
                toReturn=100+current.getId();
			}
		}
		else {
			toReturn=current.getId()+1;
			
		
				//buyProperty(player, current);
				
			
		}
		return toReturn;
    }


    public void stayAtRivalProperty(Player player, Square current) {
		payRent(player, current);
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
		p.addColorToList(property.getColor());
	}

	/**
   	* When a player stays on another player's property, this method transfers rent from the player to that another player
   	* @param p A player paying the rent or the payer
   	* @param property Property owned by the payee (p1)
   	*/
	public void payRent(Player p, Square property) {
		p.changeBalance(-1*property.getRent());

		property.getOwner().changeBalance(property.getRent());

	}

}