package Graphics;

import Game.GameState;
import com.formdev.flatlaf.icons.FlatHelpButtonIcon;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JFrame{
    private JButton playButton;

    public MenuPanel(){
        super("Menu");
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
        Container win = getContentPane();
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        JLabel title = new JLabel("Welcome to Love Letter");
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setSize(400, 40);
        title.setLocation(100,20);
        add(title);

        JLabel title2 = new JLabel("a card game for 3-6 players");
        title2.setFont(new Font("Arial", Font.ITALIC, 15));
        title2.setSize(300, 20);
        title2.setLocation(205,60);
        add(title2);

        SpinnerModel value = new SpinnerNumberModel(3, 3, 6, 1);
        JSpinner playerInput = new JSpinner(value);
        playerInput.setBounds(240,120,100,30);
        win.add(playerInput);

        JLabel playerText = new JLabel("Enter number of players:");
        playerText.setSize(150,100);
        playerText.setLocation(225,110);
        add(playerText);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLocation(240, 210);
        buttonPanel.setSize(100, 30);
        playButton = new JButton("Play Game");
        playButton.setLocation(125, 210);
        playButton.setSize(100, 30);
        win.add(buttonPanel);
        buttonPanel.add(playButton);

        JButton help = new JButton();
        help.setFocusable(false);
        help.setContentAreaFilled(false);
        help.setOpaque(false);
        help.setSize(24, 24);
        help.setLocation(550, 8);
        help.setIcon(new FlatHelpButtonIcon());
        win.add(help);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        playButton.addActionListener(e -> {
            int players = (int) playerInput.getValue();
            dispose();
            new GameState(players);
        });

    }
}
