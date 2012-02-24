package jeu;

/**
 * Class Alien
 */
public class Alien extends Vaisseau {

	//
	// Fields
	//
	protected boolean move = true;
	protected int pdv = 2;
	protected float speed = 1;

	//
	// Constructors
	//
	public Alien () { };

	public Alien (float x, float y) { 
		super(x,y,64,64);
		id = 10;
	};

	//
	// Methods
	//


	//
	// Accessor methods
	//

	//
	// Other methods
	//

}
