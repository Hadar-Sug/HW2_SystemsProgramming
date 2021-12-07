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

//            else { //Draw-WAR
//                for(int i=0; i< 3; i++) {
//                tempDeck.addCard(p1Card);
//                tempDeck.addCard(p2Card);
//                boolean checkDeck = playerOne.changeDeck();
//                if(checkDeck) {
//                        break;
//                }
//                checkDeck = playerTwo.changeDeck();
//                if(checkDeck) {
//                        break;
//                }
//                }
            //if draw:
        //phase 2- whoever won gets cards to winning deck
        //phase 3- when player has empty playing deck, shuffle winning deck and make it his playing deck
        //

        }
        if(playerOne.outOfCards())
            return playerOne.getPlayerName();
        return playerTwo.getPlayerName();

    }

}
