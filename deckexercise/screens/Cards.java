//  Cards.java

/**
 *  This is a class for constructin a deck of cards
 *
 *  @author     Eric Ehmann
 *  @version 2_11_2017
 */
import java.util.*;

public class Cards{
    
  protected ArrayList<Integer> deck = new ArrayList<Integer>();
  protected int deckSize;
  protected int counter;
  final int suitRun = 13;
  public String[]circuit;
  /**
  *  This is the constructor method for creating a deck of cards
  *  
  */
 public Cards(){
    counter=0;
    circuit = new String[4];
    this.deckSize=52;
    for(int i=0; i<deckSize; i++){
      deck.add(i);
    }
  }

  /** 
  * this will get a random card from the deck and remove it from the remaining cards
  *
  * @ return int
  */
  public int nextCard(){
    int randomCard = (int )(Math.random() * deck.size());
    int flipped=deck.get(randomCard);
    deck.remove(randomCard);
    return flipped;

  }
  /**
  *
  * This method will take the card value of show the value and suit of the card
  * @return String suit the suit of the card
  * @return String card the value of the card
  * @override toString
  */
  public String getCardName(String suit, String card){
    String nameCard= suit + " " + card;
    return nameCard;
  }
  

  /**
  * This method will get the value of the card within a suit.
  *
  *@param int the number of the card
  *@return int with value of the card within the suit
  */
    public int getNumber(int nc){
    return nc%suitRun;
  }
  /**
    * this method will take the number of the card and determine what the face value of the card will be
    *
    *@param int nc
    *
    *@return String 
    */

  public String getValue(int gn){
    switch (gn) {
      case 9:  return "Jack";
      case 10: return "Queen";
      case 11: return "King";
      case 12: return "Ace";
      default: return String.valueOf(gn+2);
      } // Goes through all possible numbers add two for starting at 0 and having no 1 of a suit 
    }

  /**
  * This method will find how many times a full run of a suit has been completed, by dividing the value by the number of cards in any suit
  *
  *@param int the value of the card
  *@return int value representing number of full runs.
  */
  public int getSuitValue(int nc){
    return nc/suitRun;
  }
  /** 
    * this method will take the number of the card and determine what the suit will be
    *
    *@param int nc the number of the card
    *
    *@return String the suit 
    */
  public String getSuit(int sV){
    String[] suitlist =new String[]{"Clubs", "Diamonds", "Hearts", "Spades"};
      return suitlist[sV];
    }

  public String getSet (int reps, int suit){
    String words= (reps +2) + " " +circuit[suit];
    return words;
  }

  public void setMovement (int suit, String movement){
    circuit[suit]=movement;
  }

  public void increaseCounter(){
    counter++;
  }
  public void decreaseCounter(){
    if(counter>0){
      counter--;
    }
  }
  public int getCounter(){
    return counter;
  }

}