package game;

import java.awt.Toolkit;
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
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.Animation;

import states.Game;


// Contient toutes les valeurs partag�es des etats

/* URGENT 
 * commenter le code et le rendre propre
 * Ajouter des textes a history et ne plus l'afficher si deja vue // fait mais il faut savoir ou son les level
 * Verifier qu'on peut aller partotu sans planter
 * 
 * 
 * EN PLUS 
 * bug dans la gestion de playlist
 * implementer keyconfig ou pas
 * renommer les XML
 * mettre en mode francais/anglais
 * Option r�solution ecran // mort trop difficile
 * credit
 * faire une fonction qui decoupe de texte avec de /n pour le mode history
 * 
 * Faire le rapport / l'affiche / le diapo
 */

/**
 * Class View
 * Singleton
 * @author Etienne Grandier-Vazeille
 *
 */
public class View {

	private static View _instance = new View();

	//
	// Fields
	//


	private Image[] background = new Image[5];
	private Image player;
	private Animation[] alien = new Animation[2];
	private Image laser;
	private Image missile;
	private Image[] boss = new Image[2];;
	
	private int intro=0;
	private boolean movingUp,movingDown;
	//private Image animatedRotatingShip1Frames;
	
	private Image nastyProjectile;
	private Image explosion;
	private Image sheet;
	private XMLPackedSheet ship;
	private XMLPackedSheet nastyProjectileSheet;
	private XMLPackedSheet animatedRotatingShip1Sheet;
	private XMLPackedSheet animatedRotatingShip2Sheet;
	private XMLPackedSheet animatedRotatingShip3Sheet;
	private String dossierSkin = "data/skin1";
	private SpriteSheet skin;
	private Animation animatedEnergyBall;
	private Animation animatedRotatingShip1;
	private Animation animatedRotatingShip2;
	private Animation animatedRotatingShip3;
	private Animation ship1CharacterSelectIntro;
	private Animation animatedSpagettie;
	private int[][] skinDef;
	private Animation ship1MoveDown;
	private Animation ship1MoveUp;

	private Image exitOption = null;
	private Image startGameOption = null;
	private Image optionOption = null;
	private Image highscoreOption = null;
	private Image controlsOption = null;
	private Image ship1 = null;
	private Image ship2 = null;
	private Image ship3 = null;
	private Image life;
	private float startGameScale = 0.7f;
	private float exitScale = 0.7f;
	private float optionScale = 0.7f;
	private float controlsScale = 0.7f;

	private ResourceManager rm = ResourceManager.getInstance();
	public DeferredResource nextResource; 
	public String shipname = "VAISSEAU_1";

	float alpha = 0;

	private UnicodeFont fonts[] = null; 

	public Music principale = null;
	private int idMusic = 0;
	private float volumeMusic = 0.8f;
	private boolean selectMusic[];

	private Image pauseBg = null;

	private int width = Main.WIDTH;
	private int height = Main.HEIGHT ;


	private int nbCligne = 0;
	boolean clignotementVie =false;

	Image valider = null;
	Image validerOk = null;
	private boolean validerMusic = true;
	private boolean validerFullscreen;

	private boolean msgOn = false;
	private String msg = "";
	private int msgTime = 0;

	private boolean debug = false;
	boolean debugCol = false;

	private Image[] buttons;

	private boolean[] champ;
	
	// En milliseconde
	private int timer;

	private XMLPackedSheet alienSheet;

	private int maxLevel;

	private String edition;










	public final static View getInstance(){
		return _instance;
	}
	//
	// Constructors
	//

