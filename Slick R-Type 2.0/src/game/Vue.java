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
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

import states.Game;

// Faire un design pattern sigletton
// Contient toutes les valeurs partagées des etats

/**
 * Class Vue
 */
public class Vue {

	private static Vue _instance = new Vue();

	//
	// Fields
	//


	private Image[] background = new Image[4];
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

	private UnicodeFont font; 

	public Music principale = null;
	private int idMusic = 0;;
	private float volumeMusic = 0.5f;

	private Image pauseBg = null;

	private int width = Main.WIDTH;
	private int height = Main.HEIGHT ;

	boolean debugCol = false;

	boolean clignotementVie =false;

	Image valider = null;
	Image validerOk = null;

	private boolean validerMusic = true;

	private boolean validerFullscreen;



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
		//System.out.println("startGameScale "+startGameScale);
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


	public float getOptionScale() {
		return optionScale;
	}

	public void setOptionScale(float optionScale) {
		this.optionScale = optionScale;
	}



	public void setInstantImage(Graphics gr){
		gr.copyArea(pauseBg,0,0);
	}

	public Image getValider() {
		return valider;
	}

	public Image getValiderOk() {
		return validerOk;
	}








	////////////////////////////////////////// INIT //////////////////////////////////////////

	public void initPause() {
		pauseBg = pauseBg.getScaledCopy(800, 600);

	}

	public void initSkin() throws SlickException{
		skin = new SpriteSheet("/data/F-4Phantom.png", 30, 40,new Color(248,0,248));
	}

