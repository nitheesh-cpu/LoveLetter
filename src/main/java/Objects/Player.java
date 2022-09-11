package Objects;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> playerHand;
    private ArrayList<Card> discardCard;
    private int points;
    private int number;

    public Player(int number){
        this.number = number;
        playerHand = new ArrayList<Card>();
        discardCard = new ArrayList<Card>();
        points = 0;
    }

    public void addCard(Card card){
        playerHand.add(card);
    }

}
