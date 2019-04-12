import static org.junit.Assert.*;
import GamePieces.*;
import org.junit.Test;

public class SquareTest {

	// Construct 5 different squares for testing
	Square cheapProperty = new Square("Med Ave", "property", "brown", 60, 2, 50, 30, 1);
	Square expensiveProperty = new Square("Illinois Ave" , "property" , "red" , 240 , 20, 150, 120,24);
	Square rail = new Square("Reading Rail" , "rail" , "r-1" , 200 , 25, 0, 100,5);
	Square goToJail = new Square("Go To Jail" , "goToJail" , "jail-send" , 0 , 0, 0, 0,30);
	Square jail = new Square("Jail" , "jail" , "j-1" , 0 , 0, 0, 0,10);

	// This method tests if square name getter method works as expected for a property
	@Test
	public void testGetName_CheapPropertyName() {
		assertEquals("Expected name to be Med Ave", "Med Ave", cheapProperty.getName());
	}

	//  This method tests if square name getter method works as expected for a railroad
	@Test
	public void testGetName_RailName() {
		assertEquals("Expected name to be Reading Rail", "Reading Rail", rail.getName());
	}

	// This method tests if square name getter method works as expected for a jail square
	@Test
	public void testGetName_GoToJailName() {
		assertEquals("Expected name to be Go To Jail", "Go To Jail", goToJail.getName());
	}

	// This method tests if square type getter method works as expected for a property
	@Test
	public void testGetType_Property() {
		assertEquals("Expected type to be of property", "property", cheapProperty.getType());
	}

	//  This method tests if square type getter method works as expected for a railroad
	@Test
	public void testGetType_Rail() {
		assertEquals("Expected type to be of rail", "rail", rail.getType());
	}

	// This method tests if square type getter method works as expected for a jail square
	@Test
	public void testGetType_GoToJail() {
		assertEquals("Expected type to be goToJail", "goToJail", goToJail.getType());
	}

	// This method tests if square color getter method works as expected for a property
	@Test
	public void testGetColor_PropertyBrown() {
		assertEquals("Expected color to be brown", "brown", cheapProperty.getColor());
	}

	// This method tests if square color getter method works as expected for another property
	@Test
	public void testGetColor_PropertyRed() {
		assertEquals("Expected color to be red", "red", expensiveProperty.getColor());
	}

	// This method tests if square color getter method works as expected for a railroad square
	@Test
	public void testGetColor_Rail() {
		assertEquals("Expected color to be r-1", "r-1", rail.getColor());
	}

	// This method tests if a property cost getter method works as expected for cheap property
	@Test
	public void testGetCost_CheapProperty() {
		assertEquals("Expected cost to be $60", 60, cheapProperty.getCost());
	}

	// This method tests if a square cost getter method works as expected for expensive property
	@Test
	public void testGetCost_ExpensiveProperty() {
		assertEquals("Expected cost to be $240", 240, expensiveProperty.getCost());
	}

	// This method tests if a square cost getter method works as expected for a railroad
	@Test
	public void testGetCost_Rail() {
		assertEquals("Expected cost to be 200", 200, rail.getCost());
	}

	// This method tests if a property rent getter method works as expected for cheap property
	@Test
	public void testGetRent_CheapPropertyNoHouse() {
		assertEquals("Expected rent to be $2", 2, cheapProperty.getRent());
	}

	// This method tests if a square rent getter method works as expected for expensive property
	@Test
	public void testGetRent_ExpensivePropertyNoHouse() {
		assertEquals("Expected rent to be $20", 20, expensiveProperty.getRent());
	}

	// This method tests if a square rent getter method works as expected for a railroad
	@Test
	public void testGetRent_Rail() {
		assertEquals("Expected rent to be $25", 25, rail.getRent());
	}

	// This method checks if the rent multiplies properly depending on number of houses on the cheap property/square
	@Test
	public void testGetRent_2HousesOnCheapProperty() {
		cheapProperty.setHouseAmount(2);
		assertEquals("Expected rent to be $20", 20, cheapProperty.getRent());
	}

	// This method checks if the rent multiplies properly depending on number of houses on the expensive property/square
	@Test
	public void testGetRent_3HousesOnExpensiveProperty() {
		expensiveProperty.setHouseAmount(3);
		assertEquals("Expected rent to be $400", 400, expensiveProperty.getRent());
	}

	// This method checks if the rent multiplies properly depending on number of houses on another cheap property/square
	@Test
	public void testGetRent_4HousesOnCheapProperty() {
		cheapProperty.setHouseAmount(4);
		assertEquals("Expected rent to be $60", 60, cheapProperty.getRent());
	}

	// This method tests if a house cost getter method works as expected for cheap property
	@Test
	public void testGetHousePrice_CheapProperty() {
		assertEquals("Expected house price to be $50", 50, cheapProperty.getHousePrice());
	}

	// This method tests if a house cost getter method works as expected for expensive property
	@Test
	public void testGetHousePrice_ExpensiveProperty() {
		assertEquals("Expected house price to be $150", 150, expensiveProperty.getHousePrice());
	}

