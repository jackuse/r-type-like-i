package game;


/**
 * Class VaisseauJoueur
 * @author Etienne Grandier-Vazeille
 *
 */
public class VaisseauJoueur extends Vaisseau {

	//
	// Fields
	//

	private int armeSelect;
	private boolean invicible = false;
	private boolean born = true;


	private int decalageX;
	private int decalageY;

	
	


	//
	// Constructors
	//
	public VaisseauJoueur () { 
		speed = 10;// Rapidité de tire ? dans un tableau
		pdv = 100;
		w=30;
		h=30;
		x=30;
		y= 240;
		armeSelect = 21;
		decalageX = 32;
		decalageY = 30;

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
		//System.out.println("invincible " + invicible);
	}

	public void setPdv ( int pdv ) {
		if(!invicible){
			this.pdv = pdv;
			//System.out.println("aie "+pdv);
		}
		//System.out.println("invincible " + invicible);
	}

	/**
	 * Permeet de selectionner l'arme suivante si next=true
	 * sinon on selectionne l'arme précédente
	 * 
	 * @param next
	 */
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

	/**
	 * Réinitialisation du vaisseaux du joueur
	 */
	public void rest(){
		speed = 10;
		pdv = 100;
		w=30;
		h=30;
		x=30;
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
	public int getDecalageX() {
		return decalageX;
	}



	public void setDecalageX(int decalageX) {
		this.decalageX = decalageX;
	}



	public int getDecalageY() {
		return decalageY;
	}



	public void setDecalageY(int decalageY) {
		this.decalageY = decalageY;
	}
}
