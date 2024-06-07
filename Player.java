package blackjack;

import java.util.ArrayList;

public class Player {
    protected ArrayList<Card> playerHand;
    protected int playerScore;
    protected int playerAceCount;

    Player(){
        playerHand = new ArrayList<>();
        this.playerScore = 0;
        this.playerAceCount = 0;
    }

    public int reducePlayerAce() {
        while (playerScore > 21 && playerAceCount > 0) {
            playerScore -= 10;
            playerAceCount -= 1;
        }
        return playerScore;
    }
}