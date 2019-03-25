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
    private Action action = new Action();
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
		if (player.getJail() == true) {
			jail.nightInJail(player);
			return("player in jail");
		}
		else {
			
		roll1 = dice.roll();
		roll2 = dice.roll();
		player.changePosition((roll1 + roll2)+ player.getPosition());
		current = config.getBoard().get(player.getPosition());
		//current.getOccupants().add(player.getIcon());
	//	config.printBoard();
		//check = kb.next();
		
		
		return(player.getName() + " rolled a " + roll1 + " and a " + roll2 + ", so they move " +
		(roll1 + roll2) + " spaces.\n");
		}
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
				jail.arrest(player, current, config);
				toReturn=2;
			}

			if (player.getPosition() == 10) {
				jail.justVisiting(player);
				toReturn=1;
				
			}
			return toReturn;
	}
	public int move3(Player player){
			int toReturn=0;
			if (current.getType().equals("property") || current.getType().equals("rail")) {
				if(current.getOwned() == false) {
					//banker.viewNewProperty(player, current);
					toReturn=3;
				}

				else if (current.getOwner() != player) {
					//banker.stayAtRivalProperty(player, current);
					toReturn=2;
				}
				else if (current.getOwner() == player) {
					toReturn=1;
					/*System.out.print(player.getName() + " has landed on their own property, which is [" +
						current.getName() + "] so nothing happens.");*/
					
				}
			}
			return toReturn;
	}
	
	public int coordinate2(Player player){
		int check=0;
		check= banker.viewNewProperty(player, current);
		return check;
		
	}
	
}
		
	
		//current.getOccupants().add(player.getIcon());
	//	config.printBoard();
		//check = kb.next();
		//System.out.println(player.getName()+ "'s current position is [" + current.getName() + "]");

