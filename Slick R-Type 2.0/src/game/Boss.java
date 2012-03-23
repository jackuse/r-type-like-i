package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class Boss
 */
public class Boss extends Ship {

	//
	// Fields
	//
	protected boolean move = true;
	protected int pdv = 50;
	protected float speed = 3;
	boolean comming = true;
	View view = View.getInstance();
	boolean flip = true;
	int cptA = 0;
	private boolean fireOn = false;
	private boolean upMove = false;
	private boolean downMove= false;
	private boolean pauseMove= false;
	protected int behavior;
	
	// Oui le vaisseau mére peut appeler ses sbires !
	private EventList event = EventList.getInstance();
	
	//
	// Constructors
	//
	private Boss () { };
	public Boss (int id) {
		this.id = id;
//		this.w = view.getIBoss(1);
//		this.h = view.getIBoss(1);
		this.x = view.getWidth()+20;
		this.y = view.getHeight()-h/2;
		
	};

	//
	// Methods
	//
	
	public Boss (float x, float y, int id, int behav){
		super(x-200,y,200,400);
		this.id=id;
		behavior=behav;
		this.pdv=400;
		view.setMusic(0);
		view.selectMusic(42);
		view.setMusic(1);
		
	}
	
	public boolean isFire(){
		return fireOn;
	}
	
	
	public void move(){
		if (comming){
			makeCome();
		}
		else{
			//il monte / il descent / il tire vers le joueur
			
			// attaque standard
			if(y>0 && upMove && !downMove ){
				y--;
				fireOn = true;
			}
			else {
				upMove = false;
				downMove = true;
			}
				
			if (y+h<view.getHeight() && downMove ){
				y++;
			}
			else {
				downMove = false;
				pauseMove = true;
				upMove = true;
				fireOn = false;
			}
//			if(upMove && pauseMove && y>view.getHeight()-h/2){
//				y--;
//				System.out.println("etat  3");
//			}else{
//				upMove = false;
//				specialAttak(1,view.getTimer());
//			}
//				
//			if(!upMove && pauseMove ){
//				
//				
//			}else{
//				upMove =true;
//				pauseMove =false;
//			}
			
			// Pellemele
			
			
			
			
		}
	}
	
	private void makeCome() { // arrivé au coup par coup
//		if(flip){
			x-=1;
//			flip = false;
//		}else{
//			cptA++;
//			if(cptA >4)
//				flip = true;
//		}
		
		if(x<view.getWidth()-w-200){
			comming = false;
		}
		
	}

	//
	// Accessor methods
	//

	//
	// Other methods
	//
	public void specialAttak(int specialId,int decalage){
		//LEVEL FILE STRUCTURE : TIME QUANTITY DELAY BEHAVIORID X Y ID*/
		int id=0;
		int time = 0;
		int quantity = 0;
		int delay=0;
		int behavior=0;
		int spawnX=0;
		int spawnY=0;
		Scanner sc2 = null;
		try {
			sc2 = new Scanner(new File("data/special"+specialId +".txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();  

		}	
		while (sc2.hasNextLine()) {

			Scanner s2 = new Scanner(sc2.nextLine());
			boolean b,out= false;
			int i=0;
			while (b = s2.hasNext() && !out) {

				String s = s2.next();
				if (i==0 && s.substring(0, 1).equalsIgnoreCase("#") ){
					out = true;
				}
				else{
					if (i==0)
						time=Integer.parseInt(s);
					if (i==1)
						quantity=Integer.parseInt(s);
					if (i==2)
						delay=Integer.parseInt(s);
					if (i==3)
						behavior=Integer.parseInt(s);
					if (i==4)
						spawnX=Integer.parseInt(s);
					if (i==5)
						spawnY=Integer.parseInt(s);
					if (i==6)
						id=Integer.parseInt(s);
					//System.out.println(s);
					i++;
				}
			}
			if(time!=0 && quantity!=0 && delay!=0 && behavior!=0 && spawnX!=0 && spawnY!=0 && id!=0)
			{	event.add(new TimedEvent(time,quantity,delay+decalage,behavior,spawnX,spawnY,id));
			System.out.println("New Event at time "+time+"ms, quantity "+quantity+",delay "+delay+"ms, behavior "+behavior+"spawn x "+spawnX+" y "+spawnY+"");
			}
		}
	}
	public void reciveDamage(int degat) {
		this.pdv-= degat;		
	}

}
