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
 
public class BoardGameImageGUI {
 
    private Image boardImage;
    private ImageView imageView;
    private List<Player> players;
    private HBox mainBox;
    private List<Square> board = BoardMaker.DefaultBoard();
    private Pane pane;
    private Map<Square, Point2D> squares = new HashMap<>();
    private Map<Player, ImageView> playerSprites = new HashMap<>();
    private Messages messages = new Messages();
    private Movement movement = new Movement();
    private int pTurn = 0;
    private MonopolyActions actions = new MonopolyActions();
    private Square current;
    private Jail jail = new Jail();
    private EndGame endGame = new EndGame();
 
    public BoardGameImageGUI(List<Player> players) {
        this.players = players;
        this.boardImage = new Image("monopolyboard.jpg");
        this.imageView = new ImageView(boardImage);
 
        imageView.setFitWidth(1000);
        imageView.setFitHeight(1000);
 
        //9 on sides, 11 on tops
 
        //TOP LOOP Y = 50, X changes
 
        List<String> images = Arrays.asList("penguin.png", "thimble.png", "boot.png", "hat.png", "bot.png");
 
        int count = 0;
        for (Player player : players) {
            ImageView v = new ImageView(new Image(images.get(count)));
 
            if (player instanceof Bot) {
                v = new ImageView(new Image(images.get(4)));
            }
            this.playerSprites.put(player, v);
 
 
            count++;
 
 
        }
 
        //Start 1 in front of go!
        /*
            Big sqaures = 150px
            Small = 90
         */
 
        for (int i = 1; i < 10; i++) {
            //Bottom small squares!
            int x = 850 - (90 * (i - 1));
            int y = 950;
            Point2D point = new Point2D(x, y);
            this.squares.put(board.get(i), point);
        }
 
        for (int i = 11; i < 20; i++) {
            int x = 50;
            int y = 850 - (92 * (i - 11));
            Point2D point = new Point2D(x, y);
            this.squares.put(board.get(i), point);
        }
 
        for (int i = 21; i < 30; i++) {
            int x = 150 + (85 * (i - 21));
            int y = 50;
            Point2D point = new Point2D(x, y);
            this.squares.put(board.get(i), point);
        }
 
        for (int i = 31; i < 40; i++) {
            int x = 950;
            int y = 150 + (85 * (i - 31));
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
 
    public void updateProperty(Player player) {
 
    }
 
 
    public Scene getScene() {
        mainBox = new HBox();
        pane = new Pane();
        pane.getChildren().add(imageView);
 
        VBox menu = new VBox();
        HBox playerInfo = new HBox();
        Label mainMessages = new Label(messages.welcomeMessage(players));
        Button start = new Button("Start");
        start.setPrefSize(150, 80);
        Button rollButton = new Button("Roll");
        rollButton.setPrefSize(150, 80);
        Button yes = new Button("Yes");
        yes.setPrefSize(150, 80);
        Button no = new Button("No");
        no.setPrefSize(150, 80);
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
        HBox jailButtons = new HBox();
        jailButtons.getChildren().addAll(tryDoubles, payBail);
        Button cont = new Button("continue");
        cont.setPrefSize(180, 100);
        Button quit = new Button ("Quit");
        quit.setPrefSize(150, 80);
        Button restart = new Button ("Restart");
        restart.setPrefSize(150, 80);
        HBox finish = new HBox();
        finish.getChildren().addAll(restart, quit);
 
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

        rollButton.setOnAction(e -> {
            mainMessages.setText(movement.rollResults(players.get(pTurn)));
            menu.getChildren().remove(rollButton);
            menu.getChildren().add(ok);
            movePlayerOnBoard(players.get(pTurn));
        });
        ok.setOnAction(e -> {
            mainMessages.setText((messages.landingMessage(players.get(pTurn), board)) + "\n" + actions.passGoMoney(players.get(pTurn)) + "\n" +
                    actions.actionType(players.get(pTurn), board.get(players.get(pTurn).getPosition())));
            current = board.get(players.get(pTurn).getPosition());
            menu.getChildren().remove(ok);
            if (players.get(pTurn).getPosition() == 30) {
                playerSprites.get(players.get(pTurn)).setTranslateX(60);
                playerSprites.get(players.get(pTurn)).setTranslateY(920);
            }
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
        accept.setOnAction(e -> {
            mainMessages.setText(actions.actionConfirmation(players.get(pTurn), board, "y"));
            menu.getChildren().remove(accept);
            menu.getChildren().add(endTurn);
        });
 
        no.setOnAction(e -> {
            mainMessages.setText(actions.actionConfirmation(players.get(pTurn), board, "n"));
            menu.getChildren().remove(yesNo);
            menu.getChildren().add(endTurn);
        });
 
        yes.setOnAction(e -> {
            mainMessages.setText(actions.actionConfirmation(players.get(pTurn), board, "y"));
            menu.getChildren().remove(yesNo);
            menu.getChildren().add(endTurn);
        });
 
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

        cont.setOnAction(e -> {
            menu.getChildren().remove(cont);
            if (endGame.isGameOver(players)) {
                mainMessages.setText(endGame.victoryMessage(players));
                menu.getChildren().add(finish);
                return;
            }
            if (pTurn >= players.size()) {
                pTurn = 0;
            }
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

        tryDoubles.setOnAction(e -> {
            mainMessages.setText(jail.rollForJail(players.get(pTurn)));
            menu.getChildren().remove(jailButtons);
            menu.getChildren().add(endTurn);
        });
        payBail.setOnAction(e -> {
            jail.payBail(players.get(pTurn));
            mainMessages.setText(jail.payBailMessage(players.get(pTurn)));
            menu.getChildren().remove(jailButtons);
            menu.getChildren().add(endTurn);
        });
 
        for (Player player : players) {
            VBox playerVbox = new VBox();
            playerVbox.setPadding(new Insets(20));
 
            Label playerName = new Label(player.getName() + " $" + player.getBalance());
            playerVbox.getChildren().add(playerName);
            playerInfo.getChildren().add(playerVbox);
        }
 
        menu.getChildren().addAll(playerInfo, mainMessages, start);
        mainBox.getChildren().addAll(pane, menu);
 
        loadPlayersToBoard();
 
        Scene scene = new Scene(mainBox, 1600, 1000);
        return scene;
    }
 
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
}
