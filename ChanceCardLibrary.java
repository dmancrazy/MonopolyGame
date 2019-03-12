import java.util.ArrayList;

public class ChanceCardLibrary {

private int libraryLength = 7;
// Make an ArrayList containing the chance cards
ArrayList<Card> chanceCards = new ArrayList<Card>(libraryLength);

/**
 * Library for created chance cards.
 */

Card Salmonella = new Card("You get salmonella and need antibiotics.", "money", -150);

Card WinLottery = new Card("You won the lottery!", "money", 1000);

Card LuckyPenny = new Card("You found a lucky penny!", "money", 1);

Card CaffeineRush = new Card("You drink a coffee and need to move!", "move", 3);

Card Mugged = new Card("You are mugged.", "money", -50);

Card ForgotMyWallet = new Card("You forgot your wallet.", "move", -5);

Card YouAreAdopted = new Card("You adopt a child and they are expensive.", "money", -500);

Card LostDog = new Card("You return someone's lost dog and are rewarded.", "money", 200);


public void makeLibrary() {

//Add chance cards to the arraylist
chanceCards.add(0, Salmonella);
chanceCards.add(1, WinLottery);
chanceCards.add(2, LuckyPenny);
chanceCards.add(3, CaffeineRush);
chanceCards.add(4, Mugged);
chanceCards.add(5, ForgotMyWallet);
chanceCards.add(6, YouAreAdopted);
chanceCards.add(6, LostDog);
}


}