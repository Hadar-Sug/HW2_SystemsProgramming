import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class Deck {
    private static final int cardAmount = 52;
    private static final int suitCardAmount = 13;
    private Card[] deck;
    private int index;

    /**
     * iterate over suits and numbers and add corresponding card to deck array
     * @param condition if condition is true we build the deck, otherwise, nothing
     */
    public Deck(boolean condition){
        deck = new Card[cardAmount];
        if (condition){
            for (Suit suit: Suit.values()) {
                for (int card = 0; card < suitCardAmount ; card++) {
                    deck[((suit.getSuitVal()-1)*suitCardAmount)+card] = new Card(card+1,suit);
                }
            }
            index = 0;
        }
    }
    public int getIndex() { return index; }
    public Card[] getDeck(){
        return deck;
    }

    public Card getTopCard() { return deck[index];}

    /**
     *iterate over the deck until we reach an empty spot, that's the top of the deck. place card there
     * @param card the card that's being added
     */
    public void addCard(Card card) {
        for (int i = 0; i<cardAmount; i++) {
            if (deck[i] == null) {
                deck[i] = card;
                index++;
                break;
            }
        }
    }

    /**iterate through the deck till we reach an empty spot, the previous spot is the top of the deck*/
    public void removeTopCard(){
        int i=0;
        while (deck[i] != null && i < cardAmount) {
            i++;
        }
        deck[i] = null;
        index--;
    }

    public boolean isEmpty(){
        return deck[0] == null;
    }

    /**
     * iterate over the deck, choose a number, swap current position with random number
     */
    public void shuffle(){
        int topOfDeck = cardAmount;
        for (int i = 0; i < cardAmount; i++) { //need to find the top of the deck first
            if (deck[i] == null){
                topOfDeck = i;
                break;
            }
            for (int j = 0; j < topOfDeck; j++) {
                int rand = Main.rnd.nextInt();
                while ((rand>=topOfDeck) || (rand<0)) //lets make sure the random number is valid
                    rand = Main.rnd.nextInt();
                Collections.swap(Arrays.asList(deck),i,rand);
            }
        }
    }
}
