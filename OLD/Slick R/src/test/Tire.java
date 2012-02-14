package test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tire {
	float x;
	float y;
	Image i;
	boolean visible;
	
	
	public Tire(float x,float y) throws SlickException{
		this.x=x;
		this.y=y;
		i = new Image("data/rocket.png");
		i.setRotation(90.0f);
		visible = true;
	}

	public void go() {
		x+=15;
		
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

	public boolean estVisible() {
		return visible;
	}

	public void setVisible(boolean b) {
		visible = b;
		
	}
}
