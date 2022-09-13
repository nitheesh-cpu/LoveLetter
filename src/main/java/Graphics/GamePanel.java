package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GamePanel extends JFrame {
    private Icon backCard;
    private JButton back;
    public GamePanel() {
        super("LoveLetterGame");
        JFrame.setDefaultLookAndFeelDecorated(true);
        setLayout(null);
        pack();
        Dimension size = new Dimension(600, 300);
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backCard = new ImageIcon("10.jpg");
        back = new JButton(backCard);
        back.setLocation(300, 15);
        back.setSize(300, 600);
        add(back);







    }
}
