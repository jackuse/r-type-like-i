package test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Alien {
	float x;
	float y;
	Image i =null;
	
	public Alien(float x,float y) throws SlickException{
		i = new Image("data/alien2.png");
		i.setRotation(270.0f);
		this.x=x;
		this.y=y;
	}
	
	public int sizeH(){
		return i.getHeight();
	}
	public int sizeW(){
		return i.getWidth();
	}
	public Image getImage() {
        return i;
    }
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
