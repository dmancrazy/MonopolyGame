import java.util.ArrayList;

public class DeckMaker {

    public ArrayList<Card> chanceCardDeck() {
        ArrayList<Card> chanceDeck = new ArrayList<Card>();
        chanceDeck.add(new Card("Salmonella, lose $150", "money", -150, 0));
        chanceDeck.add(new Card("WinLottery, get $1000", "money", 1000, 0));
        chanceDeck.add(new Card("LuckyPenny, get $1", "money", 1, 0));
        chanceDeck.add(new Card("CaffeineRush, move 3", "move", 0, 3));
        chanceDeck.add(new Card("Mugged, lose $50", "money", -50, 0));
        chanceDeck.add(new Card("ForgotMyWallet, lose $5", "move", 0, -5));
        chanceDeck.add(new Card("YouAreAdopted, lose $500", "money", -500));
        chanceDeck.add(new Card("LostDog, get $200", "money", 200) );
        return chanceDeck;

    }

    public ArrayList<Card> communityDeck() {
        ArrayList<Card> communityDeck = new ArrayList<Card>();
        communityDeck.add(new CommunityChest("Bronze, get $100", 100));
        communityDeck.add(new CommunityChest("Silver, get $200", 200));
        communityDeck.add(new CommunityChest("Gold, get $300", 300));
        communityDeck.add(new CommunityChest("Diamond, get $400", 400));
        return communityDeck;
    }

}