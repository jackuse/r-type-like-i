package jeu;


/**
 * Class Vaisseau
 */
public class Vaisseau extends Objet {

  //
  // Fields
  //

  protected boolean move;
  protected int pdv;
  protected float speed;
  
  //
  // Constructors
  //
  public Vaisseau () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of move
   * @param newVar the new value of move
   */
  protected void setMove ( boolean newVar ) {
    move = newVar;
  }

  /**
   * Get the value of move
   * @return the value of move
   */
  protected boolean getMove ( ) {
    return move;
  }

  /**
   * Set the value of pdv
   * @param newVar the new value of pdv
   */
  protected void setPdv ( int newVar ) {
    pdv = newVar;
  }

  /**
   * Get the value of pdv
   * @return the value of pdv
   */
  protected int getPdv ( ) {
    return pdv;
  }

  /**
   * Set the value of speed
   * @param newVar the new value of speed
   */
  protected void setSpeed ( float newVar ) {
    speed = newVar;
  }

  /**
   * Get the value of speed
   * @return the value of speed
   */
  protected float getSpeed ( ) {
    return speed;
  }

  //
  // Other methods
  //

  /**
   */
  public void go(  )
  {
  }


}
