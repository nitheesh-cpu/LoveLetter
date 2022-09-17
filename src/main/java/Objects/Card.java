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
                GameState.showSpyAbility();
                break;
            case GUARD:
                GameState.showGuardAbility();
                break;
            case PRIEST:
                GameState.showPriestAbility();
                break;
            case BARON:
                GameState.showBaronAbility();
                break;
            case CHANCELLOR:
                GameState.showChancellorAbility();
                break;
            case HANDMAID:
                GameState.showHandmaidAbility();
                break;
            case PRINCE:
                GameState.showPrinceAbility();
                break;
            case KING:
                GameState.showKingAbility();
                break;
            case COUNTESS:
                GameState.showCountessAbility();
                break;
            case PRINCESS:
                GameState.showPrincessAbility();
                break;
        }
    }


}
