package game;


/**
 * Class Vaisseau
 */
public class Ship extends Objet {

  //
  // Fields
  //

  protected boolean move = false;
  protected int pdv = 1;
  protected float speed = 1;
  
  //
  // Constructors
  //
  public Ship () { };
  
  public Ship (float x, float y,float w, float h) { 
	  super(x,y,w,h);
	  id = 0;
  };
  
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
  public void setMove ( boolean newVar ) {
    move = newVar;
  }

  /**
   * Get the value of move
   * @return the value of move
   */
  public boolean getMove ( ) {
    return move;
  }

  /**
   * Set the value of pdv
   * @param newVar the new value of pdv
   */
  public void setPdv ( int newVar ) {
    pdv = newVar;
  }

  /**
   * Get the value of pdv
   * @return the value of pdv
   */
  public int getPdv ( ) {
    return pdv;
  }

  /**
   * Set the value of speed
   * @param newVar the new value of speed
   */
  public void setSpeed ( float newVar ) {
    speed = newVar;
  }

  /**
   * Get the value of speed
   * @return the value of speed
   */
  public float getSpeed ( ) {
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
