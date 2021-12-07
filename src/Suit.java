public enum Suit {
    SPADES(1),
    DIAMONDS(2),
    CLUBS(3),
    HEARTS(3);


    private final int suitVal;

    Suit(int suitVal){
        this.suitVal=suitVal;
    }

    public int getSuitVal(){
        return this.suitVal;
    }

}