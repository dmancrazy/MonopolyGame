import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Actions.*;
import GamePieces.*;
import Configuration.*;

/**
* This class creates the interactible GUI board that the game will be played on
* @author Group 6
* @version 1.0
* @since 2019-04-11
*/
public class BoardGameImageGUI {

    private Image boardImage;
    private ImageView imageView;
    private List<Player> players;
    private HBox mainBox;
    private List<Square> board = BoardMaker.DefaultBoard();
    private Pane pane;
    private Map<Square, Point2D> squares = new HashMap<>();
    private Map<Player, Image> playerLogos = new HashMap<>();
    private Map<Player, ImageView> playerSprites = new HashMap<>();
    private Messages messages = new Messages();
    private Movement movement = new Movement();
    private int pTurn = 0;
    private MonopolyActions actions = new MonopolyActions();
    private Square current;
    private Jail jail = new Jail();
    private EndGame endGame = new EndGame();
    private List<String> images = Arrays.asList("penguin.png", "thimble.png", "boot.png", "hat.png", "bot.png");

    /**
     * This is the constructor for this class
     * @param players The list of players in the game
     */
    public BoardGameImageGUI(List<Player> players) {
        this.players = players;
        this.boardImage = new Image("monopolyboard.jpg");
        this.imageView = new ImageView(boardImage);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(1000);

        //Calling a function to give each player a logo as type image.
        makePlayerLogos();
        //Changing those logos to image views so that they can appear on the board.
        makePlayerSprites();
        //Assigning x and y coordinates to all the squares on the board.
        createCoordinateSystem();
    }

    /**
     * This method adds all the images of player icons onto the first square on board
     */
    public void loadPlayersToBoard() {
        Point2D go = squares.get(board.get(0));
        int count = 0;
        for (ImageView img : playerSprites.values()) {
            double x = go.getX();
            double y = go.getY();
            int offset = 30;
            if (count == 0) {

            } else if (count == 1) {
                x = x + offset;
            } else if (count == 2) {
                y = y + offset;
            } else {
                x = x + offset;
                y = y + offset;
            }
            img.setTranslateX(x);
            img.setTranslateY(y);

            pane.getChildren().add(img);
            count++;
        }
    }

    /*
    public void updateProperty(Player player) {
    } */

