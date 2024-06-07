package blackjack;

import java.util.*;

public class Deck {

    protected ArrayList<Card> deck;

    //costruttore che ci permette di creare ogni combinazione di carta per poi inserire
    //ciascuna carta al deck, dopo questa operazione il deck viene mescolato e poi stampato
    public Deck() {
        deck = new ArrayList<>();
        String[] values = {"A" , "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] types = {"C", "D", "H", "S"};

        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                Card card = new Card(values[j], types[i]);
                deck.add(card);
            }
        }

        System.out.println("DECK: ");
        System.out.println(deck);

        Collections.shuffle(deck);

        System.out.println("AFTER SHUFFLE: ");
        System.out.println(deck);
    }
}