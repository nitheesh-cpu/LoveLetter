package Objects;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> playerHand;
    private ArrayList<Card> discardCard;
    private int points;
    private int number;
    private boolean isProtected;
    private boolean isOut;
    private boolean usedSpy1;
    private boolean usedSpy2;

    public Player(int number){
        this.number = number;
        playerHand = new ArrayList<Card>();
        discardCard = new ArrayList<Card>();
        points = 0;
    }

    public void discard(Card card){
        playerHand.remove(card);
        discardCard.add(card);
        card.useCard();
    }

    public void discardCardWithoutAbility(Card card){
        playerHand.remove(card);
        discardCard.add(card);
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

    public void setProtected(boolean b) {
        isProtected = b;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void useSpy() {
        if(usedSpy1){
            usedSpy2 = true;
        } else {
            usedSpy1 = true;
        }
    }

    public int getSpiesUsed() {
        if(usedSpy1 && usedSpy2){
            return 2;
        } else if(usedSpy1 || usedSpy2){
            return 1;
        } else {
            return 0;
        }
    }
}
