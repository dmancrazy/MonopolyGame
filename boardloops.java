import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import com.sun.prism.paint.Color;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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


public class boardloops extends Application{
	
	Stage monopoly;
	
// Initialize array lists to allow iteration through each item 
	ArrayList<Label> names = new ArrayList<>();
	ArrayList<VBox> squares = new ArrayList<VBox>();
	ArrayList<HBox> playerNameInputs = new ArrayList<HBox>();

// Initialize all names of each squares
	Label go = new Label("GO");
	Label b1 = new Label("Med Ave");
	Label b2 = new Label("Comm Chest 1");
	Label b3 = new Label("Baltic Ave");
	Label b4 = new Label("Income Tax");
	Label b5 = new Label("Reading Rail");
	Label b6 = new Label("Oriental Ave");
	Label b7 = new Label("Chance");
	Label b8 = new Label("Vermont");
	Label b9 = new Label("Conn Ave");
	Label b10 = new Label("Jail");
	Label b11 = new Label("St. C Place");
	Label b12 = new Label("Electric Co");
	Label b13 = new Label("States Ave");
	Label b14 = new Label("Virginia Ave");
	Label b15 = new Label("Penn Rail");
	Label b16 = new Label("St. J Place");
	Label b17 = new Label("Comm Chest 2");
	Label b18 = new Label("Tennessee Ave");
	Label b19 = new Label("New York Ave");
	Label b20 = new Label("Free Parking");
	Label b21 = new Label("Kentucky Ave");
	Label b22 = new Label("Chance");
	Label b23 = new Label("Indiana Ave");
	Label b24 = new Label("Illinois Ave");
	Label b25 = new Label("B & O Rail");
	Label b26 = new Label("Atlantic Ave");
	Label b27 = new Label("Ventnor Ave");
	Label b28 = new Label("Water Works");
	Label b29 = new Label("Marvin Gard");
	Label b30 = new Label("Go To Jail");
	Label b31 = new Label("Pacific Ave");
	Label b32 = new Label("NC Ave");
	Label b33 = new Label("Comm Chest 3");
	Label b34 = new Label("Penn Ave");
	Label b35 = new Label("Short Line");
	Label b36 = new Label("Chance");
	Label b37 = new Label("Park Place");
	Label b38 = new Label("Luxury Tax");
	Label b39 = new Label("BoardWalk");
	
// Initialize the "menu" of the game
	Label menuPlayers = new Label("Player Names : ");
	Label menuTitle = new Label("Welcome To Monopoly!");
	Label gameTitle = new Label("MONOPOLY");
	Label playerName = new Label("Player 1 name: ");
	Label playerBalance = new Label("Player 1 balance: ");
	
