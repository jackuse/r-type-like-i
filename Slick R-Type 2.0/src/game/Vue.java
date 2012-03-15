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

import states.Game;


// Contient toutes les valeurs partag�es des etats

/* Problem avec le hight score top10 alors que non
 * implementer la play list choice
 * implementer keyconfig ou pas
 * 
 */

/**
 * Class Vue
 */
public class Vue {

	private static Vue _instance = new Vue();

	//
	// Fields
	//


	private Image[] background = new Image[5];
	private Image joueur;
	private Image[] alien = new Image[2];;
	private Image laser;
	private Image missile;

	private Image nastyProjectile;
	private Image explosion;
	private Image sheet;
	private XMLPackedSheet sheetTest;
	private XMLPackedSheet nastyProjectileSheet;
	private String dossierSkin = "data/skin1";
	private SpriteSheet skin;
	private int[][] skinDef;

	private Image exitOption = null;
	private Image startGameOption = null;
	private Image optionOption = null;
	private Image highscoreOption = null;
	private Image ship1 = null;
	private Image ship2 = null;
	private Image ship3 = null;
	private Image life;
	private float startGameScale = 0.7f;
	private float exitScale = 0.7f;
	private float optionScale = 0.7f;


	private ResourceManager rm = ResourceManager.getInstance();
	public DeferredResource nextResource; 
	public String shipname = "VAISSEAU_1";

	float alpha = 0;

	private UnicodeFont font; 

	public Music principale = null;
	private int idMusic = 0;;
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
	boolean debugCol = true;










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

	public void initCharacterSelectScreen(){
		principale = rm.getMusic("MUSIC_7");
		principale.setVolume(volumeMusic);
		Image menu2 = rm.getImage("BOUTONS_CHARSELECT");
		//System.out.println(menu.getHeight());
		background[4] = rm.getImage("BACKGROUD_CHARSELECT");
		ship1 = menu2.getSubImage(0, 28, 445, 70);
		ship2 = menu2.getSubImage(180, 105, 300, 70);
		ship3 = menu2.getSubImage(220, 184, 220, 70);

		selectMusic(7);
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
	}

