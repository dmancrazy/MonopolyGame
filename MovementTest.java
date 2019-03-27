import static org.junit.Assert.*;

import java.beans.Transient;
import java.util.ArrayList;
import org.junit.Test;
import java.util.Scanner;

public class MovementTest {
MonopolyConfiguration config = new MonopolyConfiguration();
Player player = new Player("John", "1");
Jail jail = new Jail();
Banker banker = new Banker();
Square current = config.getBoard().get(0);
Action action = new Action();


@Test
public void test_PassedGo() {
    player.setPassedGo(true);
    action.passGoMoney(player);
    assertEquals(1700, player.getBalance());

}
@Test
public void test_Arrest() {
    jail.arrest(player, current, config);
    assertEquals(10, player.getPosition());


}


}