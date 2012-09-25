package game;

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
	private boolean visible = true;

	//
	// Constructors
	//
	public Explosion () { super();};
	
	public Explosion (float x, float y) { 
		super(x,y);
		cadre = new float[]{0,0,61,61}; // x,y,w,h
	};

	//
	// Methods
	//


	//
	// Accessor methods
	//

	/**
	 * Get the value of cadre
	 * @return the value of cadre
	 */
	public float[] getCadre ( ) {
		if(cadre[1]<65*4)
			return cadre;
		else{
			return null;
		}
	}


	/**
	 * Get the value of visible
	 * @return the value of visible
	 */
	public boolean estVisible( ) {
		return visible;
	}

	//
	// Other methods
	//

	/**
	 */
	public void next(  )
	{
		if(j==3){
			j=0;
			k++;
		}		
		cadre[0]=65*j;
		j++;
		cadre[1]=65*k;
		
		if(k==4)
			visible = false;
	}

}
