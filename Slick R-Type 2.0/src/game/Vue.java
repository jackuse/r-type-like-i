package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

// Faire un design pattern sigletton
// Contient toutes les valeurs partag�es des etats

/**
 * Class Vue
 */
public class Vue {

	private static Vue _instance = new Vue();

	//
	// Fields
	//


	private Image[] background = new Image[2];
	private Image joueur;
	private Image[] alien = new Image[2];;
	private Image laser;
	private Image missile;
	private Image explosion;
	private Image sheet;
	private String dossierSkin = "data/skin1";
	private SpriteSheet skin;
	private int[][] skinDef;

	private Image exitOption = null;
	private Image startGameOption = null;
	private Image optionOption = null;
	private Image highscoreOption = null;
	private float startGameScale = 0.7f;
	private float exitScale = 0.7f;
	private float optionScale = 0.7f;

	private ResourceManager rm = ResourceManager.getInstance();
	public DeferredResource nextResource; 

	float alpha = 0;

	private Font font; 

	private Music principale = null;
	private float volumeMusic = 0.5f;

	private Image pauseBg = null;



	public final static Vue getInstance(){
		return _instance;
	}
	//
	// Constructors
	//

	/**
	 * Chargement des images
	 * @throws FileNotFoundException 
	 */
	public Vue () {
		/*
		background[0] = new Image("data/land.jpg");
		joueur = new Image("data/plane.png");
		joueur.setRotation(90.0f);
		explosion = new Image("data/explosion.png");
		missile = new Image("data/rocket.png");
		missile.setRotation(90.0f);
		alien[0] = new Image("data/alien2.png");
		alien[0].setRotation(270.0f);
		 */

		/*
		try {
			rm.loadResources(new FileInputStream("data/menu.xml"));
			rm.loadResources(new FileInputStream("data/jeu.xml"),true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 */


	};

	//
	// Methods
	//


	//
	// Accessor methods
	//


	//	/**
	//	 * Set the value of background
	//	 * @param newVar the new value of background
	//	 */
	//	private void setBackground ( Image[] newVar ) {
	//		background = newVar;
	//	}
	//
	//	/**
	//	 * Get the value of background
	//	 * @return the value of background
	//	 */
	//	private Image[] getBackground ( ) {
	//		return background;
	//	}
	//
	//	/**
	//	 * Set the value of joueur
	//	 * @param newVar the new value of joueur
	//	 */
	//	private void setJoueur ( Image newVar ) {
	//		joueur = newVar;
	//	}
	//
	//	/**
	//	 * Get the value of joueur
	//	 * @return the value of joueur
	//	 */
	//	private Image getJoueur ( ) {
	//		return joueur;
	//	}
	//
	//	/**
	//	 * Set the value of alien
	//	 * @param newVar the new value of alien
	//	 */
	//	private void setAlien ( Image[] newVar ) {
	//		alien = newVar;
	//	}
	//
	//	/**
	//	 * Get the value of alien
	//	 * @return the value of alien
	//	 */
	//	private Image[] getAlien ( ) {
	//		return alien;
	//	}
	//
	//	/**
	//	 * Set the value of laser
	//	 * @param newVar the new value of laser
	//	 */
	//	private void setLaser ( Image newVar ) {
	//		laser = newVar;
	//	}
	//
	//	/**
	//	 * Get the value of laser
	//	 * @return the value of laser
	//	 */
	//	private Image getLaser ( ) {
	//		return laser;
	//	}
	//
	//	/**
	//	 * Set the value of missile
	//	 * @param newVar the new value of missile
	//	 */
	//	private void setMissile ( Image newVar ) {
	//		missile = newVar;
	//	}
	//
	//	/**
	//	 * Get the value of missile
	//	 * @return the value of missile
	//	 */
	//	private Image getMissile ( ) {
	//		return missile;
	//	}
	//
	//	/**
	//	 * Set the value of dossierSkin
	//	 * @param newVar the new value of dossierSkin
	//	 */
	//	private void setDossierSkin ( String newVar ) {
	//		dossierSkin = newVar;
	//	}
	//
	//	/**
	//	 * Get the value of dossierSkin
	//	 * @return the value of dossierSkin
	//	 */
	//	private String getDossierSkin ( ) {
	//		return dossierSkin;
	//	}

	public Image getExitOption() {
		return exitOption;
	}

	public void setExitOption(Image exitOption) {
		this.exitOption = exitOption;
	}

	public Image getStartGameOption() {
		return startGameOption;
	}

	public void setStartGameOption(Image startGameOption) {
		this.startGameOption = startGameOption;
	}

	public float getStartGameScale() {
		return startGameScale;
	}

	public Image getOptionOption() {
		return optionOption;
	}

	public void setOptionOption(Image optionOption) {
		this.optionOption = optionOption;
	}

	public void setStartGameScale(float startGameScale) {
		this.startGameScale = startGameScale;
	}

	public float getExitScale() {
		return exitScale;
	}

	public void setExitScale(float exitScale) {
		this.exitScale = exitScale;
	}



