import java.util.ArrayList;
import java.util.Scanner;
public class HousesGui{
	//get a board to reference here
	private MonopolyConfiguration board;
	//how do scanners work
	private Scanner kb = new Scanner(System.in);
	private String checkString;
	private int checkInt;
	public HousesGui() {
    
	}
	public boolean houses(Player p){
		boolean checkHouse= false;
	
		ArrayList<String> listOfColors= new ArrayList<String>();
		ArrayList<String> checkList = new ArrayList<String>();
		listOfColors.add("brown");
		listOfColors.add("light-blue");
		listOfColors.add("purple");
		listOfColors.add("orange");
		listOfColors.add("red");
		listOfColors.add("yellow");
		listOfColors.add("green");
		listOfColors.add("dark-blue");
		for(String color:listOfColors){
			int counter= 0;
			// if the list is a certain size??
			checkList= new ArrayList<String>(p.getPlayerColorList());
			for(String checkColor: checkList){
				//if(color=="light-blue"||color=="dark-blue"){ must set different set of rules for the light and dark blue houses
				if(color==checkColor){
					counter+=1;
					if(counter==3){
						buyHouse(color, p);
						//If player house amount > 1 then sell houses function too;
					}
					
				}
			}
		}
		return checkHouse;
						
}
	
	
// i dont think this needs to be in its own class but if i have to move it to another file then im ready

	public void buyHouse(String aColor, Player calledP ){
		ArrayList<Square> checkBoard= board.getBoard();
		System.out.print("You own all of the" + aColor + "properties");
		
	//	for (int i=0;i<40;i++){
			
		for(Square property: checkBoard){
				if(property.getColor()==aColor){
					System.out.print(" Would you like to purchase a property on" + property.getName()+ "for" + property.getHousePrice()+"?");
					System.out.print(" Type Y for yes and N for no");
					// this can be cheesed if they dont type y or n
					checkString= kb.next();
					if(checkString.toUpperCase()=="Y"){
						// this can also be cheesed by players purchasing unlimited houses
						System.out.print("How many houses would you like to buy? (Between 1 and 4)");
						// is this right?
						checkInt=kb.nextInt();
			
						if(calledP.getBalance()>(property.getHousePrice()*checkInt)){
							calledP.changeBalance((-1)*(property.getHousePrice()*checkInt));
							property.setHouseAmount(checkInt);
						}
						else{ // fix this so they can be re-prompted
							System.out.print("you do not have enough to perform this action");
						}
					}
					else{
						System.out.print("maybe next time then");
					}
				}
		}
	}
	
	// create a sell houses method
	// set rents per house to be less rediculous
	// set houses to be called everytime
}
			
	