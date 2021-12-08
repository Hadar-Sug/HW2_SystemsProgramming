public class Card {
    private final int cardNumber;
    private final Suit cardSuit;
    protected static final String[] suitSymbols = {"♠","♦","♣","♥"};
    protected static final String[] cardNumbers = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};

    public Card(int cardNumber, Suit cardSuit) {
        this.cardNumber = cardNumber;
        this.cardSuit = cardSuit;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

    /**
     * compares this card with another one
     * @param other other card
     * @return 1 if this>other, -1 if other>this 0 if equal
     */
    public int compare(Card other){
        return Integer.compare(this.cardNumber, other.cardNumber);
    }

    @Override
    /**returns a string with the name of the card, i.e 4 of ♣*/
    public String toString() {
        return cardNumbers[getCardNumber()-1] + " of " + suitSymbols[getCardSuit().getSuitVal()-1];
    }
}