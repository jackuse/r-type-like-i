package states;

import game.Alien;
import game.EnergyBall;
import game.Explosion;
import game.Joueur;
import game.Laser;
import game.Level;
import game.Main;
import game.Missile;
import game.Objet;
import game.ResourceManager;
import game.TimedEvent;
import game.Tir;
import game.Vue;
import game.Bonus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.SelectTransition;
import org.newdawn.slick.state.transition.Transition;

/**
 * Class Game
 * @author Etienne Grandier-Vazeille
 *
 */
public class Game extends BasicGameState{
	int levelId=1;
	int nastyProjectileTimer = 0;
	int stateID = -1;
	private Vue vue = Vue.getInstance();

	private ArrayList<TimedEvent> event;
	private ArrayList<Alien> enemy;
	private ArrayList<Objet> playerProjectile;
	private ArrayList<Objet> nastyProjectile;
	private ArrayList<Bonus> bonus;
	private int param[];
	public static boolean[] cheat;
	private Joueur joueur[];
	private float bgSpeed = 1;
	private float posXBg1 = 0;
	private float posXBg2 = 3000;
	private boolean speedUp;
	int pauseX = 250;
	int pauseY = 230;
	private ArrayList<Explosion> explo;
	public Random rand = new Random();
	private int delaiTire = 0;
	boolean debug =false;
	private int delayClig = 200;
	private int delay = 20;
	private Level lvl;

	private int delayChangeW = 200;

	private int alienSpawnTimer=0;
	
	public int shotRandomizer;
	private SelectTransition t;
	private int timer = 0;

