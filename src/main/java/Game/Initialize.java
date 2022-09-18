package Game;

import Objects.CardEnum;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Initialize {
    public static ImageIcon[] cardImages = new ImageIcon[11];
    public static Map<CardEnum, Integer> cardMap;
    public static GameState gameState;

    public static void init(GameState gameState) {
        gameState = gameState;

        cardImages[0] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/0.png"));
        cardImages[1] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/1.png"));
        cardImages[2] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/2.png"));
        cardImages[3] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/3.png"));
        cardImages[4] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/4.png"));
        cardImages[5] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/5.png"));
        cardImages[6] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/6.png"));
        cardImages[7] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/7.png"));
        cardImages[8] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/8.png"));
        cardImages[9] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/9.png"));
        cardImages[10] = new ImageIcon(Initialize.class.getClassLoader().getResource("Cards/10.jpg"));

        cardMap = Map.ofEntries(
                Map.entry(CardEnum.SPY, 0),
                Map.entry(CardEnum.GUARD, 1),
                Map.entry(CardEnum.PRIEST, 2),
                Map.entry(CardEnum.BARON, 3),
                Map.entry(CardEnum.HANDMAID, 4),
                Map.entry(CardEnum.PRINCE, 5),
                Map.entry(CardEnum.CHANCELLOR, 6),
                Map.entry(CardEnum.KING, 7),
                Map.entry(CardEnum.COUNTESS, 8),
                Map.entry(CardEnum.PRINCESS, 9),
                Map.entry(CardEnum.BACK, 10)
        );

    }

}
