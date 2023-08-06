//Game class

import java.util.*;

public class Game {
	
	//instance variables 
    
    private Player p;
	private Deck cards;
    private int mode;
	
    //constants defined as public static final variables
    
    public static final int ROYAL_FLUSH_PAYOUT = 250;
    public static final int STRAIGHT_FLUSH_PAYOUT = 50;
    public static final int FOUR_OF_A_KIND_PAYOUT = 25;
    public static final int FULL_HOUSE_PAYOUT = 6;
    public static final int FLUSH_PAYOUT = 5;
    public static final int STRAIGHT_PAYOUT = 4;
    public static final int THREE_OF_A_KIND_PAYOUT = 3;
    public static final int TWO_PAIRS_PAYOUT = 2;
    public static final int ONE_PAIR_PAYOUT = 1;
    public static final int NO_PAIR_PAYOUT = 0;
	
	
	public Game(String[] testHand){
        
        // This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs = 1
		// d = diamonds = 2 
		// h = hearts = 3
		// s = spades = 4
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
        mode=0; //this is the mode to force a hand
        p = new Player();
        cards = new Deck();
        for(String s : testHand){
            String suit = s.substring(0,1);
            int rank = Integer.parseInt(s.substring(1,s.length()));
            if(suit.equals("c")){
                p.addCard(new Card(1,rank));
            }else if(suit.equals("d")){
                p.addCard(new Card(2,rank));
            }else if(suit.equals("h")){
                p.addCard(new Card(3,rank));
            }else if(suit.equals("s")){
                p.addCard(new Card(4,rank));
            }
                     
         }
                     
    }	
                     
                     
	public Game(){
		// This no-argument constructor is to actually play a normal game
        mode=1; //this is the normal mode
        p = new Player();
        cards = new Deck();
		
	}
	
	public void play(){
		int decision = 1;
        int round = 0;
        System.out.println("\n\n\nWelcome to Video Poker! \n");
        while(decision==1 && p.getBankroll()>0){
            cards.shuffle();
            round++;
            System.out.println("-----------------------------------------");
            System.out.println("\nThis is your round number " + round + " ! ");
            System.out.println("\nYour bankroll is " + p.getBankroll() + " ! ");
            System.out.println("\nHow much would like to bet this round ");
            Scanner input = new Scanner(System.in);
            p.bets(input.nextDouble());
            System.out.println("Your bankroll is now " + p.getBankroll() + " ! " );
            if(mode==1){
                while((p.getHand()).size()!=0){
                    p.removeCard((p.getHand()).get(0));
                }
                for(int i=0;i<5;i++){
                    Card c = cards.deal();
                    p.addCard(c);
                    }
                } 
            Collections.sort(p.getHand());
            System.out.println(p.getHand());
            System.out.println("How many cards would you like to replace ? ");
            int nbOfReplacements = input.nextInt();
            if(nbOfReplacements != 0){
                for(int i=0;i<nbOfReplacements;i++){
                System.out.println("What is the suit of the card number " + (i+1) + " you want to replace ? ");
                System.out.println("\nType the number of the suit based on the code below:");
                System.out.println("\nDiamonds --> 1\nClubs --> 2\nHearts --> 3\nSpades--> 4\n");
                int s = input.nextInt();
                System.out.println("What is the rank of the card you want to replace ? ");
                System.out.println("\nType the number in digit (Example: Don't write Ace, write 1 ; don't write Two, write 2)");
                int r = input.nextInt();
                Card toRemove = new Card(s,r);
                for(Card c : p.getHand()){
                    if(c.getRank()==toRemove.getRank() && c.getSuit()==toRemove.getSuit()){
                        p.removeCard(c);
                        break;
                    }
                }
                Card b = cards.deal();
                p.addCard(b);
                }
            }
            Collections.sort(p.getHand());
            System.out.println("\n\nThis is your new hand! " + p.getHand());
            System.out.println("\n"+checkHand(p.getHand()));
            System.out.println("\nYour bankroll is : " + p.getBankroll());
            System.out.println("\n\n\nDo you want to play again ? ");
            System.out.println("\nType 1 if you want to continue or 0 if you want to stop");
            mode=1;
            decision = input.nextInt();
            if(decision == 0){
                System.out.println("\n\nThank you for playing !\n\n\n");
            }
        }
        


        
	}
	
