package game;

import java.util.*;

import org.newdawn.slick.Image;


/**
 * Class Skin
 */
public class Skin {

  //
  // Fields
  //

  private Image background1 = null;
  private Image background2 = null;
  private Image joueur;
  private Image alien;
  private Image laser;
  private Image missile;
  private String dossierSkin = "data/skin1";
  
  //
  // Constructors
  //
  public Skin () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of background1
   * @param newVar the new value of background1
   */
  private void setBackground1 ( Image newVar ) {
    background1 = newVar;
  }

  /**
   * Get the value of background1
   * @return the value of background1
   */
  private Image getBackground1 ( ) {
    return background1;
  }

  /**
   * Set the value of background2
   * @param newVar the new value of background2
   */
  private void setBackground2 ( Image newVar ) {
    background2 = newVar;
  }

  /**
   * Get the value of background2
   * @return the value of background2
   */
  private Image getBackground2 ( ) {
    return background2;
  }

  /**
   * Set the value of joueur
   * @param newVar the new value of joueur
   */
  private void setJoueur ( Image newVar ) {
    joueur = newVar;
  }

  /**
   * Get the value of joueur
   * @return the value of joueur
   */
  private Image getJoueur ( ) {
    return joueur;
  }

  /**
   * Set the value of alien
   * @param newVar the new value of alien
   */
  private void setAlien ( Image newVar ) {
    alien = newVar;
  }

  /**
   * Get the value of alien
   * @return the value of alien
   */
  private Image getAlien ( ) {
    return alien;
  }

  /**
   * Set the value of laser
   * @param newVar the new value of laser
   */
  private void setLaser ( Image newVar ) {
    laser = newVar;
  }

  /**
   * Get the value of laser
   * @return the value of laser
   */
  private Image getLaser ( ) {
    return laser;
  }

  /**
   * Set the value of missile
   * @param newVar the new value of missile
   */
  private void setMissile ( Image newVar ) {
    missile = newVar;
  }

  /**
   * Get the value of missile
   * @return the value of missile
   */
  private Image getMissile ( ) {
    return missile;
  }

  /**
   * Set the value of dossierSkin
   * @param newVar the new value of dossierSkin
   */
  private void setDossierSkin ( String newVar ) {
    dossierSkin = newVar;
  }

  /**
   * Get the value of dossierSkin
   * @return the value of dossierSkin
   */
  private String getDossierSkin ( ) {
    return dossierSkin;
  }

  //
  // Other methods
  //

  /**
   * Retourne l'image demander suivant le code suivant
   * 1 : backgroud 1
   * 2 : backgroud 2
   * 3 : joueur
   * 4 : alien
   * 5: missile
   * 
   * 
   * //Posssible de le faire avec des strings
   * @return       Image
   * @param        noImage
   */
  public Image getImage( int noImage )
  {
	return alien;
  }


  /**
   * Teste si les fichier existe dans ce dossier et les charges
   * et revoi true
   * sinon revoi false
   * @return       boolean
   * @param        chemin
   */
  public boolean changeSkin( String chemin )
  {
	return false;
  }


  /**
   * Permet de changer une image comme l'image de fond suivant les niveaux
   * @return       boolean
   * @param        noImage
   * @param        idImage
   */
  public boolean setImage( int noImage, int idImage )
  {
	return false;
  }


}
