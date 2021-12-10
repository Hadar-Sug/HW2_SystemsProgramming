public class WarGame {
    Player playerOne;
    Player playerTwo;
    Deck tempDeck;
    private boolean gameOver = false;
    public WarGame(String p1, String p2) {
        playerOne = new Player(p1);
        playerTwo = new Player(p2);
    }

    /**
     * start the game
     * make a proper deck of cards and divide it up between the two players
     */
    public void initializeGame() {
        this.tempDeck = new Deck(true); // create a proper deck of cards
        this.tempDeck.shuffle(); // shuffle it (boogie woogie)
        // Part 1 - Initializing the two playing decks for the players
        int leftInDeck = Deck.getCardAmount() - 1;
        int first = playerOne.getPlayerName().compareTo(playerTwo.getPlayerName()); //lets see who goes first
        if (first > 0) { // so that player one goes first at all times
            Player temp = playerOne;
            playerOne = playerTwo;
            playerTwo = temp;
        }
        for (int i = 0; i < Deck.getCardAmount() / 2; i++) { //divide the main deck to the two players
            //remove card from the main deck
            playerOne.addCard(this.tempDeck.getDeck()[leftInDeck--], true);//player 1 gets a card
            tempDeck.removeTopCard();//remove card from the temp deck each time we deliver a card to a player
            playerTwo.addCard(this.tempDeck.getDeck()[leftInDeck--], true);//player 2 gets a card
            tempDeck.removeTopCard();
        }
    }

    /**
     * playing the actual game
     * @return name of the winner
     */
    public String start(){
        System.out.println("Initializing the game...");
        int roundNum = 1; // lets keep track of the rounds
        int topCardP1 = 0, topCardP2 = 0;
        initializeGame(); // start the game
        while (!(playerOne.outOfCards() || playerTwo.outOfCards())) { // we go until one player is out of cards
            System.out.println("------------------------- Round number " + roundNum + " -------------------------");
            roundNum++;
            //phase 1- compare top cards
            int currRound = playerOne.compete(playerTwo); //regular round
            if (currRound==Player.gameOver) // check if game over
                break;

            Card p1Card = playerOne.getPlayingDeck().getTopCard(); //p1Card holds the top card in playing deck
            Card p2Card = playerTwo.getPlayingDeck().getTopCard(); //p2Card holds the top card in playing deck
            if (currRound == -1) { //Player 2 wins this round
                System.out.println(playerTwo.getPlayerName() + " won");
                playerTwo.addCard(p2Card, false); //player 2 takes both cards
                playerTwo.addCard(p1Card,false);
                playerOne.drawCard(); // player 1 goes to next card in his pile
                playerTwo.drawCard(); // player 2 goes to next card in his pile
            }

            else if (currRound == 1) { //Player 1 wins this round
                System.out.println(playerOne.getPlayerName() + " won");
                playerOne.addCard(p2Card, false); // player 1 takes both cards
                playerOne.addCard(p1Card,false);
                playerOne.drawCard();  // player 1 goes to next card in his pile
                playerTwo.drawCard(); // player 2 goes to next card in his pile

            }

            else { // we have a draw ---> WAR
                //a War has begun so we put the top cards in the middle pile and then begin the War
                if(p1Card != null) {
                    tempDeck.addCard(p1Card);
                    topCardP1 = p1Card.getCardNumber();
                    playerOne.drawCard();
                }
                if(p2Card != null) {
                    tempDeck.addCard(p2Card);
                    topCardP2 = p2Card.getCardNumber();
                    playerTwo.drawCard();
                }
                boolean playerOneWin = draw(topCardP1, topCardP2); // this is where we go to war
                if(gameOver) // GAME OVER
                    break;
                int pileAmount = tempDeck.getIndex(); //amount of cards in middle pile
                if(playerOneWin) {//player 1 won the War/draw
                    for (int i = 0; i < pileAmount; i++) { // all the cards in the middle pile are in temp deck
                        playerOne.addCard(tempDeck.getTopCard(), false);//player 1 takes the cards
                        this.tempDeck.removeTopCard(); // remove the cards from the middle pile
                    }
                }
                else { // player 2 won
                    for (int i = 0; i < pileAmount; i++) { // all the cards in the middle pile are in temp deck
                        playerTwo.addCard(tempDeck.getTopCard(), false); //player 2 takes the cards
                        this.tempDeck.removeTopCard(); // remove the cards from the middle pile
                    }
                }
                playerOne.drawCard();  // player 1 goes to next card in his pile
                playerTwo.drawCard(); // player 2 goes to next card in his pile
            }
        }
        if(playerOne.outOfCards()) //Determining the winner
            return playerTwo.getPlayerName(); // player 2 won the game
        return playerOne.getPlayerName(); // player 1 won the game
    }

    /**
     * takes care of scenarios of draws
     * @return winner of the draw/war
     */
    private boolean draw(int topCardP1, int topCardP2) {
        System.out.println("Starting a war...");
        int topCardPlayer1 = topCardP1, topCardPlayer2 = topCardP2;
        Card p1Card=null, p2Card=null; //check this initializing doesnt mess things up
        //Adding top 3 cards to the middle pile (tempDeck)
        //Unless a player has less than 3 cards
        //then we add whatever he has remaining
        //We save the value of the top card in topCardPlayerX to determine the winner
        for(int i=0; i<2 ; i++) {

            p1Card = playerOne.getPlayingDeck().getTopCard(); //
            if (p1Card == null) {
                playerOne.switchDecks();
                p1Card = playerOne.getPlayingDeck().getTopCard();
            }
            System.out.println(playerOne.getPlayerName() + " drew a war card");

            p2Card = playerTwo.getPlayingDeck().getTopCard();
            if (p2Card == null) {
                playerTwo.switchDecks();
                p2Card = playerTwo.getPlayingDeck().getTopCard();
            }
            System.out.println(playerTwo.getPlayerName() + " drew a war card");
            if (p1Card != null) {
                tempDeck.addCard(p1Card); //player 1 adds a card in the pile
                topCardPlayer1 = p1Card.getCardNumber(); //lets save the value
                playerOne.drawCard(); // player 1 goes to next card in his pile
            }
            if (p2Card!=null){
                tempDeck.addCard(p2Card); //player 2 adds a card in the pile
                topCardPlayer2 = p2Card.getCardNumber(); //lets save the value
                playerTwo.drawCard(); // player 2 goes to next card in his pile
            }

        }
        int currWar = playerOne.compete(playerTwo);
        p1Card = playerOne.getPlayingDeck().getTopCard();
        p2Card = playerTwo.getPlayingDeck().getTopCard();
        topCardPlayer1 = p1Card.getCardNumber(); // save the values of the top cards in case of another draw
        topCardPlayer2 = p2Card.getCardNumber();
        tempDeck.addCard(p1Card);
        tempDeck.addCard(p2Card);//put the final 2 cards in temp deck

        // maybe need to make topCardPlayerX these cards. in case of another draw (?)

        if(currWar == 1){
            System.out.println(playerOne.getPlayerName() + " won the war");
            return true;
        }
        if(currWar == -1){
            System.out.println(playerTwo.getPlayerName() + " won the war");
            return false;
        }
        else if(currWar == 0){
            playerOne.drawCard();
            playerTwo.drawCard();
            return draw(topCardPlayer1, topCardPlayer2);
        }
        else {
            gameOver = true;
        }
        return false;

    }
}
