public class WarGame {
    Player playerOne;
    Player playerTwo;
    Deck tempDeck;
    private boolean gameOver = false;
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
            if (first < 0) {
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
        System.out.println("Initializing the game...");
        int n = 1;
        initializeGame();
        while (!(playerOne.outOfCards() || playerTwo.outOfCards())) {
            System.out.println("------------------------- Round number " + n + " -------------------------");
            n++;
            //phase 1- compare top cards

            int currRound = playerOne.compete(playerTwo);
            if (currRound==Player.gameOver)
                break;

            Card p1Card = playerOne.getPlayingDeck().getTopCard();
            Card p2Card = playerTwo.getPlayingDeck().getTopCard();
            if (currRound == -1) { //Player 2 wins this round
                System.out.println(playerTwo.getPlayerName() + " won");
                playerTwo.addCard(p1Card, false);
                playerTwo.addCard(p2Card,false);
                playerOne.drawCard();
                playerTwo.drawCard();
            }

            else if (currRound == 1) { //Player 1 wins this round
                System.out.println(playerOne.getPlayerName() + " won");
                playerOne.addCard(p1Card, false);
                playerOne.addCard(p2Card,false);
                playerOne.drawCard();
                playerTwo.drawCard();

            }

            else { //in case of a draw, we need a side function to help us manage the situations
                System.out.println("Starting a war...");
                boolean playerOneWin = draw();
                if(gameOver)
                    break;
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
        Card p1Card=null, p2Card=null; //chech this initializing doesnt mess things up
        //Adding top 3 cards to the tempdeck
        //Unless a player has less than 3 cards, then just whatever he has remaining.
        //We save the value of the top card in topCardPlayerX to determine the winner
        // something wrong with loop, doesnt draw cards properly
        for(int i=0; i<2 ; i++) {


            p1Card = playerOne.getPlayingDeck().getTopCard();
            System.out.println(playerOne.getPlayerName() + " drew a war card");

            p2Card = playerTwo.getPlayingDeck().getTopCard();
            System.out.println(playerTwo.getPlayerName() + " drew a war card");
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
        //fixes problem we had
        tempDeck.addCard(p1Card);
        topCardPlayer1 = p1Card.getCardNumber();
        playerOne.drawCard();
        tempDeck.addCard(p2Card);
        topCardPlayer2 = p2Card.getCardNumber();
        playerTwo.drawCard();
        int currWar = playerOne.compete(playerTwo);

        if(currWar == 1){
            System.out.println(playerOne.getPlayerName() + " won the war");
            return true;
        }
        if(currWar == -1){
            System.out.println(playerTwo.getPlayerName() + " won the war");
            return false;
        }
        else if(currWar == 0)
            return draw();
        else {
            gameOver = true;
        }
        return false;

    }
}
