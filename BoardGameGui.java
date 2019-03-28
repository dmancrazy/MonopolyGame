import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;


public class BoardGameGui {
	
	Stage monopoly;
	private ArrayList<Square> board;
	private Label turnMessage = new Label("");
	private Dice dice= new Dice();
	private int roll1;
	private int roll2;
	private int actionCount=1;
	//private Turn playerTurn = new Turn();
	private int playersTurn=0;
	private int buyCount=-1;
	private int messageCount=0;
// Initialize array lists to allow iteration through each item 
	private ArrayList<Label> propertyNames = new ArrayList<>();
	private ArrayList<VBox> squares = new ArrayList<VBox>();
	private ArrayList<Label> playerNumbers = new ArrayList<Label>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private static boolean quit = false;
	private MonopolyConfiguration config= new MonopolyConfiguration();
	//private MovementGui move = new MovementGui(config);
	private Button Buy = new Button();
	private Button Roll = new Button();
	private int coordinateVariable=0;
	private BankerGui banker= new BankerGui();
	private boolean yesNo=false;
	private boolean jailYesNo=false;
	private Label balanceLabel1;
	private Label balanceLabel2;
	private Label balanceLabel3;
	private Label balanceLabel4;
	private Label occupants = new Label("");
	private String ourString; 
	private Label propertyList1 = new Label ("");
	private Label propertyList2 = new Label ("");
	private Label propertyList3 = new Label ("");
	private Label propertyList4 = new Label ("");
// Initialize all names of each squares
	public BoardGameGui(ArrayList<Player> nPlayers) {
		this.board = BoardMaker.DefaultBoard();
		this.players = nPlayers;
	}

	
// Initialize the "menu" of the game
	Label menuTitle = new Label("Welcome To Monopoly!");
	Label gameTitle = new Label("MONOPOLY");
	
	
	HBox actions = new HBox();
	
// Initialize the player cards that show up inside the board
	VBox pCard1 = new VBox();
	VBox pCard2 = new VBox();
	VBox pCard3 = new VBox();
	VBox pCard4 = new VBox();
	VBox botCard = new VBox();
	VBox turn = new VBox();
	Label playerTurnLabel = new Label("");

	
	
// start the window that the board will be on
public Scene getScene2() {
	// adding the "menu" items
		
		Buy.setPrefSize(90, 50);
		Roll.setPrefSize(90, 50);
		Buy.setText("Buy");
		Roll.setText("Roll");
		actions.getChildren().addAll(Buy, Roll);
		turn.getChildren().add(playerTurnLabel);
		Roll.setOnAction(new rollHandle());
		Buy.setOnAction(new buyHandle());
		for (Square property : board) {
			propertyNames.add(new Label(property.getName()));
		}
		
		

	// creates 40 squares and adds it to the list for later iteration	
		for (int i = 0 ; i < 40 ; i++) {
			VBox square = new VBox();
			square.setAlignment(Pos.TOP_CENTER);
			square.setPrefSize(100, 100);
			Label title = new Label(board.get(i).getName());
			if(i== 1|| i==3 ){
				 title.setStyle("-fx-background-color: brown");
				 }

				else if(i== 6|| i==8 ||i==9 ){
					title.setStyle("-fx-background-color: lightblue");
					}

				else if(i==11 || i== 13|| i==14){ 
					title.setStyle("-fx-background-color: purple");
					}

				else if(i== 16|| i== 18|| i==19){ 
					title.setStyle("-fx-background-color: orange");
					}

				else if(i== 21|| i== 23||i==24 ){
					title.setStyle("-fx-background-color: red");
					}
				else if(i== 26|| i==27 ||i==29 ){
					title.setStyle("-fx-background-color: yellow");
					}

				else if(i== 31|| i== 32|| i==34){
					title.setStyle("-fx-background-color: green");
					}

				else if(i== 37|| i==39 ){	
					title.setStyle("-fx-background-color: blue");
					}
				//else {
					//title.setStyle("-fx-background-color: grey");
				//	}
			square.getChildren().add(title);
			square.getChildren().add(occupants);
			square.setStyle("-fx-border-color: black");
			//ArrayList<String> playerNames = new ArrayList<String>();
			squares.add(square);
		}
	
	
	// game title rotated in the center
		gameTitle.setRotate(-45);
		placePlayers();

		// setup of the board
		GridPane gridBoard = new GridPane();
		gridBoard.setPadding(new Insets(10, 10, 10, 10));

	// configure the positions each squares will go into (x,y = 0, 0 = top left corner)
		GridPane.setConstraints(squares.get(0), 10, 10);
		GridPane.setConstraints(squares.get(1), 9, 10);
		GridPane.setConstraints(squares.get(2), 8, 10);
		GridPane.setConstraints(squares.get(3), 7, 10);
		GridPane.setConstraints(squares.get(4), 6, 10);
		GridPane.setConstraints(squares.get(5), 5, 10);
		GridPane.setConstraints(squares.get(6), 4, 10);
		GridPane.setConstraints(squares.get(7), 3, 10);
		GridPane.setConstraints(squares.get(8), 2, 10);
		GridPane.setConstraints(squares.get(9), 1, 10);
		GridPane.setConstraints(squares.get(10), 0, 10);
		GridPane.setConstraints(squares.get(11), 0, 9);
		GridPane.setConstraints(squares.get(12), 0, 8);
		GridPane.setConstraints(squares.get(13), 0, 7);
		GridPane.setConstraints(squares.get(14), 0, 6);
		GridPane.setConstraints(squares.get(15), 0, 5);
		GridPane.setConstraints(squares.get(16), 0, 4);
		GridPane.setConstraints(squares.get(17), 0, 3);
		GridPane.setConstraints(squares.get(18), 0, 2);
		GridPane.setConstraints(squares.get(19), 0, 1);
		GridPane.setConstraints(squares.get(20), 0, 0);
		GridPane.setConstraints(squares.get(21), 1, 0);
		GridPane.setConstraints(squares.get(22), 2, 0);
		GridPane.setConstraints(squares.get(23), 3, 0);
		GridPane.setConstraints(squares.get(24), 4, 0);
		GridPane.setConstraints(squares.get(25), 5, 0);
		GridPane.setConstraints(squares.get(26), 6, 0);
		GridPane.setConstraints(squares.get(27), 7, 0);
		GridPane.setConstraints(squares.get(28), 8, 0);
		GridPane.setConstraints(squares.get(29), 9, 0);
		GridPane.setConstraints(squares.get(30), 10, 0);
		GridPane.setConstraints(squares.get(31), 10, 1);
		GridPane.setConstraints(squares.get(32), 10, 2);
		GridPane.setConstraints(squares.get(33), 10, 3);
		GridPane.setConstraints(squares.get(34), 10, 4);
		GridPane.setConstraints(squares.get(35), 10, 5);
		GridPane.setConstraints(squares.get(36), 10, 6);
		GridPane.setConstraints(squares.get(37), 10, 7);
		GridPane.setConstraints(squares.get(38), 10, 8);
		GridPane.setConstraints(squares.get(39), 10, 9);
		
		// positioning of the "menu items" and player cards/ name inputs
		GridPane.setConstraints(actions, 13, 10);
		GridPane.setConstraints(gameTitle, 5, 5);
		//GridPane.setConstraints(numPlayers, 13, 1);
		GridPane.setConstraints(pCard1, 1, 1);
		GridPane.setConstraints(pCard2, 9, 1);
		GridPane.setConstraints(botCard, 9, 1);
		GridPane.setConstraints(pCard3, 9, 9);
		GridPane.setConstraints(pCard4, 1, 9);
		GridPane.setConstraints(turn, 13, 4);
		GridPane.setConstraints(turnMessage,13,3);
		GridPane.setConstraints(propertyList1,13,5);
		GridPane.setConstraints(propertyList2,13,6);
		GridPane.setConstraints(propertyList3,13,7);
		GridPane.setConstraints(propertyList4,13,8);
		//GridPane.setConstraints(checknames, 13, 2);
	
	// adds all the named squares onto the board/gridpane
		gridBoard.getChildren().addAll(squares.get(0), squares.get(1), squares.get(2), squares.get(3), squares.get(4), squares.get(5), squares.get(6), squares.get(7), squares.get(8), squares.get(9),
				squares.get(10), squares.get(11), squares.get(12), squares.get(13), squares.get(14), squares.get(15), squares.get(16), squares.get(17), squares.get(18), squares.get(19), squares.get(20), squares.get(21), squares.get(22), squares.get(23),
				squares.get(24), squares.get(25), squares.get(26), squares.get(27), squares.get(28), squares.get(29), squares.get(30), squares.get(31), squares.get(32), squares.get(33), squares.get(34), squares.get(35), squares.get(36), squares.get(37),
				squares.get(38), squares.get(39),  actions, gameTitle, pCard1, pCard2, pCard3, pCard4, botCard, turn, turnMessage,propertyList1,propertyList2,propertyList3,propertyList4);
	// run the window with the board
		Scene scene2 = new Scene(gridBoard, 1600, 1000);
		return scene2;
	}

	
	// The action that occurs after number of players is picked		
		public void placePlayers() {
			// this creates player cards according to the number of players picked above
			if (players.size() == 1) {
				balanceLabel1 =new Label("balance: 1500" );
				propertyList1= new Label (players.get(0).getName()+ "doesn't own any properties yet");
				
				pCard1.getChildren().addAll(new Label(players.get(0).getName()), balanceLabel1);

			}
			else if (players.size() == 2) {
				propertyList1= new Label (players.get(0).getName()+ "doesn't own any properties yet");
				propertyList2= new Label (players.get(1).getName()+ "doesn't own any properties yet");
				balanceLabel1 =new Label("balance: 1500");
				balanceLabel2 = new Label("balance: 1500");
				
				pCard1.getChildren().addAll(new Label(players.get(0).getName()), balanceLabel1);
				pCard2.getChildren().addAll(new Label(players.get(1).getName()), balanceLabel2);

			}
			else if (players.size() == 3) {
				propertyList1= new Label (players.get(0).getName()+ "doesn't own any properties yet");
				propertyList2= new Label (players.get(1).getName()+ "doesn't own any properties yet");
				propertyList3= new Label (players.get(2).getName()+ "doesn't own any properties yet");
				balanceLabel1 =new Label("balance: 1500");
				balanceLabel2 = new Label("balance: 1500");
				balanceLabel3 = new Label("balance: 1500");
				pCard1.getChildren().addAll(new Label(players.get(0).getName()), balanceLabel1);
				pCard2.getChildren().addAll(new Label(players.get(1).getName()), balanceLabel2);
				pCard3.getChildren().addAll(new Label(players.get(2).getName()), balanceLabel3);

			}
			else if (players.size() == 4) {
				propertyList1= new Label (players.get(0).getName()+ "doesn't own any properties yet");
				propertyList2= new Label (players.get(1).getName()+ "doesn't own any properties yet");
				propertyList3= new Label (players.get(2).getName()+ "doesn't own any properties yet");
				propertyList4= new Label (players.get(3).getName()+ "doesn't own any properties yet");
				balanceLabel1 =new Label("balance: 1500");
				balanceLabel2 = new Label("balance: 1500");
				balanceLabel3 =new Label("balance: 1500");
				balanceLabel4 = new Label("balance: 1500");
				pCard1.getChildren().addAll(new Label(players.get(0).getName()), balanceLabel1);
				pCard2.getChildren().addAll(new Label(players.get(1).getName()), balanceLabel2);
				pCard3.getChildren().addAll(new Label(players.get(2).getName()), balanceLabel3);
				pCard4.getChildren().addAll(new Label(players.get(3).getName()), balanceLabel4);
			}

			// the "Done" button is nullified to stop player name prompts to duplicate	
				//Ok.setOnAction(null);
				
			for (Player player : players) {
				board.get(0).getOccupants().add(player.getIcon());
			}
			for (int i = 0 ; i < 40 ; i++) {
				Label occupants = new Label(board.get(i).listedOccupants());
				squares.get(i).getChildren().add(occupants);
			}
			for (int i = 0 ; i < players.size() ; i++) {
				playerTurnLabel.setText("It is player " + players.get(players.size() - players.size()).getName() + "'s turn. \nThey are at " +  board.get(players.get(i).getPosition()).getName() + 
						" and their balance is $" + players.get(i).getBalance()+"Click roll to roll the dice");
					
			}
		}
		// calling turn update means a terminal action that ends a turn and resets all counters
		public void	turnUpdate(){
			playersTurn+=1;
			if (playersTurn>players.size()-1){
				playersTurn=0;
			}
			Buy.setText("Buy");
			Roll.setText("Roll");
			
			//update this to be action count -1 so you can set an press ok to set shit up button
			//set this shit to update players balances everytime;
			//for(int i = 0 ; i < players.size() ; i++){
			refresh();
			
			if(players.size()==1){
				propertyList1.setText(players.get(0).getName() + printProperties(players.get(0).getPropertiesOwned()));
				balanceLabel1.setText("balance: "+ players.get(0).getBalance());
			}
			else if(players.size()==2){
				propertyList1.setText(players.get(0).getName() + printProperties(players.get(0).getPropertiesOwned()));
				propertyList2.setText(players.get(1).getName() + printProperties(players.get(1).getPropertiesOwned()));
				balanceLabel1.setText("balance: "+ players.get(0).getBalance());
				balanceLabel2.setText("balance: "+ players.get(1).getBalance());
			}
			else if (players.size()==3){
				propertyList1.setText(players.get(0).getName() + printProperties(players.get(0).getPropertiesOwned()));
				propertyList2.setText(players.get(1).getName() + printProperties(players.get(1).getPropertiesOwned()));
				propertyList3.setText(players.get(2).getName() + printProperties(players.get(2).getPropertiesOwned()));
				balanceLabel1.setText("balance: "+ players.get(0).getBalance());
				balanceLabel2.setText("balance: "+ players.get(1).getBalance());
				balanceLabel3.setText("balance: "+ players.get(2).getBalance());
			}
			else if(players.size()==4){
				propertyList1.setText(players.get(0).getName() + printProperties(players.get(0).getPropertiesOwned()));
				propertyList2.setText(players.get(1).getName() + printProperties(players.get(1).getPropertiesOwned()));
				propertyList3.setText(players.get(2).getName() + printProperties(players.get(2).getPropertiesOwned()));
				propertyList4.setText(players.get(3).getName() + printProperties(players.get(3).getPropertiesOwned()));
				balanceLabel1.setText("balance: "+ players.get(0).getBalance());
				balanceLabel2.setText("balance: "+ players.get(1).getBalance());
				balanceLabel3.setText("balance: "+ players.get(2).getBalance());
				balanceLabel4.setText("balance: "+ players.get(3).getBalance());
			}
			for (int j =0; j>39; j++){
				squares.get(j).getChildren().clear();
			}
			/*for(Player player: players){
				for( Square square: board){
					if(player.getPosition()==square.getId()){
						occupants.setText(player.getName());
						squares.get(player.getPosition()).getChildren().add(occupants);
						
					}
					//else if(player.getPosition()!=square.getId()){
					//	occupants.setText("");
					//	squares.get(player.getPosition()).getChildren().remove(occupants);
					//}
				}
			}*/
			playerTurnLabel.setText("it is " + players.get(playersTurn).getName()+ "'s turn");
		
			
		}
		public void refresh(){
			actionCount=0;
			buyCount=-1;
			messageCount=0;
			coordinateVariable=0;
			yesNo=false;
			jailYesNo=false;
			
		}
		public String printProperties(ArrayList<Square> s){
		String toReturn = "Properties : ";
		if(s.size()==0){ toReturn=" doesn't own properties yet";
		}
		else{
			for( Square square: s){
				if (toReturn.length()>100){
					toReturn=toReturn+"\n";
					
				}
				toReturn= toReturn+  square.getName() + ", ";
				
				
			}
		}
		return toReturn;
		
	}
	
		
		
