package game;

/**
 * Class Alien
 */
public class Alien extends Ship {

	//
	// Fields
	//
	//protected boolean move = true;
	protected int pdv = 1;
	protected float speed = 5;
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
		else if (behavior==3)
		{
			x-=speed;
			y+=speed;
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
