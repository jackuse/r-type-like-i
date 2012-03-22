package game;

/**
 * Class Alien
 */
public class Alien extends Vaisseau {

	//
	// Fields
	//
	//protected boolean move = true;
	protected int pdv = 2;
	protected float speed = 2;
	protected int behavior;
	//
	// Constructors
	//
	public Alien () { };

	public Alien (float x, float y, int id, int behav) { 
		super(x,y,64,64);
		behavior=behav;
		this.id = id;
	};
	
	public void move(){
		if (behavior==1)
			x-=speed;
		else if (behavior==2)
		{
			x-=speed;
			y-=speed;
		}
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
