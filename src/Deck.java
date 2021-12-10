public class Deck {
    private static final int cardAmount = 52;
    private static final int suitCardAmount = 13;
    private Card[] deck;
    private int index; //is always in the next empty slot

    /**
     * iterate over suits and numbers and add corresponding card to deck array
     *
     * @param condition if condition is true we build a proper deck, otherwise, nothing
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

    /**
     * gets index which is the next empty slot
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * gets this deck
     * @return the deck
     */
    public Card[] getDeck() {
        return deck;
    }

    /**
     * gets the top card in this deck
     * @return the top card
     */
    public Card getTopCard() {
        if(index != 0)
            return deck[index-1];
        else
            return null;
    }

    /**
     * gets the amount of cards in this deck
     * @return the amount of cards
     */
    public static int getCardAmount(){
        return cardAmount;
    }

    /**
     *index holds the place of the next empty slot so we place the card there, if valid
     * @param card the card that's being added
     */
    public void addCard(Card card) {
        if (index>=0 && index < cardAmount){
            deck[index] = card;
            index++;
        }
    }

    /**
     * index holds the place of the next empty slot so we remove the card in index-1
     */
    public void removeTopCard() {
        if (index!=0) {
            deck[index-1] = null; //removing -1
            index--;
        }
    }

    /**
     * checks if a deck is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return (index == 0);
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
