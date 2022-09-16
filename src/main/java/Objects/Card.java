package Objects;


import Game.GameState;

public class Card {
    private CardEnum cardType;

    public Card(CardEnum cardType) {
        this.cardType = cardType;
    }

    public CardEnum getCardType() {
        return cardType;
    }

    public void setCardType(CardEnum cardType) {
        this.cardType = cardType;
    }

    public String toString() {
        return cardType.toString();
    }

    public void useCard(){
        switch(cardType){
            case SPY:
                break;
            case GUARD:
                GameState.showGuardInput();
                break;
            case PRIEST:
                GameState.showPriestAbility();
                break;
            case BARON:
                GameState.showBaronAbility();
                break;
            case CHANCELLOR:
                break;
            case HANDMAID:
                break;
            case PRINCE:
                GameState.showPrinceAbility();
                break;
            case KING:
                break;
            case COUNTESS:
                break;
            case PRINCESS:
                break;
        }
    }


}
