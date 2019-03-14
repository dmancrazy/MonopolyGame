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


public class boardloops extends Application{
	
	Stage monopoly;
	private ArrayList<Square> board;
	private Turn playerTurn = new Turn();
	
// Initialize array lists to allow iteration through each item 
	private ArrayList<Label> propertyNames = new ArrayList<>();
	private ArrayList<VBox> squares = new ArrayList<VBox>();
	private ArrayList<HBox> playerNameInputs = new ArrayList<HBox>();
	private ArrayList<TextField> prompts = new ArrayList<TextField>();
	private ArrayList<String> enteredNames = new ArrayList<String>();
	private ArrayList<Label> playerNumbers = new ArrayList<Label>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private static boolean quit = false;

// Initialize all names of each squares
	public boardloops() {
		board = BoardMaker.DefaultBoard();
	}

	
// Initialize the "menu" of the game
	Label menuTitle = new Label("Welcome To Monopoly!");
	Label gameTitle = new Label("MONOPOLY");
	Label playerName = new Label("Player 1 name: ");
	Label playerBalance = new Label("Player 1 balance: ");
	
	Button Buy = new Button("Buy");
	Button Roll = new Button("Roll");
	Button Done = new Button("Done");
	VBox menu = new VBox();
	HBox actions = new HBox();
	Button ok = new Button("Ok");
	
// Initialize the player cards that show up inside the board
	VBox pCard1 = new VBox();
	VBox pCard2 = new VBox();
	VBox pCard3 = new VBox();
	VBox pCard4 = new VBox();
	VBox botCard = new VBox();
	VBox nameInputs = new VBox();
	VBox turn = new VBox();
	Label playerTurnLabel = new Label("");

	
	
// start the window that the board will be on
public void start(Stage PrimaryStage) throws Exception{
		monopoly = PrimaryStage;
		PrimaryStage.setFullScreen(true);
		monopoly.setTitle("Monopoly");
	// adding the "menu" items
		menu.getChildren().addAll( menuTitle);
		Buy.setPrefSize(90, 50);
		Roll.setPrefSize(90, 50);
		actions.getChildren().addAll(Buy, Roll);
		turn.getChildren().add(playerTurnLabel);
		
		

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
			square.getChildren().addAll(title);
			square.setStyle("-fx-border-color: black");
			//ArrayList<String> playerNames = new ArrayList<String>();
			squares.add(square);
		}
		
	// This is the prompt for number of players
		HBox numPlayers = new HBox();
			ComboBox<Integer> numChoice = new ComboBox<>();
			numChoice.getItems().addAll(1, 2, 3, 4);
			numChoice.setPromptText("Pick the number of players:");
			numPlayers.getChildren().addAll(numChoice, Done);
			Done.setOnAction(e -> numChoice.getValue());
	
	// game title rotated in the center
		gameTitle.setRotate(-45);

	// The action that occurs after number of players is picked		
		Done.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent pickplayers) {
				
			// according to the # of players picked, it will prompt their names
				for (int i = 0 ; i < numChoice.getValue() ; i++) {
				//	for (int j = 0 ; j < numChoice.getValue() ; j++) {
						Player player = new Player("jolly green ranger" + (i), "" + (i+1));
						players.add(player);
						if (numChoice.getValue() == 1) {
							Player bot = new Player("Bot", "B");
							players.add(bot);
						}
					//}
					
					HBox input = new HBox();
						TextField playerName = new TextField("Player " + (numChoice.getValue()-(numChoice.getValue()-(i+1))));
						Button selectName = new Button();
						selectName.setPrefSize(20, 10);
						prompts.add(playerName);
						input.getChildren().addAll(playerName, selectName);
					playerNameInputs.add(input);
					//selectName.setOnAction(new NameSetter(numChoice.getValue(), players, playerName));
				}
				for (HBox prompts : playerNameInputs) {
					nameInputs.getChildren().add(prompts);
					nameInputs.setSpacing(2);
				}
		
				// this creates player cards according to the number of players picked above
				if (numChoice.getValue() == 1) {
					pCard1.getChildren().addAll(new Label(players.get(0).getName()), new Label("Player 1 balance: "));
					botCard.getChildren().addAll(new Label("Bot name: "), new Label("Bot balance: "));
	
			
				}
				else if (numChoice.getValue() == 2) {
					pCard1.getChildren().addAll(new Label("Player 1 name: "), new Label("Player 1 balance: "));
					pCard2.getChildren().addAll(new Label("Player 2 name: "), new Label("Player 2 balance: "));

				}
				else if (numChoice.getValue() == 3) {
					pCard1.getChildren().addAll(new Label("Player 1 name: "), new Label("Player 1 balance: "));
					pCard2.getChildren().addAll(new Label("Player 2 name: "), new Label("Player 2 balance: "));
					pCard3.getChildren().addAll(new Label("Player 3 name: "), new Label("Player 3 balance: "));

					
				}
				else if (numChoice.getValue() == 4) {
					pCard1.getChildren().addAll(new Label("Player 1 name: "), new Label("Player 1 balance: "));
					pCard2.getChildren().addAll(new Label("Player 2 name: "), new Label("Player 2 balance: "));
					pCard3.getChildren().addAll(new Label("Player 3 name: "), new Label("Player 3 balance: "));
					pCard4.getChildren().addAll(new Label("Player 4 name: "), new Label("Player 4 balance: "));

	
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
		});

		

	
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
		GridPane.setConstraints(menu, 13, 0);
		GridPane.setConstraints(actions, 13, 10);
		GridPane.setConstraints(gameTitle, 5, 5);
		GridPane.setConstraints(numPlayers, 13, 1);
		GridPane.setConstraints(pCard1, 1, 1);
		GridPane.setConstraints(pCard2, 9, 1);
		GridPane.setConstraints(botCard, 9, 1);
		GridPane.setConstraints(pCard3, 9, 9);
		GridPane.setConstraints(pCard4, 1, 9);
		GridPane.setConstraints(nameInputs, 13, 2);
		GridPane.setConstraints(turn, 13, 5);
		//GridPane.setConstraints(checknames, 13, 2);
	
	// adds all the named squares onto the board/gridpane
		gridBoard.getChildren().addAll(squares.get(0), squares.get(1), squares.get(2), squares.get(3), squares.get(4), squares.get(5), squares.get(6), squares.get(7), squares.get(8), squares.get(9),
				squares.get(10), squares.get(11), squares.get(12), squares.get(13), squares.get(14), squares.get(15), squares.get(16), squares.get(17), squares.get(18), squares.get(19), squares.get(20), squares.get(21), squares.get(22), squares.get(23),
				squares.get(24), squares.get(25), squares.get(26), squares.get(27), squares.get(28), squares.get(29), squares.get(30), squares.get(31), squares.get(32), squares.get(33), squares.get(34), squares.get(35), squares.get(36), squares.get(37),
				squares.get(38), squares.get(39), menu, actions, gameTitle, numPlayers, pCard1, pCard2, pCard3, pCard4, botCard, nameInputs, turn);
		nameInputs.toFront();
	// run the window with the board
		Scene scene = new Scene(gridBoard, 1300, 700);
		monopoly.setScene(scene);
		
		monopoly.show();
		
	}



}