	/**
	 * Chargement des images
	 * @throws FileNotFoundException 
	 */
	public View () {
		/*
		background[0] = new Image("data/land.jpg");
		player = new Image("data/plane.png");
		player.setRotation(90.0f);
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
	
	public Image getControlsOption() {
		return controlsOption;
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

	public float getControlsScale() {
		return controlsScale;
	}

	public void setControlsScale(float controlsScale) {
		this.controlsScale = controlsScale;
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
		controlsOption = menu.getSubImage(90, 330, 355, 74);
		background[1] = rm.getImage("BACKGROUD_MENU");

		selectMusic(0);
		principale.setVolume(volumeMusic);
		//System.out.println("Space !! ");
		
//		try {
//			//font = new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
//			fonts[0] = new UnicodeFont("data/coolvetica.ttf", 40,false,false);
//			//uFont = new UnicodeFont(font, 20, false, false);
//			fonts[0].getEffects().add(new ColorEffect());
//			fonts[0].addAsciiGlyphs();
//			fonts[0].loadGlyphs();
//		} catch (SlickException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		try {
//			fonts[1] = initFont("data/coolvetica.ttf",40);
//			//			//font = new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
//			//			fonts[1] = new UnicodeFont("data/coolvetica.ttf", 40,false,false);
//			//			//uFont = new UnicodeFont(font, 20, false, false);
//			//			fonts[1].getEffects().add(new ColorEffect());
//			//			fonts[1].addAsciiGlyphs();
//			//			fonts[1].loadGlyphs();
//		} catch (SlickException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		valider = rm.getImage("VALIDER");
		validerOk = rm.getImage("VALIDER_OK");


		background[2] = rm.getImage("BACKGROUD_OPTION");
		champ = new boolean[10];
		
		edition = rm.getText("EDITION");
		if(edition.length()>10)
			edition =edition.substring(0, 10);
	}


	public UnicodeFont initFont(String name,int size) throws SlickException{
		UnicodeFont uf;
		uf = new UnicodeFont(name, size,false,false);
		uf.getEffects().add(new ColorEffect());
		uf.addAsciiGlyphs();
		uf.loadGlyphs();
		return uf;
	}
	
	public void initFontS() {
		try {
			fonts[0] = initFont("data/coolvetica.ttf",40);
			fonts[1] = initFont("data/Destroyed Aero.ttf",40);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void initCharacterSelectScreen() throws SlickException{

		animatedRotatingShip1Sheet = new XMLPackedSheet("data/introVaisseau1.png","data/introVaisseau1.png.xml");
		animatedRotatingShip2Sheet = new XMLPackedSheet("data/introVaisseau2.png","data/introVaisseau2.png.xml");
		animatedRotatingShip3Sheet = new XMLPackedSheet("data/introVaisseau3.png","data/introVaisseau3.png.xml");
		principale = rm.getMusic("MUSIC_7");
		principale.setVolume(volumeMusic);
		Image menu2 = rm.getImage("BOUTONS_CHARSELECT");
		Image menu2_alt=rm.getImage("BOUTONS_CHARSELECT_ALT");
		//System.out.println(menu.getHeight());
		background[4] = rm.getImage("BACKGROUD_CHARSELECT");
		ship1 = menu2.getSubImage(0, 28, 445, 70);
		ship2 = menu2.getSubImage(180, 105, 300, 70);
		ship3 = menu2.getSubImage(220, 184, 220, 70);
		
		animatedRotatingShip1 = new Animation();
		animatedRotatingShip2 = new Animation();
		animatedRotatingShip3 = new Animation();
		for (int i = 1; i<45; i++)
			animatedRotatingShip1.addFrame(animatedRotatingShip1Sheet.getSprite("v1csa"+i+".gif"), 35);
		for (int i = 1; i<45; i++)
			animatedRotatingShip2.addFrame(animatedRotatingShip2Sheet.getSprite("v2csa"+i+".gif"), 35);
		for (int i = 1; i<45; i++)
			animatedRotatingShip3.addFrame(animatedRotatingShip3Sheet.getSprite("v3csa"+i+".gif"), 35);
		//for(int i=1;i<animatedRotatingShip1Frames;i++)
		//	animatedRotatingShip1.addFrame("v1csa"+i+".gif", 15);
		selectMusic(7);
		//System.out.println("Space !! ");
//		try {
//			//font = new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
//			font = new UnicodeFont("data/coolvetica.ttf", 40,false,false);
//			//uFont = new UnicodeFont(font, 20, false, false);
//			font.getEffects().add(new ColorEffect());
//			font.addAsciiGlyphs();
//			font.loadGlyphs();
//		} catch (SlickException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}

	public void initGame() throws SlickException{
		maxLevel = Integer.parseInt(rm.getText("MAX_LEVEL"));
		//ship= new XMLPackedSheet ("data/pack1.png","data/pack1.png.xml");
		nastyProjectileSheet=new XMLPackedSheet("data/nastyProjectileSheet.png","data/nastyProjectileSheet.png.xml");
		alienSheet=new XMLPackedSheet("data/alienSheet.png","data/alienSheet.png.xml");
		animatedEnergyBall= new Animation();
		alien[0]=new Animation();
		principale = rm.getMusic("MUSIC_0");
		principale.setVolume(volumeMusic);
		background[0] = rm.getImage("BACKGROUD_JEU");	
		explosion = rm.getImage("EXPLOSION");
		missile = rm.getImage("ROCKET");
		missile.setRotation(90.0f);
		laser = rm.getImage("LASER");
		laser.setRotation(90.0f);
		boss[0] = rm.getImage("BOSS_1");
		nastyProjectile = nastyProjectileSheet.getSprite("nastyProjectile1.gif");
		//nastyProjectile.setRotation(270.0f);
		
//		Image spag = rm.getImage("SPAG");
		Image spagt[] = new Image[3];
		spagt[0] = rm.getImage("BOSS_W_1").getSubImage(0, 0, 60, 20);
		spagt[1] = rm.getImage("BOSS_W_1").getSubImage(0, 20, 60, 20);
		spagt[2] = rm.getImage("BOSS_W_1").getSubImage(0, 20, 60, 20);
		animatedSpagettie= new Animation(spagt,3,true);
		

		//player = rm.getImage("JOUEUR");
		//sheet = rm.getImage("VAISSEAU_1");
		//player = sheet.getSubImage(265,3746,123,196);
		//player = sheet.getSubImage(97,45,30,30);
		//player=sheetTest.getSprite("VAISSEAU_1.gif");
		//player.setRotation(90.0f);
		//laser = sheet.getSubImage(265,3746,123,196);
		//laser.setRotation(90.0f);

		animatedEnergyBall.addFrame(nastyProjectileSheet.getSprite("nastyProjectile1.gif"), 100);
		animatedEnergyBall.addFrame(nastyProjectileSheet.getSprite("nastyProjectile2.gif"), 100);
		
		alien[0].addFrame(alienSheet.getSprite("e1csa1.gif"), 10);
		alien[0].addFrame(alienSheet.getSprite("e1csa2.gif"), 10);
		life=rm.getImage("HEART");
		
		try {
			pauseBg = new Image(800,600);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean[] initSelectionMusic(boolean[] selectMusicO) {
		selectMusic = new boolean[rm.getNumber("NB_MUSIC")];
		selectMusicO = new boolean[rm.getNumber("NB_MUSIC")];
		for(int i=1;i<selectMusicO.length;i++){
			selectMusic[i] = true;
			selectMusicO[i] = false;
		}
		return selectMusicO;

	}



	///////////////////////////////////////// RENDER /////////////////////////////////////////

	public void renderChargement(GameContainer gc, Graphics gr) {

		gr.setColor(new Color (0,0,0));
		gr.fillRect(0,0,width,height); //fond
		gr.setColor(new Color (1.0f,1.0f,1.0f));
		/*
		if (nextResource != null) { 
			String s = ("Loading: "+nextResource.getDescription());
			int l = s.length();
			gr.drawString("Loading: "+nextResource.getDescription(), 400-l*5, 280); 
		} 

		int total = LoadingList.get().getTotalResources(); 
		int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources(); 
		System.out.println("chargement"+total+" "+loaded);

		float bar = loaded / (float) total; 
		System.out.println((int)(bar*400)+"/"+total);
		gr.setColor(new Color (0,1.0f,1.0f));
		gr.fillRect(200,300,bar*400,20); 
		gr.setColor(new Color (1.0f,0,1.0f));
		gr.drawRect(200,300,400,20); 
		 */
		/*if (started) { 
	            image.draw(100,200); 
	            font.drawString(100,500,"LOADING COMPLETE"); 
	        } */
	}

