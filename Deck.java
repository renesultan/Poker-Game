//Deck of cards class

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck
    private int index;
    private Random rand = new Random();
    private ArrayList<Card> shuffledDeck;


	
	public Deck(){
        // make a 52 card deck here
        
        cards = new Card[52]; // make a 52 card deck here
        for(int i=1;i<5;i++){
            for(int j=1;j<14;j++){
                cards[index] = new Card(i,j);
                index++;
            }
        }    
    }
    
   
	
	public void shuffle(){  //shuffle the deck here
        ArrayList<Card> initialDeck = new ArrayList<Card>();
        for(Card i : cards){
            initialDeck.add(i);
        }
        shuffledDeck = new ArrayList<Card>();
        for(int i=0;i<52;i++){
            int temp = rand.nextInt(initialDeck.size());
            Card randomCard = initialDeck.get(temp);
            shuffledDeck.add(randomCard);
            initialDeck.remove(randomCard);
        }
        
	}
	
	public Card deal(){  // deal the top card in the deck
        if(top>52){
            top = top - 52;
        }
        return(shuffledDeck.get(top++));
	}
	

}
