
//Card Class

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){ //make a card with suit s and value v
		this.suit = s; 
        this.rank = r;
	}
    
    public int getRank(){
        return this.rank;
    }
    
    public int getSuit(){
        return this.suit;
    }
	
	public int compareTo(Card c){
		// use this method to compare cards so they 
		//may be easily sorted
        if(this.rank == c.rank) {
            return 0;
        } else if(this.rank == c.rank - 1){ //added condition to use in the checkStraight method
           return -2;
        } else if(this.rank > c.rank){
            return 2;
        } else{
            return -1;
        }

	}
    
    public int compareSuit(Card c){  //this method is to compare the suits of cards
        if(this.suit == c.suit){
            return 0;
        }else {
            return -1;
        } 
    }
	
	public String toString(){
		return rankText() + " " + suitText(); // use this method to easily print a Card object
	}
    
    public String rankText(){
        int r = getRank();
        switch(r){
            case 1:
                return "Ace";
            case 2:
                return "Two";
            case 3: 
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
        }
        return "";
    }
    
   
    public String suitText(){
        int s = getSuit();
        switch(s){
            case 1:
                return "of Diamonds";
            case 2:
                return "of Clubs";
            case 3:
                return "of Hearts";
            case 4:
                return "of Spades";
            default:
                return "";
        }        
    }
    
    
}
