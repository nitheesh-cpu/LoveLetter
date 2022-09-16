package Objects;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> playerHand;
    private ArrayList<Card> discardCard;
    private int points;
    private int number;
    private boolean isProtected;
    private boolean isOut;

    public Player(int number){
        this.number = number;
        playerHand = new ArrayList<Card>();
        discardCard = new ArrayList<Card>();
        points = 0;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public ArrayList<Card> getDiscardCard() {
        return discardCard;
    }

    public void setDiscardCard(ArrayList<Card> discardCard) {
        this.discardCard = discardCard;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addCard(Card card){
        playerHand.add(card);
    }

    public void setOut(boolean b) {
        isOut = b;
    }

    public boolean isOut() {
        return isOut;
    }
}
