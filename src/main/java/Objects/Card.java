package Objects;

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
}
