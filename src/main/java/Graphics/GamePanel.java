package Graphics;

import Game.GameState;
import Game.Initialize;
import Objects.Card;
import Objects.CardEnum;
import Objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import static Game.GameState.drawCard;

public class GamePanel extends JFrame {
    private static ImageIcon discardedImages[][][];
    private static JLabel discardedCards[][][];
    private static JLabel playerIcons[];
    private final ImageIcon backCard;
    private static JLabel card1;
    private static JLabel card2;
    private static JLabel card3;
    private static JButton startGame, discardCard;
    private static JLabel turn;
    private static JLabel[] playerPoints;
    private static JButton draw, playAgain, quit;
    private static JLabel gameOver, playerWinner;

    public GamePanel() {
        super("LoveLetterGame");
        JFrame.setDefaultLookAndFeelDecorated(true);
        GridLayout layout = new GridLayout(0, 2);
        setLayout(layout);

        discardedImages = new ImageIcon[3][2][4];
        discardedCards = new JLabel[3][2][4];
        playerIcons = new JLabel[3];
        playerPoints = new JLabel[3];

        JPanel gameWindow = new JPanel();
        gameWindow.setSize(500, 600);
//        gameWindow.setBackground(Color.WHITE); //remove later
        gameWindow.setLayout(null);
        gameWindow.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 0, Color.WHITE));
        add(gameWindow);
        JPanel cardWindow = new JPanel();
        cardWindow.setSize(500, 600);