	//
	// Other methods
	//

	/**
	 * DEPRECIATED
	 * Retourne l'image demander suivant le code suivant
	 * 0 : background
	 * 1 : joueur
	 * 2 : alien
	 * 3 : missile
	 * 4 : laser
	 * 
	 * 
	 * //Possible de le faire avec des strings
	 * @return       Image
	 * @param        noImage
	 */
	public Image getImage( int noImage )
	{
		switch (noImage) {
		case 0:
			return background[0];
		case 1:
			return joueur;	
		case 2:
			return alien[0];
		case 3:
			return missile;
		case 4:
			return laser;

		default:
			break;
		}
		return joueur;
	}


	/**
	 * Teste si les fichier existe dans ce dossier et les charges
	 * et revoi true
	 * sinon revoi false
	 * @return       boolean
	 * @param        chemin
	 */
	public boolean changeSkin( String chemin )
	{
		return false;
	}


	/**
	 * Permet de changer une image comme l'image de fond suivant les niveaux
	 * @return       boolean
	 * @param        noImage
	 * @param        idImage
	 */
	public boolean setImage( int noImage, int idImage )
	{
		return false;
	}








	public float getOptionScale() {
		return optionScale;
	}

	public void setOptionScale(float optionScale) {
		this.optionScale = optionScale;
	}



	public void setInstantImage(Graphics gr){
		gr.copyArea(pauseBg,0,0);
	}









	////////////////////////////////////////// INIT //////////////////////////////////////////

	public void initPause() {
		pauseBg = pauseBg.getScaledCopy(800, 600);

	}

	public void initSkin() throws SlickException{
		skin = new SpriteSheet("/data/F-4Phantom.png", 30, 40,new Color(248,0,248));
	}

