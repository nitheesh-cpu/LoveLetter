package Game;
import Graphics.GamePanel;
import Objects.Card;
import Objects.CardEnum;
import Objects.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class GameState {
    private int amtPlayers;
    private static ArrayList<Card> deck;
    private ArrayList<Card> cardOnTable;
    private Player[] players;

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

    public static ArrayList<Card> getDeck() {
        return deck;
    }
}
