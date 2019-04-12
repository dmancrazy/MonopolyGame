import static org.junit.Assert.*;
import org.junit.Test;
import GamePieces.*;
import Actions.*;

/**
 * This class tests whether the Banker class is working properly
 * @author Group 6
 * @version 1.0
 * @since 2019-04-11
 */
public class BankerTest {
	Banker b = new Banker();
	Player p = new Human("John", "1");
	Player p2 = new Human("Tim", "2");
	Square s1 = new Square("Med Ave" , "property" , "brown" , 60 , 2, 50 , 30,1);
	Square s2 = new Square("Indiana Ave" , "property" , "red" , 220 , 18, 150, 110,23);

	// This method tests if the right amount of money is being withdrawn from a player's balance
	// when a cheap property is purchased
	@Test
	public void testBuyProperty_CheapPropertyCost() {
		b.buyProperty(p, s1);
		assertEquals("Expected player balance to be 1440", 1440, p.getBalance());
	}

	// This method tests if the right amount of money is being withdrawn from a player's balance
	// when an expensive property is purchased
	@Test
	public void testBuyProperty_ExpensivePropertyCost() {
		b.buyProperty(p, s2);
		assertEquals("Expected player balance to be 1280", 1280, p.getBalance());
	}

	// This method checks that the property properly owned after it's been bought
	@Test
	public void testBuyProperty_OwnedProperty() {
		b.buyProperty(p, s2);
		assertEquals("Expected property to be owned", true, s2.getOwned());
	}

	// This method checks that the bought property is properly owned by the player who bought it
	@Test
	public void testBuyProperty_PropertyOwner() {
		b.buyProperty(p, s2);
		assertEquals("Expected John to be the owner", "John", s2.getOwner().getName());
	}

	// This method checks that the bought property appears in the player's owned property list
	@Test
	public void testBuyProperty_PropertyList() {
		b.buyProperty(p, s1);
		assertEquals("Expected property list length to be 1", 1, p.getPropertiesOwned().size());
	}

	// This method checks that the bought property has the expected color
	@Test
	public void testBuyProperty_PropertyColor() {
		b.buyProperty(p, s1);
		assertEquals("Expected owned property color to be brown", "brown", p.getPropertiesOwned().get(0).getColor());
	}

	// This method checks that when a player lands on an owned cheap property, the right amount
	// is paid to the right person
	@Test
	public void testPayRent_CheapProperty() {
		s1.setOwner(p2);
		b.payRent(p, s1);
		assertEquals("Expected player balance to be 1498.", 1498, p.getBalance());
	}

	// This method checks that when a player lands on an owned expensive property,
	// the right amount is withdrawn as rent
	@Test
	public void testPayRent_ExpensiveProperty() {
		s2.setOwner(p2);
		b.payRent(p, s2);
		assertEquals("Expected player balance to be 1482.", 1482, p.getBalance());
	}

	// This method checks that the owner player recieves the right amount of money
	// after another player lands on his property
	@Test
	public void testPayRent_OwnerBalance() {
		b.buyProperty(p2, s2);
		b.payRent(p, s2);
		assertEquals("Expected owner balance to be 1298.", 1298, p2.getBalance());
	}

}