//        cardWindow.setBackground(Color.DARK_GRAY); //remove later
        cardWindow.setLayout(null);
        cardWindow.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.WHITE));
        add(cardWindow);

        pack();
        Dimension size = new Dimension(1000, 600);
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        turn = new JLabel("Player 1 Turn"); //player turn
        turn.setFont(new Font("Arial", Font.BOLD, 30)); //makes text bigger
        turn.setForeground(Color.WHITE); //text color
        turn.setLocation(20, 10);
        turn.setSize(200, 35);
        gameWindow.add(turn);

        backCard = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/back.png")); //back of card
        Image resized  = backCard.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
        card1 = new JLabel(new ImageIcon(resized)); //adds card to button
        card1.setVisible(false); //hides card
        card1.setLocation(75, 395); //sets location
        card1.setSize(114, 159); //sets size
        gameWindow.add(card1);

        card2 = new JLabel(new ImageIcon(resized)); //adds card to button
        card2.setVisible(false); //hides card
        card2.setLocation(325, 395); //sets location
        card2.setSize(114, 159); //sets size
        gameWindow.add(card2);

        card3 = new JLabel(new ImageIcon(resized)); //adds card to button
        card3.setVisible(false); //hides card
        card3.setLocation(200, 395); //sets location
        card3.setSize(114, 159); //sets size
        gameWindow.add(card3);

        ImageIcon backCardd = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/back.png")); //back of card
        resized  = backCard.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
        draw = new JButton(new ImageIcon(resized)); //adds card to button
        draw.setLocation(200, 165); //sets location
        draw.setSize(114, 159); //sets size
        gameWindow.add(draw);

        //add reference card to the right of the draw card
        ImageIcon referenceCard = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/ReferenceCard.png")); //back of card
        resized  = referenceCard.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
        JButton reference = new JButton(new ImageIcon(resized)); //adds card to button
        reference.setLocation(325, 165); //sets location
        reference.setSize(114, 159); //sets size
        gameWindow.add(reference);

        startGame = new JButton("Start Game");
        startGame.setSize(150,25);
        startGame.setLocation(30, 230);
        gameWindow.add(startGame);

        discardCard = new JButton("Discard");
        discardCard.setSize(150,25);
        discardCard.setLocation(30, 230);
        discardCard.setVisible(false);
        gameWindow.add(discardCard);

        draw.setEnabled(false);
        draw.setFocusable(false);

        //create gameOver label in the top center of the gameWindow
        gameOver = new JLabel("Game Over,");
        gameOver.setFont(new Font("Arial", Font.BOLD, 50)); //makes text bigger
        gameOver.setForeground(new Color(163, 190, 140)); //text color
        gameOver.setLocation(100, 395);
        gameOver.setSize(400, 45);
        gameOver.setVisible(false);
        gameWindow.add(gameOver);

        //create playerWinner label in the top center of the gameWindow
        playerWinner = new JLabel("Player 1 Wins!");
        playerWinner.setFont(new Font("Arial", Font.BOLD, 50)); //makes text bigger
        playerWinner.setForeground(new Color(163, 190, 140)); //text color
        playerWinner.setLocation(75, 455);
        playerWinner.setSize(400, 55);
        playerWinner.setVisible(false);
        gameWindow.add(playerWinner);

        //create playAgain button to the left of the draw card
        playAgain = new JButton("Play Again");
        playAgain.setSize(150,25);
        playAgain.setLocation(30, 230);
        playAgain.setVisible(false);
        gameWindow.add(playAgain);

        //create quit button to the right of the draw card
        quit = new JButton("Quit");
        quit.setSize(150,25);
        quit.setLocation(30, 260);
        quit.setVisible(false);
        gameWindow.add(quit);

        //actionlistener for playAgain button
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameState.playAgain();
                GameState.resetPlayerPoints();
                gameOver.setVisible(false);
                playerWinner.setVisible(false);
                playAgain.setVisible(false);
                quit.setVisible(false);
                draw.setEnabled(true);
            }
        });

        //actionlistener for quit button
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        for(int player = 0; player < 3; player++){
            ImageIcon icon = new ImageIcon(GamePanel.class.getClassLoader().getResource("Icons/player"+(player + 1)+"Icon.png"));
            resized = icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            playerIcons[player] = new JLabel(new ImageIcon(resized));
            playerIcons[player].setLocation(30, 55 + (player * 187));
            playerIcons[player].setSize(75, 75);
            cardWindow.add(playerIcons[player]);
            //add point icon under the player icon with the points next to it
            ImageIcon pointIcon = new ImageIcon(GamePanel.class.getClassLoader().getResource("Icons/points.png"));
            resized = pointIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            JLabel point = new JLabel(new ImageIcon(resized));
            point.setLocation(410, 82 + (player * 187));
            point.setSize(35, 35);
            cardWindow.add(point);
            //add points label
            playerPoints[player] = new JLabel("0");
            playerPoints[player].setFont(new Font("Arial", Font.BOLD, 30)); //makes text bigger
            playerPoints[player].setForeground(Color.WHITE); //text color
            playerPoints[player].setLocation(455, 75 + (player * 187));
            playerPoints[player].setSize(50, 50);
            cardWindow.add(playerPoints[player]);
            for(int row = 0; row < 2; row++){
                for(int col = 0; col < 4; col++){
                    discardedImages[player][row][col] = new ImageIcon(GamePanel.class.getClassLoader().getResource("Cards/back.png"));
                    resized = discardedImages[player][row][col].getImage().getScaledInstance(57, 80, Image.SCALE_SMOOTH);
                    discardedCards[player][row][col] = new JLabel(new ImageIcon(resized));
                    discardedCards[player][row][col].setLocation(125 + (col * 60), 10 + (row * 85) + (player * 189));
                    discardedCards[player][row][col].setSize(57, 80);
//                    discardedCards[player][row][col].setVisible(false);
                    cardWindow.add(discardedCards[player][row][col]);
                }
            }
            //add lines in between the players
            if(player != 2){
                JLabel line = new JLabel();
                line.setLocation(0, 187 + (player * 187));
                line.setSize(1000, 3);
                line.setBackground(Color.WHITE);
                line.setOpaque(true);
                cardWindow.add(line);
            }
        }

        revalidate();

        startGame.addActionListener(e -> {
            startGame.setVisible(false);
            draw.setEnabled(true);
            draw.setFocusable(false);
            updateCards();
        });

        draw.addActionListener(e -> {
            GameState.getCurrentPlayer().getPlayerHand().add(drawCard());
            updateCards();
            draw.setEnabled(false);
            draw.setFocusable(false);
            discardCard.setVisible(true);
            discardCard.setEnabled(true);
            updateCards();

        });

        discardCard.addActionListener(e -> {
            JFrame pp = new JFrame("Choose a card to play");
            String[] playerCards = new String[GameState.getCurrentPlayer().getPlayerHand().size()];
            for(int i = 0; i < GameState.getCurrentPlayer().getPlayerHand().size(); i++){
                playerCards[i] = GameState.getCurrentPlayer().getPlayerHand().get(i).toString();
            }
            String cardInput2 = "";
            if(GameState.getCurrentPlayer().getPlayerHand().size() > 1) {
                cardInput2 = (String) JOptionPane.showInputDialog(pp, "Choose a card to discard", "Discard", JOptionPane.QUESTION_MESSAGE, null, playerCards, playerCards[0]);
                //show input until player chooses a card
                while (cardInput2 == null) {
                    cardInput2 = (String) JOptionPane.showInputDialog(pp, "Choose a card to discard", "Discard", JOptionPane.QUESTION_MESSAGE, null, playerCards, playerCards[0]);
                }
            }
            //remove card from hand
            for (int i = 0; i < GameState.getCurrentPlayer().getPlayerHand().size(); i++) {
                if (GameState.getCurrentPlayer().getPlayerHand().get(i).getCardType().toString().equals(cardInput2)) {
                    GameState.getCurrentPlayer().discard((GameState.getCurrentPlayer().getPlayerHand().remove(i)));
                    break;
                }
            }
            discardCard.setVisible(false);
            updateCards();
            GameState.nextTurn();
            draw.setEnabled(true);
            draw.setFocusable(true);
        });
    }

    public static void updateCards() {
        turn.setText("Player " + (GameState.getCurrentPlayer().getNumber() + 1) + " Turn");
        //get current players cards as an array
        Player player = GameState.getCurrentPlayer();
        Card[] cards = player.getPlayerHand().toArray(new Card[0]);
        JLabel cardsButtons[] = {card1, card2, card3};
        if(cards.length == 1) {
            cardsButtons[0].setVisible(false);
            cardsButtons[1].setVisible(false);
            cardsButtons[2].setVisible(true);

            ImageIcon card = Initialize.cardImages[Initialize.cardMap.get(cards[0].getCardType())]; //gets card image from deck
            Image resize  = card.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
            cardsButtons[2].setIcon(new ImageIcon(resize)); //adds card to button
        } else if (cards.length == 2) {
            cardsButtons[0].setVisible(true);
            cardsButtons[1].setVisible(true);
            cardsButtons[2].setVisible(false);

            ImageIcon card = Initialize.cardImages[Initialize.cardMap.get(cards[0].getCardType())]; //gets card image from deck
            Image resize  = card.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
            cardsButtons[0].setIcon(new ImageIcon(resize)); //adds card to button
            ImageIcon card2 = Initialize.cardImages[Initialize.cardMap.get(cards[1].getCardType())]; //gets card image from deck
            Image resize2  = card2.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
            cardsButtons[1].setIcon(new ImageIcon(resize2)); //adds card to button
        } else if (cards.length == 3) {
            cardsButtons[0].setVisible(true);
            cardsButtons[1].setVisible(true);
            cardsButtons[2].setVisible(true);

            ImageIcon card = Initialize.cardImages[Initialize.cardMap.get(cards[0].getCardType())]; //gets card image from deck
            Image resize  = card.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
            cardsButtons[0].setIcon(new ImageIcon(resize)); //adds card to button
            ImageIcon card2 = Initialize.cardImages[Initialize.cardMap.get(cards[1].getCardType())]; //gets card image from deck
            Image resize2  = card2.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
            cardsButtons[1].setIcon(new ImageIcon(resize2)); //adds card to button
            ImageIcon card3 = Initialize.cardImages[Initialize.cardMap.get(cards[2].getCardType())]; //gets card image from deck
            Image resize3  = card3.getImage().getScaledInstance(114,159,Image.SCALE_SMOOTH); //resizes card
            cardsButtons[2].setIcon(new ImageIcon(resize3)); //adds card to button
        }

        for(int i = 0; i < 3; i++){
            Player p = GameState.players[i];
            Card[] discarded = p.getDiscardCard().toArray(new Card[0]);
            int index = 0;
            for(int row = 0; row < 2; row++){
                for(int col = 0; col < 4; col++){
                    if(index<discarded.length){
                        discardedCards[i][row][col].setVisible(true);
                        discardedImages[i][row][col] = Initialize.cardImages[Initialize.cardMap.get(discarded[index].getCardType())];
                        Image resized = discardedImages[i][row][col].getImage().getScaledInstance(57, 80, Image.SCALE_SMOOTH);
                        discardedCards[i][row][col].setIcon(new ImageIcon(resized));
                        index++;
                    } else {
                        discardedCards[i][row][col].setVisible(false);
                    }
                }
            }
            playerPoints[i].setText(String.valueOf(p.getPoints()));
        }

    }

    public static void showWinner(int x){
        card1.setVisible(false);
        card2.setVisible(false);
        card3.setVisible(false);

        gameOver.setVisible(true);
        playerWinner.setText("Player " + (x+1) + " Wins!");
        playerWinner.setVisible(true);
    }

    public static void disableButtons() {
        draw.setEnabled(false);
        startGame.setEnabled(false);
        discardCard.setEnabled(false);
        playAgain.setVisible(true);
        quit.setVisible(true);
    }
}
