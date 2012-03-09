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
	private boolean invicible = false;
	private boolean born = true;


	//
	// Constructors
	//
	public VaisseauJoueur () { 
		speed = 10;// Rapidité de tire ? dans un tableau
		pdv = 100;
		w=90;
		h=90;
		x=0;
		y= 240;
		armeSelect = 21;

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


	public boolean isInvicible() {
		return invicible;
	}

	public void setInvicible(boolean invicible) {
		this.invicible = invicible;
		System.out.println("invincible " + invicible);
	}

	public void setPdv ( int pdv ) {
		if(!invicible){
			this.pdv = pdv;
			System.out.println("aie "+pdv);
		}
		System.out.println("invincible " + invicible);
	}

	public void setNArme(boolean next) {
		if(next){
			if(armeSelect < 22)
				armeSelect++;
			else
				armeSelect = 21;
		}else{
			if(armeSelect > 21)
				armeSelect--;
			else
				armeSelect = 22;
		}

	}


	//
	// Other methods
	//

	public void rest(){
		speed = 10;
		pdv = 100;
		w=90;
		h=90;
		x=0;
		y= 240;
		armeSelect = 21;
		born = true;
		invicible = true;
	}



	public boolean isBorn() {
		return born;
	}
	
	public boolean setBorn(boolean b) {
		return born = b;
	}
}
