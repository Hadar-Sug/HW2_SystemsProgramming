public class Player {
    private String playerName;
    private Deck playingDeck;
    private Deck winningDeck;
    public static int gameOver = 666;

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
            switchDecks();
            if(!playingDeck.isEmpty()) {
                this.playingDeck.removeTopCard();
            }
        }
        else
            this.playingDeck.removeTopCard();
    }

    public void switchDecks(){
        playingDeck = winningDeck;
        playingDeck.shuffle();
        winningDeck = new Deck (false);
    }
    public boolean outOfCards(){
        return  (playingDeck.isEmpty() && winningDeck.isEmpty());
    }

    public String toString(){
        return getPlayerName();
    }

    public int compete(Player otherPlayer){
        Card p1Card = this.playingDeck.getTopCard();
        if (p1Card==null){
            this.switchDecks();
            p1Card = this.playingDeck.getTopCard();
            if(p1Card == null)
                return gameOver;
        }
        System.out.println(this.playerName + " drew " + p1Card.toString());
        Card p2Card = otherPlayer.playingDeck.getTopCard();
        if (p2Card==null){
            otherPlayer.switchDecks();
            p2Card = otherPlayer.playingDeck.getTopCard();
            if(p2Card == null)
                return gameOver;
        }
        System.out.println(otherPlayer.playerName + " drew " + p2Card.toString());
        return p1Card.compare(p2Card);

    }
}

