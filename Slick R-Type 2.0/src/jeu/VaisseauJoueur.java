package jeu;


/**
 * Class VaisseauJoueur
 */
public class VaisseauJoueur extends Vaisseau {

	//
	// Fields
	//

	//	protected boolean move = false;
	//	protected int pdv = 10;
	//	protected float speed = 10;
	private int armeSelect;

	//
	// Constructors
	//
	public VaisseauJoueur () { 
		speed = 10;
		pdv = 10;
		move = false;
		w=130;
		h=120;
		y= 240;
		armeSelect = 22;

	};

	//
	// Methods
	//


	//
	// Accessor methods
	//

	/**
	 * Set the value of speed
	 * @param newVar the new value of speed
	 */
	protected void setArme ( int newVar ) {
		armeSelect = newVar;
	}

	/**
	 * Get the value of speed
	 * @return the value of speed
	 */
	protected int getArme ( ) {
		return armeSelect;
	}

	//
	// Other methods
	//

}
