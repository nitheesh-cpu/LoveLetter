package Graphics;

import Game.GameState;
import Game.Initialize;
import com.formdev.flatlaf.icons.FlatHelpButtonIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;

public class MenuPanel extends JFrame{
    private JButton playButton;
    private JFrame helpFrame;

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

        JLabel title2 = new JLabel("a card game for 3 players");
        title2.setFont(new Font("Arial", Font.ITALIC, 15));
        title2.setSize(300, 20);
        title2.setLocation(205,60);
        add(title2);

//        SpinnerModel value = new SpinnerNumberModel(3, 3, 6, 1);
//        JSpinner playerInput = new JSpinner(value);
//        playerInput.setBounds(240,120,100,30);
//        win.add(playerInput);
//
//        JLabel playerText = new JLabel("Enter number of players:");
//        playerText.setSize(150,100);
//        playerText.setLocation(225,110);
//        add(playerText);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLocation(240, 160);
        buttonPanel.setSize(100, 40);
        playButton = new JButton("Play Game");
        playButton.setLocation(125, 210);
        playButton.setSize(100, 40);
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

        AtomicBoolean helpOpen = new AtomicBoolean(false);
        help.addActionListener(e -> {
            if(!helpOpen.get()) {
                helpOpen.set(true);
                //create new JFrame to display help
                helpFrame = new JFrame("Instructions");
                helpFrame.setSize(500, 770);
                helpFrame.setLocationRelativeTo(null);
                helpFrame.setVisible(true);
                helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //make JTabbed Pane to hold help and rules from images
                JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
                tabbedPane.setSize(500, 700);
                tabbedPane.setLocation(0, 0);
                helpFrame.add(tabbedPane);
                //fill each tab with an image from CardBuffers.help
                for (int i = 0; i < Initialize.help.length; i++) {
                    JLabel helpImage = new JLabel(new ImageIcon(Initialize.help[i].getImage().getScaledInstance(500, 700, Image.SCALE_SMOOTH)));
                    tabbedPane.addTab(""+(i + 1), helpImage);
                }
                //on close of helpFrame, set helpOpen to false
                helpFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        helpOpen.set(false);
                    }
                });
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        playButton.addActionListener(e -> {
//            int players = (int) playerInput.getValue();
            if(helpOpen.get()) {
                helpFrame.dispose();
            }
            dispose();
            new GameState(3);
        });

    }

}
