public class Player {
    private String playerName;
    private Deck playingDeck;
    private Deck winningDeck;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Deck getPlayingDeck() { return playingDeck; }

    public Player(String playerName){
        setPlayerName(playerName);
        playingDeck = new Deck(false);
        winningDeck = new Deck(false);
    }

    public void addCard(Card card, boolean playingDeck){
        if (playingDeck){
            this.playingDeck.addCard(card);
        }else
            this.winningDeck.addCard(card);
    }

    public void drawCard(){
        this.playingDeck.removeTopCard();
    }

    public boolean outOfCards(){
        return  (playingDeck.isEmpty() && winningDeck.isEmpty());
    }

//    public boolean changeDeck() {
//        if(playingDeck.isEmpty())
//        {
//            boolean checkPlayingDeck = switchDecks();
//            if(checkPlayingDeck) {
//                return true;
//            }
//        }
//        else
//            playingDeck.removeTopCard();
//        return false;
//
//    }
//
//    private boolean switchDecks() {
//        playingDeck = winningDeck;
//        winningDeck = new Deck(false);
//        return playingDeck.isEmpty();
//    }

    public String toString(){
        return getPlayerName();
    }

}

