package game;

public class Bonus extends Objet {

	  protected boolean visible = true;
	  protected float speed;
	  protected int type;
	  /* 1 : life
	   * 2 : medickit
	   * 3 : lvl up wearpon
	   * 
	   */
	  
	  
	  
	  public Bonus (float x, float y, float w, float h,int t) { 
		  super(x,y,w,h);
		  id = 100;
		  speed=13;
		  if(t>0 && t<=3)
			  type = t;
		  else
			  type = 2;
	  }

	public void doEffect(Player joueur) {
		switch (type) {
		case 1:
			joueur.setLife(joueur.getLife()+1);
			break;
		case 2:
			int life = joueur.getShip().getPdv()+30;
			if(life >= 100)
				joueur.getShip().setPdv(100);
			else
				joueur.getShip().setPdv(life);
			break;
		case 3:
			
			break;
		case 4:
			
			break;

		default:
			break;
		}
		
		
		
	};
}
