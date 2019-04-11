import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MonopolyApp extends Application {
	// Initialize all necessary nodes that will be added to the menu window
	private VBox root = new VBox();
	private TextField p1Name = new TextField("Player 1's Name");
	private TextField p2Name = new TextField("Player 2's Name");
	private TextField p3Name = new TextField("Player 3's Name");
	private TextField p4Name = new TextField("Player 4's Name");
	private Button botButton = new Button("Add Bot");
	private Label botConfirmed = new Label("A bot has been added");
	private ArrayList<Player> players = new ArrayList<Player>();
	private int totalPlayers;
	private ComboBox<Integer> numChoice;
	private HBox numPlayers = new HBox();
	private Button startGame = new Button("Start Game");
	private Stage window;
	private Scene scene1; 

	/**
	 * This method makes the window and adds all initialized nodes
	 * @param primaryStage The "stage" everything will occur and will be added to
	 */
	@Override
	public void start(Stage primaryStage) {
		// Set title and size of the initialized window
		window = primaryStage;
		scene1 = new Scene(root, 600, 400); 
		primaryStage.setTitle("Monopoly");
		primaryStage.setScene(scene1);
		primaryStage.show();

		// Welcome message
		root.setAlignment(Pos.TOP_CENTER);
		Label welcomeMessage = new Label("Welcome to Monopoly " +
			"by Group 6, please select the\nnumber of players that " +
			"you would like to play with.");
		welcomeMessage.setPadding(new Insets (45,0,25,0));
		root.getChildren().add(welcomeMessage);
		
		// The prompts for and takes the number of players
		numChoice = new ComboBox<Integer>();
		numChoice.getItems().addAll(1, 2, 3, 4);
		numChoice.setPromptText("Pick the number of players:");
		Button ok = new Button("OK");
		
		numPlayers.setAlignment(Pos.TOP_CENTER);
		numPlayers.getChildren().addAll(numChoice, ok);
		root.getChildren().add(numPlayers);
		
		// The "ok" button is given an action depending on the number of player picked
		ok.setOnAction(new nameEntries());
					
	}
	/*
	 * This class 
	 */
	public class nameEntries implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
		try {
			root.getChildren().remove(numPlayers);
			totalPlayers = numChoice.getValue();
		}
		catch (NullPointerException n) {
			Label error = new Label("You clicked ok without selecting a number of players, so I assume you play alone.");
			totalPlayers = 1;
			root.getChildren().add(error);
		}
			if (totalPlayers == 1) {
				root.getChildren().add(p1Name);
			}

			else if (totalPlayers == 2) {
				root.getChildren().add(p1Name);
				root.getChildren().add(p2Name);
				root.getChildren().add(botButton);
			}

			else if (totalPlayers == 3) {
				root.getChildren().add(p1Name);
				root.getChildren().add(p2Name);
				root.getChildren().add(p3Name);
				root.getChildren().add(botButton);
			}

			else if (totalPlayers == 4) {
				root.getChildren().add(p1Name);
				root.getChildren().add(p2Name);
				root.getChildren().add(p3Name);
				root.getChildren().add(p4Name);
			}
			root.getChildren().add(startGame);
			botButton.setOnAction(new AddBot());
			startGame.setOnAction(new GameStart());
		}
	}

	public class AddBot implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			players.add(new Bot("bot", "B"));
			root.getChildren().remove(botButton);
			root.getChildren().add(botConfirmed);
		}
	}

	public class GameStart implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if (totalPlayers == 1) {
				players.add(new Human(p1Name.getText(), "1")); 
				players.add(new Bot("bot", "B"));
			}
			else if (totalPlayers == 2) {
				players.add(new Human(p1Name.getText(), "1")); 
				players.add(new Human(p2Name.getText(), "2"));
			}

			else if (totalPlayers == 3) {
				players.add(new Human(p1Name.getText(), "1")); 
				players.add(new Human(p2Name.getText(), "2"));
				players.add(new Human(p3Name.getText(), "3"));
			}

			else if (totalPlayers == 4) {
				players.add(new Human(p1Name.getText(), "1")); 
				players.add(new Human(p2Name.getText(), "2"));
				players.add(new Human(p3Name.getText(), "3"));
				players.add(new Human(p4Name.getText(), "4"));
			}
			//BoardGameGui monopolyGUI = new BoardGameGui(players);
			BoardGameImageGUI imageGUI = new BoardGameImageGUI(players);
			window.setScene(imageGUI.getScene());
		}
	}

}