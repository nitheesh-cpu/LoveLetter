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
        setLayout(null);
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
        back.setLocation(300, 15);
        back.setSize(114, 159);
        add(back);

        back.addActionListener(e -> {
            ImageIcon change = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/9.png"));
            Image resize  = change.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH);
            back.setIcon(new ImageIcon(resize));
        });







    }
}
