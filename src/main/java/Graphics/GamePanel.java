package Graphics;

import Game.GameState;
import Game.Initialize;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GamePanel extends JFrame {
    private final ImageIcon backCard;
    private JButton back;
    public GamePanel() {
        super("LoveLetterGame");
        JFrame.setDefaultLookAndFeelDecorated(true);
        GridLayout layout = new GridLayout(0, 2);
        setLayout(layout);

        JPanel gameWindow = new JPanel();
        gameWindow.setSize(500, 600);
        gameWindow.setBackground(Color.WHITE); //remove later
        gameWindow.setLayout(null);
        add(gameWindow);
        JPanel cardWindow = new JPanel();
        cardWindow.setSize(500, 600);
        cardWindow.setBackground(Color.DARK_GRAY); //remove later
        cardWindow.setLayout(null);
        add(cardWindow);

        pack();
        Dimension size = new Dimension(1000, 600);
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel turn = new JLabel("Player 1 Turn");
        turn.setFont(new Font("Arial", Font.BOLD, 30));
        turn.setForeground(Color.BLACK);
        turn.setLocation(10, 10);
        turn.setSize(200, 35);
        gameWindow.add(turn);

        backCard = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/10.jpg"));
        Image resized  = backCard.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH);
        back = new JButton(new ImageIcon(resized));
        back.setVisible(false);
        back.setLocation(15, 403);
        back.setSize(114, 159);
        gameWindow.add(back);

        ImageIcon backCardd = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/10.jpg"));
        resized  = backCard.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH);
        JButton draw = new JButton(new ImageIcon(resized));
        draw.setLocation(200, 165);
        draw.setSize(114, 159);
        gameWindow.add(draw);

        revalidate();

        draw.addActionListener(e -> {
            ImageIcon card = Initialize.cardImages[Initialize.cardMap.get(GameState.getDeck().get(0).getCardType())];
            Image resize  = card.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH);
            back.setIcon(new ImageIcon(resize));
            back.setVisible(true);
        });







    }
}
