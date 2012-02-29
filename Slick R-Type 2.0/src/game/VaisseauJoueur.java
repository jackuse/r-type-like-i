package game;


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
	public void setArme ( int newVar ) {
		armeSelect = newVar;
	}

	/**
	 * Get the value of armeSelect
	 * @return the value of armeSelect
	 */
	public int getArme ( ) {
		return armeSelect;
	}

	//
	// Other methods
	//

}