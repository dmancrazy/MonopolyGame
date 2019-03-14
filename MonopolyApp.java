import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class MonopolyApp extends Application {
	protected VBox root = new VBox();
	protected TextField p1Name = new TextField("Player 1's Name");
	protected TextField p2Name = new TextField("Player 2's Name");
	protected TextField p3Name = new TextField("Player 3's Name");
	protected TextField p4Name = new TextField("Player 4's Name");
	protected ArrayList<Player> players = new ArrayList<Player>();
	protected int totalPlayers;
	protected ComboBox<Integer> numChoice;
	protected HBox numPlayers = new HBox();
	protected Button startGame = new Button("Start Game");
	Stage window;
	Scene scene1, scene2;

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		scene1 = new Scene(root, 600, 400); 
		primaryStage.setTitle("Monopoly Setup");
		primaryStage.setScene(scene1);
		primaryStage.show();

		root.setAlignment(Pos.TOP_CENTER);
		Label welcomeMessage = new Label("Welcome to Monopoly " +
			"by Group 6, please select the\nnumber of players that " +
			"you would like to play with.");
		welcomeMessage.setPadding(new Insets (45,0,25,0));
		root.getChildren().add(welcomeMessage);

		numChoice = new ComboBox<Integer>();
		numChoice.getItems().addAll(1, 2, 3, 4);
		numChoice.setPromptText("Pick the number of players:");
		root.getChildren().add(numChoice);
		Button ok = new Button("OK");
		
		numPlayers.setAlignment(Pos.TOP_CENTER);
		numPlayers.getChildren().addAll(numChoice, ok);
		root.getChildren().add(numPlayers);

		ok.setOnAction(new nameEntries());
					
	}

	public class nameEntries implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			root.getChildren().remove(numPlayers);
			totalPlayers = numChoice.getValue();

			if (totalPlayers == 1) {
				root.getChildren().add(p1Name);
			}

			else if (totalPlayers == 2) {
				root.getChildren().add(p1Name);
				root.getChildren().add(p2Name);
			}

			else if (totalPlayers == 3) {
				root.getChildren().add(p1Name);
				root.getChildren().add(p2Name);
				root.getChildren().add(p3Name);
			}

			else if (totalPlayers == 4) {
				root.getChildren().add(p1Name);
				root.getChildren().add(p2Name);
				root.getChildren().add(p3Name);
				root.getChildren().add(p4Name);
			}
			root.getChildren().add(startGame);
			startGame.setOnAction(new gameStart());
		}
	}

	public class gameStart implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if (totalPlayers == 1) {
				players.add(new Player(p1Name.getText(), "1")); 
				players.add(new Player("bot", "B"));
			}
			else if (totalPlayers == 2) {
				players.add(new Player(p1Name.getText(), "1")); 
				players.add(new Player(p2Name.getText(), "2"));
			}

			else if (totalPlayers == 3) {
				players.add(new Player(p1Name.getText(), "1")); 
				players.add(new Player(p2Name.getText(), "2"));
				players.add(new Player(p3Name.getText(), "3"));
			}

			else if (totalPlayers == 4) {
				players.add(new Player(p1Name.getText(), "1")); 
				players.add(new Player(p2Name.getText(), "2"));
				players.add(new Player(p3Name.getText(), "3"));
				players.add(new Player(p4Name.getText(), "4"));

			}
			BoardGameGui monopolyGUI = new BoardGameGui(players);
			window.setScene(monopolyGUI.getScene2());
			


		}
	}

}