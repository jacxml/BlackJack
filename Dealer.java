package blackjack;

import java.util.ArrayList;

public class Dealer {
    protected Card hiddenCard;
    protected ArrayList<Card> dealerHand;
    protected int dealerScore; // variabile che ci serve per tenere conto del punteggio della mano del dealer
    protected int dealerAceCount; //variabile che ci serve per alternare il valore dell'asso (se presente) nella mano del dealer per
                                  //raggiungere il 17 o non superare il 21

    public Dealer() {
        this.dealerHand = new ArrayList<>();
        this.dealerScore = 0;
        this.dealerAceCount = 0;
    }

    public int reduceDealerAce() {
        while (dealerScore > 21 && dealerAceCount > 0) {
            dealerScore -= 10;
            dealerAceCount -= 1;
        }
        return dealerScore;
    }
}