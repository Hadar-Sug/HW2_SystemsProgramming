public class WarGame {
    Player playerOne;
    Player playerTwo;
    Deck tempDeck;
    public WarGame(String p1, String p2) {
        playerOne = new Player(p1);
        playerTwo = new Player(p2);
        tempDeck = new Deck (false);
    }

    public void initializeGame() {
        tempDeck = new Deck(true);
        tempDeck.shuffle();
        // Part 1 - Initializing the two playing decks for the players
        int leftInDeck = 51;
        int first = playerOne.getPlayerName().compareTo(playerTwo.getPlayerName());
        for (int i=0; i< 26; i++) {
            if (first > 0) {
                playerOne.addCard(tempDeck.getDeck()[leftInDeck--], true);
                playerTwo.addCard(tempDeck.getDeck()[leftInDeck--], true);
            }
            else {
                playerTwo.addCard(tempDeck.getDeck()[leftInDeck--], true);
                playerOne.addCard(tempDeck.getDeck()[leftInDeck--], true);
            }
        }
    }

    public String start(){
        while (!(playerOne.outOfCards() || playerTwo.outOfCards())) {
            //phase 1- compare top cards
            Card p1Card = playerOne.getPlayingDeck().getTopCard();
            Card p2Card = playerTwo.getPlayingDeck().getTopCard();
            int currRound = p1Card.compare(p2Card);
            if (currRound == -1) { //Player 2 wins this round
                playerTwo.addCard(p1Card, false);
                playerTwo.addCard(p2Card,false);
                playerOne.drawCard();
                playerTwo.drawCard();
            }

            if (currRound == 1) { //Player 1 wins this round
                playerOne.addCard(p1Card, false);
                playerOne.addCard(p2Card,false);
                playerOne.drawCard();
                playerTwo.drawCard();
            }

            else { //in case of a draw, we need a side function to help us manage the situation.
                boolean playerOneWin = draw();
                if(playerOneWin) { //player 1 won the draw situation
                    for (int i = 0; i < tempDeck.getIndex(); i++) {
                        playerOne.addCard(tempDeck.getTopCard(), false);
                        tempDeck.removeTopCard();
                    }
                }
                else { // player 2 won
                    for (int i = 0; i < tempDeck.getIndex(); i++) {
                        playerTwo.addCard(tempDeck.getTopCard(), false);
                        tempDeck.removeTopCard();
                    }
                }
            }
        }
        if(playerOne.outOfCards()) //Determining the winner
            return playerOne.getPlayerName();
        return playerTwo.getPlayerName();
    }

    /**
     * takes care of scenarios of draws
     * @return
     */
    private boolean draw() {
        int topCardPlayer1 = 0, topCardPlayer2 = 0;
        Card p1Card, p2Card;
        //Adding top 3 cards to the tempdeck
        //Unless a player has less than 3 cards, then just whatever he has remaining.
        //We save the value of the top card in topCardPlayerX to determine the winner
        for(int i=0; i<3; i++) {
            p1Card = playerOne.getPlayingDeck().getTopCard();
            p2Card = playerTwo.getPlayingDeck().getTopCard();
            if(p1Card != null) {
                tempDeck.addCard(p1Card);
                topCardPlayer1 = p1Card.getCardNumber();
                playerOne.drawCard();
            }
            if(p2Card != null) {
                tempDeck.addCard(p2Card);
                topCardPlayer2 = p2Card.getCardNumber();
                playerTwo.drawCard();
            }
        }
        if(topCardPlayer1>topCardPlayer2){
            return true;
        }
        if(topCardPlayer1<topCardPlayer2){
            return false;
        }
        else
            return draw();

    }
}
