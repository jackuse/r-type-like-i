package game;

public class Spagettie extends Shot {
	Player p = Player.getInstance(1);
	int speedTack;

	public Spagettie () { }

	public Spagettie(float x, float y) {
		super(x,y,60,20);
		id = 24;
		speed=20;
		speedTack = 5;
	}

	//	public void next(int kFrame  )
	//	{
	//		if (kFrame==0)
	//			kFrame++;
	//		if (kFrame==1)
	//			kFrame=0;
	//
	//	}

	public void nastyGo()
	{
		x-=speed;
		if(p.getShip().getX()<x+100){
			if(p.getShip().getY()<y){
				y-=speedTack;
			}else{
				y+=speedTack; 
			}
		}
	}

}
