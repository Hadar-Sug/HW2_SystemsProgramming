public enum Suit {
    SPADES(1),
    DIAMONDS(2),
    CLUBS(3),
    HEARTS(4);

    private final int suitVal;

    /**
     * constructor for Suit
     * @param suitVal number value for specific suit
     */
    Suit(int suitVal){
        this.suitVal=suitVal;
    }

    /**
     * gets the value for corresponding suit
     * @return value for corresponding suit
     */
    public int getSuitVal(){
        return this.suitVal;
    }

}