	// This method tests if the property ID getter method works as expected
	@Test
	public void testGetID_FirstProperty() {
		assertEquals("Expected square ID to be 1", 1, cheapProperty.getId());
	}

	// This method tests if the property ID getter method works as expected for another square
	@Test
	public void testGetID_TwentyFourthSquare() {
		assertEquals("Expected square ID to be 24", 24, expensiveProperty.getId());
	}

	// This method tests if setting a cheap property as owned works as expected
	@Test
	public void testGetOwned_Owned() {
		cheapProperty.setOwned(true);
		assertEquals("Expected owned to be true", true, cheapProperty.getOwned());
	}

	// This method tests if setting a cheap property as not owned works as expected
	@Test
	public void testGetOwned_NotOwned() {
		assertEquals("Expected owned to be false", false, cheapProperty.getOwned());
	}

	// This method tests if setting a player as the owner of a square works as expected
	@Test
	public void testSquareOwner() {
		Player p = new Human("John", "1");
		cheapProperty.setOwner(p);
		assertEquals("Expected owner name to be John", "John", cheapProperty.getOwner().getName());
	}

	// This method tests if the toString method adds the proper amount of spacing by checking the length of the string
	@Test
	public void testToString_PropertyStringSize() {
		assertEquals("Expected Mediterranean Ave to be a string of length 14", 14, cheapProperty.toString().length());
	}

	// This method tests if the toString method adds the proper amount of spacing so that the length is 13
	@Test
	public void testToString_JailStringView() {
		assertEquals("Expected Jail on the board to be centered using the toString method", "     Jail     ", jail.toString());
	}

	// This method tests if the toString method adds the proper amount of spacing to the property names so that its length is 13
	@Test
	public void testToString_ExpensivePropertyStringView() {
		assertEquals("Expected Illinois Ave on the board to be centered using the toString method", " Illinois Ave ", expensiveProperty.toString());
	}

	// This method tests when a single player is added to jail, the length of the list of players in jail is 1
	@Test
	public void testGetOccupants_JailWithOneOccupant() {
		Player p = new Human("John", "1");
		jail.getOccupants().add(p.getIcon());
		assertEquals("Expected the property to contain 1 occupants", 1, jail.getOccupants().size());
	}

	// This method tests when two players are added to jail, the length of the list of players in jail is 2
	@Test
	public void testGetOccupants_PropertyWithTwoOccupants() {
		Player p = new Human("John", "1");
		Player b = new Bot("bot", "B");
		expensiveProperty.getOccupants().add(p.getIcon());
		expensiveProperty.getOccupants().add(b.getIcon());
		assertEquals("Expected the property to contain 2 occupants", 2, expensiveProperty.getOccupants().size());
	}

	// This method tests that when no players are occupying a cheap property, its listed occupants list is an empty string
	@Test
	public void testListedOccupants_EmptyProperty() {
		assertEquals("Expected listed occupants one Med Ave to be empty ", "", cheapProperty.listedOccupants());
	}

	// This method tests that when no players are occupying a jail square, its listed occupants list is an empty string
	@Test
	public void testListedOccupants_EmptyJail() {
		assertEquals("Expected listed occupants on jail square to be empty ", "", jail.listedOccupants());
	}

	// This method tests that the proper human player icon (with a space on its left)
	// is in the jail's listed occupants after the player is added to it
	@Test
	public void testListedOccupants_HumanAtJail() {
		Player p = new Human("John", "1");
		jail.getOccupants().add(p.getIcon());
		assertEquals("Expected listed occupants contain player 1 with a space on the left", " 1", jail.listedOccupants());
	}

	// This method tests that the proper bot player icon (with a space on its left)
	// is in the jail's listed occupants after the bot is added to it
	@Test
	public void testListedOccupants_BotAtJail() {
		Player p = new Bot("bot", "B");
		jail.getOccupants().add(p.getIcon());
		assertEquals("Expected listed occupants contain the bot logo B with 1 space on the left.", " B", jail.listedOccupants());
	}

	// This method tests that all four added players are on the cheap property's list of occupants
	@Test
	public void testListedOccupants_FourPlayersOnPropertyShowingIcons() {
		Player j = new Human("John", "1");
		Player a = new Human("Amy", "2");
		Player m = new Human("Mark", "3");
		Player d = new Human("Dick", "4");
		cheapProperty.getOccupants().add(j.getIcon());
		cheapProperty.getOccupants().add(a.getIcon());
		cheapProperty.getOccupants().add(m.getIcon());
		cheapProperty.getOccupants().add(d.getIcon());
		assertEquals("Expected listed occupants numbers 1 to 4 for the four players", " 1 2 3 4", cheapProperty.listedOccupants());
	}

	// This method tests that the human and bot players are on the railroad's list of occupants
	@Test
	public void testListedOccupants_1Human1BotOnRail() {
		Player j = new Human("John", "1");
		Player b = new Bot("bot","B");
		rail.getOccupants().add(j.getIcon());
		rail.getOccupants().add(b.getIcon());
		assertEquals("Expected listed occupants to be player 1's and the bot's logo ", " 1 B", rail.listedOccupants());
	}
}