	public void initMenu(){

		//		try {
		//			rm.loadResources(new FileInputStream("data/menu.xml"));
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (SlickException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		Image menu = rm.getImage("BOUTONS");
		//System.out.println(menu.getHeight());

		startGameOption = menu.getSubImage(0, 28, 445, 70);
		optionOption = menu.getSubImage(180, 105, 300, 70);
		exitOption = menu.getSubImage(278, 184, 200, 70);
		background[1] = rm.getImage("BACKGROUD_MENU");

		selectMusic(0);
		principale.setVolume(volumeMusic);
		//System.out.println("Space !! ");
		try {
			//font = new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
			font = new UnicodeFont("data/coolvetica.ttf", 40,false,false);
			//uFont = new UnicodeFont(font, 20, false, false);
			font.getEffects().add(new ColorEffect());
			font.addAsciiGlyphs();
			font.loadGlyphs();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		valider = rm.getImage("VALIDER");
		validerOk = rm.getImage("VALIDER_OK");


		background[2] = rm.getImage("BACKGROUD_OPTION");
	}

	public void initGame(){
		principale = rm.getMusic("MUSIC_0");
		principale.setVolume(volumeMusic);
		background[0] = rm.getImage("BACKGROUD_JEU");	
		explosion = rm.getImage("EXPLOSION");
		missile = rm.getImage("ROCKET");
		missile.setRotation(90.0f);

		//joueur = rm.getImage("JOUEUR");
		sheet = rm.getImage("VAISSEAU_1");
		//joueur = sheet.getSubImage(265,3746,123,196);
		joueur = sheet.getSubImage(97,45,30,30);
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

		gr.setColor(new Color (0,0,0));
		gr.fillRect(0,0,width,height); //fond
		gr.setColor(new Color (1.0f,1.0f,1.0f));

		if (nextResource != null) { 
			String s = ("Loading: "+nextResource.getDescription());
			int l = s.length();
			gr.drawString("Loading: "+nextResource.getDescription(), 400-l*5, 280); 
		} 

		int total = LoadingList.get().getTotalResources(); 
		int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources(); 
		System.out.println("chargement"+total+" "+loaded);

		float bar = loaded / (float) total; 
		System.out.println(bar+"/"+total);
		gr.setColor(new Color (0,1.0f,1.0f));
		gr.fillRect(200,300,bar*400,20); 
		gr.setColor(new Color (1.0f,0,1.0f));
		gr.drawRect(200,300,400,20); 

		/*if (started) { 
	            image.draw(100,200); 
	            font.drawString(100,500,"LOADING COMPLETE"); 
	        } */
	}

	public void renderOption(GameContainer gc, Graphics gr,int optionX, int optionY) {
		//System.out.println("rendu d'option");

		//startGameOption.draw(optionX, optionY, startGameScale);
		background[2].draw(0, 0);

		font.drawString(optionX, optionY, "Music",new Color(1.0f,1.0f,1.0f));
		if(validerMusic)
			validerOk.draw(optionX+width*0.20f, optionY,0.7f);
		else
			valider.draw(optionX+width*0.20f, optionY,0.7f);

		font.drawString(optionX, optionY+height*0.10f, "Fullscreen",new Color(1.0f,1.0f,1.0f));
		if(validerFullscreen)
			validerOk.draw(optionX+width*0.20f, optionY+height*0.10f,0.7f);
		else
			valider.draw(optionX+width*0.20f, optionY+height*0.10f,0.7f);
		//optionOption.draw(optionX, optionY+45, optionScale);
		exitOption.draw(optionX, optionY+width*0.20f, exitScale);

	}

	public void renderSelection(Graphics g) {
		// TODO Auto-generated method stub
	}

	public int renderTest(Graphics g) {
		//skin.draw();
		return 0;
	}

	public int renderMenu(Graphics g,int menuX, int menuY,int titreX,int titreY) {
		Rectangle fond = new Rectangle (0, 0, 800, 600);
		g.setColor(new Color (0.2f, 0.2f, 0.2f));
		g.fill(fond);
		background[1].draw(0, 0);
		font.drawString(titreX, titreY, "R-Type Mania Nightcore Edition",new Color(1.0f,1.0f,1.0f));
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
	public int renderExplosion( Graphics gr, ArrayList<Explosion> explo  )
	{
		float[] cadre;
		if(!explo.isEmpty()){
			Iterator<Explosion> it2 = explo.iterator();
			while(it2.hasNext()){
				Explosion e =((Explosion) it2.next());
				cadre = e.getCadre();

				if(cadre != null)
					explosion.getSubImage((int)cadre[0],(int)cadre[1],(int)cadre[2],(int)cadre[3]).draw(e.getX(),e.getY(),1.5f);
			}
		}
		return 0;
	}

	public int renderJoueur(Graphics gr, VaisseauJoueur v) {


		if(debugCol){
			Rectangle rect = new Rectangle (v.getX(), v.getY(), v.getW(), v.getH());
			gr.setColor(new Color (0.2f, 0.2f, 0.2f));
			gr.fill(rect);
		}
		joueur.draw(v.getX()+60, v.getY(),3.0f);
		return 0;
	}

	public int renderBoard(Graphics gr, int[] param) {

		//Tableaux de bord
		//		g.setColor(org.newdawn.slick.Color.black);
		Rectangle board1 = new Rectangle (0, 571, 801, 30);
		gr.setColor(new Color (0.2f, 0.2f, 0.2f));
		gr.fill(board1);
		//g.drawRect(0, 0, 400, 30);
		//		g.drawImage(board, 100,100);
		gr.setColor(org.newdawn.slick.Color.white);
		//		uFont.drawString(50,10, "nb Explosion ="+nbEx);
		//		uFont.drawString(100,10,"Explosion :"+nbEx,	org.newdawn.slick.Color.black);
		gr.drawString("Explosion :"+param[0], 10,height*0.96f);
		gr.drawString("Alien :"+param[1], 150,height*0.96f);
		gr.drawString("Missile :"+param[2], width*0.31f,height*0.96f);
		gr.drawString("Score :"+param[3], width*0.85f,height*0.96f);

		//Lifebar
		Rectangle lifeBack = new Rectangle (width*0.50f, height*0.96f, width*0.20f, height*0.04f);
		gr.setColor(new Color (0,0,0));
		gr.fill(lifeBack);

		if(param[4]<=10){
			clignotementVie = true;
		}
		else
			clignotementVie = false;


		if(clignotementVie && param[5] == 1){}	
		else{
			Rectangle lifebar = new Rectangle (width*0.50f, height*0.96f, (width*0.20f)*(param[4]/100f), height*0.04f);

			if(param[4]>30){
				gr.setColor(new Color (0,1.0f,0));
			}
			else 
				gr.setColor(new Color (1f,0,0));


			gr.fill(lifebar);
		}

		//Ajouter un timer
		return 0;
	}

	public int render1Vaisseau(Graphics gr, Objet ob,int type) {	

		if(debugCol){
			Rectangle rect = new Rectangle (ob.getX(), ob.getY(), ob.getW(), ob.getH());
			gr.setColor(new Color (0.2f, 0.2f, 0.2f));
			gr.fill(rect);
		}

		alien[type].draw(ob.getX(),ob.getY());
		return 0;
	}

	public int render1Tir(Graphics gr, Objet ob, int type) {
		if(((Tir)ob).estVisible()){
			switch (type) {
			case 0:

				if(debugCol){
					Rectangle rect = new Rectangle (ob.getX(), ob.getY(), ob.getW(), ob.getH());
					gr.setColor(new Color (0.2f, 0.2f, 0.2f));
					gr.fill(rect);
				}
				missile.draw(ob.getX()+joueur.getWidth()/2,ob.getY()-joueur.getHeight()/2);
				break;
			case 1:
				laser.draw(ob.getX()+joueur.getWidth()/2,ob.getY()+joueur.getHeight()/2);
				break;

			default:
				break;
			}
		}

		return 0;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
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
		//		System.out.println("isMusic "+principale.playing()+" et "+principale.toString());
		return principale.playing();
	}


	public void setMusic(int etatMusic) {
		//System.out.println(principale.toString());
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
		case 4:
			principale.play();
			break;

		default:
			break;
		}
	}

	public void nextMusic(){
		System.out.println("next music is "+rm.getMusic("MUSIC_"+(idMusic+1))+" and "+"MUSIC_"+(idMusic+1));

		if(Game.cheat[0] ||Game.cheat[1] ||Game.cheat[2] ||Game.cheat[3] ){
			if(idMusic<rm.getNumber("NB_MUSIC_CHEAT")){
				if(idMusic == (rm.getNumber("NB_MUSIC_CHEAT")-1))
					idMusic = 0;
				else
					idMusic++;
				principale = rm.getMusic("MUSIC_CHEAT_"+idMusic);
			}
		}else if(idMusic<rm.getNumber("NB_MUSIC")){
			if(idMusic == (rm.getNumber("NB_MUSIC")-1))
				idMusic = 0;
			else
				idMusic++;
			principale = rm.getMusic("MUSIC_"+idMusic);
		}

		System.out.println("real music is "+principale+" and "+"MUSIC_"+idMusic);
	}

	public void selectMusic(int id) {
//		boolean ok = true;
//		while(ok){
			//System.out.println("boucle select "+id);
			if(Game.cheat[0] ||Game.cheat[1] ||Game.cheat[2] ||Game.cheat[3] ){
				if(id > 0 && id < 3){
					idMusic = id;
					principale = rm.getMusic("MUSIC_CHEAT_"+(id+1));
				}
			}
			else if(id > 0 && id < 8){
				idMusic = id;
				System.out.println("MUSIC_"+id);
				System.out.println("load a "+principale);
				principale = rm.getMusic("MUSIC_"+id);
				System.out.println("load b "+principale);
//			}
//			else
//				ok = false;
//			if(principale == null ){
//				loadNext();
//				System.out.println("load next ");
//			}
//			else
//				ok = false;
		}

			
		/*
		switch (id) {
		case 0:
			principale = rm.getMusic("MUSIC_1");
			//principale = null;
			//principale = rm.getMusic("MENU_MUSIC");
//			System.out.println("MENU_MUSIC "+principale.toString()+" et "+principale.playing());
			break;
		case 1:
			principale = rm.getMusic("MUSIC_2");
			//principale = rm.getMusic("GAME_MUSIC");
//			System.out.println("GAME_MUSIC "+principale.toString()+" et "+principale.playing());
			break;
		case 2:
			principale = rm.getMusic("MUSIC_3");
			break;
		case 3:
			principale = rm.getMusic("MUSIC_4");
			break;
		case 4:
			principale = rm.getMusic("MUSIC_5");
			break;
		case 5:
			principale = rm.getMusic("MUSIC_6");
			break;
		case 6:
			principale = rm.getMusic("MUSIC_7");
			break;

		default:
			break;
		}*/
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


	public void setScreen(int width, int height,boolean fullscreen) {
		this.width = width;
		this.height = height;
		try {
			System.out.println(width+" "+height+" "+fullscreen);
			Main.app.setDisplayMode(width,height,fullscreen);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void renderGameOver(GameContainer gc, Graphics gr, int gmOvX,
			int gmOvY,boolean ok) {
		pauseBg.draw(0, 0);
		Rectangle rect = new Rectangle (0, 0, 801, 600);
		gr.setColor(new Color (0.2f, 0.2f, 0.2f, alpha));
		gr.fill(rect);

		if (alpha < 0.8f)
			alpha += 0.05f;

		gr.fill(rect);

		gr.setColor(new Color (1.0f,1.0f,1.0f));

		gr.drawString("You loose ! ", 400, 280); 
		if(ok)
			gr.drawString("Press escape", 400, 300); 
	}

	public void setValiderMusic(boolean b) {
		validerMusic  = b;

	}

	public boolean isValiderMusic() {
		return validerMusic;

	}

	public void setValiderFullScreen(boolean b) {
		validerFullscreen = b;

	}

	public boolean getValiderFullScreen() {
		return validerFullscreen;
	}

	public void loadNext(){
		try { 
			nextResource.load(); 
			// slow down loading for example purposes 
			//try { Thread.sleep(50); } catch (Exception e) {} 
		} catch (IOException e) { 
			try {
				throw new SlickException("Failed to load: "+nextResource.getDescription(), e);
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		} 
	}
}