	public String checkHand(ArrayList<Card> hand){
        if(checkStraight(hand)==3){
            p.winnings(ROYAL_FLUSH_PAYOUT);
            return "Royal Flush!!!!!";
        }else if(checkStraight(hand)==1){
            p.winnings(STRAIGHT_FLUSH_PAYOUT);
            return "Straight Flush!!!";
        }else if(checkPair(hand)==5){
            p.winnings(FOUR_OF_A_KIND_PAYOUT);
            return "Four of a kind!!";
        }else if(checkPair(hand)==3){
            p.winnings(FULL_HOUSE_PAYOUT);
            return "Full House!!";
        }else if(checkFlush(hand)==true){
            p.winnings(FLUSH_PAYOUT);
            return "Flush!!";
        }else if(checkStraight(hand)==2){
            p.winnings(STRAIGHT_PAYOUT);
            return "Straight!";
        }else if(checkPair(hand)==4){
            p.winnings(THREE_OF_A_KIND_PAYOUT);
            return "Three of a kind!";
        }else if(checkPair(hand)==2){
            p.winnings(TWO_PAIRS_PAYOUT);
            return "Two pairs!";
        }else if(checkPair(hand)==1){
            p.winnings(ONE_PAIR_PAYOUT);
            return "One pair!";
        }else if(checkPair(hand)==0){
            p.winnings(NO_PAIR_PAYOUT);
            return "No pair :( ";
        }
        
        return "";
        
	}
	
    ////////////////////////////////////////////////
    //Methods to check for different winning hands//
    ////////////////////////////////////////////////
    
    //Method to check for pairs
    public int checkPair(ArrayList<Card> t){ 
        int count=0; //count increments when we find two consecutive cards with the same rank
        for(int i=1;i<t.size();i++){
            if((t.get(i-1)).compareTo(t.get(i))==0){ 
                count++;
            }
        }
        //At this stage we counted the amount of consecutive cards with the same rank and we do comparisons to check it is which kind of winning hand
        if(count==3){ 
            for(int i=3;i<t.size();i++){
                if((t.get(i-3)).compareTo(t.get(i-2))==0 && (t.get(i-2)).compareTo(t.get(i-1))==0 && (t.get(i-1)).compareTo(t.get(i))==0){ //if there are four consecutive cards with the same rank it is a four of a kind 
                    count=5;
                    break;
                }
            }
        
        }else if(count==2){
            for(int i=2;i<t.size();i++){
                if(((t.get(i-2)).compareTo(t.get(i-1))==0) && ((t.get(i-1)).compareTo(t.get(i))==0)){    
                    count=4;
                    break;
                }
            }
        }        

        return count;
                
      }
            

    
    public boolean checkFlush(ArrayList<Card> t){
        int i = 1;
        boolean condition = true;
        while(condition == true && i< t.size()){
            if((t.get(i-1)).compareSuit(t.get(i))== -1){
                condition = false;
            }
            i++;
        }
        return condition;
        
    }
    
    public int checkStraight(ArrayList<Card> t){
        int typeOfHand = 0;
        int firstIndex = 0;
        int i = 1;
        boolean condition = true; 
        while(condition == true && i<t.size()){
            if((t.get(i-1)).compareTo(t.get(i))!=-2){
                condition = false;
            }
            if((t.get(i)).getRank() == 1 && i == 4){
                condition = true;
            }
            i++;
        }
        if(condition == true && checkFlush(t)==true) {
            typeOfHand = 1;
            if(((t.get(firstIndex)).getRank()) ==10){
                typeOfHand = 3;
            }
        }else if(condition == true){
            typeOfHand = 2;
        }else {
            typeOfHand= -1;
        }
        
        return typeOfHand;
    }
        

}
                     