	/* Mettre en place des bonus et le boss et 2 lvl
	 * Faire des lvl pour les armes
	 * Faire un echange de variable score avec gameover
	 * 
	 * 
	 */
	public Game(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
			 {
		vue.initGame();
		playerProjectile = new ArrayList<Objet>();
		nastyProjectile = new ArrayList<Objet>();
		event = new ArrayList<TimedEvent>();
		enemy = new ArrayList<Alien>();
		bonus = new ArrayList<Bonus>();		


		joueur = new Joueur[1];
		joueur[0] = Joueur.getInstance(1);
		cheat = new boolean[4];
		for(int i=0;i<4;i++)
			cheat[i] = false;

		param = new int[15];
		explo = new ArrayList<Explosion>();

		debug = true;
		
		lvl = new Level(1);
		initLevel();
		joueur[0].setLevel(levelId);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderBg(gr, posXBg1, posXBg2);

		//sbg.enterState(Main.GAMEOVERSTATE);
		param[1] = explo.size();
		param[2] = enemy.size();
		param[3] = playerProjectile.size();
		param[4] = joueur[0].getScore();
		param[5] = joueur[0].getV().getPdv();
		param[6] = joueur[0].getV().getArme();
		param[7] = joueur[0].getLife();
		param[8] = lvl.getLvl();
		param[9] = joueur[0].getKill();
		param[10] = lvl.getKill();
		param[11] = timer;



		for (Iterator<Alien> e = enemy.iterator(); e.hasNext(); ) {
			Objet obE = (Objet) e.next();
			vue.render1Vaisseau(gr, obE,0);
		}


		for (Iterator<Objet> pp = playerProjectile.iterator(); pp.hasNext(); ) {
			Objet obPp = (Objet) pp.next();
			vue.render1Tir(gr, obPp,obPp.getId());
		}
		
		for (Iterator<Objet> pp = nastyProjectile.iterator(); pp.hasNext(); ) {
			Objet obPp = (Objet) pp.next();
			vue.render1Tir(gr, obPp, obPp.getId());
		}

		vue.renderJoueur(gr,joueur[0].getV(),param[0]);
		vue.renderExplosion(gr, explo);
		vue.renderHUD(gr,param);
		//vue.renderTest(g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		timer+=delta;
		//System.out.println("delta "+delta+" timer "+timer );
		//BEGIN CHEAT CODE
		if(cheat[0] ||joueur[0].getV().isBorn())
			joueur[0].getV().setInvicible(true);
		else
			joueur[0].getV().setInvicible(false);

		//END CHEAT CODE


		// BEGIN MUSIC


		if(Main.previousState == Main.SELECTSTATE){
			reset();
			Main.previousState = sbg.getCurrentStateID();	

			//vue.setMusic(1);
			vue.setMusic(0);
			vue.selectMusic(1);
			//vue.principale.setPosition(120.0f);
			if(vue.isValiderMusic()){	
				vue.setMusic(4);
			}
			vue.principale.setVolume(0.4f);


		}

		if(vue.isValiderMusic()){
			if(!vue.isMusic()){
				vue.setMusic(0);
				vue.nextMusic();
				vue.setMusic(4);		
			}
		}

		//		if(!vue.isMusic()){
		//			vue.setMusic(0);
		//		}
		///System.out.println("music is "+vue.isMusic()+" and pos="+vue.principale.getPosition()+" and vol="+vue.principale.getVolume()  ); 
		// END MUSIC

		delayClig -=delay;//a modifier
		if(delayClig < 0){
			if(param[0] == 1)
				param[0] = 0;
			else
				param[0] = 1;
			delayClig = 200;
		}


		//System.out.println("etat "+sbg.getCurrentStateID()); 

		////////////////////////////////////////LES COMMANDES /////////////////////////////////////////
		Input input = gc.getInput(); // On r�cup�re les input

		//System.out.println("1: "+vue.getIJoueur().getHeight()+"  1: "+vue.getIJoueur().getWidth());
		// Les commandes de d�placements
		if(input.isKeyDown(Input.KEY_Z))
		{		
			if(speedUp)
				joueur[0].getV().setY(joueur[0].getV().getY()-joueur[0].getV().getSpeed()*2);
			else
				joueur[0].getV().setY(joueur[0].getV().getY()-joueur[0].getV().getSpeed());
			if(joueur[0].getV().getY()<((vue.getIJoueur().getHeight()-joueur[0].getV().getH())/2))
				joueur[0].getV().setY(((vue.getIJoueur().getHeight()-joueur[0].getV().getH())/2));
		}
		if(input.isKeyDown(Input.KEY_S))
		{
			if(speedUp)
				joueur[0].getV().setY(joueur[0].getV().getY()+joueur[0].getV().getSpeed()*2);
			else
				joueur[0].getV().setY(joueur[0].getV().getY()+joueur[0].getV().getSpeed());
			if(joueur[0].getV().getY()+joueur[0].getV().getH()>(vue.getHeight()-((vue.getIJoueur().getHeight()+joueur[0].getV().getH())/2)))
				joueur[0].getV().setY(vue.getHeight()-((vue.getIJoueur().getHeight()+joueur[0].getV().getH())/2));
		}
		if(input.isKeyDown(Input.KEY_Q))
		{
			System.out.println("1: "+joueur[0].getV().getX()+"  2: "+((vue.getIJoueur().getWidth()-joueur[0].getV().getW())/2));
			joueur[0].getV().setX(joueur[0].getV().getX()-joueur[0].getV().getSpeed());
			if(joueur[0].getV().getX()<((vue.getIJoueur().getWidth()-joueur[0].getV().getW())/2))
				joueur[0].getV().setX(((vue.getIJoueur().getWidth()-joueur[0].getV().getW())/2));

		}
		if(input.isKeyDown(Input.KEY_D))
		{
			System.out.println("1: "+joueur[0].getV().getX()+"  2: "+((vue.getIJoueur().getWidth()-joueur[0].getV().getW())/2));
			joueur[0].getV().setX(joueur[0].getV().getX()+joueur[0].getV().getSpeed());
			if(joueur[0].getV().getX()+joueur[0].getV().getW()>(vue.getWidth()-((vue.getIJoueur().getWidth()+joueur[0].getV().getW())/2)))
				joueur[0].getV().setX((vue.getWidth()-((vue.getIJoueur().getWidth()+joueur[0].getV().getW())/2)));
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

		// Commande next Weapon
		if(input.isKeyDown(Input.KEY_E))
		{
			delayChangeW -=20;//a modifier
			if(delayChangeW < 0){
				joueur[0].getV().setNArme(true);
				delayChangeW = 150;
			}
		}

		// Commande previous Weapon
		if(input.isKeyDown(Input.KEY_A))
		{
			delayChangeW-=20;//a modifier
			if(delayChangeW < 0){
				joueur[0].getV().setNArme(false);
				delayChangeW = 150;
			}
		}



		// Commande debug 										A SUPRIMER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			delaiTire-=delay;//a modifier
			if(delaiTire < 0){
				joueur[0].getV().setPdv(joueur[0].getV().getPdv()-10);
				//System.out.println(joueur[0].getV().getPdv());
				delaiTire = 100;
			}
		}
		// Commande debug 		2								A SUPRIMER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if(input.isKeyDown(Input.KEY_UP))
		{
			delaiTire-=delay;//a modifier
			if(delaiTire < 0){
				joueur[0].getV().setPdv(joueur[0].getV().getPdv()+10);
				//System.out.println(joueur[0].getV().getPdv());
				delaiTire = 100;
			}
		}

		// Commande debug 		3								A SUPRIMER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			delaiTire-=delay;//a modifier
			if(delaiTire < 0){
				delaiTire = 100;
//				ResourceManager rm = ResourceManager.getInstance();
//				rm.listR();
				vue.setMusic(0);
				vue.nextMusic();
				if(vue.isValiderMusic()){
					vue.setMusic(4);
				}
			}

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
				delaiTire-=40;//a modifier
				if(delaiTire < 0){

					playerProjectile.add(new Laser(joueur[0].getV().getX()+joueur[0].getV().getW()/2,joueur[0].getV().getY()+joueur[0].getV().getH()/2));

					delaiTire = 100;
				}
				break;
			case 22:
				delaiTire-=10;
				if(delaiTire < 0){

					playerProjectile.add(new Missile(joueur[0].getV().getX()+joueur[0].getV().getW()/2,joueur[0].getV().getY()+joueur[0].getV().getH()/2-12));

					delaiTire = 100;
					break;
				}


			}
		}
		//*
		// La pause
		if (gc.getInput().isKeyPressed(Input.KEY_P) || input.isKeyPressed(Input.KEY_ESCAPE)){
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


		// D�filement du background
		posXBg1-=bgSpeed;
		posXBg2-=bgSpeed;
		if(posXBg1<-vue.getIBackground(0).getWidth()){
			posXBg1=vue.getIBackground(0).getWidth()-1;
			posXBg2=-1;
		}
		if(posXBg2<-vue.getIBackground(0).getWidth()){
			posXBg2=vue.getIBackground(0).getWidth()-1;
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
			if(t.getX()>800) // Il il depasse de l'�cran on dit qu'il sont invisible
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
					joueur[0].incKill();
					if(t.estVisible()){
						itMovProj.remove();
					}
					break;
				}
			}
		}

		Iterator<Objet> itMovNastyProj = nastyProjectile.iterator();
		while (itMovNastyProj.hasNext()){
			// Iterator<Objet> itMovEnemy = enemy.iterator();
			Objet nastyOb=((Objet) itMovNastyProj.next());
			Tir nastyT = (Tir) nastyOb;
			nastyT.nastyGo();
			if(nastyT.getX()<-20) // Il il depasse de l'�cran on dit qu'il sont invisible
				nastyT.setVisible(false);
			if(!nastyT.estVisible())
				itMovNastyProj.remove();

			boolean col=nastyT.collision((Objet)joueur[0].getV());
			if (col){
				joueur[0].getV().setPdv(joueur[0].getV().getPdv()-5);
				explo.add(new Explosion(joueur[0].getV().getX(), joueur[0].getV().getY()));
				nastyT.setVisible(false);
				break;
			}
			
		}

		for (int i=0;i<enemy.size();i++)
		{
			enemy.get(i).move();
			
			Objet ob2=((Objet) enemy.get(i));
			boolean col=((Objet)joueur[0].getV()).collision(ob2);
			if (col){
				joueur[0].getV().setPdv(joueur[0].getV().getPdv()-10);
				explo.add(new Explosion(ob2.getX(), ob2.getY()));
				enemy.remove(i);
				joueur[0].incKill();
				break;
			}
			//shotRandomizer=(int) (rand.nextFloat()*(500-0));
			//if (shotRandomizer>500){
			nastyProjectileTimer+=delta;
			if(nastyProjectileTimer > 3500 && ob2.getX()<780){
				nastyProjectileTimer=0;
				nastyProjectile.add(new EnergyBall(ob2.getX()+ob2.getW()/2,ob2.getY()+ob2.getH()/2-12));
			}
			
			//}
			
		}

		for (int i=0;i<bonus.size();i++)
		{
			Objet ob2=((Objet) bonus.get(i));
			boolean col=((Objet)joueur[0].getV()).collision(ob2);
			if (col){
				bonus.get(i).doEffect(joueur[0]);
				bonus.remove(i);
				break;
			}
		}


		if(joueur[0].getV().getPdv()<=0){
			joueur[0].setLife(joueur[0].getLife()-1);
			boolean fin =lvl.next(); // c'est la fin on applaudi
			joueur[0].restKill();
			if(joueur[0].getLife() < 1){
				vue.setPauseBg(gc.getGraphics());
				vue.setMusic(0);
				for(int i=0;i<4;i++)
					cheat[i]=false;
				//GameOversetParam(int i, int p)
				joueur[0].setTime(timer/1000);
				sbg.enterState(Main.GAMEOVERSTATE);
			}
			else{
				joueur[0].getV().rest();
			}
		}



		runLevel(timer,delta);


		//////////////////////////////////////FIN DES TRAITEMENTS //////////////////////////////////////
		/*alienSpawnTimer+=delta;
		if (alienSpawnTimer>240)
		{
			alienSpawnTimer=0;
			for(int i=0;i<2;i++)
				enemy.add(new Alien(800+rand.nextFloat()*(900-800),rand.nextFloat()*(500-0)));
		}*/
		
		
		
		//* Test alien

		//if(enemy.size()<10 && debug ){
			//for(int i=0;i<10;i++)
				//enemy.add(new Alien(300+rand.nextFloat()*(700-300),rand.nextFloat()*(500-0)));
			//System.out.println("Un alien arrive");
		//}//*/

		/* Test missile
		if(playerProjectile.size()<2000 && debug ){
			for(int i=0;i<50;i++)
				playerProjectile.add(new Missile(0,rand.nextFloat()*(550-0)));

		}//*/
		/* Test laser
		if(playerProjectile.size()<2000 && debug ){
			for(int i=0;i<50;i++)
				playerProjectile.add(new Laser(0,rand.nextFloat()*(550-0)));

		}//*

		/* Test explosion
		if(explo.size()<10000 && debug ){
			for(int i=0;i<100;i++)
				explo.add(new Explosion(rand.nextFloat()*(700-100),rand.nextFloat()*(500-0)));
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
		}//*/





	}

	@Override
	public int getID() {
		return stateID;
	}

	public void reset(){
//		joueur[0].getV().rest();
//		joueur[0].setLife(3);
//		joueur[0].getV().setInvicible(true);
//		joueur[0].restTotalKill();
		//joueur[0].rest();
		enemy.clear();
		playerProjectile.clear();
		explo.clear();
		nastyProjectile.clear();
		lvl.set(1);
		timer =0;
	}
	
	public void initLevel(){
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
			sc2 = new Scanner(new File("data/level"+levelId+".txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();  
			
		}	
		while (sc2.hasNextLine()) {
			
			Scanner s2 = new Scanner(sc2.nextLine());
			boolean b;
			int i=0;
			while (b = s2.hasNext()) {
				
				String s = s2.next();
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
			if(time!=0 && quantity!=0 && delay!=0 && behavior!=0 && spawnX!=0 && spawnY!=0 && id!=0)
			{	event.add(new TimedEvent(time,quantity,delay,behavior,spawnX,spawnY,id));
				System.out.println("New Event at time "+time+"ms, quantity "+quantity+",delay "+delay+"ms, behavior "+behavior+"spawn x "+spawnX+" y "+spawnY+"");
			}
		}
	}
	
	public void runLevel(int timer,int delta){
		
		for(int i=0;i<event.size();i++)
		{
			if(timer>=event.get(i).getTime() && event.get(i).isEnabled() )
			{	
				enemy.add(new Alien(event.get(i).getSpawnX(), event.get(i).getSpawnY(),event.get(i).getBehavior()));
				System.out.println("new alien");
				for (int q=0;q<event.get(i).getQuantity()-1;q++)
				{
					event.get(i).setNextSpawnTime(event.get(i).getNextSpawnTime()+event.get(i).getDelay());
					event.add(new TimedEvent(event.get(i).getNextSpawnTime(),0,0,event.get(i).getBehavior(),event.get(i).getSpawnX(),event.get(i).getSpawnY(),event.get(i).getId()));
				}
				event.get(i).setEnabled(false);
			}
		}
	}

}
