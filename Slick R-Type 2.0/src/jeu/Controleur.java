package jeu;

import java.util.*;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;



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
	private Vue vue;
	private ArrayList<Objet> objet;
	private int affichage; // 0:Menu  1:Option  2:Jeu  3:Pause  4:HightScore
	private int param[];
	private Joueur joueur[];

	//
	// Constructors
	//
	public Controleur (){ 

		super("R-Type Like It !");
		
		
		affichage = 2;
		joueur= new Joueur[1];
		joueur[0]= new Joueur();
		
		param = new int[10];
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
	 * @param        gc
	 */
	public void init( GameContainer gc ) throws SlickException 
	{
		vue = new Vue();
		gc.setTargetFrameRate(60);
		gc.setMaximumLogicUpdateInterval(20);
		gc.setMinimumLogicUpdateInterval(20);
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
		
		//On compte les animations pour l'affichage
		//param[1]=objet.
		param[0]=joueur[0].getScore();
				
				
		// Suivant le type d'affichae on affiche le bon		
		switch (affichage) {
		case 0:
			
			break;
		case 1:
			
			break;
		case 2:
			vue.renderBg(g, posXBg1, posXBg2,param);
			//vue.renderTire(g, objet);
			//vue.renderVaisseau(g,objet);
			//vue.renderExplosion(g, explo);
			
			break;
		case 3:
			
			break;

		default:
			break;
		}

		
	}


	/**
	 * @param        args
	 * @throws SlickException 
	 */
	public static void main( String[] args ) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new Controleur());
		app.setDisplayMode(800, 600, false);
		app.start();
	}


}
