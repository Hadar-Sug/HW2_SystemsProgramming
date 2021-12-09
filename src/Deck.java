import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class Deck {
    public static final int cardAmount = 52;
    private static final int suitCardAmount = 13;
    private Card[] deck;
    private int index; //is always in the next empty slot

    /**
     * iterate over suits and numbers and add corresponding card to deck array
     *
     * @param condition if condition is true we build the deck, otherwise, nothing
     */
    public Deck(boolean condition) {
        deck = new Card[cardAmount];
        if (condition) {
            for (Suit suit : Suit.values()) {
                for (int card = 0; card < suitCardAmount; card++) {
                    deck[((suit.getSuitVal() - 1) * suitCardAmount) + card] = new Card(card + 1, suit);
                }
            }
            index = cardAmount;
        }
        else
            index = 0;
    }

    public int getIndex() {
        return index;
    }

    public Card[] getDeck() {
        return deck;
    }

    public Card getTopCard() {
        if(index != 0)
            return deck[index-1];
        else
            return null;
    }

    public int getCardAmount(){
        return cardAmount;
    }
    /**
     * iterate over the deck until we reach an empty spot, that's the top of the deck. place card there
     *
     * @param card the card that's being added
     */
    public void addCard(Card card) {
        if (index>=0 && index <cardAmount){
            deck[index++] = card;
        }
    }

    /**
     * iterate through the deck till we reach an empty spot, the previous spot is the top of the deck
     */
    public void removeTopCard() {
        if (index!=0) {
            deck[index-1] = null; //removing -1
            index--;
        }
        /*int i = 0;
        while (i < cardAmount && deck[i] != null) {
            i++;
        }
        if(i == cardAmount)
            deck[i-1] = null;
        else
            deck[i] = null;*/ //not sure whats going on here

    }

    public boolean isEmpty() {
        return index == 0;
    }

    /**
     * iterate over the deck, choose a number, swap current position with random number
     */
    public void shuffle() {
        for (int j = 0; j < index; j++) {
            int rand = Main.rnd.nextInt(index);
            Card temp;
            temp = deck[j];
            deck[j] = deck[rand];
            deck[rand] = temp;
        }
    }
}
