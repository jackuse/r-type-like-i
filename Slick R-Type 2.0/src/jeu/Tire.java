package jeu;

import java.util.*;


/**
 * Class Tire
 */
public class Tire extends Objet {

  //
  // Fields
  //

  protected boolean visible;
  protected float speed;
  protected int degat;
  protected int delaiTire;
  
  //
  // Constructors
  //
  public Tire () { };
  
  public Tire (float x, float y) { 
	  super(x,y);
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of visible
   * @param newVar the new value of visible
   */
  protected void setVisible ( boolean newVar ) {
    visible = newVar;
  }

  /**
   * Get the value of visible
   * @return the value of visible
   */
  protected boolean getVisible ( ) {
    return visible;
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

  /**
   * Set the value of degat
   * @param newVar the new value of degat
   */
  protected void setDegat ( int newVar ) {
    degat = newVar;
  }

  /**
   * Get the value of degat
   * @return the value of degat
   */
  protected int getDegat ( ) {
    return degat;
  }

  /**
   * Set the value of delaiTire
   * @param newVar the new value of delaiTire
   */
  protected void setDelaiTire ( int newVar ) {
    delaiTire = newVar;
  }

  /**
   * Get the value of delaiTire
   * @return the value of delaiTire
   */
  protected int getDelaiTire ( ) {
    return delaiTire;
  }

  //
  // Other methods
  //

  /**
   * @return       boolean
   */
  public boolean estVisible(  )
  {
	return visible;
  }


  /**
   */
  public void go(  )
  {
  }


}
