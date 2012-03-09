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
  private int life;
  private int kill;
  private int totalKill;
  
  //
  // Constructors
  //
  public Joueur () { 
	  score = 0;
	  life = 3;
	  kill = 0;
	  
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

public int getLife() {
	return life;
}

public void setLife(int l) {
	life = l;
	
}

public int getKill() {
	return kill;
}

public int getTotalKill(){
	return totalKill;
}

public void incKill() {
	kill++;
	totalKill++;
}

public void restKill() {
	kill = 0;
}

public void restTotalKill() {
	kill = 0;
}

  //
  // Other methods
  //

}