	public void initMenu(){

		try {
			rm.loadResources(new FileInputStream("data/menu.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image menu = rm.getImage("BOUTONS");
		System.out.println(menu.getHeight());

		startGameOption = menu.getSubImage(0, 28, 445, 70);
		optionOption = menu.getSubImage(180, 105, 300, 70);
		exitOption = menu.getSubImage(278, 184, 200, 70);

		selectMusic(0);
		principale.setVolume(volumeMusic);
		System.out.println("Space !! ");
		try {
			font = new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void initGame(){
		principale = rm.getMusic("GAME_MUSIC");
		principale.setVolume(volumeMusic);
		background[0] = rm.getImage("BACKGROUD_JEU");
		explosion = rm.getImage("EXPLOSION");
		missile = rm.getImage("ROCKET");
		missile.setRotation(90.0f);
		
		//joueur = rm.getImage("JOUEUR");
		sheet = rm.getImage("VAISSEAU_1");
		joueur = sheet.getSubImage(265,3746,123,196);
		joueur.setRotation(90.0f);
		laser = sheet.getSubImage(265,3746,123,196);
		laser.setRotation(90.0f);


		alien[0] = rm.getImage("ALIEN");
		alien[0].setRotation(270.0f);	
		try {
			pauseBg = new Image(800,600);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	///////////////////////////////////////// RENDER /////////////////////////////////////////

	public void renderChargement(GameContainer gc, Graphics gr) {
		if (nextResource != null) { 
			String s = ("Loading: "+nextResource.getDescription());
			int l = s.length();
			//System.out.println("longeur "+l);
			gr.drawString("Loading: "+nextResource.getDescription(), 400-l*5, 280); 
		} 

		int total = LoadingList.get().getTotalResources(); 
		int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources(); 

		float bar = loaded / (float) total; 
		gr.fillRect(250,300,loaded*40,20); 
		gr.drawRect(250,300,total*40,20); 

		/*if (started) { 
	            image.draw(100,200); 
	            font.drawString(100,500,"LOADING COMPLETE"); 
	        } */
	}

	public void renderOption(GameContainer gc, Graphics gr,int optionX, int optionY) {
		System.out.println("rendu d'option");

		startGameOption.draw(optionX, optionY, startGameScale);
		optionOption.draw(optionX, optionY+45, optionScale);
		exitOption.draw(optionX, optionY+90, exitScale);
	}

	public void renderSelection(Graphics g) {
		// TODO Auto-generated method stub
	}

	public int renderTest(Graphics g) {
		//skin.draw();
		return 0;
	}

	public int renderMenu(Graphics g,int menuX, int menuY) {
		Rectangle fond = new Rectangle (0, 0, 800, 600);
		g.setColor(new Color (0.2f, 0.2f, 0.2f));
		g.fill(fond);

		startGameOption.draw(menuX, menuY, startGameScale);
		optionOption.draw(menuX+350, menuY, optionScale);
		exitOption.draw(menuX+600, menuY, exitScale);
		return 0;

	}

	/**
	 * @return       boolean
	 * @param        g
	 */
	public boolean renderBg( Graphics g, float x1, float x2)
	{
		background[0].draw(x1, 0);
		background[0].draw(x2, 0);
		return false;
	}


	/**
	 * @return       int
	 * @param        g
	 */
	public int renderVaisseau( Graphics g, ArrayList<Vaisseau> vaisseaux )
	{
		return 0;
	}


	/**
	 * @return       int
	 * @param        g
	 */
	public int renderTire( Graphics g, ArrayList<Tir> tirs )
	{
		if(!tirs.isEmpty()){
			Iterator<Tir> it = tirs.iterator();
			while(it.hasNext()){
				Tir t =((Tir) it.next());
				//if(t.estVisible())
				//t.getImage().draw(t.getX()+10,t.getY()+38);
			}
		}
		return 0;
	}


	/**
	 * @return       int
	 * @param        g
	 */
	public int renderExplosion( Graphics g, ArrayList<Explosion> explo  )
	{
		float[] cadre;
		if(!explo.isEmpty()){
			Iterator<Explosion> it2 = explo.iterator();
			while(it2.hasNext()){
				Explosion e =((Explosion) it2.next());
				cadre = e.getCadre();

				if(cadre != null)
					explosion.getSubImage((int)cadre[0],(int)cadre[1],(int)cadre[2],(int)cadre[3]).draw(e.getX()+cadre[2]/3,e.getY()+cadre[3]/3,1.5f);
			}
		}
		return 0;
	}

	public int renderJoueur(Graphics g, VaisseauJoueur v) {
		joueur.draw(v.getX(), v.getY());
		return 0;
	}

	public int renderBoard(Graphics g, int[] param) {

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
		g.drawString("Explosion :"+param[0], 10,575);
		g.drawString("Alien :"+param[1], 150,575);
		g.drawString("Missile :"+param[2], 300,575);
		g.drawString("Score :"+param[3], 600,575);
		//Ajouter un timer
		return 0;
	}

	public int render1Vaisseau(Graphics g, Objet ob,int type) {
		alien[type].draw(ob.getX()+ob.getW()/2,ob.getY()+ob.getH()/2);
		return 0;
	}

	public int render1Tir(Graphics g, Objet ob, int type) {
		if(((Tir)ob).estVisible()){
			switch (type) {
			case 0:
				missile.draw(ob.getX()+10,ob.getY()+38);
				break;
			case 1:
				laser.draw(ob.getX()+10,ob.getY()+38);
				break;

			default:
				break;
			}
		}

		return 0;
	}

	public void renderPause(GameContainer gc, Graphics gr,int pauseX, int pauseY) {
		//		if (gc.isPaused())
		//		{
		Rectangle rect = new Rectangle (0, 0, 800, 600);
		gr.setColor(new Color (0.2f, 0.2f, 0.2f, alpha));
		gr.fill(rect);

		if (alpha < 0.8f)
			alpha += 0.05f;
		pauseBg.draw(0, 0);
		gr.fill(rect);
		startGameOption.draw(pauseX, pauseY, startGameScale);
		optionOption.draw(pauseX, pauseY+45, optionScale);

		exitOption.draw(pauseX, pauseY+90, exitScale);
		//		}
		//		else
		//		{
		//			if (alpha > 0)
		//				alpha -= 0.01f;
		//		}



	}



	///////////////////////////////////////// UTILS //////////////////////////////////////////

	public boolean isMusic() {
		System.out.println("isMusic "+principale.playing()+" et "+principale.toString());
		return principale.playing();
	}

	public void setMusic(int etatMusic) {
		switch (etatMusic) {
		case 0:
			principale.stop();
			break;
		case 1:
			principale.loop();
			break;
		case 2:
			principale.pause();
			break;
		case 3:
			principale.resume();
			break;

		default:
			break;
		}
	}

	public void selectMusic(int id) {
		switch (id) {
		case 0:
			principale = null;
			principale = rm.getMusic("MENU_MUSIC");
			System.out.println("MENU_MUSIC "+principale.toString()+" et "+principale.playing());
			break;
		case 1:
			principale = rm.getMusic("GAME_MUSIC");
			System.out.println("GAME_MUSIC "+principale.toString()+" et "+principale.playing());
			break;
		case 2:

			break;

		default:
			break;
		}
	}

	public void setPauseBg(Graphics gr) {
		gr.copyArea(pauseBg, 0, 0);		
	}

	public void load(int etat) throws SlickException{
		switch (etat) {
		case 0:

			break;
		case 1:


			break;
		case 2:
			while (nextResource != null) { 
				try { 
					nextResource.load(); 
					// slow down loading for example purposes 
					//		                try { Thread.sleep(50); } catch (Exception e) {} 
				} catch (IOException e) { 
					throw new SlickException("Failed to load: "+nextResource.getDescription(), e); 
				} 

				nextResource = null; 

				if (LoadingList.get().getRemainingResources() > 0) { 
					nextResource = LoadingList.get().getNext(); 
				} else { 
					/*if (!started) { 
			                started = true; 
			                music.loop(); 
			                sound.play(); 
			            } */
				}
			} 


			break;

		default:
			break;
		}
	}

	public void chargemementGame(){
		try {

			rm.loadResources(new FileInputStream("data/jeu.xml"),true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}