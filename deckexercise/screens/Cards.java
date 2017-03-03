//  Cards.java

/**
 *  This is a class for constructin a deck of cards
 *
 *  @author     Eric Ehmann
 *  @version 2_11_2017
 */
import java.util.*;

public class Cards {
    
  protected ArrayList<Integer> deck = new ArrayList<Integer>();
  protected int deckSize;
  protected int counter;
  final int suitRun = 13;
  public String[]circuit;
  final String[] suitlist =new String[]{"Clubs", "Diamonds", "Hearts", "Spades"};
  /**
  *  This is the constructor method for creating a deck of cards
  *  
  */
 public Cards() {
    counter=0;
    circuit = new String[4];
    this.deckSize=52;
    for(int i=0; i<deckSize; i++){
      deck.add(i);
    }
  }
  /**
  * This method will return the number of cards remaining.
  * @return size of arrayList containing the numeric representation of the cards
  */
  public int countCards() {
    return deck.size();
  }


      /*
  * This method will return the number of the counter, which represents which suit a movement is being chosen for
  */
  public int getCounter() {
    return counter;
  }
  /**
  * This method will increase the counter being used to decide which suit is being chosen
  */
  public void increaseCounter() {
    counter++;
  }
  /**
  * This method will decrease the counter being used to determine which suit a movement is being chosen for
  */
  public void decreaseCounter() {
    if(counter>0){
      counter--;
    }
  }
  /**
  * This method will set one of the four movements the user will be doing throughout the workout
  *@param suit an integer representing which card suit the movement will be associated with
  *@param movement a string with the name of a a movement on it.
  */
  public void setMovement (int suit, String movement) {
    circuit[suit]=movement;
  }

    /** 
  * This method will take a number and return a string based on a previous decided movement.
  *
  *@param int number reprsenting circuit
  *@return string stating the movement
  */
  public String getMovement(int circuitNumber) {
    return circuit[circuitNumber];
  }

  /** 
  * this will get a random card from the deck and remove it from the remaining cards
  *
  * @ return int
  */
  public int nextCard() {
    int randomCard = (int )(Math.random() * countCards());
    int flipped=deck.get(randomCard);
    deck.remove(randomCard);
    return flipped;
  }

   /**
  * This method will get the value of the card within a suit.
  *
  *@param int the number of the card
  *@return int with value of the card within the suit
  */
    public int getNumber(int cardNumber) {
    return cardNumber%suitRun;
  }

  /**
    * this method will take the number of the card and determine what the face value of the card will be
    *
    *@param int gn the result from getNumber.  
    *
    *@return String 
    */

  public String getCardFace(int faceNumber) {
    switch (faceNumber) {
      case 9:  return "Jack";
      case 10: return "Queen";
      case 11: return "King";
      case 12: return "Ace";
      default: return String.valueOf(faceNumber+2);
      } // will add two because value can be 0, but lowest card is 2
    }

  
  /**
  * This method will find how many times a full run of a suit has been completed, by dividing the value by the number of cards in any suit
  *
  *@param int the number of the card
  *@return int value representing number of full runs.
  */
  public int getSuitValue(int cardNumber) {
    return cardNumber/suitRun;
  }
  /** 
    * this method will take the number of the card and determine what the suit will be
    *
    *@param int nc the number of the card
    *
    *@return String the suit 
    */
  public String getSuit(int suitValue) {
      return suitlist[suitValue];
    }

    /**
  *
  * This method will take the card value and show the value and suit of the card
  * @return String suit the suit of the card
  * @return String card the value of the card
  */
  public String getCardName(int newCard) {
    return((getCardFace(getNumber(newCard)) + " of " + getSuit(getSuitValue(newCard))));
  }


  /** This method will determine take an int and return the type of movement and number
  * of repetitions that the user must do.
  *@param cardNumber a integer representing what card number it is.
  *@return String stating the movement. 
  */
  public String getSet (int cardNumber) {
    return ((getNumber(cardNumber)+2) + " " +getMovement(getSuitValue(cardNumber)));
  }

}