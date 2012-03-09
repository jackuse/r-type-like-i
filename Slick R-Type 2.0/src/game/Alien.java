package game;

/**
 * Class Alien
 */
public class Alien extends Vaisseau {

	//
	// Fields
	//
	protected boolean move = true;
	protected int pdv = 2;
	protected float speed = 2;

	//
	// Constructors
	//
	public Alien () { };

	public Alien (float x, float y) { 
		super(x,y,64,64);
		id = 10;
	};
	
	public void move(){
		x-=speed;
		
	}

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
