package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlackJackGame {
    private void startGame(){

        int boardWidth = 600;
        int boardWeight = boardWidth;

        int cardWidth = 110;
        int cardHeight  = 154;

        Deck deck = new Deck();
        Dealer dealer = new Dealer();

        dealer.hiddenCard = deck.deck.remove(deck.deck.size()-1); //toglie l'ultima carta (ultimo indice)
        dealer.dealerScore += dealer.hiddenCard.getValue();
        dealer.dealerAceCount += dealer.hiddenCard.isAce() ? 1 : 0;

        Card card = deck.deck.remove(deck.deck.size()-1);

        dealer.dealerScore += card.getValue();
        dealer.dealerAceCount += card.isAce() ? 1 : 0;
        dealer.dealerHand.add(card);

        System.out.println("\nDEALER: ");
        System.out.println(dealer.hiddenCard);
        System.out.println(dealer.dealerHand);
        System.out.println(dealer.dealerScore);
        System.out.println(dealer.dealerAceCount);

        Player player = new Player();

        for (int i = 0; i < 2; i++) {
            card = deck.deck.remove(deck.deck.size()-1);
            player.playerScore += card.getValue();
            player.playerAceCount += card.isAce() ? 1 : 0;
            player.playerHand.add(card);
        }

        System.out.println("\nPLAYER: ");
        System.out.println(player.playerHand);
        System.out.println(player.playerScore);
        System.out.println(player.playerAceCount);

        JFrame frame = new JFrame("BlackJack");

        JPanel buttonPanel = new JPanel();

        JButton hitButton = new JButton("Hit");
        JButton stayButton = new JButton("Stay");

        JPanel gamePanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    // "disegna" carta nascosta
                    Image hiddenCardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                    if(!stayButton.isEnabled()){
                        hiddenCardImg = new ImageIcon(getClass().getResource(dealer.hiddenCard.getImagePath())).getImage();
                    }
                    g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);

                    // "disegna" la mano del dealer
                    for (int i = 0; i < dealer.dealerHand.size(); i++) {
                        Card card = dealer.dealerHand.get(i);
                        Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                        g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5)*i, 20, cardWidth, cardHeight, null);
                    }

                    // "disegna" la mano del giocatore
                    for (int i = 0; i < player.playerHand.size(); i++) {
                        Card card = player.playerHand.get(i);
                        Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                        g.drawImage(cardImg, 20 + (cardWidth + 5)*i, 320, cardWidth, cardHeight, null);
                    }

                    if (!stayButton.isEnabled()){
                        dealer.dealerScore = dealer.reduceDealerAce();
                        player.playerScore = player.reducePlayerAce();
                        System.out.println("STAY: ");
                        System.out.println(dealer.dealerScore);
                        System.out.println(player.playerScore);

                        String message = " ";
                        if ( player.playerScore > 21){
                            message = "YOU LOSE!";
                        } else if (dealer.dealerScore > 21){
                            message ="YOU WIN!";
                        } else if (player.playerScore == dealer.dealerScore){
                            message ="TIE!";
                        } else if (player.playerScore > dealer.dealerScore){
                            message = "YOU WIN!";
                        } else if (player.playerScore < dealer.dealerScore) {
                            message ="YOU LOSE!";
                        }
                        g.setFont(new Font("Arial", Font.PLAIN, 30));
                        g.setColor(Color.white);
                        g.drawString(message, 220, 250);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        frame.setVisible(true);
        frame.setSize(boardWidth, boardWeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quando il giocatore clikka su la "x" per chiudere la finestra, chiude anche il programma

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);

        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card card = deck.deck.remove(deck.deck.size()-1);
                player.playerScore += card.getValue();
                player.playerAceCount += card.isAce() ? 1 : 0;
                player.playerHand.add(card);
                if (player.reducePlayerAce() > 21) {
                    hitButton.setEnabled(false);
                }
                gamePanel.repaint();
            }
        });

        stayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);

                while (dealer.dealerScore < 17){
                    Card card = deck.deck.remove(deck.deck.size()-1);
                    dealer.dealerScore += card.getValue();
                    dealer.dealerAceCount += card.isAce() ? 1 : 0;
                    dealer.dealerHand.add(card);
                }
                gamePanel.repaint();
            }
        });

        gamePanel.repaint();
    }

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.startGame();
    }
}