	public void renderOption(GameContainer gc, Graphics gr,int optionX, int optionY,int size, boolean[] inside,int posTxt[][]) {
		background[2].draw(0, 0);
		

		gr.setColor(new Color (1.0f, 1.0f, 1.0f));
		

		fonts[0].drawString(optionX, optionY+posTxt[0][1], "Music",new Color(1.0f,1.0f,1.0f));
		if(validerMusic)
			validerOk.draw(optionX+posTxt[0][0], optionY+posTxt[0][1],0.7f);
		else
			valider.draw(optionX+posTxt[0][0], optionY+posTxt[0][1],0.7f);
		if(inside[0]){
			gr.fill(new Rectangle (optionX, optionY+42+posTxt[0][1], 100, 5));
		}

		fonts[0].drawString(optionX+posTxt[1][0], optionY+posTxt[1][1], "Music Playlist",new Color(1.0f,1.0f,1.0f));
		if(inside[1]){
			gr.fill(new Rectangle (optionX+posTxt[1][0], optionY+42+posTxt[1][1], 220, 5));
		}

//		fonts[0].drawString(optionX, optionY+height*0.30f, "Fullscreen",new Color(0.5f,0.5f,0.5f));
//		if(validerFullscreen)
//			validerOk.draw(optionX+width*0.20f, optionY+height*0.30f,0.7f);
//		else
//			valider.draw(optionX+width*0.20f, optionY+height*0.30f,0.7f);
//		if(inside[2]){
//			gr.fill(new Rectangle (optionX, optionY+height*0.37f, 180, 5));
//		}
		//System.out.println("rendu d'option pro ");
//		gr.setColor(new Color(0.5f,0.5f,0.5f));
//		gr.drawString( "Only Pro version",optionX+width*0.28f, optionY+height*0.325f);
		//optionOption.draw(optionX, optionY+45, optionScale);
		exitOption.draw(optionX+posTxt[2][0], optionY+posTxt[2][1], exitScale);

	}


