package game;

public class EnergyBall extends Shot {
	
	public EnergyBall () { }

	public EnergyBall(float x, float y) {
	  super(x,y,25,25);
	  id = 23;
	  speed=28;
	}
	  
	public void next(int kFrame  )
	{
		if (kFrame==0)
			kFrame++;
		if (kFrame==1)
			kFrame=0;

	}
}