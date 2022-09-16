package Graphics;

import Game.GameState;
import Game.Initialize;
import Objects.Card;
import Objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GamePanel extends JFrame {
    private final ImageIcon backCard;
    private static JButton card1;
    private static JButton card2;
    private static JButton card3;
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

        JLabel turn = new JLabel("Player 1 Turn"); //player turn
        turn.setFont(new Font("Arial", Font.BOLD, 30)); //makes text bigger
        turn.setForeground(Color.BLACK); //text color
        turn.setLocation(20, 10);
        turn.setSize(200, 35);
        gameWindow.add(turn);

        backCard = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/10.jpg")); //back of card
        Image resized  = backCard.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
        card1 = new JButton(new ImageIcon(resized)); //adds card to button
        card1.setVisible(false); //hides card
        card1.setLocation(35, 403); //sets location
        card1.setSize(114, 159); //sets size
        gameWindow.add(card1);

        card2 = new JButton(new ImageIcon(resized)); //adds card to button
        card2.setVisible(false); //hides card
        card2.setLocation(185, 403); //sets location
        card2.setSize(114, 159); //sets size
        gameWindow.add(card2);

        card3 = new JButton(new ImageIcon(resized)); //adds card to button
        card3.setVisible(false); //hides card
        card3.setLocation(335, 403); //sets location
        card3.setSize(114, 159); //sets size
        gameWindow.add(card3);

        ImageIcon backCardd = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/10.jpg")); //back of card
        resized  = backCard.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
        JButton draw = new JButton(new ImageIcon(resized)); //adds card to button
        draw.setLocation(200, 165); //sets location
        draw.setSize(114, 159); //sets size
        gameWindow.add(draw);

        revalidate();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(250, 250);
        frame.setVisible(true);
        JLabel discarded = new JLabel("Discarded Cards");
        discarded.setFont(new Font("Arial", Font.BOLD, 30));
        discarded.setForeground(Color.WHITE);
        discarded.setLocation(0, 0);
        discarded.setSize(200, 20);
        frame.add(discarded);


        draw.addActionListener(e -> {
            updateCards();
        });





    }

    public static void updateCards() {
        //get current players cards as an array
        Player player = GameState.getCurrentPlayer();
        Card[] cards = player.getPlayerHand().toArray(new Card[0]);
        JButton cardsButtons[] = {card1, card2, card3};
        for(int i = 0; i < cards.length; i++) {
            ImageIcon card = Initialize.cardImages[Initialize.cardMap.get(cards[i].getCardType())]; //gets card image from deck
            Image resize  = card.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
            cardsButtons[i].setIcon(new ImageIcon(resize)); //adds card to button
            cardsButtons[i].setVisible(true); //shows card
        }
    }
}
