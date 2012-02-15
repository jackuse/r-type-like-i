package jeu;

import java.util.*;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;







/**
 * Class Controleur
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
	private ArrayList<Objet> objet;
	private int affichage; // 0:Menu  1:Option  2:Jeu  3:Pause  4:HightScore
	private int param[];
	private Joueur joueur[];
	private int delaiTire = 0; // A supprimer
	private ArrayList<Explosion> explo; // Je suis pas sure mais je pense qu'il n'y a pas mieux

	//
	// Constructors
	//
	public Controleur (){ 

		super("R-Type Like It !");
		objet = new ArrayList<Objet>();

		affichage = 2;
		joueur= new Joueur[1];
		joueur[0]= new Joueur();

		param = new int[10];
		explo = new ArrayList<Explosion>();

	};

	//
	// Methods
	//


	//
	// Accessor methods
	//

	/**
	 * Set the value of rand
	 * @param newVar the new value of rand
	 */
	public void setRand ( Random newVar ) {
		rand = newVar;
	}

	/**
	 * Get the value of rand
	 * @return the value of rand
	 */
	public Random getRand ( ) {
		return rand;
	}

	//
	// Other methods
	//



	/**
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
	 * @param        gc
	 * @param        delta
	 */
	public void update( GameContainer gc, int delta )
	{

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
			delaiTire-=delta;
			if(delaiTire < 0){
				objet.add(new Tire(joueur[0].getV().getX(),joueur[0].getV().getY()));
				//				tires.add(new Tire(shipX,shipY));
				delaiTire = 100;
			}
		}

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
		//////////////////////////////////////FIN DES TRAITEMENTS //////////////////////////////////////

		///////////////////////////////////////// ZONE DE TEST //////////////////////////////////////////
		//* Test explosions
		if(explo.size()<10000 ){
			for(int i=0;i<100;i++)
				explo.add(new Explosion(255+rand.nextFloat()*(700-255),-45+rand.nextFloat()*((int)(0.85*600))));
		}
		//*/
	}


	/**
	 * @param        container
	 * @param        g
	 */
	public void render( GameContainer container, Graphics g )
	{	

		//On compte les animations pour l'affichage
		//param[1]=objet.
		param[3]=joueur[0].getScore();
		param[0]=explo.size();


		// Suivant le type d'affichae on affiche le bon		
		switch (affichage) {
		case 0:

			break;
		case 1:

			break;
		case 2:
			vue.renderBg(g, posXBg1, posXBg2);

			vue.renderJoueur(g,joueur[0].getV());
			vue.renderExplosion(g, explo);
			//System.out.println("Je suis ici "+ objet.size());
			/*
			for (Iterator<Objet> o = objet.iterator(); o.hasNext(); ) {
				System.out.println("Je suis la aussi ");
				Objet ob = (Objet) o.next();
				if (ob instanceof VaisseauJoueur) {
					VaisseauJoueur v = (VaisseauJoueur) o;
					vue.renderJoueur(g, v);
				}

				if(o instanceof Tire)
					vue.renderTire(g, o);
				if(o instanceof Vaisseau)
					vue.renderTire(g, o);
				if(o instanceof Explosion)
					vue.renderTire(g, o);

			}//*/

			//vue.renderTire(g, objet);
			//vue.renderVaisseau(g,objet);
			//vue.renderExplosion(g, explo);

			break;
		case 3:

			break;

		default:
			break;
		}
		
		vue.renderBoard(g,param);

	}


	/**
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
