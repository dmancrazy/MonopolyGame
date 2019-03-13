import java.util.ArrayList;

public class ChanceCardDeck {

/**
 * This method creates a "deck of Chance Cards
 * Each card has a name, type, value (if "money"), and positionmoved (if "move").
 * @return This is the list with each card in it." 
 */   
public static ArrayList<ChanceCard> defaultDeck() {

    ArrayList<ChanceCard> deck = new ArrayList<ChanceCard>();
    deck.add(new ChanceCard("Salmonella", "money", -150, 0));
    deck.add(new ChanceCard("WinLottery", "money", 1000, 0));
    deck.add(new ChanceCard("LuckyPenny", "money", 1, 0));
    deck.add(new Chancecard("CaffeineRush", "move", 0, 3));
    deck.add(new ChanceCard("Mugged", "money", -50, 0));
    deck.add(new ChanceCard("ForgotMyWallet", "move", 0, -5));
    deck.add(new ChanceCard("YouAreAdopted", "money", -500));
    deck.add(new ChanceCard("LostDog", "money", 200) );
    
    return deck;
}
}