package jeu;

import java.util.*;


/**
 * Class Tir
 */
public class Tir extends Objet {

  //
  // Fields
  //

  protected boolean visible = true;
  protected float speed;
  protected int degat;
  protected int delaiTir;
  
  //
  // Constructors
  //
  public Tir () { };
  
  public Tir (float x, float y, float w, float h) { 
	  super(x,y,w,h);
	  id = 20;
	  speed=13;
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
   * Set the value of delaiTir
   * @param newVar the new value of delaiTir
   */
  protected void setDelaiTir ( int newVar ) {
    delaiTir = newVar;
  }

  /**
   * Get the value of delaiTir
   * @return the value of delaiTir
   */
  protected int getDelaiTir ( ) {
    return delaiTir;
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
   * Fait avancer le tire de sa vitesse speed.
   */
  public void go(  )
  {
	  x+=speed;
  }


}
