import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class Deck {
    private static final int cardAmount = 52;
    private static final int suitCardAmount = 13;
    public Card[] deck; //not sure if this should be here or in the constructor


    /**
     * iterate over suits and numbers and add corresponding card to deck array
     * @param condition if condition is true we buld the deck, otherwise, nothing
     */
    public Deck(boolean condition){
        deck = new Card[cardAmount];
        if (condition){
            for (Suit suit: Suit.values()) {
                for (int card = 0; card < suitCardAmount ; card++) {
                    deck[((suit.getSuitVal()-1)*suitCardAmount)+card] = new Card(card+1,suit);
                }
            }
        }
    }

    /**
     *iterate over the deck until we reach an empty spot, that's the top of the deck. place card there
     * @param card the card that's being added
     */
    public void addCard(Card card) {
        for (int i = 0; i<cardAmount; i++) {
            if (deck[i] == null) {
                deck[i] = card;
                break;
            }
        }
    }

    /**iterate through the deck till we reach an empty spot, the previous spot is the top of the deck*/
    public void removeTopCard(){
        for (int i = 0; i <cardAmount ; i++) {
            if (deck[i]==null){
                deck[i-1]=null;
                break;
            }
        }
    }

    public boolean isEmpty(){
        return deck[0] == null;
    }

    /**
     * iterate over the deck, choose a number, swap current position with random number
     */
    public void shuffle(){
        Random random = new Random();
        int topOfDeck=cardAmount;
        for (int i = 0; i < cardAmount; i++) { //need to find the top of the deck first
            if (deck[i] == null){
                topOfDeck = i;
                break;
            }
            for (int j = 0; j < topOfDeck; j++) {
                int rnd = random.nextInt();
                while (rnd>=topOfDeck) //lets make sure the random number is valid
                    rnd = random.nextInt();
                Collections.swap(Arrays.asList(deck),i,rnd);
            }
        }
    }
}
