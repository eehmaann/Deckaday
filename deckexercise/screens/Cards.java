//  cards.java

/**
 *  This is a class for constructin a deck of cards
 *
 *  @author     Eric Ehmann
 *  @version 2_11_2017
 */
import java.util.*;

public abstract class Cards{
    
  protected ArrayList<Integer> deck = new ArrayList<Integer>();
  protected int deckSize;
  final int suitRun = 13;
  /**
  *  This is the constructor method for creating a deck of cards
  *  
  */
 public Cards(){
    this.deckSize=52;
    for(int i=0; i<deckSize; i++){
      deck.add(i);
    }
  }

   public Cards(int deckSize){
    this.deckSize=deckSize;
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
  public String toString(String suit, String card){
    String nameCard= suit + " " + card;
    return nameCard;
  }
  
  public String toString(int nc){
    String nameCard = "Joker";
    return nameCard;
  }

  /**
  * This method will get the value of the card within a suit.
  *
  *@param int the number of the card
  *@return int with value of the card within the suit
  */
    public int getNum(int nc){
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
    String card;
    switch (gn) {
      case 9:  card = "Jack";
            break;
      case 10:  card = "Queen";
            break;
      case 11:  card = "King";
            break;
      case 12:  card = "Ace";
            break;
      default: card = String.valueOf(gn+2);
            break;
      } // Goes through all possible numbers add two for starting at 0 and having no 1 of a suit 
      return card;
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
    String suit;

    switch(sV){
      case 0: suit="Clubs";
            break;
      case 1: suit="Diamonds";
            break;
      case 2: suit="Hearts";
            break;
      default: suit="Spades";
            break;
      }
      return suit;
  }

}