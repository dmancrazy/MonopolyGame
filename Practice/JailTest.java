import static org.junit.Assert.*;
import org.junit.Test;
import GamePieces.*;
import Actions.*;

/**
 * This class tests whether the Jail class is working properly
 * @author Group 6
 * @version 1.0
 * @since 2019-04-11
 */
public class JailTest {
	Player p = new Human("John", "1");
	Player b = new Bot("Bot", "b");
	Jail jail = new Jail();

	// This method checks that the expected amount is paid when a human player pays bail
	@Test
	public void testPayBail_BalanceChange() {
		jail.payBail(p);
		assertEquals("Expected balance to be $1400", 1400, p.getBalance());
	}

	// This method checks that the human player is out of jail after he pays bail
	@Test
	public void testPayBail_setJail() {
		jail.payBail(p);
		assertEquals("Expected jail boolean to be false", false, p.getJail());
	}

	// This method checks that the expected amount is paid when a bot player pays bail
	@Test
	public void testPayBail_BalanceChangeBot() {
		jail.payBail(b);
		assertEquals("Expected balance to be $1400", 1400, b.getBalance());
	}

	// This method checks that the bot player is out of jail after he pays bail
	@Test
	public void testPayBail_setJailBot() {
		jail.payBail(b);
		assertEquals("Expected jail boolean to be false", false, b.getJail());
	}

	// This method checks that the expected message is returned after the player pays bail
	@Test
	public void testPayBailMessage() {
		jail.payBail(p);
		assertEquals("John is free and their new balance is $1400.", jail.payBailMessage(p));
	}

	// This method checks that a proper bail money is paid with added money in the balance
	@Test
	public void testPayBailMessage_AddedMoney() {
		p.changeBalance(1300);
		jail.payBail(p);
		assertEquals("John is free and their new balance is $2700.", jail.payBailMessage(p));
	}

	// This method checks that the expected message is returned
	// when the bot player pays bail money by default
	@Test
	public void testBotInJail_payBail() {
		assertEquals("Bot has freed themself from jail, by paying the bail amount of $100.\n", jail.botInJail(b));
	}

	// This method checks that the expected message is returned when the bot has stayed
	// in jail for 3 turns and is forced to pay bail.
	@Test
	public void testBotInJail_3JailCount() {
		b.changeBalance(-1401);
		for (int i = 0 ; i < 3 ; i++) {
			b.addJailCount(i);
		}
		assertEquals("The bot has spent 3 turns in jail and was forced to pay bail.\nTheir new balance is $0.\n", jail.botInJail(b));
	}

	// This method checks that the expected message is returned when the player has stayed
	// in jail for 3 turns and is forced to pay bail.
	@Test
	public void testForcedBail() {
		assertEquals("John has spent 3 turns in jail and was forced to pay bail.\nTheir new balance is $1400.", jail.forcedBail(p));
	}
}
