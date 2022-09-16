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
    private static Player[] players;
    private static Player currentPlayer;

    private GamePanel hehe;
    public GameState(int amtPlayers) {
        Initialize.init(this);
        this.amtPlayers = amtPlayers; //set amount of players
        deck = initCards(); //initialize deck
        cardOnTable = new ArrayList<>(); //initialize card on table
        cardOnTable.add(deck.remove(0)); //add first card face down to the table
        players = new Player[amtPlayers]; //initialize players
        for (int i = 0; i < amtPlayers; i++) {
            players[i] = new Player(i); //create player
            players[i].addCard(deck.remove(0)); //deal card to player
        }
        hehe = new GamePanel();
        currentPlayer = players[0]; //set current player to player 1
    }


    public ArrayList<Card> initCards() {
        deck = new ArrayList<>();
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

    public static void showGuardInput() {
        JFrame frame = new JFrame("Guard Input");
        String[] playerNums = new String[GameState.getAmtPlayers()-1];
        int j = 0;
        for (int i = 0; i < GameState.getAmtPlayers(); i++) {
            if(i != GameState.getCurrentPlayer().getNumber()) {
                playerNums[j] = "Player " + (i+1);
                j++;
            }
        }
        String playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Guard Input", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        //show input until player chooses a player
        while (playerInput == null) {
            playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Guard Input", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        }
        int playerNum = Integer.parseInt(playerInput.substring(7))-1;
        String[] options = {"Guard", "Priest", "Baron", "Handmaid", "Prince", "King", "Countess", "Princess"};
        CardEnum enums[] = {CardEnum.GUARD, CardEnum.PRIEST, CardEnum.BARON, CardEnum.HANDMAID, CardEnum.PRINCE, CardEnum.KING, CardEnum.COUNTESS, CardEnum.PRINCESS};
        String cardInput = (String) JOptionPane.showInputDialog(frame, "Choose a card to guess", "Guard Input", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        //show input until player chooses a card
        while (cardInput == null) {
            cardInput = (String) JOptionPane.showInputDialog(frame, "Choose a card to guess", "Guard Input", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }
        //get card enum from card name
        CardEnum cardEnum = enums[0];
        System.out.println(cardInput);
        for (int i = 0; i < options.length; i++) {
            if(options[i].equals(cardInput)) {
                cardEnum = enums[i];
                System.out.println(i);
            }
        }
        System.out.println(cardEnum);
        players[playerNum].getPlayerHand().add(new Card(cardEnum));
        //if player has selected card, they are out
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
            if(i != GameState.getCurrentPlayer().getNumber()) {
                playerNums[j] = "Player " + (i+1);
                j++;
            }
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
            if(i != GameState.getCurrentPlayer().getNumber()) {
                playerNums[j] = "Player " + (i+1);
                j++;
            }
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
        //TODO: add protection
    }

    public static void showPrinceAbility(){
        JFrame frame = new JFrame("Prince Ability");
        String[] playerNums = new String[GameState.getAmtPlayers()-1];
        for (int i = 0; i < GameState.getAmtPlayers(); i++) {
            playerNums[i] = "Player " + (i+1);
        }
        String playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Prince Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        //show input until player chooses a player
        while (playerInput == null) {
            playerInput = (String) JOptionPane.showInputDialog(frame, "Choose a player", "Prince Ability", JOptionPane.QUESTION_MESSAGE, null, playerNums, playerNums[0]);
        }
        int playerNum = Integer.parseInt(playerInput.substring(7))-1;
        Card discard = players[playerNum].getPlayerHand().remove(0);
        if(deck.size() > 0) {
            players[playerNum].getPlayerHand().add(deck.get(0));
            deck.remove(0);
        }
        else {
            JOptionPane.showMessageDialog(frame, "There are no more cards in the deck!");
            players[playerNum].getPlayerHand().add(cardOnTable.remove(0));
        }
        if(discard.getCardType().getValue() == 9){
            players[playerNum].setOut(true);
            JOptionPane.showMessageDialog(frame, "Player " + (playerNum+1) + " is out!");
        }
    }

    private static Player getCurrentPlayer() {
        return currentPlayer;
    }

    private static int getAmtPlayers() {
        return amtPlayers;
    }

    public static ArrayList<Card> getDeck() {
        return deck;
    }
}
