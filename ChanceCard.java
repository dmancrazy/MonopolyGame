import java.util.ArrayList;
import java.util.Random;

public class ChanceCard {
/**
 * This Class provides the skeleto for the creation of chance cards.
 */

    public String name;
    public String action;
    public int value;
    
    public ChanceCard(String aName, String anAction, int aValue) {
        this.name = aName;
        this.action = anAction;
        this.value = aValue;
    }

    //update this number whenever a new card is added and the library
//length increases
private int libraryLength = 7;
private Random rand = new Random();
private ArrayList<ChanceCard> chanceCards = new ArrayList<ChanceCard>();

public ChanceCard drawChanceCard(){
    System.out.println("You have drawn a Chance Card!");
    int randCardIndex = rand.nextInt(chanceCards.size());
    ChanceCard drawnCard = chanceCards.get(randCardIndex);
    return drawnCard;
}

/**
 * Library for created chance cards.
 */

ChanceCard Salmonella = new ChanceCard("You get salmonella and need antibiotics.", "money", -150);

ChanceCard WinLottery = new ChanceCard("You won the lottery!", "money", 1000);

ChanceCard LuckyPenny = new ChanceCard("You found a lucky penny!", "money", 1);

ChanceCard CaffeineRush = new ChanceCard("You drink a coffee and need to move!", "move", 3);

ChanceCard Mugged = new ChanceCard("You are mugged.", "money", -50);

ChanceCard ForgotMyWallet = new ChanceCard("You forgot your wallet.", "move", -5);

ChanceCard YouAreAdopted = new ChanceCard("You adopt a child and they are expensive.", "money", -500);

ChanceCard LostDog = new ChanceCard("You return someone's lost dog and are rewarded.", "money", 200);


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

    //This method uses the drawn chance card, executing the actions
    //depending on the card type and description.
    public void useChanceCard() {
        if (action.equals("money")) {
            // Update user's money with card.value
        } 
        else if (action.equals("move")) {
            // Update user's position with card.value
        } 
        else if (action.equals("prison")) {
            // Update user's position; they go to prison
        } 
       // else if (action.equals("")) {
            // and so on...
        }
    }
}
