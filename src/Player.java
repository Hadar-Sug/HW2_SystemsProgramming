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

    /**
     * constructor, gets a name and builds two decks, playing deck and winning deck.
     * Deck is given false because we want to build empty decks
     * @param playerName is the players name
     */
    public Player(String playerName){
        setPlayerName(playerName);
        playingDeck = new Deck(false);
        winningDeck = new Deck(false);
    }

    /**
     * adds card to the playing deck based on condition. if condition is true we add it to the playing deck.
     * otherwise to the winning deck
     * @param card card that's being added
     * @param playingDeck is the condition mentioned above
     */
    public void addCard(Card card, boolean playingDeck){
        if (playingDeck){
            this.playingDeck.addCard(card); // add the card to playing deck
        }else
            this.winningDeck.addCard(card); // add the card to the winning deck
    }

    /**
     * removes the top card from the playing deck.
     * if we've reached the end of the pile
     * then we use switch decks with that method. see below
     */
    public void drawCard(){
        if(playingDeck.isEmpty()) { //first we check if we've got an empty pile
            switchDecks();// if so we switch decks
            if(!playingDeck.isEmpty()) { //if our new deck isnt empty we resume to remove the top card
                this.playingDeck.removeTopCard();
            }
        }
        else // all is normal so we just remove the top card
            this.playingDeck.removeTopCard();
    }

    /**
     * shuffle the winning deck and make it the playing deck.
     */
    public void switchDecks(){
        this.winningDeck.shuffle();  // lets shuffle (boogie woogie)
        this.playingDeck = this.winningDeck; // now playingDeck points to winningDeck
        this.winningDeck = new Deck (false); //reset winning deck to an empty deck (hence the false in the condition)
    }

    /**
     * we check if this player is completely out of cards
     * @return true if hes out of cards, false otherwise
     */
    public boolean outOfCards(){
        return  (playingDeck.isEmpty() && winningDeck.isEmpty());
    }

    /**
     * same as get players name
     * @return players name
     */
    public String toString(){
        return getPlayerName();
    }

    /**
     * this method is essentially a normal round between two players.
     * @param otherPlayer the player were competing against
     * @return 1 if this player won, -1 if other player won, 0 if tie(war)
     * gameOver if one of the players' cards is finished
     */
    public int compete(Player otherPlayer){

        Card p1Card = this.playingDeck.getTopCard();//lets get the top card
        if (p1Card==null){ // check if we have cards in playing deck
            this.switchDecks();// we don't so we'll switch decks
            p1Card = this.playingDeck.getTopCard(); // now lets get the top card again
            if(p1Card == null)// if we still don't have cards
                return gameOver; //that's GAME OVER
        }
        System.out.println(this.playerName + " drew " + p1Card.toString()); //print message

        //we do same as we did for player 1
        Card p2Card = otherPlayer.playingDeck.getTopCard();
        if (p2Card==null){
            otherPlayer.switchDecks();
            p2Card = otherPlayer.playingDeck.getTopCard();
            if(p2Card == null)
                return gameOver;
        }
        System.out.println(otherPlayer.playerName + " drew " + p2Card.toString());
        return p1Card.compare(p2Card); // now we compare the cards and continue

    }
}