	public int renderTest(Graphics g) {
		//skin.draw();
		return 0;
	}

	public int renderMenu(Graphics gr,int menuX, int menuY,int titreX,int titreY, int hSX,String s[],int pos[],int posTxt[][]) {
		Rectangle fond = new Rectangle (0, 0, 800, 600);
		gr.setColor(new Color (0.2f, 0.2f, 0.2f));
		gr.fill(fond);
		background[1].draw(0, 0);
		fonts[0].drawString(titreX, titreY, "R-Type Mania "+edition+" Edition",new Color(1.0f,1.0f,1.0f));
		//font.drawString(titreX+430, titreY+30, "v."+Main.version,new Color(1.0f,1.0f,1.0f));
		gr.setColor(new Color (0,0,0));
		gr.drawString("v."+Main.version, titreX+430, titreY+50);

		//HighScore
		gr.setColor(new Color(1.0f,1.0f,1.0f));
		gr.drawString("HighScores", hSX+80, height*0.30f);

		if(s!=null && s.length>0){	
			for(int i=0;i<s.length;i++){	// Il faut faire apparaitre les scores 1 par 1
				gr.drawString(s[i], pos[i], height*0.35f+25*i);
			}
			if(s.length == 10)
				gr.drawString(s[9], pos[9], height*0.35f+25*9);
		}
		//gr.drawString(hm.getHighscoreString(), hSX, 300);
		//System.out.println(hm.getHighscoreString());


//		startGameOption.draw(menuX, menuY, startGameScale);
//		optionOption.draw(menuX+350, menuY, optionScale);
//		controlsOption.draw(menuX+550, menuY, controlsScale);
//		exitOption.draw(menuX+700, menuY, exitScale);
		startGameOption.draw(menuX+posTxt[0][0], menuY+posTxt[0][1], startGameScale);
		optionOption.draw(menuX+posTxt[1][0], menuY+posTxt[1][1], optionScale);
		controlsOption.draw(menuX+posTxt[2][0], menuY+posTxt[2][1], controlsScale);
		exitOption.draw(menuX+posTxt[3][0], menuY+posTxt[3][1], exitScale);
		
		fonts[1].drawString(width*0.84f, 0, "Credit",new Color(1.0f,1.0f,1.0f));
		return 0;

	}

