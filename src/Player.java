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
        if(playingDeck.isEmpty()) {
            playingDeck = winningDeck;
            playingDeck.shuffle();
            winningDeck = new Deck (false);
            if(!playingDeck.isEmpty()) {
                this.playingDeck.removeTopCard();
            }
        }
        else
            this.playingDeck.removeTopCard();
    }

    public boolean outOfCards(){
        return  (playingDeck.isEmpty() && winningDeck.isEmpty());
    }

    public String toString(){
        return getPlayerName();
    }

}

