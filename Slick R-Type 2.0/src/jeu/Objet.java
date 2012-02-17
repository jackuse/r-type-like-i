package jeu;

import java.util.*;


/**
 * Class Objet
 * 
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
   * Retourne la valeur de  x
   * @return la valeur de x
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
   * Retourne la valeur de y
   * @return la valeur de y
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
   * Retourne la valeur de w
   * @return la valeur de w
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
   * Retourne la valeur de h
   * @return la valeur de h
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
   * Retourne la valeur de id
   * @return la valeur de id
   */
  public int getId ( ) {
    return id;
  }

  //
  // Other methods
  //



  /**
   * Detection des collisions entre 2 objets.
   * Revoi true si collision sinon false.
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
