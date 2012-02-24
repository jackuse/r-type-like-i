package jeu;

import java.util.*;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;



/* Idées optimisation
 * On fait un array list pour explosion, vaisseaux et tire
 * 
 * Pour les collisons:  complexité objet² faut trouver mieux
 * on fait un parcourt de tout les objets dans un array list
 * on fait un tableaux de correspondance des collision (c'est la qu'il faut bien optimisé)
 * On effectue les action associé
 * 
 * Sinon il faut subdivisé l'ecran en zone de collision pour passer a un complexité objet
 * 
 * Pour les explosions je sais pas si elle sont threader sinon on peut le faire
 */





/**
 * Class Controleur
 * 
 * Gére tout les traitements et la communication entre la vue et le modéle de donnée.
 */
public class Controleur extends BasicGame {

	//
	// Fields
	//

	public Random rand = new Random();
	private float bgSpeed = 1;
	private float posXBg1 = 0;
	private float posXBg2 = 800;
	private boolean speedUp;
	private Vue vue;

	private ArrayList<Objet> enemy;
	private ArrayList<Objet> playerProjectile;
	private ArrayList<Objet> nastyProjectile;
	private ArrayList<Objet> movable; // Je crois qu'il faut faire 2 collection a cause des collisions
	
	//private ArrayList<Objet> movable; // Je crois qu'il faut faire 2 collection a cause des collisions
	private int etat = 0; // 0:Menu  1:Option  2:Selection  3:HightScore  10:Jeu  11:Pause

	private int param[];
	private Joueur joueur[];
	private int delaiTire = 0; // A supprimer
	private ArrayList<Explosion> explo; // Je suis pas sure mais je pense qu'il n'y a pas mieux
	private ArrayList<Alien> aliens;
	private ArrayList<Tir> tirs;

	int menuX = 30;
	int menuY = 500;
	float scaleStep = 0.0002f;

    boolean debug;

	//
	// Constructors
	//
	public Controleur (){ 

		super("R-Type Like It !");

		//movable = new ArrayList<Objet>();
		playerProjectile = new ArrayList<Objet>();
		nastyProjectile = new ArrayList<Objet>();
		enemy = new ArrayList<Objet>();


		joueur= new Joueur[1];
		joueur[0]= new Joueur();

		param = new int[10];
		explo = new ArrayList<Explosion>();

		debug = true;
	};

	//
	// Methods
	//


	//
	// Accessor methods
	//

	/**
	 * Récupére une valeur aléatoire
	 * @return une valeur aléatoire
	 */
	public Random getRand ( ) {
		return rand;
	}

	//
	// Other methods
	//



	/**
	 * Fonction obligatoire de slick Basigame.
	 * C'est ici que doivent être charger les images et pas ailleur.
	 * @param        gc
	 */
	public void init( GameContainer gc ) throws SlickException 
	{
		vue = new Vue();
		gc.setTargetFrameRate(60);
		gc.setMaximumLogicUpdateInterval(20);
		gc.setMinimumLogicUpdateInterval(20);
	}


	/**
	 * Fonction obligatoire de slick Basigame.
	 * C'est ici qu'on effectue les traitements sur les données.
	 * @param        gc
	 * @param        delta
	 */
	public void update( GameContainer gc, int delta )
	{
		switch (etat) {
		case 0: // Menu
			menu(gc,delta );
			break;
		case 1: // Option
			break;
		case 2: // Selection
			etat = 10;// TEMPORAIRE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			break;
		case 10: // JEU
			jeu(gc,delta );
			break;
		case 11: // PAUSE
			jeu(gc,delta );
			break;


		default:
			break;
		}

	}