	public void initGame() throws SlickException{
		sheetTest= new XMLPackedSheet ("data/pack1.png","data/pack1.png.xml");
		nastyProjectileSheet=new XMLPackedSheet("data/nastyProjectileSheet.png","data/nastyProjectileSheet.png.xml");
		principale = rm.getMusic("MUSIC_0");
		principale.setVolume(volumeMusic);
		background[0] = rm.getImage("BACKGROUD_JEU");	
		explosion = rm.getImage("EXPLOSION");
		missile = rm.getImage("ROCKET");
		missile.setRotation(90.0f);
		laser = rm.getImage("LASER");
		laser.setRotation(90.0f);
		nastyProjectile = nastyProjectileSheet.getSprite("nastyProjectile1.gif");
		//nastyProjectile.setRotation(270.0f);

		//joueur = rm.getImage("JOUEUR");
		sheet = rm.getImage("VAISSEAU_1");
		//joueur = sheet.getSubImage(265,3746,123,196);
		//joueur = sheet.getSubImage(97,45,30,30);
		joueur=sheetTest.getSprite("VAISSEAU_1.gif");
		joueur.setRotation(90.0f);
		//laser = sheet.getSubImage(265,3746,123,196);
		//laser.setRotation(90.0f);


		alien[0] = rm.getImage("ALIEN_1");
		alien[0].setRotation(270.0f);	
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

	public void renderOption(GameContainer gc, Graphics gr,int optionX, int optionY) {
		//System.out.println("rendu d'option");

		//startGameOption.draw(optionX, optionY, startGameScale);
		background[2].draw(0, 0);

		font.drawString(optionX, optionY, "Music",new Color(1.0f,1.0f,1.0f));
		if(validerMusic)
			validerOk.draw(optionX+width*0.20f, optionY,0.7f);
		else
			valider.draw(optionX+width*0.20f, optionY,0.7f);

		font.drawString(optionX, optionY+height*0.10f, "Music Playlist",new Color(1.0f,1.0f,1.0f));

		font.drawString(optionX, optionY+height*0.20f, "Fullscreen",new Color(0.5f,0.5f,0.5f));
		if(validerFullscreen)
			validerOk.draw(optionX+width*0.20f, optionY+height*0.20f,0.7f);
		else
			valider.draw(optionX+width*0.20f, optionY+height*0.20f,0.7f);
		//System.out.println("rendu d'option pro ");
		gr.setColor(new Color(0.5f,0.5f,0.5f));
		gr.drawString( "Only Pro version",optionX+width*0.28f, optionY+height*0.225f);
		//optionOption.draw(optionX, optionY+45, optionScale);
		exitOption.draw(optionX, optionY+height*0.30f, exitScale);

	}

	public void renderSelection(Graphics g) {
		// TODO Auto-generated method stub
	}

	public int renderTest(Graphics g) {
		//skin.draw();
		return 0;
	}

	public int renderMenu(Graphics gr,int menuX, int menuY,int titreX,int titreY, int hSX,HighscoreManager hm) {
		Rectangle fond = new Rectangle (0, 0, 800, 600);
		gr.setColor(new Color (0.2f, 0.2f, 0.2f));
		gr.fill(fond);
		background[1].draw(0, 0);
		font.drawString(titreX, titreY, "R-Type Mania Nightcore Edition",new Color(1.0f,1.0f,1.0f));
		//font.drawString(titreX+430, titreY+30, "Beta",new Color(1.0f,1.0f,1.0f));
		gr.setColor(new Color (0,0,0));
		gr.drawString("Beta 3.0", titreX+430, titreY+50);
		//System.out.println("titreY "+titreY);
		//HighScore
		String s[] = hm.getHighscoreStringTab();
		gr.setColor(new Color(1.0f,1.0f,1.0f));
		gr.drawString("HighScores", hSX+50, 200);
		for(int i=0;i<s.length;i++){	// Il faut faire apparaitre les scores 1 par 1
			gr.drawString(s[i].substring(4), hSX, 230+25*i);
		}
		//gr.drawString(hm.getHighscoreString(), hSX, 300);
		//System.out.println(hm.getHighscoreString());

		 
		startGameOption.draw(menuX, menuY, startGameScale);
		optionOption.draw(menuX+350, menuY, optionScale);
		exitOption.draw(menuX+600, menuY, exitScale);
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
	public int renderVaisseau( String sh  )
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

	public int renderJoueur(Graphics gr, VaisseauJoueur v,int clig) {

		joueur=sheetTest.getSprite(shipname); // Whats the fuck !!!!!!!!
		joueur.setRotation(90.0f);




		if(v.isBorn()){
			if(clig == 1){
				nbCligne++;
				joueur.draw(v.getX()-v.getDecalageX(), v.getY()-v.getDecalageY());
				//System.out.println("je clignote "+nbCligne);
			}else{

			}
			if(nbCligne >50){
				v.setBorn(false);
				nbCligne = 0;
			}

		}else{

			joueur.draw(v.getX()-v.getDecalageX(), v.getY()-v.getDecalageY());
		}
		if(debugCol){
			Rectangle rect = new Rectangle (v.getX(), v.getY(), v.getW(), v.getH());
			gr.setColor(new Color (0.2f, 0.2f, 0.2f));
			gr.fill(rect);
		}


		//joueur.draw(v.getX(), v.getY(), v.getW(), v.getH());


		return 0;
	}

	public int renderHUD(Graphics gr, int[] param) {

		//Tableaux de bord 1
		/*
		Rectangle board1 = new Rectangle (0, height*0.955f, (width+1), 30);
		gr.setColor(new Color (0.2f, 0.2f, 0.2f));
		gr.fill(board1);
		gr.setColor(org.newdawn.slick.Color.white);
		if(debug ){
			gr.drawString("Explosion :"+param[1], 10,height*0.96f);
			gr.drawString("Alien :"+param[2], 150,height*0.96f);
			gr.drawString("Missile :"+param[3], width*0.31f,height*0.96f);
		}
		gr.drawString("Score:"+param[4], width*0.85f,height*0.96f);
		gr.drawString("Life:"+param[7], width*0.15f,height*0.96f);
		gr.drawString("Level :"+param[8]+" Objectif : "+param[9]+"/"+param[10], width*0.15f,height*0.96f);

		gr.drawString("Weapon:", 10,height*0.96f);
		if(param[6] == 21){
			laser.draw(width*0.11f,height*0.95f, 1f);
		}else if(param[6] == 22){
			missile.draw(width*0.09f,height*0.945f, 0.7f);
		}

		//Lifebar
		Rectangle lifeBack = new Rectangle (width*0.50f, height*0.96f, width*0.20f, height*0.04f);
		gr.setColor(new Color (0,0,0));
		gr.fill(lifeBack);

		if(param[5]<=10){
			clignotementVie = true;
		}
		else
			clignotementVie = false;


		if(clignotementVie && param[0] == 1){}	
		else{
			Rectangle lifebar = new Rectangle (width*0.50f, height*0.96f, (width*0.20f)*(param[5]/100f), height*0.04f);

			if(param[5]>30){
				gr.setColor(new Color (0,1.0f,0));
			}
			else 
				gr.setColor(new Color (1f,0,0));


			gr.fill(lifebar);

		}
		gr.setColor(new Color (1f,0,1f));
		gr.drawString(""+param[5], width*0.58f,height*0.965f);

		 */

		// HUD
		if(debug ){
			gr.drawString("Explosion :"+param[1], 10,height*0.96f);
			gr.drawString("Alien :"+param[2], 150,height*0.96f);
			gr.drawString("Missile :"+param[3], width*0.31f,height*0.96f);
		}

		//back
		gr.setColor(org.newdawn.slick.Color.black);
		gr.drawString("P1 - "+param[4], width*0.0125f+1,height*0.04f+1);
		gr.drawString(param[7]+" x ", width*0.0125f+1,height*0.08f+1);
		life.draw(width*0.050f+1,height*0.08f+1);
		gr.drawString("Level 1-"+param[8], width*0.875f+1,height*0.0125f+1);
		gr.drawString("Timer "+param[11]/1000, width*0.89f+1,height*0.04f+1);

		gr.drawString("Weapon:", width*0.0125f+1,height*0.92f+1);
		if(param[6] == 21){
			laser.draw(width*0.11f,height*0.91f, 1f);
		}else if(param[6] == 22){
			missile.draw(width*0.09f,height*0.905f, 0.7f);
		}

		//white
		gr.setColor(org.newdawn.slick.Color.white);
		//				gr.drawString("P1 - "+param[4], width*0.0125f,height*0.0125f);
		gr.drawString("P1 - "+param[4], width*0.0125f,height*0.04f);
		gr.drawString(param[7]+" x ", width*0.0125f,height*0.08f);
		gr.drawString("Level 1-"+param[8], width*0.875f,height*0.0125f);
		gr.drawString("Timer "+param[11]/1000, width*0.89f,height*0.04f);			
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

		alien[type].draw(ob.getX(),ob.getY());
		return 0;
	}

	public int render1Tir(Graphics gr, Objet ob, int type) {
		if(((Tir)ob).estVisible()){
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
				//missile.draw(ob.getX()+joueur.getWidth()/2,ob.getY()-joueur.getHeight()/2);
				missile.draw(ob.getX(), ob.getY()-ob.getH()/2);
				break;
			case 23:
				nastyProjectile.draw(ob.getX(), ob.getY());
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

	public void renderPause(GameContainer gc, Graphics gr,int pauseX, int pauseY, boolean cheatModOn, ArrayList<Chara> pass) {
		//		if (gc.isPaused())
		//		{
		Rectangle rect = new Rectangle (0, 0, 801, 601);
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

		if(cheatModOn){
			Rectangle textArea= new Rectangle (0, 0, 801, 30);
			gr.setColor(new Color (0, 0, 0));
			gr.fill(textArea);
			String s = "";
			for(int i=0;i<pass.size();i++){
				s+=pass.get(i).c;
				//System.out.println("je passe "+s);
			}
			gr.setColor(new Color (1.0f, 1.0f, 1.0f));
			gr.drawString(s,10f,5f);

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
		Rectangle rect = new Rectangle (0, 0, 801, 600);
		gr.setColor(new Color (0.2f, 0.2f, 0.2f, alpha));
		gr.fill(rect);

		if (alpha < 0.8f)
			alpha += 0.05f;

		gr.fill(rect);

		gr.setColor(new Color (1.0f,1.0f,1.0f));

		if(param[2] == 1)
			gr.drawString("Best score !", width*0.45f, 220); 
		if(param[1] == 0)
			gr.drawString("You are not in highscore ", width*0.42f, 240); 
		else
			gr.drawString("You are TOP10 ", width*0.45f, 240);

		gr.drawString("Score :  "+param[0], width*0.45f, 260); 

		if(param[1] ==1){
			gr.drawString("Type your name here ", width*0.42f, 280);
			gr.setColor(new Color (0,0,0));
			gr.fill(new Rectangle (width*0.415f, 300, 180,25 ));
			gr.setColor(new Color (1.0f,1.0f,1.0f));
			gr.drawString(name, width*0.42f, 300);
		}
		else{
			if(ok)
				gr.drawString("Press space", width*0.45f, 340); 
		}
	}


	public void renderSelection(GameContainer gc, Graphics gr,int selectX, int selectY) {
		for(int i=1;i<rm.getNumber("NB_MUSIC");i++){
			//			Rectangle rect = new Rectangle (selectX+10, selectY+40*(i-1)+50, getValider().getWidth()*0.45f, getValider().getHeight()*0.45f);
			//			gr.setColor(new Color (1.0f, 0.2f, 0.2f));
			//			gr.fill(rect);
			font.drawString(selectX+10, selectY, "Select your music : ",new Color(1.0f,1.0f,1.0f));
			font.drawString(selectX+width*0.08f, selectY+height*0.066f*i, rm.getPlaylist("MUSIC_"+i),new Color(1.0f,1.0f,1.0f));
			if(selectMusic[i])
				validerOk.draw(selectX, selectY+height*0.066f*i,0.7f);
			else
				valider.draw(selectX, selectY+height*0.066f*i,0.7f);

			exitOption.draw(selectX+10, selectY+height*0.80f, exitScale);



			//selectX && mouseX <= selectX + vue.getValider().getWidth()) &&
			//( mouseY >= selectY+vue.getValider().getHeight()*i && mouseY <= selectY  + vue.getValider().getHeight()*i*0.05f) ){
		}

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
			principale = rm.getMusic("MUSIC_"+idMusic);
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
			//rm.loadResources(new FileInputStream("data/jeu.xml"),true);
			rm.loadResources(getClass().getResourceAsStream("/data/jeu.xml"),true); // Methode compatible avec les jars


		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Image getIJoueur() {
		return joueur;
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


}
