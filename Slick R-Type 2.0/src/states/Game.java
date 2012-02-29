package states;

import game.Alien;
import game.Explosion;
import game.Joueur;
import game.Laser;
import game.Main;
import game.Missile;
import game.Objet;
import game.Tir;
import game.Vue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();

	private ArrayList<Objet> enemy;
	private ArrayList<Objet> playerProjectile;
	private ArrayList<Objet> nastyProjectile;
	private int param[];
	private Joueur joueur[];
	private float bgSpeed = 1;
	private float posXBg1 = 0;
	private float posXBg2 = 800;
	private boolean speedUp;
	int pauseX = 250;
	int pauseY = 230;
	private ArrayList<Explosion> explo;
	public Random rand = new Random();
	private int delaiTire = 0;
	boolean debug = false;

	public Game(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		vue.initGame();
		playerProjectile = new ArrayList<Objet>();
		nastyProjectile = new ArrayList<Objet>();
		enemy = new ArrayList<Objet>();


		joueur= new Joueur[1];
		joueur[0]= new Joueur();

		param = new int[10];
		explo = new ArrayList<Explosion>();

		debug = true;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderBg(gr, posXBg1, posXBg2);

		for (Iterator<Objet> e = enemy.iterator(); e.hasNext(); ) {
			Objet obE = (Objet) e.next();
			vue.render1Vaisseau(gr, obE,0);
		}


		for (Iterator<Objet> pp = playerProjectile.iterator(); pp.hasNext(); ) {
			Objet obPp = (Objet) pp.next();
			vue.render1Tir(gr, obPp,0);
		}

		vue.renderJoueur(gr,joueur[0].getV());
		vue.renderExplosion(gr, explo);
		vue.renderBoard(gr,param);
		//vue.renderTest(g);
		//vue.renderPause(gc,gr,pauseX,pauseY);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		System.out.println("etat "+sbg.getCurrentStateID()); 
		
		////////////////////////////////////////LES COMMANDES /////////////////////////////////////////
		Input input = gc.getInput(); // On récupére les input


		// Les commandes de déplacements
		if(input.isKeyDown(Input.KEY_Z))
		{
			joueur[0].getV().setY(joueur[0].getV().getY()-joueur[0].getV().getSpeed());
			if(joueur[0].getV().getY()<-19)
				joueur[0].getV().setY(joueur[0].getV().getY()+joueur[0].getV().getSpeed());
		}
		if(input.isKeyDown(Input.KEY_S))
		{
			joueur[0].getV().setY(joueur[0].getV().getY()+joueur[0].getV().getSpeed());
			if(joueur[0].getV().getY()+joueur[0].getV().getH()>599)
				joueur[0].getV().setY(joueur[0].getV().getY()-joueur[0].getV().getSpeed());
		}
		if(input.isKeyDown(Input.KEY_Q))
		{
			joueur[0].getV().setX(joueur[0].getV().getX()-joueur[0].getV().getSpeed());
			if(joueur[0].getV().getX()<-8)
				joueur[0].getV().setX(joueur[0].getV().getX()+joueur[0].getV().getSpeed());
		}
		if(input.isKeyDown(Input.KEY_D))
		{
			joueur[0].getV().setX(joueur[0].getV().getX()+joueur[0].getV().getSpeed());
			if(joueur[0].getV().getX()+joueur[0].getV().getW()>810)
				joueur[0].getV().setX(joueur[0].getV().getX()-joueur[0].getV().getSpeed());
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
			switch (joueur[0].getV().getArme()) {
			case 21:
				delaiTire-=delta;//a modifier
				if(delaiTire < 0){

					playerProjectile.add(new Laser(joueur[0].getV().getX()+25,joueur[0].getV().getY()+12));

					delaiTire = 100;
				}
				break;
			case 22:
				delaiTire-=delta;
				if(delaiTire < 0){

					playerProjectile.add(new Missile(joueur[0].getV().getX()+25,joueur[0].getV().getY()+12));

					delaiTire = 100;
					break;
				}


			}
		}
		//*
		// La pause
		if (gc.getInput().isKeyPressed(Input.KEY_P)){
			gc.setPaused(!gc.isPaused());
			vue.setPauseBg(gc.getGraphics());
			sbg.enterState(Main.PAUSESTATE);
			//vue.setMusic(2);
			/*
			if(etatTmp == 11){
				etatTmp = 10;
				changementDEtat = true;
			}
			else if(etatTmp == 10){
				etatTmp = 11;
				changementDEtat = true;
			}//*/
		}//*/

		///////////////////////////////////////FIN DES COMMANDES ///////////////////////////////////////


		// Défilement du background
		posXBg1-=bgSpeed;
		posXBg2-=bgSpeed;
		if(posXBg1<-800){
			posXBg1=799;
			posXBg2=-1;
		}
		if(posXBg2<-800){
			posXBg2=799;
			posXBg1=-1;
		}

		////////////////////////////////////////LES TRAITEMENTS ////////////////////////////////////////
		// Traitement d'explosion
		Iterator<Explosion> itexp = explo.iterator();
		while(itexp.hasNext()){ 
			Explosion e =  itexp.next();
			// on fait avancer les explosions
			e.next();
			if(!e.estVisible())
				itexp.remove();
		}

		//Traitement des missiles et des aliens
		Iterator<Objet> itMovProj = playerProjectile.iterator();
		while (itMovProj.hasNext()){
			// Iterator<Objet> itMovEnemy = enemy.iterator();
			Objet ob=((Objet) itMovProj.next());
			Tir t = (Tir) ob;
			t.go();
			if(t.getX()>800) // Il il depasse de l'écran on dit qu'il sont invisible
				t.setVisible(false);
			if(!t.estVisible())
				itMovProj.remove();



			//test des collisions
			for (int i=0;i<enemy.size();i++)
			{
				Objet ob2=((Objet) enemy.get(i));
				boolean col=t.collision(ob2);
				if (col){
					explo.add(new Explosion(ob2.getX(), ob2.getY()));
					enemy.remove(i);
					joueur[0].setScore(joueur[0].getScore()+1);
					itMovProj.remove();
				}
			}
		}




		//////////////////////////////////////FIN DES TRAITEMENTS //////////////////////////////////////

		//* Test alien

		if(enemy.size()<2 && debug ){
			for(int i=0;i<10;i++)
				enemy.add(new Alien(300+rand.nextFloat()*(700-300),rand.nextFloat()*(500-0)));
			//System.out.println("Un alien arrive");
		}//*/

		// TODO Auto-generated method stub
		/*
		if(changementDEtat){
			if (etatTmp == 10){
				vue.setMusic(3);
				changementDEtat = false;
			}
			else if (etatTmp == 11){
				vue.setMusic(2);
				changementDEtat = false;
			}
		}
		if(etatTmp == 11){
			pause(gc,delta );		
		}*/

	}

	@Override
	public int getID() {
		return stateID;
	}

}