	public int renderCharacterSelectScreen(Graphics g, int menuX, int menuY){
		Rectangle fond = new Rectangle (0, 0, 800, 600);
		g.setColor(new Color (0.2f, 0.2f, 0.2f));
		g.fill(fond);
		background[4] = rm.getImage("BACKGROUD_CHARSELECT");
		background[4].draw(0,0);
		ship1.draw(menuX, menuY, startGameScale);
		ship2.draw(menuX+350, menuY, optionScale);
		ship3.draw(menuX+600, menuY, exitScale);
		if (intro==1)
			animatedRotatingShip1.draw(800/2-300/2, 600/2, 300, 150);
		if (intro==2)
			animatedRotatingShip2.draw(800/2-300/2, 600/2, 300, 150);
		if (intro==3)
			animatedRotatingShip3.draw(800/2-300/2, 600/2, 300, 150);
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

//
//	/**
//	 * @return       int
//	 * @param        g
//	 */
//	public int renderVaisseau( String sh  )
//	{
//		return 0;
//	}
//
//
//	/**
//	 * @return       int
//	 * @param        g
//	 */
//	public int renderShot( Graphics g, ArrayList<Shot> tirs )
//	{
//		if(!tirs.isEmpty()){
//			Iterator<Shot> it = tirs.iterator();
//			while(it.hasNext()){
//				Tir t =((Tir) it.next());
//				//if(t.estVisible())
//				//t.getImage().draw(t.getX()+10,t.getY()+38);
//			}
//		}
//		return 0;
//	}


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

	public int renderPlayer(Graphics gr, PlayerShip v,int clig) {

		if(intro==1)
		{
			player=ship.getSprite("v1idle.gif");
			player.setRotation(90.0f);
		}
		else if (intro ==2)
			player=ship.getSprite("v3idle.gif");
		else if (intro ==3)
			player=ship.getSprite("v2idle.gif");
				




		if(v.isBorn()){
			if(clig == 1){
				nbCligne++;
				if(!movingDown && !movingUp){
					player.draw(v.getX()-v.getDecalageX(), v.getY()-v.getDecalageY());
					ship1MoveDown.restart();
					ship1MoveUp.restart();
					
				}
				else if(movingDown)
					getShip1MoveDown().draw(v.getX()-v.getDecalageX(), v.getY()-v.getDecalageY());
				else if(movingUp)
				{
					getShip1MoveUp().draw(v.getX()-v.getDecalageX(), v.getY()-v.getDecalageY());
				}
			}else{

			}
			if(nbCligne >50){
				v.setBorn(false);
				nbCligne = 0;
			}

		}else{

			if(!movingDown && !movingUp){
				player.draw(v.getX()-v.getDecalageX(), v.getY()-v.getDecalageY());
				ship1MoveDown.restart();
				ship1MoveUp.restart();
				
			}
			else if(movingDown)
				getShip1MoveDown().draw(v.getX()-v.getDecalageX(), v.getY()-v.getDecalageY());
			else if(movingUp)
			{
				getShip1MoveUp().draw(v.getX()-v.getDecalageX(), v.getY()-v.getDecalageY());
			}
		}
		if(debugCol){
			Rectangle rect = new Rectangle (v.getX(), v.getY(), v.getW(), v.getH());
			gr.setColor(new Color (0.2f, 0.2f, 0.2f));
			gr.fill(rect);
		}


		//player.draw(v.getX(), v.getY(), v.getW(), v.getH());


		return 0;
	}

	public int renderHUD(Graphics gr, int[] param) {
		
		// Pour le timer interne de la vue
		timer = param[11];
		int tempTimer = param[11]/1000;
		int min = tempTimer/60;
		int sec = tempTimer-min*60;
		String secS;
		if(sec <10)
			secS="0"+sec;
		else
			secS=""+sec;

		// HUD
		if(debug ){
			gr.drawString("Explosion :"+param[1], 10,height*0.96f);
			gr.drawString("Alien :"+param[2], 150,height*0.96f);
			gr.drawString("Missile :"+param[3], width*0.31f,height*0.96f);
		}

		//black
		gr.setColor(org.newdawn.slick.Color.black);
		//gr.drawString("P1 - "+param[4], width*0.0125f+2,height*0.04f+2);
		gr.drawString("P1 - "+param[4], width*0.0125f+2,height*0.0125f+2);
		gr.drawString(param[7]+" x ", width*0.0125f+2,height*0.04f+2);
		life.draw(width*0.050f+1,height*0.04f+2);
		gr.drawString("Level 1-"+param[8], width*0.875f+2,height*0.0125f+2);
		gr.drawString("Timer "+min+"'"+secS+"''", width*0.87f+2,height*0.04f+2);	
		

		gr.drawString("Weapon:", width*0.0125f+1,height*0.92f+1);
		if(param[6] == 21){
			laser.draw(width*0.11f,height*0.91f, 1f);
		}else if(param[6] == 22){
			missile.draw(width*0.09f,height*0.905f, 0.7f);
		}

		//white
		gr.setColor(org.newdawn.slick.Color.white);
		//				gr.drawString("P1 - "+param[4], width*0.0125f,height*0.0125f);
		gr.drawString("P1 - "+param[4], width*0.0125f,height*0.0125f);
		gr.drawString(param[7]+" x ", width*0.0125f,height*0.04f);
		gr.drawString("Level 1-"+param[8], width*0.875f,height*0.0125f);
		gr.drawString("Timer "+min+"'"+secS+"''", width*0.87f,height*0.04f);			
		gr.drawString("Weapon:", width*0.0125f,height*0.92f);




		//Lifebar
		Rectangle lifeBack = new Rectangle (width*0.0125f, height*0.96f, width*0.20f, height*0.04f);
		gr.setColor(new Color (0,0,0));
		gr.fill(lifeBack);

		if(param[5]<=10){
			clignotementVie = true;
		}
		else
			clignotementVie = false;


		if(clignotementVie && param[0] == 1){}	
		else{
			Rectangle lifebar = new Rectangle (width*0.0125f, height*0.96f, (width*0.20f)*(param[5]/100f), height*0.04f);

			if(param[5]>30){
				gr.setColor(new Color (0,1.0f,0));
			}
			else 
				gr.setColor(new Color (1f,0,0));


			gr.fill(lifebar);

		}
		gr.setColor(new Color (1f,0,1f));
		gr.drawString(""+param[5], width*0.0925f,height*0.965f);

		//*/

		return 0;
	}

	public int render1Vaisseau(Graphics gr, Objet ob,int type) {	

		if(debugCol){
			Rectangle rect = new Rectangle (ob.getX(), ob.getY(), ob.getW(), ob.getH());
			gr.setColor(new Color (0.2f, 0.2f, 0.2f));
			gr.fill(rect);
		}
		//System.out.println("type = "+type+"  timer = "+timer);
		if(type>100 && type<200){
			alien[type-101].draw(ob.getX(),ob.getY());
		}
//		else if(type>200 && type<300)
//		{
//			System.out.println("type = "+type+"  timer = "+timer);
//			boss[type-201].draw(ob.getX(),ob.getY());
//		}
			
		
		return 0;
	}

	public int render1Tir(Graphics gr, Objet ob, int type) {
		if(((Shot)ob).estVisible()){
			if(debugCol){
				Rectangle rect = new Rectangle (ob.getX(), ob.getY(), ob.getW(), ob.getH());
				gr.setColor(new Color (0.2f, 0.2f, 0.2f));
				gr.fill(rect);
			}
			switch (type) {
			case 21:
				laser.draw(ob.getX()+10,ob.getY()-15);
				break;

			case 22:
				//missile.draw(ob.getX()+player.getWidth()/2,ob.getY()-player.getHeight()/2);
				missile.draw(ob.getX(), ob.getY()-ob.getH()/2);
				break;
			case 23:
				animatedEnergyBall.draw(ob.getX(), ob.getY());
				break;
			case 24:
				animatedSpagettie.draw(ob.getX(), ob.getY());
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

	public void renderPause(GameContainer gc, Graphics gr,int pauseX, int pauseY, boolean cheatModOn, String pass, int posTxt[][]) {
		//		if (gc.isPaused())
		//		{
		Rectangle rect = new Rectangle (0, 0, 801, 601);
		gr.setColor(new Color (0.2f, 0.2f, 0.2f, alpha));
		gr.fill(rect);



		if (alpha < 0.8f)
			alpha += 0.05f;
		pauseBg.draw(0, 0);
		gr.fill(rect);
		startGameOption.draw(pauseX+posTxt[0][0], pauseY+posTxt[0][1], startGameScale);
		optionOption.draw(pauseX+posTxt[1][0], pauseY+posTxt[1][1], optionScale);
		controlsOption.draw(pauseX+posTxt[2][0], pauseY+posTxt[2][1], controlsScale);
		exitOption.draw(pauseX+posTxt[3][0], pauseY+posTxt[3][1], exitScale);
		
		//		}
		//		else
		//		{
		//			if (alpha > 0)
		//				alpha -= 0.01f;
		//		}

		if(cheatModOn){
			Rectangle textArea= new Rectangle (0, 0, 801, 30);
			gr.setColor(new Color (0, 0, 0));
			gr.fill(textArea);
			gr.setColor(new Color (1.0f, 1.0f, 1.0f));
			gr.drawString(pass,10f,5f);

		}

		if(msgOn){
			//System.out.println("on message "+msg);
			gr.setColor(new Color (1f,1f,1f));
			//gr.drawString(msg, width*0.80f,5f);
			gr.drawString(msg, 10f,5f);
			//font.drawString(width*0.8f, 10, msg, new Color(1f,1f,1f));		
			msgTime-=10;
			if(msgTime <0){
				//System.out.println("fin boucle message");
				msgTime = 0;
				msgOn = false;
			}
		}


	}

	public void renderGameOver(GameContainer gc, Graphics gr, int gmOvX,
			int gmOvY,boolean ok,int param[],String name) {
		pauseBg.draw(0, 0);
		Rectangle rect = new Rectangle (0, 0, width+1, height+1);
		gr.setColor(new Color (0.2f, 0.2f, 0.2f, alpha));
		gr.fill(rect);

		if (alpha < 0.8f)
			alpha += 0.05f;

		gr.fill(rect);

		gr.setColor(new Color (1.0f,1.0f,1.0f));

		if(param[4] == 1){

			if(param[2] == 1)
				gr.drawString("Best score !", width*0.45f, height*0.33f); 
			if(param[1] == 0)
				gr.drawString("You are not in highscore ", width*0.42f, height*0.36f); 
			else
				gr.drawString("You are TOP10 ", width*0.45f, height*0.36f);

			gr.drawString("Score :  "+param[0], width*0.45f, height*0.40f); 
			gr.drawString("Time :  "+param[3], width*0.45f, height*0.43f); 

			if(param[1] ==1){
				gr.drawString("Type your name here ", width*0.42f, height*0.46f);
				gr.setColor(new Color (0,0,0));
				gr.fill(new Rectangle (width*0.415f, height*0.50f, 180,25 ));
				gr.setColor(new Color (1.0f,1.0f,1.0f));
				gr.drawString(name, width*0.42f, height*0.50f);
			}
			else{
				if(ok)
					gr.drawString("Press space", width*0.45f, height*0.56f); 
			}

		}else{

			fonts[1].drawString(gmOvX-width*0.16f, gmOvY-height*0.06f, "Level "+param[5]+" completed !");
			//gr.drawString("Level "+param[5]+" completed !", gmOvX-width*0.05f, gmOvY); 
			gr.drawString("Score :  "+param[0], gmOvX, gmOvY+height*0.03f); 
			gr.drawString(" Time :  "+param[3], gmOvX, gmOvY+height*0.06f); 

			gr.drawString("Press space to continu", gmOvX-width*0.075f, gmOvY+height*0.12f); 
			gr.drawString("Press escape to Main Menu", gmOvX-width*0.09f,gmOvY+height*0.16f); 
		}

	}


	public void renderSelection(GameContainer gc, Graphics gr,int selectX, int selectY,boolean[] inside) {
		for(int i=1;i<rm.getNumber("NB_MUSIC");i++){
			//			Rectangle rect = new Rectangle (selectX+10, selectY+40*(i-1)+50, getValider().getWidth()*0.45f, getValider().getHeight()*0.45f);
			//			gr.setColor(new Color (1.0f, 0.2f, 0.2f));
			//			gr.fill(rect);
			fonts[0].drawString(selectX+10, selectY, "Select your music : ",new Color(1.0f,1.0f,1.0f));
			fonts[0].drawString(selectX+width*0.08f, selectY+height*0.066f*i, rm.getPlaylist("MUSIC_"+i),new Color(1.0f,1.0f,1.0f));
			if(selectMusic[i])
				validerOk.draw(selectX, selectY+height*0.066f*i,0.7f);
			else
				valider.draw(selectX, selectY+height*0.066f*i,0.7f);

			
			if(inside[i]){
				gr.setColor(new Color(1.0f,1.0f,1.0f));
				gr.fill(new Rectangle (selectX+width*0.075f, selectY+height*0.066f*i+10, 5, 30));
			}


			//selectX && mouseX <= selectX + vue.getValider().getWidth()) &&
			//( mouseY >= selectY+vue.getValider().getHeight()*i && mouseY <= selectY  + vue.getValider().getHeight()*i*0.05f) ){
		}
		exitOption.draw(selectX+10, selectY+height*0.80f, exitScale);

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
		//System.out.println("next music is "+rm.getMusic("MUSIC_"+(idMusic+1))+" and "+"MUSIC_"+(idMusic+1));

		if(Game.cheat[0] ||Game.cheat[1] ||Game.cheat[2] ||Game.cheat[3] ){
			if(idMusic == (rm.getNumber("NB_MUSIC_CHEAT")-1) || idMusic>rm.getNumber("NB_MUSIC_CHEAT"))
				idMusic = 0;
			else
				idMusic++;
			principale = rm.getMusic("MUSIC_CHEAT_"+idMusic);

		}else{
			if(idMusic == (rm.getNumber("NB_MUSIC")-1) || idMusic>rm.getNumber("NB_MUSIC"))
				idMusic = 1;
			else
				idMusic++;
			if(selectMusic[idMusic])
				principale = rm.getMusic("MUSIC_"+idMusic);
			//			else
			//				principale = rm.getMusic("MUSIC_0");
		}

		//System.out.println("real music is "+principale+" and "+"MUSIC_"+idMusic);
	}

	public void selectMusic(int id) {
		//		boolean ok = true;
		//		while(ok){
		//System.out.println("boucle select "+id);
		if(Game.cheat[0] ||Game.cheat[1] ||Game.cheat[2] ||Game.cheat[3] ){
			if(id > -1 && id < rm.getNumber("NB_MUSIC_CHEAT")){
				idMusic = id;
				principale = rm.getMusic("MUSIC_CHEAT_"+(id+1));
			}
		}
		else if(id > -1 && id < rm.getNumber("NB_MUSIC")){
			idMusic = id;
			//System.out.println("MUSIC_"+id);
			//System.out.println("load a "+principale);
			principale = rm.getMusic("MUSIC_"+id);
			//System.out.println("load b "+principale);
		}
		else if(id == 42){
			principale = rm.getMusic("MUSIC_BOSS_1");
		}
	}
	
	public void setAltBouton(boolean insideChar1,boolean insideChar2, boolean insideChar3, int menuX, int menuY){
		
		Image menu2 = rm.getImage("BOUTONS_CHARSELECT");
		Image menu2_alt=rm.getImage("BOUTONS_CHARSELECT_ALT");
		if(insideChar1 && !insideChar2 && !insideChar3)
		{
			ship1 = menu2_alt.getSubImage(0, 28, 445, 70);
			intro=1;
			
		}
		else if(!insideChar1 && insideChar2 && !insideChar3)
		{
			ship2= menu2_alt.getSubImage(180, 105, 300, 70);
			intro=2;
		}
		
		else if(!insideChar1 && !insideChar2 && insideChar3)
		{
			ship3 = menu2_alt.getSubImage(220, 184, 220, 70);
			intro=3;
		}
		else
		{
			ship1 = menu2.getSubImage(0, 28, 445, 70);
			ship2 = menu2.getSubImage(180, 105, 300, 70);
			ship3 = menu2.getSubImage(220, 184, 220, 70);
			intro=0;
		}
		ship1.draw(menuX,menuY);
		ship2.draw(menuX,menuY);
		ship3.draw(menuX,menuY);
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

	public void loadGame(){
		try {
			//rm.loadResources(new FileInputStream("data/jeu.xml"),true);
			rm.loadResources(getClass().getResourceAsStream("/data/ressources.xml"),true); // Methode compatible avec les jars


		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fonts= new UnicodeFont[3];
		initFontS();
	}


	public void setScreen(int width, int height,boolean fullscreen) {
		this.width = width;
		this.height = height;
		try {
			//System.out.println(width+" "+height+" "+fullscreen);
			Main.app.setDisplayMode(width,height,fullscreen);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void setValiderSetMusic(boolean b) {
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





	public boolean isSelectMusic(int i) {
		return selectMusic[i];
	}



	public void setValiderSelectMusic(int i, boolean b) {
		selectMusic[i] =b;

	}

	public void setMessage(String msg, int i) {
		msgOn = true;
		this.msg=msg;
		msgTime=i;
		//System.out.println("new message");

	}

	public Image getIPlayer() {
		return player;
	}

	//	public void initRes() throws SlickException {
	//		initMenu();
	//		initGame();
	//		initPause();
	//		initCharacterSelectScreen();
	//		
	//		
	//		
	//	}
	public int getIdMusic() {
		return idMusic;
	}

	public Image getIBackground(int id) {
		return background[id];
	}

	public void initOption() {
		buttons = new Image[2];
		buttons[0] = rm.getImage("BOUTON_L");
		buttons[1] = rm.getImage("BOUTON_R");

	}

	public Image getButton(int id) {
		if(id <=buttons.length)
			return buttons[id];
		else
			return null;
	}

	public void inOption(int i) {
		champ[i]= true;
	}

	public void resetInOption() {
		for(int i= 0;i<champ.length;i++){
			champ[i] = false;
		}
		
	}

	public void renderControls(GameContainer gc, Graphics gr,int ctrX, int ctrY,int posTxt[][]) {
		background[2].draw(0, 0);
		
		fonts[1].drawString(ctrX, ctrY+posTxt[0][1],  "Up                                     Z", new Color(1.0f,1.0f,1.0f));
		fonts[1].drawString(ctrX, ctrY+posTxt[1][1],  "Down                              S", new Color(1.0f,1.0f,1.0f));
		fonts[1].drawString(ctrX, ctrY+posTxt[2][1],  "Right                               D", new Color(1.0f,1.0f,1.0f));
		fonts[1].drawString(ctrX, ctrY+posTxt[3][1],  "Left                                Q", new Color(1.0f,1.0f,1.0f));
		fonts[1].drawString(ctrX, ctrY+posTxt[4][1],  "Fire                         SPACE", new Color(1.0f,1.0f,1.0f));	
		fonts[1].drawString(ctrX, ctrY+posTxt[5][1], "Next Weapon               E", new Color(1.0f,1.0f,1.0f));
		fonts[1].drawString(ctrX, ctrY+posTxt[6][1], "Previous Weapon     	  A", new Color(1.0f,1.0f,1.0f));
		fonts[1].drawString(ctrX, ctrY+posTxt[7][1], "SpeedUp                    Ctr", new Color(1.0f,1.0f,1.0f));
		fonts[1].drawString(ctrX, ctrY+posTxt[8][1], "Pause                              P", new Color(1.0f,1.0f,1.0f));
		
		
		
		fonts[1].drawString(50, height-50, "Press Any Key to quit", new Color(1.0f,1.0f,1.0f));
		
	}
	
	public XMLPackedSheet getShip() {
		return ship;
	}

	public void setShip(XMLPackedSheet ship) {
		this.ship = ship;
	}


	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public Animation getShip1MoveDown() {
		return ship1MoveDown;
	}

	public void setShip1MoveDown(Animation ship1MoveDown) {
		this.ship1MoveDown = ship1MoveDown;
	}

	public Animation getShip1MoveUp() {
		return ship1MoveUp;
	}

	public void setShip1MoveUp(Animation ship1MoveUp) {
		this.ship1MoveUp = ship1MoveUp;
	}

	

	
	public int getTimer() {
		return timer;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void render1Boss(Graphics gr, Objet obBo, int id) {
		// TODO Auto-generated method stub
		boss[0].draw(obBo.getX(),obBo.getY());
	}


}


