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
  protected void setX ( float newVar ) {
    x = newVar;
  }

  /**
   * Get the value of x
   * @return the value of x
   */
  protected float getX( ) {
    return x;
  }

  /**
   * Set the value of y
   * @param newVar the new value of y
   */
  protected void setY ( float newVar ) {
    y = newVar;
  }

  /**
   * Get the value of y
   * @return the value of y
   */
  protected float getY ( ) {
    return y;
  }

  /**
   * Set the value of w
   * @param newVar the new value of w
   */
  protected void setW ( float newVar ) {
    w = newVar;
  }

  /**
   * Get the value of w
   * @return the value of w
   */
  protected float getW ( ) {
    return w;
  }

  /**
   * Set the value of h
   * @param newVar the new value of h
   */
  protected void setH ( float newVar ) {
    h = newVar;
  }

  /**
   * Get the value of h
   * @return the value of h
   */
  protected float getH ( ) {
    return h;
  }

  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  protected void setId ( int newVar ) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  protected int getId ( ) {
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
  public boolean collision( Objet objet )
  {
	  
	  return true;
  }


}
