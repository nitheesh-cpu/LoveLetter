package Objects;

public enum CardEnum {
    SPY, GUARD, PRIEST, BARON, CHANCELLOR, HANDMAID, PRINCE, KING, COUNTESS, PRINCESS, BACK;

    public int getValue() {
        switch (this) {
            case SPY:
                return 0;
            case GUARD:
                return 1;
            case PRIEST:
                return 2;
            case BARON:
                return 3;
            case CHANCELLOR:
                return 4;
            case HANDMAID:
                return 5;
            case PRINCE:
                return 6;
            case KING:
                return 7;
            case COUNTESS:
                return 8;
            case PRINCESS:
                return 9;
            default:
                return -1;
        }
    }
}
