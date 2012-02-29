package game;

import java.util.*;


/**
 * Class Joueur
 */
public class Joueur {

  //
  // Fields
  //

  private int score;
  private VaisseauJoueur v = new VaisseauJoueur();
  
  //
  // Constructors
  //
  public Joueur () { 
	  score = 0;
	  
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of score
   * @param newVar the new value of score
   */
  public void setScore ( int newVar ) {
    score = newVar;
  }

  /**
   * Get the value of score
   * @return the value of score
   */
  public int getScore ( ) {
    return score;
  }
  
  /**
   * Get the value of score
   * @return the value of score
   */
  public VaisseauJoueur getV ( ) {
	  return v;
  }

  //
  // Other methods
  //

}
