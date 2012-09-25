package states;

import javax.jws.Oneway;

import game.HighscoreManager;
import game.IOManager;
import game.Main;
import game.View;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.newdawn.slick.state.transition.Transition;


/**
 * Class Menu
 * @author Etienne Grandier-Vazeille
 *
 */
public class Menu extends BasicGameState{
	int stateID = -1;
	private View view = View.getInstance();
	//private IOManager io = IOManager.getInstance();

	int menuX = 80;
	int menuY = 460;
	float scaleStep = 0.0002f;
	boolean firstLauch = true;
	int delay = 20;
	private int titreX = 0;
	private int titreY = -300;
	private Transition t[];
	private Transition to[];
	private HighscoreManager hm;
	private int hSX = 800;
	private boolean credit = false;
	private int pos[]; 
	private int delayK = 100;
	private boolean[] inside;
	private int kB = 0;
	private int maxItemMenu = 4;
	private int posTxt[][];
	int val = 0;
	boolean security = true;
	



	public Menu(int stateID) {
		this.stateID = stateID;
	}

	public void enter(GameContainer gc, StateBasedGame sgb) throws SlickException{
		
		// On charge les transistions
		try {
			t[0] = FadeOutTransition.class.newInstance();
			t[1] = FadeInTransition.class.newInstance();
			to[0] = null;
			to[1] = SelectTransition.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		// On réintialise les entrées
		gc.getInput().clearKeyPressedRecord();
		gc.getInput().clearMousePressedRecord();

		hm = HighscoreManager.getInstance();

		resetMenu();

		// On lance la musique si il le faut
		if(view.isSelectMusic(0)){
			view.selectMusic(0);
			view.setMusic(0);
			if(view.isValiderMusic()){
				view.setMusic(1);
			}
		}
		view.selectMusic(0);
		
		// On réinitialise la selection au clavier
		for(int i=0;i<maxItemMenu;i++)
			inside[i]=false;

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		view.initMenu();
		titreX = (int)(view.getWidth()*0.20);
		t = new Transition[2];
		to = new Transition[2];
		pos = new int[10];
		
		inside = new boolean[maxItemMenu];
		for(int i=0;i<maxItemMenu;i++)
			inside[i]=false;
		
		
		// Initialisation des position du texte pour une syncronisation entre la vue et le controleur
		posTxt = new int[4][2];
		posTxt[0][0] = 180;
		posTxt[0][1] = 0;
		posTxt[1][0] = 0;
		posTxt[1][1] = 70;
		posTxt[2][0] = 210;
		posTxt[2][1] = 73;
		posTxt[3][0] = 500;
		posTxt[3][1] = 70;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {

		if(!credit){ // les crédit son mi par dessus le menu
			view.renderMenu(gr, menuX, menuY, titreX, titreY,hSX,hm.getHighscoreStringTab(),pos,posTxt);
		}
		else
		{
			gr.drawString("version 4.2", 50, 50);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("etat "+sbg.getCurrentStateID()+" l'autre c'est "+Main.previousState);
		//System.out.println("Music on: "+view.isMusic()+" firstLauch: "+Main.etatprecedent);
		//		if(Main.etatprecedent == -1){
		//			view.selectMusic(0);
		//			view.setMusic(1);
		//			//Main.etatprecedent = sbg.getCurrentStateID();
		//		}


		if(Main.previousState != sbg.getCurrentStateID()){

			if(titreY<100){
				titreY+=5;
			}
			else{
				if(hSX==800 && Main.previousState != Main.OPTIONSTATE && Main.previousState != Main.CONTROLSSTATE){
					view.selectMusic(0);
					security = false;
					//view.setMusic(0);
					if(view.isValiderMusic()){
						view.setMusic(1);
					}
				}
				if(hSX>320){
					hSX-=40;
				}

				if(pos[9]>300){
					for (int p=0;p<10;p++) {
						pos[p]-=40;
						if(pos[p] <=300)
							pos[p]=300;
					}

				}else{
					Main.previousState = sbg.getCurrentStateID();
				}



			}

		}

		/*
		if(!view.isMusic()&& (Main.etatprecedent == sbg.getCurrentStateID())){
			view.selectMusic(0);
			if(view.isValiderMusic()){
				view.setMusic(1);
			}

		}*/
		//if(!view.isMusic())
		//view.setMusic(1);


		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStartGame = false;
		boolean insideExit = false;
		boolean insideOption = false;
		boolean insideCredit = false;
		boolean insideControls = false;
		boolean enterPress = false;

		if(!security){
		if( ( mouseX >= menuX+posTxt[0][0] && mouseX <= menuX+posTxt[0][0] + view.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= menuY+posTxt[0][1] && mouseY <= menuY+posTxt[0][1] + view.getStartGameOption().getHeight()*0.7) ){
			insideStartGame = true;
		}else if( ( mouseX >= menuX+posTxt[3][0] && mouseX <= menuX+posTxt[3][0] + view.getExitOption().getWidth()*0.7) &&
				( mouseY >= menuY+posTxt[3][1] && mouseY <= menuY+posTxt[3][1] + view.getExitOption().getHeight()*0.7) ){
			insideExit = true;
		}
		else if( ( mouseX >= menuX+posTxt[1][0] && mouseX <= menuX+posTxt[1][0] + view.getOptionOption().getWidth()*0.7) &&
				( mouseY >= menuY+posTxt[1][1] && mouseY <= menuY+posTxt[1][1] + view.getOptionOption().getHeight()*0.7) ){
			insideOption = true;
		}
		else if( ( mouseX >= menuX+posTxt[2][0] && mouseX <= menuX+posTxt[2][0] + view.getOptionOption().getWidth()*0.7) &&
				( mouseY >= menuY+posTxt[2][1] && mouseY <= menuY+posTxt[2][1] + view.getOptionOption().getHeight()*0.7) ){
			insideControls = true;
		}
		else if( ( mouseX >= view.getWidth()*0.84f + view.getWidth()) &&
				( mouseY <=  40) ){
			insideCredit = true;
			System.out.println("truc");
		}
		
		
		// Déplacement au clavier
		if (input.isKeyPressed(Input.KEY_Q)){
			inside[kB] = false;
			kB--;
			if(kB<0)
				kB=maxItemMenu-1;
			inside[kB] = true;
		}

		if (input.isKeyPressed(Input.KEY_D)){
			inside[kB] = false;
			kB++;
			if(kB>maxItemMenu-1)
				kB=0;
			inside[kB] = true;
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER)){
			enterPress = true;
		}
		}
		
		// On réinitilalise le déplacement clavier si on utilise la souris
		if(insideExit || insideCredit || insideControls || insideOption || insideStartGame){
			for(int i=0;i<maxItemMenu;i++)
				inside[i]=false;
		}

		if(!credit){

			if(insideStartGame || inside[0]){
				if(view.getStartGameScale() < 0.8f)
					view.setStartGameScale(view.getStartGameScale()+scaleStep * delay);

				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress ){
					view.setStartGameScale(0.7f);
					Main.previousState = Main.MENUSTATE;
					//sbg.enterState(Main.SELECTSTATE,t[0],t[1]);
					sbg.enterState(Main.SELECTSTATE);
				}
			}else{
				if(view.getStartGameScale() > 0.7f)
					view.setStartGameScale(view.getStartGameScale()-scaleStep * delay);
			}

			if(insideOption || inside[1]){
				if(view.getOptionScale() < 0.8f)
					view.setOptionScale(view.getOptionScale()+scaleStep * delay);

				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
					view.setOptionScale(0.7f);
					Main.previousState = Main.MENUSTATE;
					//sbg.enterState(Main.OPTIONSTATE,to[0],to[1]); // Bug de transition si on quit dans le jeu
					sbg.enterState(Main.OPTIONSTATE);
				}
			}else{
				if(view.getOptionScale() > 0.7f)
					view.setOptionScale(view.getOptionScale()-scaleStep * delay);
			}
			
			if(insideControls || inside[2]){
				if(view.getControlsScale() < 0.8f)
					view.setControlsScale(view.getControlsScale()+scaleStep * delay);

				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
					view.setControlsScale(0.7f);
					Main.previousState = Main.MENUSTATE;
					sbg.enterState(Main.CONTROLSSTATE);

				}
			}else{
				if(view.getControlsScale() > 0.7f)
					view.setControlsScale(view.getControlsScale()-scaleStep * delay);
			}

			if(insideExit || inside[3])
			{
				if(view.getExitScale() < 0.8f)
					view.setExitScale(view.getExitScale() + scaleStep * delay);
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress)
					gc.exit();
			}else{
				if(view.getExitScale() > 0.7f)
					view.setExitScale(view.getExitScale() - scaleStep * delay);
			}
		}

		if(insideCredit){
			credit = true;
		}


		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			Main.app.exit();
			credit = false;
		}


		if (input.isKeyPressed(Input.KEY_R)){
			hm.rest();
		}

		if (input.isKeyPressed(Input.KEY_T)){
			hm.restZ();
		}
		
		

		if ( input.isKeyPressed(Input.KEY_M) ){
			if(!view.isMusic()){
				view.setValiderSetMusic(true);
				view.setMusic(3);
			}
			else{
				view.setValiderSetMusic(false);
				view.setMusic(2);
			}
		}
	}

			public void resetMenu(){
				titreY = -80;
				hSX = 800;
				for (int i=0;i<10;i++){
					pos[i] = view.getWidth()+i*200;
				}
			}

			@Override
			public int getID() {
				return stateID;
			}
		}