    /**
     * This method creates the scene
     * @return The scene which will have the board and all necessary buttons
     */
    public Scene getScene() {
        //Creating the main view of the GUI the board on the left and menu on the right
        mainBox = new HBox();
        pane = new Pane();
        pane.getChildren().add(imageView);
        VBox menu = new VBox();

        //Creating the Nodes for the menu
        HBox playerInfo = new HBox();
        Label mainMessages = new Label(messages.welcomeMessage(players));
        Button start = new Button("Start");
        start.setPrefSize(150, 80);

        //Creates a label for each player showing their name and what logo represents them
        for (Player player : players) {
            VBox playerVbox = new VBox();
            playerVbox.setPadding(new Insets(20));

            Label playerName = new Label(player.getName());
            playerName.setGraphic(new ImageView(playerLogos.get(player)));
            playerVbox.getChildren().add(playerName);
            playerInfo.getChildren().add(playerVbox);
        }

        //Adds the starting nodes to the menu and adds menu and board to main layout
        menu.getChildren().addAll(playerInfo, mainMessages, start);
        mainBox.getChildren().addAll(pane, menu);

        //Loads all of the players to the go square
        loadPlayersToBoard();

        //Creating all the different buttons that will be used in our GUI
        Button rollButton = new Button("Roll");
        rollButton.setPrefSize(150, 80);
        Button yes = new Button("Yes");
        yes.setPrefSize(150, 80);
        Button no = new Button("No");
        no.setPrefSize(150, 80);
        //Creating an HBox to put the yes and no buttons side by side
        HBox yesNo = new HBox();
        yesNo.getChildren().addAll(no, yes);
        Button ok = new Button("OK");
        ok.setPrefSize(180, 100);
        Button accept = new Button("Accept");
        accept.setPrefSize(180, 100);
        Button endTurn = new Button("End Turn");
        endTurn.setPrefSize(180, 100);
        Button payBail = new Button("Pay Bail");
        payBail.setPrefSize(150, 80);
        Button tryDoubles = new Button("Try Doubles");
        tryDoubles.setPrefSize(150, 80);
        //Creating an HBox to put the two jail options side by side
        HBox jailButtons = new HBox();
        jailButtons.getChildren().addAll(tryDoubles, payBail);
        Button cont = new Button("continue");
        cont.setPrefSize(180, 100);
        Button quit = new Button ("Quit");
        quit.setPrefSize(150, 80);

        //The start button displays whos turn it is and allows them to roll, unless it's the bot, then they roll automatically
        start.setOnAction(e -> {
            menu.getChildren().remove(start);
            if (players.get(pTurn) instanceof Bot) {
                mainMessages.setText(movement.rollResults(players.get(pTurn)));
                menu.getChildren().add(ok);
                movePlayerOnBoard(players.get(pTurn));
            }
            else {
                menu.getChildren().add(rollButton);
                mainMessages.setText(messages.turnStartMessage(players.get(pTurn), board));
            }
        });
        //The roll button rolls the dice for the player then they recieve a message of where they landed it also moves their icon
        rollButton.setOnAction(e -> {
            mainMessages.setText(movement.rollResults(players.get(pTurn)));
            menu.getChildren().remove(rollButton);
            menu.getChildren().add(ok);
            movePlayerOnBoard(players.get(pTurn));
        });
        //The ok button informs the player of the action that will occur due to the position they landed on.
        ok.setOnAction(e -> {
            mainMessages.setText((messages.landingMessage(players.get(pTurn), board)) + "\n" + actions.passGoMoney(players.get(pTurn)) + "\n" +
                    actions.actionType(players.get(pTurn), board.get(players.get(pTurn).getPosition())));
            current = board.get(players.get(pTurn).getPosition());
            menu.getChildren().remove(ok);
            //If the player lands on the go to jails space, they get sent to jail
            if (players.get(pTurn).getPosition() == 30) {
                playerSprites.get(players.get(pTurn)).setTranslateX(60);
                playerSprites.get(players.get(pTurn)).setTranslateY(920);
            }
            //If the player lands on a property they are given the option to buy it, if the property is not owned. Otherwise they must pay rent or do nothing if they themselves own it
            if (current.getType().equals("property") || current.getType().equals("rail") || current.getName().equals("Income Tax")) {
                if (!current.getOwned()) {
                    if (players.get(pTurn) instanceof Bot) {
                        mainMessages.setText(actions.actionConfirmation(players.get(pTurn), board, "y"));
                        menu.getChildren().add(endTurn);
                    }
                    else {
                        menu.getChildren().add(yesNo);
                    }
                }
                else {
                    menu.getChildren().add(accept);
                }
            }
            else {
                menu.getChildren().add(accept);
            }
        });
        //The accept button proceeds to carry on the action that the player must go through
        accept.setOnAction(e -> {
            mainMessages.setText(actions.actionConfirmation(players.get(pTurn), board, "y"));
            menu.getChildren().remove(accept);
            menu.getChildren().add(endTurn);
        });
        //The no button says no to any optional events such as purchasing properties or rails and paying $200 tax instead of 10% 
        no.setOnAction(e -> {
            mainMessages.setText(actions.actionConfirmation(players.get(pTurn), board, "n"));
            menu.getChildren().remove(yesNo);
            menu.getChildren().add(endTurn);
        });
        //The yes button says yes to any optional events such as purchasing properties or rails and paying $200 tax instead of 10% 
        yes.setOnAction(e -> {
            mainMessages.setText(actions.actionConfirmation(players.get(pTurn), board, "y"));
            menu.getChildren().remove(yesNo);
            menu.getChildren().add(endTurn);
        });
        //The endTurn button checks to see if the player lost this round, if so they are eliminated, else it increments to the next player.
        endTurn.setOnAction(e -> {
            menu.getChildren().remove(endTurn);
            if (endGame.isLoser(players.get(pTurn))) {
                mainMessages.setText(endGame.eliminationMessage(players.get(pTurn), players));
            }
            else {
                pTurn++;
            }
            menu.getChildren().add(cont);
        });
        //The continue button checks to see if the game has ended, which then you see a final message
        //else they carry out a normal turn.
        cont.setOnAction(e -> {
            menu.getChildren().remove(cont);
            if (endGame.isGameOver(players)) {
                mainMessages.setText(endGame.victoryMessage(players));
                menu.getChildren().add(quit);
                return;
            }
            //If the game is not over it checks to make sure that the index is still in bounds after eliminating losers or incrementing from the end turn. It loops back to player 1 otherwise.
            if (pTurn >= players.size()) {
                pTurn = 0;
            }
            //Then it goes to the next players turn. If that player is in jail they proceed with the jail actions
            if (players.get(pTurn).getJail()) {
                if (players.get(pTurn).getJailCount() < 3) {
                    if (players.get(pTurn) instanceof Bot) {
                        mainMessages.setText(jail.botInJail(players.get(pTurn)));
                        menu.getChildren().add(endTurn);
                    }
                    else {
                        mainMessages.setText(messages.jailStartMessage(players.get(pTurn)));
                        menu.getChildren().add(jailButtons);
                    }
                }
                else {
                    mainMessages.setText(jail.forcedBail(players.get(pTurn)) + "\n" + messages.turnStartMessage(players.get(pTurn), board));
                    menu.getChildren().add(endTurn);
                }
            }
            //Else they carry out a normal turn.
            else {
                if (players.get(pTurn) instanceof Bot) {
                    mainMessages.setText(movement.rollResults(players.get(pTurn)));
                    menu.getChildren().add(ok);
                    movePlayerOnBoard(players.get(pTurn));
                }
                else {
                    mainMessages.setText(messages.turnStartMessage(players.get(pTurn), board));
                    menu.getChildren().add(rollButton);
                }
            }
        });
        //When a player is in jail, they can attempt to roll doubles to try and escape from jail by pressing the try doubles button
        tryDoubles.setOnAction(e -> {
            mainMessages.setText(jail.rollForJail(players.get(pTurn)));
            menu.getChildren().remove(jailButtons);
            menu.getChildren().add(endTurn);
        });
        //When a player is in jail, they can choose to pay bail of $100 to get a regular turn next turn by pressing the payBail button
        payBail.setOnAction(e -> {
            jail.payBail(players.get(pTurn));
            mainMessages.setText(jail.payBailMessage(players.get(pTurn)));
            menu.getChildren().remove(jailButtons);
            menu.getChildren().add(endTurn);
        });
        //Quit button exits the game
        quit.setOnAction( e-> {
            System.exit(0);
        });

        Scene scene = new Scene(mainBox, 1600, 1000);
        return scene;
    }

