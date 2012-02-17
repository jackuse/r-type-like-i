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
	private ArrayList<Objet> movable; // Je crois qu'il faut faire 2 collection a cause des collisions
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
		movable = new ArrayList<Objet>();

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
				movable.add(new Tire(joueur[0].getV().getX()+25,joueur[0].getV().getY()+12));
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

		// Traitement des missiles et des aliens
		Iterator<Objet> itMov = movable.iterator();
		while(itMov.hasNext()){
			Objet ob= ((Objet) itMov.next());
			if(ob.getId()==20){ // C'est un missile
				Tire t = (Tire)ob;
				t.go();
				if(t.getX()>800) // Il il depasse de l'écran on dit qu'il sont invisible
					t.setVisible(false); 
				/*boolean col = t.collision(obj);  
				if(col){ // SI colision on détruit le missile et l'alien mais comment on fait la ?
					explo.add(new Explosion(obj.getX(), obj.getY()));
					movable.remove(obj);
					joueur[0].setScore(joueur[0].getScore()+1);
					itMov.remove();
					//obj =null;
				}
				else */if(!t.estVisible())
					itMov.remove();
			}
			if(ob.getId()==10){ // C'est un alien

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
		if(movable.size()<200 ){
			for(int i=0;i<10;i++)
				movable.add(new Alien(300+rand.nextFloat()*(700-300),rand.nextFloat()*(500-0)));
			//System.out.println("Un alien arrive");
		}//*/
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
		//param[1]=objet.
		param[3]=joueur[0].getScore();
		param[0]=explo.size();
		param[2]=movable.size();


		// Suivant le type d'affichae on affiche le bon		
		switch (affichage) {
		case 0:

			break;
		case 1:

			break;
		case 2:
			vue.renderBg(g, posXBg1, posXBg2);


			for (Iterator<Objet> o = movable.iterator(); o.hasNext(); ) {
				Objet ob = (Objet) o.next();
				if(ob.getId()==10){
					vue.render1Vaisseau(g, ob,0);
				}
				else if(ob.getId()==20){
					vue.render1Tire(g, ob,0);
				}
				else{
					System.out.println("Objet inconnue");
				}


			}

			vue.renderJoueur(g,joueur[0].getV());
			vue.renderExplosion(g, explo);
			vue.renderBoard(g,param);

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