	Button Buy = new Button("Buy");
	Button Roll = new Button("Roll");
	Button Ok = new Button("Done");
	VBox menu = new VBox();
	HBox actions = new HBox();
	
// Initialize the player cards that show up inside the board
	VBox pCard1 = new VBox();
	VBox pCard2 = new VBox();
	VBox pCard3 = new VBox();
	VBox pCard4 = new VBox();
	VBox botCard = new VBox();
	VBox nameInputs = new VBox();

// Add all square names into its list for later iteration	
	List<Label> eachNames = Arrays.asList(go,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23
			,b24,b25,b26,b27,b28,b29,b30,b31,b32,b33,b34,b35,b36,b37,b38,b39);	
	
	
// start the window that the board will be on
public void start(Stage PrimaryStage) throws Exception{
		monopoly = PrimaryStage;
		PrimaryStage.setFullScreen(true);
		monopoly.setTitle("Monopoly");
	// adding the "menu" items
		names.addAll(eachNames);
		menu.getChildren().addAll( menuTitle, menuPlayers);
		Buy.setPrefSize(100, 50);
		Roll.setPrefSize(100, 50);
		actions.getChildren().addAll(Buy, Roll);
		

	// creates 40 squares and adds it to the list for later iteration	
		for (int i = 0 ; i < 40 ; i++) {
			VBox square = new VBox();
			square.setPrefSize(100, 100);	
			square.setStyle("-fx-border-color: black");
			ArrayList<String> playerNames = new ArrayList<String>();
			squares.add(square);
		}
		
	// This is the prompt for number of players
		HBox numPlayers = new HBox();
			ComboBox<Integer> numChoice = new ComboBox<>();
			numChoice.getItems().addAll(1, 2, 3, 4);
			numChoice.setPromptText("Pick the number of players:");
			numPlayers.getChildren().addAll(numChoice, Ok);
			Ok.setOnAction(e -> numChoice.getValue());
	
	// game title rotated in the center
		gameTitle.setRotate(-45);

	// The action that occurs after number of players is picked		
		Ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent pickplayers) {
			
			// this creates player cards according to the number of players picked above
				if (numChoice.getValue() == 1) {
					pCard1.getChildren().addAll(new Label("Player 1 name: "), new Label("Player 1 balance: "));
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
				
			// according to the # of players picked, it will prompt their names
				for (int i = 0 ; i < numChoice.getValue() ; i++) {
					HBox input = new HBox();
						Label playerName = new Label("Player " + (numChoice.getValue()-(numChoice.getValue()-(i+1))) + " name :");
						TextField player = new TextField();
						input.getChildren().addAll(playerName, player);
					playerNameInputs.add(input);
				}
				for (HBox prompts : playerNameInputs) {
					nameInputs.getChildren().add(prompts);
					nameInputs.setSpacing(2);
				}
			// the "Done" button is nullified to stop player name prompts to duplicate	
				Ok.setOnAction(null);
				
				
			}
		});


	// adds all names to their corresponding squares (indexed from their lists)
		squares.get(0).getChildren().add(names.get(0));
		squares.get(1).getChildren().add(names.get(1));
		squares.get(2).getChildren().add(names.get(2));
		squares.get(3).getChildren().add(names.get(3));
		squares.get(4).getChildren().add(names.get(4));
		squares.get(5).getChildren().add(names.get(5));
		squares.get(6).getChildren().add(names.get(6));
		squares.get(7).getChildren().add(names.get(7));
		squares.get(8).getChildren().add(names.get(8));
		squares.get(9).getChildren().add(names.get(9));
		squares.get(10).getChildren().add(names.get(10));
		squares.get(11).getChildren().add(names.get(11));
		squares.get(12).getChildren().add(names.get(12));
		squares.get(13).getChildren().add(names.get(13));
		squares.get(14).getChildren().add(names.get(14));
		squares.get(15).getChildren().add(names.get(15));
		squares.get(16).getChildren().add(names.get(16));
		squares.get(17).getChildren().add(names.get(17));
		squares.get(18).getChildren().add(names.get(18));
		squares.get(19).getChildren().add(names.get(19));
		squares.get(20).getChildren().add(names.get(20));
		squares.get(21).getChildren().add(names.get(21));
		squares.get(22).getChildren().add(names.get(22));
		squares.get(23).getChildren().add(names.get(23));
		squares.get(24).getChildren().add(names.get(24));
		squares.get(25).getChildren().add(names.get(25));
		squares.get(26).getChildren().add(names.get(26));
		squares.get(27).getChildren().add(names.get(27));
		squares.get(28).getChildren().add(names.get(28));
		squares.get(29).getChildren().add(names.get(29));
		squares.get(30).getChildren().add(names.get(30));
		squares.get(31).getChildren().add(names.get(31));
		squares.get(32).getChildren().add(names.get(32));
		squares.get(33).getChildren().add(names.get(33));
		squares.get(34).getChildren().add(names.get(34));
		squares.get(35).getChildren().add(names.get(35));
		squares.get(36).getChildren().add(names.get(36));
		squares.get(37).getChildren().add(names.get(37));
		squares.get(38).getChildren().add(names.get(38));
		squares.get(39).getChildren().add(names.get(39));
	
	// setup of the board
		GridPane board = new GridPane();
		board.setPadding(new Insets(10, 10, 10, 10));

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
	
	// adds all the named squares onto the board/gridpane
		board.getChildren().addAll(squares.get(0), squares.get(1), squares.get(2), squares.get(3), squares.get(4), squares.get(5), squares.get(6), squares.get(7), squares.get(8), squares.get(9),
				squares.get(10), squares.get(11), squares.get(12), squares.get(13), squares.get(14), squares.get(15), squares.get(16), squares.get(17), squares.get(18), squares.get(19), squares.get(20), squares.get(21), squares.get(22), squares.get(23),
				squares.get(24), squares.get(25), squares.get(26), squares.get(27), squares.get(28), squares.get(29), squares.get(30), squares.get(31), squares.get(32), squares.get(33), squares.get(34), squares.get(35), squares.get(36), squares.get(37),
				squares.get(38), squares.get(39), menu, actions, gameTitle, numPlayers, pCard1, pCard2, pCard3, pCard4, botCard, nameInputs);
	
	// run the window with the board
		Scene scene = new Scene(board, 1300, 700);
		monopoly.setScene(scene);
		
		monopoly.show();
}

}