    /**
     * This method moves a player by translating the player icon images across the board image
     * @param player The player whose icon is being moved
     */
    public void movePlayerOnBoard(Player player) {
        movement.moveToken(player, board);
        Square newSpace = board.get(player.getPosition());

        Point2D newPoint = squares.get(newSpace);
        double x = newPoint.getX();
        double y = newPoint.getY();

        int playerCount = newSpace.getOccupants().size();

        int xOffset = 20;
        int yOffset = 20;

        if (playerCount == 1) {

        } else if (playerCount == 2) {
            x = x + xOffset;
        } else if (playerCount == 3) {
            y = y + yOffset;
        } else {
            x = x + xOffset;
            y = y + yOffset;
        }

        ImageView view = playerSprites.get(player);

        view.setTranslateX(x);
        view.setTranslateY(y);
    }

    /**
     * This method assigns images to each player using a hashmap
     */
    public void makePlayerLogos() {
        int count = 0;
        for (Player player : players) {
            Image i = new Image(images.get(count));
            if (player instanceof Bot) {
                i = new Image(images.get(4));
            }
            playerLogos.put(player, i);
            count++;
        }
    }
    /**
     * This method assigns imageviews to each player using a hashmap so they can apear on the board
     */
    public void makePlayerSprites() {
        int count = 0;
        for (Player player : players) {
            ImageView v = new ImageView(playerLogos.get(player));
            this.playerSprites.put(player, v);
            count++;
        }
    }
    /**
     * This method assigns coordinates to every square on the board using a hashmap
     */
    public void createCoordinateSystem() {
        //Creating a Hashmap for the coordinates of each square
        for (int i = 1; i < 10; i++) {
            //Bottom small squares!
            double x = 850 - (90 * (i - 1));
            double y = 950;
            Point2D point = new Point2D(x, y);
            this.squares.put(board.get(i), point);
        }

        for (int i = 11; i < 20; i++) {
            double x = 50;
            double y = 850 - (92 * (i - 11));
            Point2D point = new Point2D(x, y);
            this.squares.put(board.get(i), point);
        }

        for (int i = 21; i < 30; i++) {
            double x = 150 + (85 * (i - 21));
            double y = 50;
            Point2D point = new Point2D(x, y);
            this.squares.put(board.get(i), point);
        }

        for (int i = 31; i < 40; i++) {
            double x = 950;
            double y = 150 + (85 * (i - 31));
            Point2D point = new Point2D(x, y);
            this.squares.put(board.get(i), point);
        }

        //LOAD BIG SQUARES

        Square go = board.get(0);
        Square jail = board.get(10);
        Square parking = board.get(20);
        Square goToJail = board.get(30);

        Point2D loc1 = new Point2D(925, 930);
        this.squares.put(go, loc1);

        Point2D loc2 = new Point2D(50, 950);
        this.squares.put(jail, loc2);

        Point2D loc3 = new Point2D(50, 50);
        this.squares.put(parking, loc3);

        Point2D loc4 = new Point2D(950, 50);
        this.squares.put(goToJail, loc4);
    }
}
