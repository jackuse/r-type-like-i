package test;


import java.util.*;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;

import java.awt.Font;


public class Jeu extends BasicGame {
	Image land = null;
	Image land2 = null;
	Image plane = null;
	float shipX = 10;
	float shipY = 250;
	ArrayList<Tire> tires;
	int delaiTire=0;
	float shipSpeed = 10;
	float bgSpeed = 1;
	float xl =0;
	float xl2 = 800;
	boolean  speedUp = false;
	ArrayList<Alien> aliens;
	ArrayList<Explosion> explo;
	Random rand = new Random();
	String fontPath = "Verdana";
	UnicodeFont uFont;
	Image board = null;
	int score=0;


	public Jeu() {
		super("R-Type Clone");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		//gc.setShowFPS(false);
		gc.setTargetFrameRate(60);
		land = new Image("data/land.jpg");
		land2 = new Image("data/land.jpg");
		plane = new Image("data/plane.png");
		plane.setRotation(90.0f);
		tires = new ArrayList<Tire>();
		aliens = new ArrayList<Alien>();
		explo = new ArrayList<Explosion>();
		gc.setMaximumLogicUpdateInterval(20);
		gc.setMinimumLogicUpdateInterval(20);
		uFont = new UnicodeFont("data/Destroyed Aero.ttf" , 20, false, false);
//		gc.getGraphics().setColor(org.newdawn.slick.Color.black);
//		uFont.addAsciiGlyphs(); //marche
//		uFont.addGlyphs(400, 600);
//		uFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE)); 
//		uFont.loadGlyphs(); 
//		board = new Image (30,800);//ne marche pas
//		board.getGraphics().setBackground(new Color (0,0,0,0));
		//board.getGraphics().clear();
	}

	@Override
	public void update(GameContainer gc, int delta)
			throws SlickException {
				
		//////////////////////////////////////// LES COMMANDES /////////////////////////////////////////

		Input input = gc.getInput(); // On récupére les input


		// Les commandes de déplacements
		if(input.isKeyDown(Input.KEY_Z))
		{
			shipY-=shipSpeed;
			if(shipY<-19)
				shipY+=shipSpeed;
		}
		if(input.isKeyDown(Input.KEY_S))
		{
			shipY+=shipSpeed;
			if(shipY+plane.getHeight()>599)
				shipY-=shipSpeed;
		}
		if(input.isKeyDown(Input.KEY_Q))
		{
			shipX-=shipSpeed;
			if(shipX<-8)
				shipX+=shipSpeed;
		}
		if(input.isKeyDown(Input.KEY_D))
		{
			shipX+=shipSpeed;
			if(shipX+plane.getWidth()>810)
				shipX-=shipSpeed;
		}
		
		//Commande laser
		if(input.isKeyDown(Input.MOUSE_LEFT_BUTTON))
		{

		}
		
		// Commande missile
		if(input.isKeyDown(Input.MOUSE_RIGHT_BUTTON))
		{
			
		}

		// Commande speed UP !!
		if(input.isKeyDown(Input.KEY_LCONTROL))
		{
			bgSpeed = 10;
			speedUp= true;
		}

		if(!input.isKeyDown(Input.KEY_LCONTROL) && speedUp)
		{
			bgSpeed = 1;
			speedUp = false;
		}


		// Commande du tire
		if(input.isKeyDown(Input.KEY_SPACE))
		{
			delaiTire-=delta;
			if(delaiTire < 0){
				tires.add(new Tire(shipX,shipY));
				delaiTire = 100;
			}
		}
		
		///////////////////////////////////////FIN DES COMMANDES ///////////////////////////////////////
		
		
		// Défilement du background
		xl-=bgSpeed;
		xl2-=bgSpeed;
		if(xl<-800){
			xl=799;
			xl2=-1;
		}
		if(xl2<-800){
			xl2=799;
			xl=-1;
		}

		////////////////////////////////////////LES TRAITEMENTS ////////////////////////////////////////

		// Traitement des missiles
		Iterator<Tire> itTir = tires.iterator();
		while(itTir.hasNext()){
			Tire t= ((Tire) itTir.next());
			t.go();
			if(t.getX()>800) // Il il depasse de l'écran on dit qu'il sont invisible
				t.setVisible(false); 
			Alien ac = ACollision(t); // SI colision on détruit le missile et l'alien
			if(ac != null){
				explo.add(new Explosion(ac.getX(), ac.getY()));
				aliens.remove(ac);
				score++;
				itTir.remove();
				ac =null;
			}
			else if(!t.estVisible())
				itTir.remove();
		}






		// Traitement d'explosion
		Iterator<Explosion> itexp = explo.iterator();
		while(itexp.hasNext()){ 
			Explosion e=  itexp.next();
			// on fait avancer les explosions
			e.next();
			if(!e.estvisible())
				itexp.remove();
		}
		////////////////////////////////////// FIN DES TRAITEMENTS //////////////////////////////////////

		///////////////////////////////////////// ZONE DE TEST //////////////////////////////////////////
		/* Test explosions
				if(explo.size()<10000 ){
					for(int i=0;i<500;i++)
					explo.add(new Explosion(300-45+rand.nextFloat()*(700-300+45),-45+rand.nextFloat()*(500+45)));
					//System.out.println("Un alien arrive");
				}
		//*/

		/* Test missiles
		if(tires.size()<100){
			for(int i=0;i<15;i++)
				tires.add(new Tire(10,rand.nextFloat()*(500-0)));
			//System.out.println("Un alien arrive");
		}
		//*/

		//* Test alien
		if(aliens.size()<20 ){
			//aliens.add(new Alien(600,300));
			for(int i=0;i<1;i++)
				aliens.add(new Alien(300+rand.nextFloat()*(700-300),rand.nextFloat()*(500-0)));
			//System.out.println("Un alien arrive");
		}//*/
		
		
		///////////////////////////////////////FIN ZONE DE TEST//////////////////////////////////////////////////



	}

