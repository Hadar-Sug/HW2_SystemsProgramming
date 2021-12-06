public enum Suit {
    SPADES(0),
    DIAMONDS(1),
    CLUBS(2),
    HEARTS(3);


    private final int suitVal;

    Suit(int suitVal){
        this.suitVal=suitVal;
    }

    public int getSuitVal(){
        return this.suitVal;
    }

}