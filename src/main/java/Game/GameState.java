package Game;
import Graphics.GamePanel;
import Objects.Card;
import Objects.CardEnum;
import Objects.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameState {
    private static int amtPlayers;
    private static ArrayList<Card> deck;
    private static ArrayList<Card> cardOnTable;
    public static Player[] players;
    private static Player currentPlayer;
    private GamePanel gamePanel;
    private static int currentPlayerIndex;
    private static int turn;

    public GameState(int amtPlayers) {
        this.amtPlayers = amtPlayers; //set amount of players
        deck = initCards(); //initialize deck
        cardOnTable = new ArrayList<>(); //initialize card on table
        cardOnTable.add(deck.remove(0)); //add first card face down to the table
        players = new Player[amtPlayers]; //initialize players
        for (int i = 0; i < amtPlayers; i++) {
            players[i] = new Player(i); //create player
            players[i].addCard(deck.remove(0)); //deal card to player
        }
        gamePanel = new GamePanel();
        currentPlayer = players[0]; //set current player to player 1
        currentPlayerIndex = 0;
        turn = 1;
    }

    public static void newRound(){

    }

    public static void nextTurn() {
        if (currentPlayerIndex == amtPlayers - 1) {
            currentPlayerIndex = 0;
            currentPlayer = players[currentPlayerIndex];
            turn++;
        } else {
            currentPlayerIndex++;
            currentPlayer = players[currentPlayerIndex];
        }
        if(checkPlayersOut()){
            JOptionPane.showMessageDialog(null, "All other players are out.");
            players[getLastPlayer()].setPoints(players[getLastPlayer()].getPoints() + 1);
            if(checkSpy() != null){
                checkSpy().setPoints(checkSpy().getPoints() + 1);
                JOptionPane.showMessageDialog(null, "Player " + (checkSpy().getNumber()+1) + " got a spy token!");
            }
            GamePanel.updateCards();
            resetGame();
            return;
        }
        else if(currentPlayer.isOut()) {
            nextTurn();
        }
        if(currentPlayer.isProtected()){
            currentPlayer.setProtected(false);
        }
        GamePanel.updateCards();
    }

    public static Card drawCard(){
        if(GameState.getDeck().size() == 0){
            //compare cards for winner
            players[getRoundWinnerByCardValue()].setPoints(players[getRoundWinnerByCardValue()].getPoints() + 1);
            JOptionPane.showMessageDialog(null, "No more cards in deck");
            JOptionPane.showMessageDialog(null, "Player " + (getRoundWinnerByCardValue()+1) + " wins the round!");
            if(checkSpy() != null){
                checkSpy().setPoints(checkSpy().getPoints() + 1);
                JOptionPane.showMessageDialog(null, "Player " + (checkSpy().getNumber()+1) + " got a spy token!");
            }
            GamePanel.updateCards();
            //reset deck
            resetGame();
            return null;
        }
        return GameState.getDeck().remove(0);
    }

    private static int getRoundWinnerByCardValue() {
        int[] pointValues = new int[3];
        for (int i = 0; i < 3; i++) {
            pointValues[i] = players[i].getPlayerHand().get(0).getCardType().getValue();
        }
        int max = pointValues[0];
        int maxIndex = 0;
        for (int i = 1; i < pointValues.length; i++) {
            if (pointValues[i] > max) {
                max = pointValues[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void checkGameEnd(){
        int[] pointValues = new int[3];
        for (int i = 0; i < 3; i++) {
            pointValues[i] = players[i].getPoints();
        }
        int max = pointValues[0];
        int maxIndex = 0;
        for (int i = 1; i < pointValues.length; i++) {
            if (pointValues[i] > max) {
                max = pointValues[i];
                maxIndex = i;
            }
        }
        if(max >= 5) {
            JOptionPane.showMessageDialog(null, "Player " + (maxIndex+1) + " wins the game!");
            GamePanel.disableButtons();
            GamePanel.showWinner(maxIndex);
        }
    }

    public static boolean checkPlayersOut(){
        int count = 0;
        for (int i = 0; i < amtPlayers; i++) {
            if (players[i].isOut()){
                count++;
            }
        }
        return count == amtPlayers - 1;
    }

    public static int getLastPlayer(){
        int lastPlayer = 0;
        for (int i = 0; i < amtPlayers; i++) {
            if (!players[i].isOut()){
                lastPlayer = i;
            }
        }
        return lastPlayer;
    }

    public static void resetGame() {
        System.out.println("Resetting game");
        for (int i = 0; i < 3; i++) {
            players[i].setOut(false);
            players[i].setProtected(false);
            players[i].setPlayerHand(new ArrayList<>());
            players[i].setDiscardCard(new ArrayList<>());
        }
        GameState.deck = new ArrayList<>();
        System.out.println(GameState.deck.size());
        GameState.deck = initCards();
        currentPlayerIndex = 0;
        currentPlayer = players[currentPlayerIndex];
        dealCards();
        GamePanel.updateCards();
        checkGameEnd();
    }

    public static void resetPlayerPoints(){
        for (int i = 0; i < 3; i++) {
            players[i].setPoints(0);
        }
    }

    private static void dealCards() {
        for (int i = 0; i < 3; i++) {
            players[i].getPlayerHand().add(drawCard());
        }
    }

    public static Player checkSpy(){
        //check if only one player discarded a spy
        int count = 0;
        int player = 0;
        boolean usedSpy1 = false;
        boolean usedSpy2 = false;
        Player spyPlayer = null;
        for (int i = 0; i < amtPlayers; i++) {
            if (players[i].getSpiesUsed() == 2){
                return players[i];
            }
            else if (players[i].getSpiesUsed() == 1){
                count++;
                player ++;
                spyPlayer = players[i];
            }
        }
        if(player == 1){
            return spyPlayer;
        }else{
            return null;
        }
    }

    public static ArrayList<Card> initCards() {
        deck = new ArrayList<>();
        deck.add(new Card(CardEnum.GUARD));
        deck.add(new Card(CardEnum.GUARD));
        deck.add(new Card(CardEnum.GUARD));
        deck.add(new Card(CardEnum.GUARD));
        deck.add(new Card(CardEnum.GUARD));
        deck.add(new Card(CardEnum.GUARD));
        deck.add(new Card(CardEnum.PRIEST));
        deck.add(new Card(CardEnum.PRIEST));
        deck.add(new Card(CardEnum.BARON));
        deck.add(new Card(CardEnum.BARON));
        deck.add(new Card(CardEnum.HANDMAID));
        deck.add(new Card(CardEnum.HANDMAID));
        deck.add(new Card(CardEnum.PRINCE));
        deck.add(new Card(CardEnum.PRINCE));
        deck.add(new Card(CardEnum.KING));
        deck.add(new Card(CardEnum.COUNTESS));
        deck.add(new Card(CardEnum.PRINCESS));
        deck.add(new Card(CardEnum.SPY));
        deck.add(new Card(CardEnum.SPY));
        deck.add(new Card(CardEnum.CHANCELLOR));
        deck.add(new Card(CardEnum.CHANCELLOR));
        Collections.shuffle(deck);
        return deck;
    }

    public static void showGuardAbility() {
        JFrame frame = new JFrame("Guard Input");
        String[] playerNums = new String[GameState.getAmtPlayers()-1];
        int j = 0;
        for (int i = 0; i < GameState.getAmtPlayers(); i++) {
            if(i != GameState.getCurrentPlayer().getNumber() && !players[i].isProtected() && !players[i].isOut()) {
                playerNums[j] = "Player " + (i+1);
                j++;
            }
        }
        if (j == 0) {
            JOptionPane.showMessageDialog(frame, "No players to choose from!");
            return;
        }
        String playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Guard Input", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        //show input until player chooses a player
        while (playerInput == null) {
            playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Guard Input", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        }
        int playerNum = Integer.parseInt(playerInput.substring(7))-1;
        String[] options = {"Guard", "Priest", "Baron", "Handmaid", "Prince", "Chancellor", "King", "Countess", "Princess"};
        CardEnum enums[] = {CardEnum.GUARD, CardEnum.PRIEST, CardEnum.BARON, CardEnum.HANDMAID, CardEnum.PRINCE, CardEnum.CHANCELLOR, CardEnum.KING, CardEnum.COUNTESS, CardEnum.PRINCESS};
        String cardInput = (String) JOptionPane.showInputDialog(frame, "Choose a card to guess", "Guard Input", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        //show input until player chooses a card
        while (cardInput == null) {
            cardInput = (String) JOptionPane.showInputDialog(frame, "Choose a card to guess", "Guard Input", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }
        //if player has selected card, they are out
        CardEnum cardEnum = enums[0];
        for (int i = 0; i < options.length; i++) {
            if (cardInput.equals(options[i])) {
                cardEnum = enums[i];
            }
        }
        if(players[playerNum].getPlayerHand().get(0).getCardType() == cardEnum) {
            players[playerNum].setOut(true);
            JOptionPane.showMessageDialog(frame, "Player " + (playerNum+1) + " is out!");
        }
        else {
            JOptionPane.showMessageDialog(frame, "Player " + (playerNum+1) + " is not out!");
        }
    }

    public static void showPriestAbility(){
        JFrame frame = new JFrame("Priest Ability");
        String[] playerNums = new String[GameState.getAmtPlayers()-1];
        int j = 0;
        for (int i = 0; i < GameState.getAmtPlayers(); i++) {
            if(i != GameState.getCurrentPlayer().getNumber() && !players[i].isProtected() && !players[i].isOut()) {
                playerNums[j] = "Player " + (i+1);
                j++;
            }
        }
        //check if playerNums is empty
        if (j == 0) {
            JOptionPane.showMessageDialog(frame, "No players to choose from!");
            return;
        }

        String playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Priest Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        //show input until player chooses a player
        while (playerInput == null) {
            playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Priest Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        }
        int playerNum = Integer.parseInt(playerInput.substring(7))-1;
        JOptionPane.showMessageDialog(frame, "Player " + (playerNum+1) + " has a " + players[playerNum].getPlayerHand().get(0).getCardType().toString());
    }

    public static void showBaronAbility(){
        JFrame frame = new JFrame("Baron Ability");
        String[] playerNums = new String[GameState.getAmtPlayers()-1];
        int j = 0;
        for (int i = 0; i < GameState.getAmtPlayers(); i++) {
            if(i != GameState.getCurrentPlayer().getNumber()  && !players[i].isProtected() && !players[i].isOut()) {
                playerNums[j] = "Player " + (i+1);
                j++;
            }
        }
        if (j == 0) {
            JOptionPane.showMessageDialog(frame, "No players to choose from!");
            return;
        }
        String playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Baron Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        //show input until player chooses a player
        while (playerInput == null) {
            playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Baron Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        }
        int playerNum = Integer.parseInt(playerInput.substring(7))-1;
        if(players[playerNum].getPlayerHand().get(0).getCardType().getValue() > currentPlayer.getPlayerHand().get(0).getCardType().getValue()) {
            currentPlayer.setOut(true);
            JOptionPane.showMessageDialog(frame, "Player " + (currentPlayer.getNumber()+1) + " is out!");
        }
        else if(players[playerNum].getPlayerHand().get(0).getCardType().getValue() < currentPlayer.getPlayerHand().get(0).getCardType().getValue()) {
            players[playerNum].setOut(true);
            JOptionPane.showMessageDialog(frame, "Player " + (playerNum+1) + " is out!");
        }
        else {
            JOptionPane.showMessageDialog(frame, "Both players are safe!");
        }
    }

    public static void showHandmaidAbility(){
        JFrame frame = new JFrame("Handmaid Ability");
        JOptionPane.showMessageDialog(frame, "You are protected from all other players' cards until your next turn!");
        getCurrentPlayer().setProtected(true);
        //TODO: add protection
        //Until the start of your next turn,
        //other players cannot choose you
        //for their card effects.
        //In the rare case that all other players still in
        //the round are “protected” by a Handmaid when
        //you play a card, do the following:
        //❧ If that card requires you to choose another
        //player (Guard, Priest, Baron, King), your
        //card is played with no effect.
        //❧ If that card requires you to choose any
        //player (Prince), then you must choose
        //yourself for the effect.
    }

    public static void showPrinceAbility(){
        JFrame frame = new JFrame("Prince Ability");
        String[] playerNums = new String[GameState.getAmtPlayers()];
        int j = 0;
        for (int i = 0; i < GameState.getAmtPlayers(); i++) {
            if(!players[i].isOut() && !players[i].isProtected()) {
                playerNums[j] = "Player " + (i+1);
                j++;
            }
        }
        if (j == 0) {
            JOptionPane.showMessageDialog(frame, "No players to choose from!");
            return;
        }
        String playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Prince Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        //show input until player chooses a player
        while (playerInput == null) {
            playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Prince Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        }
        int playerNum = Integer.parseInt(playerInput.substring(7))-1;
        while( players[playerNum].getPlayerHand().size() > 0) {
            Card discard = players[playerNum].getPlayerHand().remove(0);
            if(discard.getCardType().getValue() == 9){
                players[playerNum].setOut(true);
                JOptionPane.showMessageDialog(frame, "Player " + (playerNum+1) + " is out!");
            }
        }
        if(players[playerNum].isOut()){
            GamePanel.updateCards();
            return;
        }
        else if(deck.size() > 0) {
            players[playerNum].getPlayerHand().add(drawCard());
        }
        else {
            JOptionPane.showMessageDialog(frame, "There are no more cards in the deck!");
            players[playerNum].getPlayerHand().add(cardOnTable.remove(0));
        }
        GamePanel.updateCards();
    }

    public static void showPrincessAbility(){
        JFrame frame = new JFrame("Princess Ability");
        JOptionPane.showMessageDialog(frame, "You are out!");
        currentPlayer.setOut(true);
    }

    public static void showChancellorAbility(){
        JFrame frame = new JFrame("Chancellor Ability");
        JOptionPane.showMessageDialog(frame, "You can draw the top 2 cards of the deck!");
        int amtCards = 2;
        if(deck.size() == 1) {
            currentPlayer.getPlayerHand().add(drawCard());
            amtCards--;
        }
        if(deck.size() == 0) {
            JOptionPane.showMessageDialog(frame, "There are no more cards in the deck! This card has no effect!");
        }
        else {
            currentPlayer.getPlayerHand().add(drawCard());
            currentPlayer.getPlayerHand().add(drawCard());
        }
        GamePanel.updateCards();
        String[] playerCards = new String[currentPlayer.getPlayerHand().size()];
        for (int i = 0; i < currentPlayer.getPlayerHand().size(); i++) {
            playerCards[i] = currentPlayer.getPlayerHand().get(i).getCardType().toString();
        }
        String cardInput = (String) JOptionPane.showInputDialog(frame, "Choose a card to discard", "Chancellor Ability", JOptionPane.QUESTION_MESSAGE, null, playerCards, playerCards[0]);
        //show input until player chooses a card
        while (cardInput == null) {
            cardInput = (String) JOptionPane.showInputDialog(frame, "Choose a card to discard", "Chancellor Ability", JOptionPane.QUESTION_MESSAGE, null, playerCards, playerCards[0]);
        }
        //remove card from hand
        for (int i = 0; i < currentPlayer.getPlayerHand().size(); i++) {
            if(currentPlayer.getPlayerHand().get(i).getCardType().toString().equals(cardInput)) {
                deck.add(currentPlayer.getPlayerHand().remove(i));
                break;
            }
        }
        GamePanel.updateCards();
        if(amtCards == 2) {
            String[] playerCards2 = new String[currentPlayer.getPlayerHand().size()];
            for (int i = 0; i < currentPlayer.getPlayerHand().size(); i++) {
                playerCards2[i] = currentPlayer.getPlayerHand().get(i).getCardType().toString();
            }

            String cardInput2 = (String) JOptionPane.showInputDialog(frame, "Choose a card to discard", "Chancellor Ability", JOptionPane.QUESTION_MESSAGE, null, playerCards2, playerCards2[0]);
            //show input until player chooses a card
            while (cardInput2 == null) {
                cardInput2 = (String) JOptionPane.showInputDialog(frame, "Choose a card to discard", "Chancellor Ability", JOptionPane.QUESTION_MESSAGE, null, playerCards2, playerCards2[0]);
            }
            //remove card from hand
            for (int i = 0; i < currentPlayer.getPlayerHand().size(); i++) {
                if (currentPlayer.getPlayerHand().get(i).getCardType().toString().equals(cardInput2)) {
                    deck.add((currentPlayer.getPlayerHand().remove(i)));
                    break;
                }
            }
        }
        GamePanel.updateCards();

    }

    public static void showKingAbility(){
        JFrame frame = new JFrame("King Ability");
        String[] playerNums = new String[GameState.getAmtPlayers()-1];
        int j = 0;
        for (int i = 0; i < GameState.getAmtPlayers(); i++) {
            if(i != currentPlayer.getNumber()  && !players[i].isProtected() && !players[i].isOut()) {
                playerNums[j] = "Player " + (i+1);
                j++;
            }
        }
        if (j == 0) {
            JOptionPane.showMessageDialog(frame, "No players to choose from!");
            return;
        }
        String playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "King Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        //show input until player chooses a player
        while (playerInput == null) {
            playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "King Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        }
        int playerNum = Integer.parseInt(playerInput.substring(7))-1;
        Card temp = currentPlayer.getPlayerHand().get(0);
        currentPlayer.getPlayerHand().set(0, players[playerNum].getPlayerHand().get(0));
        players[playerNum].getPlayerHand().set(0, temp);
        GamePanel.updateCards();
    }

    public static void showCountessAbility(){
        JFrame frame = new JFrame("Countess Ability");
        JOptionPane.showMessageDialog(frame, "You used the Countess Ability!");
    }

    public static void showSpyAbility(){
        JFrame frame = new JFrame("Spy Ability");
        JOptionPane.showMessageDialog(frame, "Player " + (currentPlayer.getNumber()+1) + " discarded a spy card!");
        currentPlayer.useSpy();
    }


    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    private static int getAmtPlayers() {
        return amtPlayers;
    }

    public static ArrayList<Card> getDeck() {
        return deck;
    }

    public static void playAgain() {
        System.out.println("Resetting game");
        for (int i = 0; i < 3; i++) {
            players[i].setOut(false);
            players[i].setProtected(false);
            players[i].setPlayerHand(new ArrayList<>());
            players[i].setDiscardCard(new ArrayList<>());
        }
        GameState.deck = new ArrayList<>();
        System.out.println(GameState.deck.size());
        GameState.deck = initCards();
        currentPlayerIndex = 0;
        currentPlayer = players[currentPlayerIndex];
        dealCards();
        GamePanel.updateCards();
    }
}