	private Alien ACollision(Tire t){
		if(!aliens.isEmpty()){
			Iterator<Alien> it = aliens.iterator();
			while(it.hasNext()){
				Alien a =((Alien) it.next());
				if(collision(t,a)){
					return a;
				}
			}
		}
		return null;
	}
	private boolean collision(Tire t,Alien a) {
		if((a.getX() >= t.getX() + t.sizeW())         	// trop à droite
				|| (a.getX() + a.sizeW() <= t.getX())   // trop à gauche
				|| (a.getY() >= t.getY() + t.sizeH())   // trop en bas
				|| (a.getY() + a.sizeH() <= t.getY()))  // trop en haut 
			return false; 
		else{
			//			System.out.println("col "+(a.getX() >= t.getX() + t.sizeW()));
			return true; 
		}
	}



	@SuppressWarnings("null")
	@Override
	public void render(GameContainer container, Graphics  g)
			throws SlickException {
		
		//Background
		land.draw(xl, 0);
		land.draw(xl2, 0);

		//Tableaux de bord
//		g.setColor(org.newdawn.slick.Color.black);
		Rectangle board1 = new Rectangle (0, 571, 801, 30);
	    g.setColor(new Color (0.2f, 0.2f, 0.2f));
	    g.fill(board1);
		//g.drawRect(0, 0, 400, 30);
//		g.drawImage(board, 100,100);
		g.setColor(org.newdawn.slick.Color.white);
//		uFont.drawString(50,10, "nb Explosion ="+nbEx);
//		uFont.drawString(100,10,"Explosion :"+nbEx,	org.newdawn.slick.Color.black);
		g.drawString("Explosion :"+explo.size(), 10,575);
		g.drawString("Alien :"+aliens.size(), 150,575);
		g.drawString("Missile :"+tires.size(), 300,575);
		g.drawString("Score :"+score, 600,575);
		//Ajouter un timer

		// On affiche les missiles
		if(!tires.isEmpty()){
			Iterator<Tire> it = tires.iterator();
			while(it.hasNext()){
				Tire t =((Tire) it.next());
				if(t.estVisible())
					t.getImage().draw(t.getX()+10,t.getY()+38);
			}
		}

		// ON affiche les aliens
		if(!aliens.isEmpty()){
			Iterator<Alien> it = aliens.iterator();
			while(it.hasNext()){
				Alien a =((Alien) it.next());
				a.getImage().draw(a.getX()+a.sizeW()/2,a.getY()+a.sizeH()/2);
			}
		}
		plane.draw(shipX, shipY); // On affiche l'avion

		// On affiche les explosions
		float[] cadre;
		if(!explo.isEmpty()){
			Iterator<Explosion> it2 = explo.iterator();
			while(it2.hasNext()){
				Explosion e =((Explosion) it2.next());
				cadre = e.getCadre();
				if(cadre != null)
					e.getImage().getSubImage((int)cadre[0],(int)cadre[1],(int)cadre[2],(int)cadre[3]).draw(e.getX()+cadre[2]/3,e.getY()+cadre[3]/3,1.5f);
				//					e.getImage().draw(e.getX()-cadre[2]/2,e.getY()-cadre[3]/2,cadre[0],cadre[1],cadre[0]+cadre[2],cadre[1]+cadre[3]);
			}

		}




	}

	public static void main(String [] args) throws SlickException {
			AppGameContainer app = new AppGameContainer(new Jeu());
			app.setDisplayMode(800, 600, false);
			app.start();

	}
}
