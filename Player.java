//Player class

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;
    private double winnings;
    private Scanner input; 

		
	public Player(){		
	    // create a player here
        this.hand = new ArrayList<Card>();
        this.bankroll = 50;
        this.bet = 0;
	}

	public void addCard(Card c){
	    hand.add(c);// add the card c to the player's hand
	}

	public void removeCard(Card c){
	    hand.remove(c);// remove the card c from the player's hand
    }
		
    public void bets(double amt){
        bankroll -= amt;    // player makes a bet
        bet = amt;
    }

    public void winnings(double odds){
        this.bankroll+= odds*bet;   //	adjust bankroll if player wins
        this.winnings = odds*bet;
    }

    public double getBankroll(){
        return bankroll;   // return current balance of bankroll
    }
    
    public ArrayList<Card> getHand(){
        return hand;  // gets hand during game
    }


}


