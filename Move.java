import java.util.Random;
    import java.util.Scanner;
public class Move{
    private int roll1;
    private int roll2;
    //whoseTurn()
    private int doubles=0;
    private Square current;
    // set proper parameters.
    public void roll(HumanPlayer currentPlayer){
		//player= currentPlayer
	Scanner kb = new Scanner();
	String payCheck;
	//must update the player method to have an inJail check, and a setjail check, as well as a jail turn count.
	if(player.inJail==true){
		 system.out.println("you are in jail. You can roll doubles or pay 100$ to get out");
		 system.out.println("Will you pay 100$? Y for yes, N for no");
		 payCheck=kb.next();
		 if(payCheck.toUpper()==y){
			 player.setBalance(player.getBalance()-100);
			 player.setJail(false);
			 system.out.println("you are free");
		 }
		 else{
			 roll1 = ran.nextInt(6)+1;
			 roll2 = ran.nextInt(6)+1;
			 if (roll1 == roll2){
				 system.out.println("Congratulations! You rolled 2" + roll1 +"'s");
				 player.setJail(false);
			 }
			 else{
				 system.out.println("Unfortunately you rolled a" + roll1 + "and a" + roll2);
				 player.setJailCount(1);
			 }
		 }
	}
	else{
		 
		 
		roll1 = rand.nextInt(6) + 1;
		roll2 = rand.nextInt(6) + 1;
		player().setCurrentPosition((roll1 + roll2)+getCurrentPosition());
		for (Square i : board){
			if (i.getID()==player.getCurrentPosition()){
			player.setCurrentPositionName(i.getName());
			current = i;
			}
		}
		// make sure this is right
		if(current.getName()=="Go to Jail"){
			player.setJail(true);
			player.setCurrentPosition(10);
			player.setCurrentPositionName("Jail");
			system.out.println("go to jail");
		}
		else{
				
			System.out.println("your current position is" + player.getCurrentPositionName());
			String check;
		
		
			if( current.getOwned==false){
				if (player.getBalance()-current.getCost()){
					System.println("you do not have enough money to purchase this property");
				}
				else{
					System.out.println("would you like to buy" + current.getName());
					System.out.println("press y for yes or n for n");
					check = kb.next();
					if (check==y){
						player.setBalance(player.getBalance-current.getPrice());
					}
					else{}
				}
			}
			//gotta set property owners and get property owners, and houses count too
			// we should set a house multiplier for each property too.
			// make sure the payee variable under the square property references the legit player in the list
			// a deposit and withdraw method would be nice
			else{
				system.out.println("that property is owned by" property.getOwner());
				
				player.setBalance(player.getBalance()-(current.getRent()*current.houseMultiplier()));
				current.getOwner().setBalance(current.getOwner().getBalance()+(current.getRent()*current.houseMultiplier()));
				// a printLN to say how much was payed
			}
		}
	}
	}
}
		
				
			    
			    // public ifDoubles()
				
				// alot of this can probably be broken into separate functions, like a jailcheck and jail roll
				// doubles can probably be checked in the play function everytime the loop runs. 