	public class rollHandle implements EventHandler<ActionEvent> {
		@Override
		//add two wrappers
		
		public void handle(ActionEvent event) {
			if (players.get(playersTurn).getJail()==true && jailYesNo==false){
				//move.jailHandler(players.get(playersTurn))
				turnMessage.setText("You are in jail. Would you like to pay 100$ to get out, or roll for doubles?");
				Buy.setText("Pay");
				Roll.setText("Roll");
				jailYesNo=true;
				
			}
			//else if( jailYesNo==true){
					//ourString=move.jailRoll(players.get(playersTurn));
					// if they roll doubles or pay they get a turn;
					//turnMessage.setText(ourString);
					//turnUpdate();
					//move.jailHandler(players.get(playersTurn))
				//	player.changeBalance(-100);
				//	player.setJail(false);
			//}
			
				
				
			
			else if (actionCount==0){
				actionCount+=1;
				turnMessage.setText("Click roll to roll the dice");
				Buy.setText("Buy");
				Roll.setText("Roll");
			}
			else if (actionCount==1){
				
				actionCount++;
				Roll.setText("Ok");
				//turnMessage.setText(move.move1(players.get(playersTurn))+"Click ok to continue");
			
			
			}
			else if (actionCount==2){
				//messageCount=move.move2(players.get(playersTurn));
				
				if (messageCount==4){
					turnMessage.setText(players.get(playersTurn).getName()+" passed go and is visiting jail Click Ok to continue");
									actionCount++;
									//buyCount=move.coordinate2(players.get(playersTurn));
				//coordinateVariable=move.move3(players.get(playersTurn));
				}
				
				else if (messageCount==3){
					turnMessage.setText(players.get(playersTurn).getName()+" passed go, Collect 200$ Click Ok to continue");
									actionCount++;
									//buyCount=move.coordinate2(players.get(playersTurn));
				//coordinateVariable=move.move3(players.get(playersTurn));
				}
				else if (messageCount==2){
					Roll.setText("Roll");
					//set this shit to say click continue to continue
					turnMessage.setText(players.get(playersTurn).getName()+" goes to jail. Roll doubles to get out next time. Click roll to continue");
					//player.setJailcount();
					
					turnUpdate();
				}
				else if (messageCount==1){
					turnMessage.setText(players.get(playersTurn).getName()+" is just visiting jail  Click Ok to continue ");
					actionCount++;
					//buyCount=move.coordinate2(players.get(playersTurn));
					//coordinateVariable=move.move3(players.get(playersTurn));
				}
				else{
					turnMessage.setText("Nice to not be in jail. Press ok to continue");
					actionCount++;
					//buyCount=move.coordinate2(players.get(playersTurn));
					//coordinateVariable=move.move3(players.get(playersTurn));
				}
				messageCount=0;
			//	actionCount++;
			//	buyCount=move.coordinate2(players.get(playersTurn));
			//	coordinateVariable=move.move3(players.get(playersTurn));
			}
			else if (actionCount==3){
				//coordinateVariable=move.move3(players.get(playersTurn));
				if (coordinateVariable==1){
					turnMessage.setText(players.get(playersTurn).getName()+" has landed on their own property");
					actionCount=0;
					Roll.setText("Roll");
					turnUpdate();
					coordinateVariable=0;
				}
				else if (coordinateVariable==2){

					//turnMessage.setText(players.get(playersTurn).getName()+" has landed at " + move.getOwner() +"'s property. They will pay them "+ board.get(buyCount-1).getRent()+ " Press Ok to continue");
					//move.payRival(players.get(playersTurn));
					//set thus shit to update a pay count
					//actionCount++;
				//	buyCount=move.coordinate2(players.get(playersTurn));
					//coordinateVariable=0;
					turnUpdate();
				}
				else if (coordinateVariable==3){
					//buyCount=move.coordinate2(players.get(playersTurn));
					turnMessage.setText(players.get(playersTurn).getName()+"  has landed at " + board.get(buyCount-1).getName()+ ". Buy for " + board.get(buyCount-1).getCost() +" ?");
		
					actionCount++;
					coordinateVariable=0;
					Buy.setText("No");
					Roll.setText("Yes");
					yesNo=true;
					
				}
				else{
					turnMessage.setText("landed on specialtyProperty");
					actionCount=0;
					coordinateVariable=0;
					turnUpdate();
				}
		
			}
			else if( (buyCount>-1) && (yesNo==true)){
				if(buyCount==0){
					turnMessage.setText(players.get(playersTurn).getName()+" does not have enough money to purchase this property, " +
				"if they do purchase it, they lose the game.");
				buyCount=-1;
				turnUpdate();
				}
				else if(buyCount>100){
					turnMessage.setText("the bot has purchased " + board.get(buyCount-100).getName() + " for " + board.get(buyCount-100).getCost());
					buyCount=-1;
					turnUpdate();
				}
				else if(buyCount>0){
				
				/*	Buy.setText("Yes");
					Roll.setText("No");
					yesNo=true;*/
				//move.buy(players.get(playersTurn));
				turnMessage.setText(players.get(playersTurn).getName() + " has purchased " +board.get(buyCount-1).getName()+ " for " + board.get(buyCount-1).getCost());
				buyCount=-1;
				turnUpdate();
				actionCount=0;
				}
			}
				
			}
		
			
			
		}
	
	
	public class buyHandle implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if(yesNo==true){
				turnMessage.setText("ait dont then");
				turnUpdate();
			
				buyCount=-1;
				actionCount=0;
				}
			else if(jailYesNo==true){
					players.get(playersTurn).changeBalance(-100);
					players.get(playersTurn).setJail(false);
					jailYesNo=false;
					turnMessage.setText("You have paid 100$ and are free!");
					turnUpdate();
				}
			}
		}
	
	
		
		
	//created a resetter to move all values back to deault

}