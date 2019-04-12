import static org.junit.Assert.*;
import GamePieces.*;
import java.beans.Transient;
import java.util.ArrayList;
import org.junit.Test;

/**
 * This class checks if each method in Player class functions as expected
 * @author Group 6
 * @version 1.0
 * @since 2019-04-11
 */
public class PlayerTest {

    // Construct a new Player with name "John", and icon "1"
    Player player = new Human("John", "1");

    // This method makes sure balance changes after balance is changed by 0
    @Test
    public void test_changeBalance0() {
        player.changeBalance(0);
        assertEquals(1500, player.getBalance());
    }

    // This method checks if a certain amount is properly subtracted from the balance
    @Test
    public void test_changeBalanceNeg() {
        player.changeBalance(-150);
        assertEquals(1350, player.getBalance());
    }

    // This method checks if a certain amount is properly added to the balance
    @Test
    public void test_changeBalancePos() {
        player.changeBalance(150);
        assertEquals(1650, player.getBalance());
    }

    // This method checks if the balance goes to 0 if an amount more
    //  than the initial balance is subtracted
    @Test
    public void test_changeBalanceBankrupt() {
        player.changeBalance(-1600);
        assertEquals(0, player.getBalance());
    }

    // This method checks that the name has been properly set to a player
    @Test
    public void test_playerName() {
        assertEquals("John", player.getName());
    }

    // This method checks that the player icon has been properly set to a player
    @Test
    public void test_playerIcon() {
        assertEquals("1", player.getIcon());
    }

    // This method checks that the default player position is 0
    @Test
    public void test_playerPosition() {
        assertEquals(0, player.getPosition());
    }

    // This method checks if a player's position changes properly
    @Test
    public void test_newSpotNorm() {
        player.changePosition(15);
        assertEquals(15, player.getPosition());
    }

    // This method checks if a player's position changes properly
    @Test
    public void test_newSpot39() {
        player.changePosition(39);
        assertEquals(39, player.getPosition());
    }

    // This method checks if a player's position resets after the player
    // goes completely around the board
    @Test
    public void test_newSpotGr39() {
        player.changePosition(41);
        assertEquals(1, player.getPosition());
    }


}
