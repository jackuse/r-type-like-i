package states;

import game.Alien;
import game.Boss;
import game.EnergyBall;
import game.EventList;
import game.Explosion;
import game.Player;
import game.Laser;
import game.Level;
import game.Main;
import game.Missile;
import game.Objet;
import game.ResourceManager;
import game.Ship;
import game.Spagettie;
import game.TimedEvent;
import game.Shot;
import game.View;
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
	private View view = View.getInstance();

//	private ArrayList<TimedEvent> event;
	private EventList event = EventList.getInstance();
	private ArrayList<Alien> enemy;
	private ArrayList<Objet> playerProjectile;
	private ArrayList<Objet> nastyProjectile;
	private ArrayList<Bonus> bonus;
	private ArrayList<Boss> boss;
	private int param[];
	public static boolean[] cheat;
	private Player player[];
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
	private boolean win = false;

	private int delayChangeW = 200;

	private int alienSpawnTimer=0;

	public int shotRandomizer;
	private SelectTransition t;
	private int timer = 0;

	/* Mettre en place des bonus et le boss et 2 lvl
	 * Faire des lvl pour les armes
	 * 
	 * 
	 */
	public Game(int stateID) {
		this.stateID = stateID;
	}

	public void enter(GameContainer gc, StateBasedGame sgb) {
		if(Main.previousState == Main.SELECTSTATE){
			// On réinitialise les mots de passe
			for(int i=0;i<4;i++){
				Game.cheat[i]=false;
			}
		}

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		view.initGame();
		playerProjectile = new ArrayList<Objet>();
		nastyProjectile = new ArrayList<Objet>();
		boss = new ArrayList<Boss>();
//		event = new ArrayList<TimedEvent>();
		enemy = new ArrayList<Alien>();
		bonus = new ArrayList<Bonus>();		


		player = new Player[1];
		player[0] = Player.getInstance(1);
		cheat = new boolean[4];
		for(int i=0;i<4;i++)
			cheat[i] = false;

		param = new int[15];
		explo = new ArrayList<Explosion>();

		debug = true;

		lvl = new Level(1);
		initLevel();
		player[0].setLevel(levelId);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		view.renderBg(gr, posXBg1, posXBg2);

		//sbg.enterState(Main.GAMEOVERSTATE);
		param[1] = explo.size();
		param[2] = enemy.size();
		param[3] = playerProjectile.size();
		param[4] = player[0].getScore();
		param[5] = player[0].getShip().getPdv();
		param[6] = player[0].getShip().getArme();
		param[7] = player[0].getLife();
		param[8] = lvl.getLvl();
		param[9] = player[0].getKill();
		param[10] = lvl.getKill();
		param[11] = timer;


		for (Iterator<Alien> e = enemy.iterator(); e.hasNext(); ) {
			Objet obE = (Objet) e.next();
			view.render1Vaisseau(gr, obE,obE.getId());
		}


		for (Iterator<Objet> pp = playerProjectile.iterator(); pp.hasNext(); ) {
			Objet obPp = (Objet) pp.next();
			view.render1Tir(gr, obPp,obPp.getId());
		}

		for (Iterator<Objet> np = nastyProjectile.iterator(); np.hasNext(); ) {
			Objet obNp = (Objet) np.next();
			view.render1Tir(gr, obNp, obNp.getId());
		}
		
		for (Iterator<Boss> bo = boss.iterator(); bo.hasNext(); ) {
			Objet obBo = (Objet) bo.next();
			//System.out.println("je blit du boss"+obBo.getId());
			view.render1Boss(gr, obBo, obBo.getId());
		}

		
		view.renderPlayer(gr,player[0].getShip(),param[0]);
		view.renderExplosion(gr, explo);
		view.renderHUD(gr,param);
		//view.renderTest(g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		if(delta <1){ // Correction du bug "DELTA"
			delta =20;
		}
		
		timer+=delta;
		//System.out.println("delta "+delta+" timer "+timer );
		//BEGIN CHEAT CODE
		if(cheat[0] ||player[0].getShip().isBorn())
			player[0].getShip().setInvicible(true);
		else
			player[0].getShip().setInvicible(false);

		//END CHEAT CODE


		// BEGIN MUSIC


		if(Main.previousState == Main.SELECTSTATE){
			reset();
			Main.previousState = sbg.getCurrentStateID();	

			//view.setMusic(1);
			view.setMusic(0);
			view.selectMusic(1);
			//view.principale.setPosition(120.0f);
			if(view.isValiderMusic()){	
				view.setMusic(4);
			}
			view.principale.setVolume(0.4f);


		}

		if(view.isValiderMusic()){
			if(!view.isMusic()){
				view.setMusic(0);
				view.nextMusic();
				view.setMusic(4);		
			}
		}

		//		if(!view.isMusic()){
		//			view.setMusic(0);
		//		}
		///System.out.println("music is "+view.isMusic()+" and pos="+view.principale.getPosition()+" and vol="+view.principale.getVolume()  ); 
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
		Input input = gc.getInput(); // On récupére les input

		//System.out.println("1: "+view.getIplayer().getHeight()+"  1: "+view.getIplayer().getWidth());
		// Les commandes de déplacements
		if(!input.isKeyDown(Input.KEY_Z) && !input.isKeyDown(Input.KEY_S))
		{
			view.setMovingUp(false);
			view.setMovingDown(false);
		}
		if(input.isKeyDown(Input.KEY_Z))
		{
			view.setMovingUp(true);
			view.setMovingDown(false);
			if(speedUp)
				player[0].getShip().setY(player[0].getShip().getY()-player[0].getShip().getSpeed()*2);
			else
				player[0].getShip().setY(player[0].getShip().getY()-player[0].getShip().getSpeed());
			if(player[0].getShip().getY()<((view.getIPlayer().getHeight()-player[0].getShip().getH())/2))
				player[0].getShip().setY(((view.getIPlayer().getHeight()-player[0].getShip().getH())/2));
		}
		else if(input.isKeyDown(Input.KEY_S))
		{
			view.setMovingDown(true);
			view.setMovingUp(false);
			if(speedUp)
				player[0].getShip().setY(player[0].getShip().getY()+player[0].getShip().getSpeed()*2);
			else
				player[0].getShip().setY(player[0].getShip().getY()+player[0].getShip().getSpeed());
			if(player[0].getShip().getY()+player[0].getShip().getH()>(view.getHeight()-((view.getIPlayer().getHeight()+player[0].getShip().getH())/2)))
				player[0].getShip().setY(view.getHeight()-((view.getIPlayer().getHeight()+player[0].getShip().getH())/2));
		}
		if(input.isKeyDown(Input.KEY_Q))
		{
			//System.out.println("1: "+player[0].getShip().getX()+"  2: "+((view.getIplayer().getWidth()-player[0].getShip().getW())/2));
			player[0].getShip().setX(player[0].getShip().getX()-player[0].getShip().getSpeed());
			if(player[0].getShip().getX()<((view.getIPlayer().getWidth()-player[0].getShip().getW())/2))
				player[0].getShip().setX(((view.getIPlayer().getWidth()-player[0].getShip().getW())/2));

		}
		if(input.isKeyDown(Input.KEY_D))
		{

			player[0].getShip().setX(player[0].getShip().getX()+player[0].getShip().getSpeed());
			if(player[0].getShip().getX()+player[0].getShip().getW()>(view.getWidth()-((view.getIPlayer().getWidth()+player[0].getShip().getW())/2)))
				player[0].getShip().setX((view.getWidth()-((view.getIPlayer().getWidth()+player[0].getShip().getW())/2)));
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
				player[0].getShip().setNArme(true);
				delayChangeW = 150;
			}
		}

		// Commande previous Weapon
		if(input.isKeyDown(Input.KEY_A))
		{
			delayChangeW-=20;//a modifier
			if(delayChangeW < 0){
				player[0].getShip().setNArme(false);
				delayChangeW = 150;
			}
		}



		// Commande debug 										A SUPRIMER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			delaiTire-=delay;//a modifier
			if(delaiTire < 0){
				player[0].getShip().setPdv(player[0].getShip().getPdv()-10);
				//System.out.println(player[0].getShip().getPdv());
				delaiTire = 100;
			}
		}
		// Commande debug 		2								A SUPRIMER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if(input.isKeyDown(Input.KEY_UP))
		{
			delaiTire-=delay;//a modifier
			if(delaiTire < 0){
				player[0].getShip().setPdv(player[0].getShip().getPdv()+10);
				//System.out.println(player[0].getShip().getPdv());
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
				view.setMusic(0);
				view.nextMusic();
				if(view.isValiderMusic()){
					view.setMusic(4);
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
			switch (player[0].getShip().getArme()) {
			case 21:
				delaiTire-=40;//a modifier
				if(delaiTire < 0){

					playerProjectile.add(new Laser(player[0].getShip().getX()+player[0].getShip().getW()/2,player[0].getShip().getY()+player[0].getShip().getH()/2));

					delaiTire = 100;
				}
				break;
			case 22:
				delaiTire-=10;
				if(delaiTire < 0){

					playerProjectile.add(new Missile(player[0].getShip().getX()+player[0].getShip().getW()/2,player[0].getShip().getY()+player[0].getShip().getH()/2-12));

					delaiTire = 100;
					break;
				}


			}
		}
		//*
		// La pause
		if (gc.getInput().isKeyPressed(Input.KEY_P) || input.isKeyPressed(Input.KEY_ESCAPE)){
			gc.setPaused(!gc.isPaused());
			view.setPauseBg(gc.getGraphics());
			sbg.enterState(Main.PAUSESTATE);
			//view.setMusic(2);
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
		if(posXBg1<-view.getIBackground(0).getWidth()){
			posXBg1=view.getIBackground(0).getWidth()-1;
			posXBg2=-1;
		}
		if(posXBg2<-view.getIBackground(0).getWidth()){
			posXBg2=view.getIBackground(0).getWidth()-1;
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
			Shot sh=((Shot) itMovProj.next());
			sh.go();
			if(sh.getX()>800) // Il il depasse de l'écran on dit qu'il sont invisible
				sh.setVisible(false);
			if(!sh.estVisible())
				itMovProj.remove();



			//test des collisions
			for (int i=0;i<enemy.size();i++)
			{

				Ship ob2= ((Ship)enemy.get(i));
				boolean col=sh.collision(ob2);
				if (col){
					explo.add(new Explosion(ob2.getX(), ob2.getY()));
					enemy.remove(i);
					player[0].setScore(player[0].getScore()+1);
					player[0].incKill();
					if(sh.estVisible()){
						itMovProj.remove();
					}
					break;
				}
			}
			
			for (int i=0;i<boss.size();i++)
			{

				Boss bo= ((Boss)boss.get(i));
				boolean col=sh.collision(bo);
				if (col){
					explo.add(new Explosion(bo.getX(), bo.getY()));
					bo.reciveDamage(sh.getDegat());
					System.out.println("PDV : "+bo.getPdv());
					if(bo.getPdv()<=0){
						enemy.remove(i);
						player[0].setScore(player[0].getScore()+1);
						player[0].incKill();
					}
					if(sh.estVisible()){
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
			Shot nastyT = (Shot) nastyOb;
			nastyT.nastyGo();
			if(nastyT.getX()<-20) // Il il depasse de l'écran on dit qu'il sont invisible
				nastyT.setVisible(false);
			if(!nastyT.estVisible())
				itMovNastyProj.remove();

			boolean col=nastyT.collision((Objet)player[0].getShip());
			if (col){
				player[0].getShip().setPdv(player[0].getShip().getPdv()-5);
				explo.add(new Explosion(player[0].getShip().getX(), player[0].getShip().getY()));
				nastyT.setVisible(false);
				break;
			}

		}
		
		int timerTmp = timer/100;
		for (int i=0;i<boss.size();i++){
			boss.get(i).move();
			
			if(boss.get(i).isFire() && timer%2 == 1){
				System.out.println("time "+timerTmp+ "time%2 "+timerTmp%2);
				nastyProjectile.add(new Spagettie(boss.get(i).getX()+boss.get(i).getW()/2,boss.get(i).getY()+boss.get(i).getH()/2-12));
			}
		}
		
		for (int i=0;i<enemy.size();i++)
		{
			enemy.get(i).move();

			Objet ob2=((Objet) enemy.get(i));
			boolean col=((Objet)player[0].getShip()).collision(ob2);
			if (col){
				player[0].getShip().setPdv(player[0].getShip().getPdv()-10);
				explo.add(new Explosion(ob2.getX(), ob2.getY()));
				enemy.remove(i);
				player[0].incKill();
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
		
		for (int i=0;i<boss.size();i++)
		{
			Objet ob2=((Objet) boss.get(i));
			boolean col=((Objet)player[0].getShip()).collision(ob2);
			if (col){
				player[0].getShip().setPdv(0);
				break;
			}
		}

		for (int i=0;i<bonus.size();i++)
		{
			Objet ob2=((Objet) bonus.get(i));
			boolean col=((Objet)player[0].getShip()).collision(ob2);
			if (col){
				bonus.get(i).doEffect(player[0]);
				bonus.remove(i);
				break;
			}
		}


		if(player[0].getShip().getPdv()<=0){
			player[0].setLife(player[0].getLife()-1);
			boolean fin =lvl.next(); // c'est la fin on applaudi
			player[0].restKill();
			if(player[0].getLife() < 1){
				view.setPauseBg(gc.getGraphics());
				view.setMusic(0);
				for(int i=0;i<4;i++)
					cheat[i]=false;
				//GameOversetParam(int i, int p)
				player[0].setTime(timer/1000);
				sbg.enterState(Main.GAMEOVERSTATE);
			}
			else{
				player[0].getShip().rest();
			}
		}



		runLevel(timer,delta,sbg);


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
				view.setMusic(3);
				changementDEtat = false;
			}
			else if (etatTmp == 11){
				view.setMusic(2);
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
		//		player[0].getShip().rest();
		//		player[0].setLife(3);
		//		player[0].getShip().setInvicible(true);
		//		player[0].restTotalKill();
		//player[0].rest();
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
		sc2 = new Scanner(getClass().getResourceAsStream("/data/level"+levelId+".txt"));


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
			{	event.add(new TimedEvent(time,quantity,delay,behavior,spawnX,spawnY,id));
			System.out.println("New Event at time "+time+"ms, quantity "+quantity+",delay "+delay+"ms, behavior "+behavior+"spawn x "+spawnX+" y "+spawnY+" id "+id);
			}
		}
	}

	public void runLevel(int timer,int delta, StateBasedGame sbg){

		for(int i=0;i<event.size();i++)
		{
			if(timer>=event.get(i).getTime() && event.get(i).isEnabled() )
			{	
				//System.out.println("le i: "+i);
				if(event.get(i).getId()>100 && event.get(i).getId()<200 && event.get(i).isEnabled()){
					System.out.println("timer"+timer+" "+event.get(i).isEnabled());
					System.out.println(event.get(i).getTime()+"");
					
					enemy.add(new Alien(event.get(i).getSpawnX(), event.get(i).getSpawnY(),event.get(i).getId(),event.get(i).getBehavior()));
					
					System.out.println("new alien");
				}else if(event.get(i).getId()>200 && event.get(i).getId()<300){
					boss.add(new Boss(event.get(i).getSpawnX(), event.get(i).getSpawnY(),event.get(i).getId(),event.get(i).getBehavior()));
					System.out.println("new boss");
				}
				else if(event.get(i).getId()==999)
				{
					
				}
				for (int q=0;q<event.get(i).getQuantity()-1;q++)
				{
					event.get(i).setNextSpawnTime(event.get(i).getNextSpawnTime()+event.get(i).getDelay());
					event.add(new TimedEvent(event.get(i).getNextSpawnTime(),0,0,event.get(i).getBehavior(),event.get(i).getSpawnX(),event.get(i).getSpawnY(),event.get(i).getId()));
					
				}
				event.get(i).setEnabled(false);
			}
		}
	}
	
	public void nextLevel(StateBasedGame sbg)
	{
		if(levelId<5)
			levelId++;
		
		reset();
		//event.reset();

		lvl.set(levelId);
		sbg.enterState(Main.GAMEOVERSTATE);
	}

}
