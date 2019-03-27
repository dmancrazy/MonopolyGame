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
	//private Turn playerTurn = new Turn();
	private int playersTurn=0;
// Initialize array lists to allow iteration through each item 
	private ArrayList<Label> propertyNames = new ArrayList<>();
	private ArrayList<VBox> squares = new ArrayList<VBox>();
	private ArrayList<Label> playerNumbers = new ArrayList<Label>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private static boolean quit = false;
	private MonopolyConfiguration config= new MonopolyConfiguration();
	private Movement move = new Movement(config);
// Initialize all names of each squares
	public BoardGameGui(ArrayList<Player> nPlayers) {
		this.board = BoardMaker.DefaultBoard();
		this.players = nPlayers;
	}

	
// Initialize the "menu" of the game
	Label menuTitle = new Label("Welcome To Monopoly!");
	Label gameTitle = new Label("MONOPOLY");
	
	Button Buy = new Button("Buy");
	Button Roll = new Button("Roll");
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
		actions.getChildren().addAll(Buy, Roll);
		turn.getChildren().add(playerTurnLabel);
		Roll.setOnAction(new rollHandle());
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
		for (int i = 0 ; i < 10 ; i++) {
			GridPane.setConstraints(squares.get(i), 10-i, 10);
		}
		
		for (int j = 0 ; j < 10 ; j++) {
			GridPane.setConstraints(squares.get(j + 10), 0, 10 - j);
		}
		
		for (int k = 0 ; k < 10 ; k++) {
			GridPane.setConstraints(squares.get(k + 20), k, 0);
		}
		
		for (int n = 0 ; n < 10 ; n++) {
			GridPane.setConstraints(squares.get(n + 30), 10, n);
		}
		
		// positioning of the "menu items" and player cards/ name inputs
		GridPane.setConstraints(actions, 13, 10);
		GridPane.setConstraints(gameTitle, 5, 5);
		//GridPane.setConstraints(numPlayers, 13, 1);
		GridPane.setConstraints(pCard1, 1, 1);
		GridPane.setConstraints(pCard2, 9, 1);
		GridPane.setConstraints(botCard, 9, 1);
		GridPane.setConstraints(pCard3, 9, 9);
		GridPane.setConstraints(pCard4, 1, 9);
		GridPane.setConstraints(turn, 13, 5);
		GridPane.setConstraints(turnMessage,13,4);
		//GridPane.setConstraints(checknames, 13, 2);
	
	// adds all the named squares onto the board/gridpane
		gridBoard.getChildren().addAll(squares.get(0), squares.get(1), squares.get(2), squares.get(3), squares.get(4), squares.get(5), squares.get(6), squares.get(7), squares.get(8), squares.get(9),
				squares.get(10), squares.get(11), squares.get(12), squares.get(13), squares.get(14), squares.get(15), squares.get(16), squares.get(17), squares.get(18), squares.get(19), squares.get(20), squares.get(21), squares.get(22), squares.get(23),
				squares.get(24), squares.get(25), squares.get(26), squares.get(27), squares.get(28), squares.get(29), squares.get(30), squares.get(31), squares.get(32), squares.get(33), squares.get(34), squares.get(35), squares.get(36), squares.get(37),
				squares.get(38), squares.get(39),  actions, gameTitle, pCard1, pCard2, pCard3, pCard4, botCard, turn, turnMessage);
	// run the window with the board
		Scene scene2 = new Scene(gridBoard, 1600, 1000);
		return scene2;
	}


	// The action that occurs after number of players is picked		
		public void placePlayers() {
			// this creates player cards according to the number of players picked above
			if (players.size() == 1) {
				pCard1.getChildren().addAll(new Label(players.get(0).getName()), new Label("balance: " + players.get(0).getBalance()));

			}
			else if (players.size() == 2) {
				pCard1.getChildren().addAll(new Label(players.get(0).getName()), new Label("balance: " + players.get(0).getBalance()));
				pCard2.getChildren().addAll(new Label(players.get(1).getName()), new Label("balance: " + players.get(1).getBalance()));

			}
			else if (players.size() == 3) {
				pCard1.getChildren().addAll(new Label(players.get(0).getName()), new Label("balance: " + players.get(0).getBalance()));
				pCard2.getChildren().addAll(new Label(players.get(1).getName()), new Label("balance: " + players.get(1).getBalance()));
				pCard3.getChildren().addAll(new Label(players.get(2).getName()), new Label("balance: " + players.get(2).getBalance()));

			}
			else if (players.size() == 4) {
				pCard1.getChildren().addAll(new Label(players.get(0).getName()), new Label("balance: " + players.get(0).getBalance()));
				pCard2.getChildren().addAll(new Label(players.get(1).getName()), new Label("balance: " + players.get(1).getBalance()));
				pCard3.getChildren().addAll(new Label(players.get(2).getName()), new Label("balance: " + players.get(2).getBalance()));
				pCard4.getChildren().addAll(new Label(players.get(3).getName()), new Label("balance: " + players.get(3).getBalance()));
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
						" and their balance is $" + players.get(i).getBalance());
					
			}

			
		}
	public class rollHandle implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
	
			roll1=dice.roll();
			roll2=dice.roll();
			turnMessage.setText("You rolled a " + roll1 + " and a " + roll2 + " which equals " +(roll1+roll2));
			
			playersTurn++;
			if(playersTurn==players.size()){
				playersTurn=0;
			}
		}
	
	}

}
