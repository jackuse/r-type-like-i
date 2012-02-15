package jeu;

import java.util.*;


/**
 * Class Objet
 * Doit on mettre l'attribu visible
 */
public class Objet {

  //
  // Fields
  //

  protected float x;
  protected float y;
  protected float w;
  protected float h;
  protected int id;
  
  //
  // Constructors
  //
  public Objet () { };
  public Objet (float x, float y) { 
	  this.x = x;
	  this.y =y;
	  id = 0;
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of x
   * @param newVar the new value of x
   */
  public void setX ( float newVar ) {
    x = newVar;
  }

  /**
   * Get the value of x
   * @return the value of x
   */
  public float getX( ) {
    return x;
  }

  /**
   * Set the value of y
   * @param newVar the new value of y
   */
  public void setY ( float newVar ) {
    y = newVar;
  }

  /**
   * Get the value of y
   * @return the value of y
   */
  public float getY ( ) {
    return y;
  }

  /**
   * Set the value of w
   * @param newVar the new value of w
   */
  public void setW ( float newVar ) {
    w = newVar;
  }

  /**
   * Get the value of w
   * @return the value of w
   */
  public float getW ( ) {
    return w;
  }

  /**
   * Set the value of h
   * @param newVar the new value of h
   */
  public void setH ( float newVar ) {
    h = newVar;
  }

  /**
   * Get the value of h
   * @return the value of h
   */
  public float getH ( ) {
    return h;
  }

  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setId ( int newVar ) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public int getId ( ) {
    return id;
  }

  //
  // Other methods
  //



  /**
   * @param        x
   * @param        y
   */
  public void Objet( float x, float y )
  {
  }


  /**
   * @return       boolean
   * @param        objet
   */
  public boolean collision( Objet ob )
  {
		if((x >= ob.getX() + ob.getW())         // trop à droite
				|| (x + w <= ob.getX())   		// trop à gauche
				|| (y >= ob.getY() + ob.getH()) // trop en bas
				|| (y + h <= ob.getY())) 		// trop en haut 
			return false; 
		else{
			return true; 
		}
  }


}
