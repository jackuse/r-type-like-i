package jeu;

import java.util.*;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


/**
 * Class Controleur
 */
public class Controleur extends BasicGame {

  //
  // Fields
  //

  public Random rand;
  private float bgSpeed;
  private float posXBg1;
  private float posXBg2;
  private boolean speedUp;
  
  //
  // Constructors
  //
  public Controleur () { 
	  
	  super("R-Type Like It !");
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of rand
   * @param newVar the new value of rand
   */
  public void setRand ( Random newVar ) {
    rand = newVar;
  }

  /**
   * Get the value of rand
   * @return the value of rand
   */
  public Random getRand ( ) {
    return rand;
  }

  /**
   * Set the value of bgSpeed
   * @param newVar the new value of bgSpeed
   */
  private void setBgSpeed ( float newVar ) {
    bgSpeed = newVar;
  }

  /**
   * Get the value of bgSpeed
   * @return the value of bgSpeed
   */
  private float getBgSpeed ( ) {
    return bgSpeed;
  }

  /**
   * Set the value of posXBg1
   * @param newVar the new value of posXBg1
   */
  private void setPosXBg1 ( float newVar ) {
    posXBg1 = newVar;
  }

  /**
   * Get the value of posXBg1
   * @return the value of posXBg1
   */
  private float getPosXBg1 ( ) {
    return posXBg1;
  }

  /**
   * Set the value of posXBg2
   * @param newVar the new value of posXBg2
   */
  private void setPosXBg2 ( float newVar ) {
    posXBg2 = newVar;
  }

  /**
   * Get the value of posXBg2
   * @return the value of posXBg2
   */
  private float getPosXBg2 ( ) {
    return posXBg2;
  }

  /**
   * Set the value of speedUp
   * @param newVar the new value of speedUp
   */
  private void setSpeedUp ( boolean newVar ) {
    speedUp = newVar;
  }

  /**
   * Get the value of speedUp
   * @return the value of speedUp
   */
  private boolean getSpeedUp ( ) {
    return speedUp;
  }

  //
  // Other methods
  //

  /**
   */
  public void Jeu(  )
  {
  }


  /**
   * @param        gc
   */
  public void init( GameContainer gc )
  {
  }


  /**
   * @param        gc
   * @param        delta
   */
  public void update( GameContainer gc, int delta )
  {
  }


  /**
   * @param        container
   * @param        g
   */
  public void render( GameContainer container, Graphics g )
  {
  }


  /**
   * @param        args
   */
  public static void main( String[] args )
  {
  }


}
