package Graphics;

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
        gameWindow.setBackground(Color.WHITE);
        gameWindow.setLayout(null);
        add(gameWindow);
        JPanel cardWindow = new JPanel();
        cardWindow.setSize(500, 600);
        cardWindow.setBackground(Color.DARK_GRAY);
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

        backCard = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/10.jpg"));
        Image resized  = backCard.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH);
        back = new JButton(backCard);
        back.setLocation(15, 403);
        back.setSize(114, 159);
        gameWindow.add(back);

        revalidate();

        back.addActionListener(e -> {
            ImageIcon change = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/9.png"));
            Image resize  = change.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH);
            back.setIcon(new ImageIcon(resize));
        });







    }
}
