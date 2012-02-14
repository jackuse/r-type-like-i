package jeu;

/**
 * Class Explosion
 */
public class Explosion extends Objet {

  //
  // Fields
  //

  private float[] cadre;
  private int j;
  private int k;
  private boolean visible;
  
  //
  // Constructors
  //
  public Explosion () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of cadre
   * @param newVar the new value of cadre
   */
  private void setCadre ( float[] newVar ) {
    cadre = newVar;
  }

  /**
   * Get the value of cadre
   * @return the value of cadre
   */
  private float[] getCadre ( ) {
    return cadre;
  }

  /**
   * Set the value of j
   * @param newVar the new value of j
   */
  private void setJ ( int newVar ) {
    j = newVar;
  }

  /**
   * Get the value of j
   * @return the value of j
   */
  private int getJ ( ) {
    return j;
  }

  /**
   * Set the value of k
   * @param newVar the new value of k
   */
  private void setK ( int newVar ) {
    k = newVar;
  }

  /**
   * Get the value of k
   * @return the value of k
   */
  private int getK ( ) {
    return k;
  }

  /**
   * Set the value of visible
   * @param newVar the new value of visible
   */
  private void setVisible ( boolean newVar ) {
    visible = newVar;
  }

  /**
   * Get the value of visible
   * @return the value of visible
   */
  private boolean getVisible ( ) {
    return visible;
  }

  //
  // Other methods
  //

  /**
   */
  public void next(  )
  {
  }

}
