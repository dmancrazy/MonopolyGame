import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class outlines the interaction a player will have with each square and the game in general.
 * @author Group 6
 * @version 1.0
 * @since 2019-02-25
 */
public class MovementGui{

  // initializes dice rolls, current position, and the board
	private Dice dice = new Dice();
	private int roll1;
	private int roll2;
    private int doubles=0;
    private Square current;
    private MonopolyConfiguration config;
    private Jail jail = new Jail();
    private Scanner kb = new Scanner(System.in);
    private String check;
    private ActionGui action = new ActionGui();
    private BankerGui banker = new BankerGui();
    // set proper parameters.

    /**
     * This method sets the current position as the first square on the configured board
     * @param c A setup for the monopoly board
     */
    public MovementGui(MonopolyConfiguration c) {
    	config = c;
    	current = config.getBoard().get(0);
    }
	public String move1(Player player) {
		current = config.getBoard().get(player.getPosition());
		
			
		roll1 = dice.roll();
		roll2 = dice.roll();
		//roll1=10;
		//roll2=20;
		player.changePosition((roll1 + roll2)+ player.getPosition());
		current = config.getBoard().get(player.getPosition());
		//current.getOccupants().add(player.getIcon());
	//	config.printBoard();
		//check = kb.next();
		
		
		return(player.getName() + " rolled a " + roll1 + " and a " + roll2 + ", so \nthey move " +
		(roll1 + roll2) + " spaces.\n");
		}
	
	public int move2(Player player) {
		
		int toReturn = 0;
			if (player.getPassedGo() == true && player.getPosition()==10) {
				action.passGoMoney(player);
				toReturn=4;
			}
			if (player.getPassedGo() == true) {
				action.passGoMoney(player);
				toReturn=3;
			}
			if (player.getPosition() == 30) {
				//jail.arrest(player);
				player.setJail(true);
				player.changePosition(10);
				toReturn=2;
			}

			if (player.getPosition() == 10 &&player.getJail()==false) {
				//jail.justVisiting(player);
				toReturn=1;
				
			}
			/*if (player.getPosition() == 10 &&player.getJail()==true) {
				//jail.justVisiting(player);
				toReturn=5;
				
			}*/
			//System.out.print(toReturn);
			System.out.print(player.getJail());
			
			return toReturn;
	}
	public int move3(Player player){
			int toReturn=0;
			if (current.getType().equals("property") || current.getType().equals("rail")) {
				if(current.getOwned() == false) {
					//banker.viewNewProperty(player, current);
					toReturn=3;
				}

				else if (config.getBoard().get(player.getPosition()).getOwner() != player) {
					//banker.stayAtRivalProperty(player, current);
					toReturn=2;
				}
				else if (current.getOwner() == player) {
					toReturn=1;
					/*System.out.print(player.getName() + " has landed on their own property, which is [" +
						current.getName() + "] so nothing happens.");*/
					
				}
			}
			//System.out.print(toReturn);
			//System.out.print(current.getOwned());
			return toReturn;
	}
	
	public int coordinate2(Player player){
		int check=0;
		check= banker.viewNewProperty(player, current);
		return check;
		
	}
	public void buy(Player player){
		banker.buyProperty(player, current);
	}
	public void payRival(Player player){
		banker.stayAtRivalProperty(player,current );
	}
	public String getOwner(){
		return current.getOwnerName();
	}
	public String jailRoll(Player player){
		String toReturn=" ";
			//if (player.getJail() == true) {
			//jail.nightInJail(player);
			//return("player in jail");
			roll1 = dice.roll();
			roll2 = dice.roll();
			if (roll1==roll2){
				toReturn="Success! You rolled two " + roll1 + "'s, you are free!";
				player.setJail(false);
			}
			else{
				toReturn="Sorry You rolled a " +roll1 + " & a " + roll2;
				player.addJailCount(1);
			}
		return toReturn;
		}
		
	}
	

		
	
		//current.getOccupants().add(player.getIcon());
	//	config.printBoard();
		//check = kb.next();
		//System.out.println(player.getName()+ "'s current position is [" + current.getName() + "]");

