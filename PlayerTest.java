import static org.junit.Assert.*;

import java.beans.Transient;
import java.util.ArrayList;
import org.junit.Test;
import java.util.Scanner;


public class PlayerTest {

Player player = new Human("John", "1");



@Test
public void test_changeBalance0() {
player.changeBalance(0);
assertEquals(1500, player.getBalance());
}

@Test
public void test_changeBalanceNeg() {
player.changeBalance(-150);
assertEquals(1350, player.getBalance());

}

@Test
public void test_changeBalancePos() {
player.changeBalance(150);
assertEquals(1650, player.getBalance());

}

@Test
public void test_changeBalanceBankrupt() {
player.changeBalance(-1600);
assertEquals(0, player.getBalance());

}

@Test
public void test_playerName() {
assertEquals("John", player.getName());

}

@Test
public void test_playerIcon() {
assertEquals("1", player.getIcon());

}
@Test
public void test_playerPosition() {
assertEquals(0, player.getPosition());

}

@Test
public void test_newSpotNorm() {
player.changePosition(15);
assertEquals(15, player.getPosition());
}

@Test
public void test_newSpot39() {
player.changePosition(39);
assertEquals(39, player.getPosition());


}
@Test
public void test_newSpotGr39() {
player.changePosition(41);
assertEquals(1, player.getPosition());

}


}