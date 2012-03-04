package game;

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
  public Objet (float x, float y, float w, float h) { 
	  this.x = x;
	  this.y =y;
	  this.h=h;
	  this.w=w;
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
  public boolean collision(Objet a) {
//      System.out.println("it's working");
//	  System.out.println("x de l'ennemi :"+a.getX()+" y de l'ennemi :"+a.getY());
//      System.out.println("w de l'ennemi :"+a.getW()+" h de l'ennemi :"+a.getH());
//	  System.out.println("x du missile :" +x+" y du missile :"+ y);
//	  System.out.println("w du missile :" +w+" h du missile :"+ h);
	  if((a.getX() >= x + w)           // trop à droite
                      || (a.getX() + a.getW() <= x)   // trop à gauche
                      || (a.getY() >= y + h)   // trop en bas
                      || (a.getY() + a.getH() <= y))  // trop en haut 
              return false; 
      else{
              //                      System.out.println("col "+(a.getX() >= t.getX() + t.sizeW()));
              return true; 
      }
  
 /* public boolean collision( Objet ob )
  {
	  return
			  x>=ob.getX()+ob.getW()-1 && x+w-1<=ob.getX() &&
			  y>=ob.getY()+ob.getH()-1 && y+h-1<=ob.getY();
	  
		if((ob.getX() >= x + w)         // trop à droite
				|| (ob.getX()+ob.getW() <= x)   		// trop à gauche
				|| (ob.getY() >= y + h) // trop en bas
				|| (ob.getY() + ob.getH() <= y)) 		// trop en haut 
			return false; 
		else{
			return true; 
		
	
  }*/

  }
}
