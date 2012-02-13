package test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Explosion {
	float x;
	float y;
	Image i =null;
	float[] cadre;
	int j = 0;
	int k = 0;
	boolean visible = true;

	public Explosion(float x, float y) throws SlickException{
		i = new Image("data/explosion.png");
		this.x=x;
		this.y=y;
		cadre = new float[]{0,0,61,61}; // x,y,w,h
		
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Image getImage() {
		return i;
	}
	
	public boolean estvisible(){
		return visible;
	}

	public float[] aff(){

		if(j==3){
			j=0;
			k++;
		}		
		cadre[0]=65*j;
		j++;
		cadre[1]=65*k;
		
		//System.out.println("cadre j = "+j+" et k = "+k);

		if(k<4)
			return cadre;
		else{
			visible = false;
			return null;
		}

	}
	public void next(){
		
		if(j==3){
			j=0;
			k++;
		}		
		cadre[0]=65*j;
		j++;
		cadre[1]=65*k;
		
//		System.out.println("cadre j = "+j+" et k = "+k);
		
		if(k==4)
			visible = false;
		
	}
	
	public float[] getCadre(){
		if(cadre[1]<65*4)
			return cadre;
		else{
			return null;
		}
	}
}