	private void jeu(GameContainer gc, int delta) {
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
		
		// La pause
		if (gc.getInput().isKeyPressed(Input.KEY_P))
		    gc.setPaused(!gc.isPaused());

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
                //collisionChecker(t);
                
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

		///////////////////////////////////////// ZONE DE TEST //////////////////////////////////////////
		/* Test explosions
if(explo.size()<10000 ){
for(int i=0;i<100;i++)
explo.add(new Explosion(255+rand.nextFloat()*(700-255),-45+rand.nextFloat()*((int)(0.85*600))));
}
//*/

		/* Test missiles
if(movable.size()<10){
for(int i=0;i<1;i++)
movable.add(new Tire(10,rand.nextFloat()*(500-0)));
//System.out.println("Un alien arrive");
}
//*/

		//* Test alien

		if(enemy.size()<2 && debug ){
			for(int i=0;i<10;i++)
				enemy.add(new Alien(300+rand.nextFloat()*(700-300),rand.nextFloat()*(500-0)));
			//System.out.println("Un alien arrive");
		}//*/
	}
	

	
	public void collisionChecker(Tir t)
	{
		Iterator<Objet> itMovEnemy = enemy.iterator();
		while(itMovEnemy.hasNext()){
			Objet ob2=((Objet) itMovEnemy.next());
			
			boolean col=t.collision(ob2);
			if(col){ // SI colision on détruit le missile et l'alien mais comment on fait la ?
				explo.add(new Explosion(ob2.getX(), ob2.getY()));
				enemy.remove(ob2);
				joueur[0].setScore(joueur[0].getScore()+1);
				//itMovProj.remove();
				//obj =null;
           }	
               
		}
	}
	private void menu(GameContainer gc, int delta) {
		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStartGame = false;
		boolean insideExit = false;
		boolean insideOption = false;

		if( ( mouseX >= menuX && mouseX <= menuX + vue.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getStartGameOption().getHeight()*0.7) ){
			insideStartGame = true;
		}else if( ( mouseX >= menuX+600 && mouseX <= menuX+600 + vue.getExitOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getExitOption().getHeight()*0.7) ){
			insideExit = true;
		}
		else if( ( mouseX >= menuX+350 && mouseX <= menuX+350 + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getOptionOption().getHeight()*0.7) ){
			insideOption = true;
		}

		if(insideStartGame){
			if(vue.getStartGameScale() < 0.8f)
				vue.setStartGameScale(vue.getStartGameScale()+scaleStep * delta);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				etat = 2;
			}
		}else{
			if(vue.getStartGameScale() > 0.7f)
				vue.setStartGameScale(vue.getStartGameScale()-scaleStep * delta);

			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
				//gc.exit();
		}

		if(insideOption){
			if(vue.getOptionScale() < 0.8f)
				vue.setOptionScale(vue.getOptionScale()+scaleStep * delta);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				etat = 2;
			}
		}else{
			if(vue.getOptionScale() > 0.7f)
				vue.setOptionScale(vue.getOptionScale()-scaleStep * delta);

			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		if(insideExit)
		{
			if(vue.getExitScale() < 0.8f)
				vue.setExitScale(vue.getExitScale() + scaleStep * delta);
		}else{
			if(vue.getExitScale() > 0.7f)
				vue.setExitScale(vue.getExitScale() - scaleStep * delta);
		}

	}

	/**
	 * Fonction obligatoire de slick Basigame.
	 * C'est ici qu'on fait l'affichage.
	 * @param        container
	 * @param        g
	 */
	public void render( GameContainer container, Graphics g )
	{	

		//On compte les animations pour l'affichage
		param[2]=enemy.size();
		param[3]=joueur[0].getScore();
		param[0]=explo.size();
		param[1]=playerProjectile.size();


		// Suivant le type d'affichae on affiche le bon		
		switch (etat) {
		case 0: // Menu
			vue.renderMenu(g, menuX, menuY);
			break;
		case 1: // Option
			vue.renderOption(g);
			break;
		case 2: // Selection
			vue.renderSelection(g);
			break;
		case 10: // JEU
			vue.renderBg(g, posXBg1, posXBg2);
			
			for (Iterator<Objet> e = enemy.iterator(); e.hasNext(); ) {
				Objet obE = (Objet) e.next();
				vue.render1Vaisseau(g, obE,0);
			}


			for (Iterator<Objet> pp = playerProjectile.iterator(); pp.hasNext(); ) {
				Objet obPp = (Objet) pp.next();
				vue.render1Tire(g, obPp,0);
			}
			

			

			

			vue.renderJoueur(g,joueur[0].getV());
			vue.renderExplosion(g, explo);
			vue.renderBoard(g,param);
			//vue.renderTest(g);
			vue.renderPause(container,g);


			break;
		case 11: // PAUSE
			
			break;
		case 3:

			break;

		default:
			break;
		}


	}


	/**
	 * Fonction principale qui permet de lancer l'application.
	 * @param        args
	 * @throws SlickException 
	 */
	public static void main( String[] args ) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new Controleur());
		app.setDisplayMode(800, 600, false);
		app.start();
	}


}
