package states;

import game.Chara;

import java.util.ArrayList;

import game.IOManager;
import game.Joueur;
import game.Main;
import game.Objet;
import game.Vue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Class Pause
 * @author Etienne Grandier-Vazeille
 *
 */
public class Pause extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();
	private IOManager io = IOManager.getInstance();

	int pauseX = 250;
	int pauseY = 230;
	private boolean cheatModOn = false; 
	String cheatCodeString[];
	//private ArrayList<Chara> pass;
	private String pass = "";
	int nbPassword = 3;
	private int delayClick = 150;
	float scaleStep = 0.0002f;
	private int posTxt[][];

	/* Ajouter un curseur pour de déplacer au clavier
	 * 
	 * 
	 * 
	 * 
	 */


	public Pause(int stateID) {
		this.stateID = stateID;
	}
	
	public void enter(GameContainer gc, StateBasedGame sgb) {
		gc.getInput().clearKeyPressedRecord();
		cheatModOn = false;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		vue.initPause();
		cheatCodeString = new String[nbPassword];
		cheatCodeString[0]= "dnkrow";
		cheatCodeString[1]= "allyourbasearebelongtous";
		cheatCodeString[2]= "dnsduff";
		
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
		
		vue.renderPause(gc,gr,pauseX,pauseY,cheatModOn,pass);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("pause delta"+ delta);
		if(Main.previousState != Main.PAUSESTATE)
			Main.previousState = Main.PAUSESTATE;


		//System.out.println("etat "+sbg.getCurrentStateID()+" et "+Main.etatprecedent);

		Input input = gc.getInput(); // On récupére les input

		if (input.isKeyPressed(Input.KEY_P) || input.isKeyPressed(Input.KEY_ESCAPE)){
			gc.setPaused(!gc.isPaused());
			sbg.enterState(Main.GAMESTATE);
		}

		if(vue.isValiderMusic()){
			if(!vue.isMusic()){
				vue.setMusic(0);
				vue.nextMusic();
				vue.setMusic(4);		
			}
		}

		///////////////////////////////////////// CHEAT CODE  //////////////////////////////////////////////////////////////
		if (input.isKeyPressed(42)){
			if(!cheatModOn){
				cheatModOn = true;
				pass = "";
			}
			else
				cheatModOn = false;

		}

		if(cheatModOn){
			pass = io.getKeyString(input, pass,42);

			if (input.isKeyPressed(input.KEY_ENTER)){
				for(int i=0;i<nbPassword;i++){
					if(pass.contentEquals(cheatCodeString[i]) ){
						if(Game.cheat[i]){
							Game.cheat[i] = false;
							
							switch (i) {
							case 0:
								vue.setMessage("GodMod : OFF",1000);
								vue.nextMusic();
								break;
							case 1:
								
								break;
							case 2:
								
								break;

							default:
								break;
							}
						}
						else{
							Game.cheat[i] = true;
							
							switch (i) {
							case 0:
								vue.setMessage("GodMod : ON",1000);
								vue.nextMusic();
								break;
							case 1:
								
								break;
							case 2:
								
								break;

							default:
								break;
							}
						}	
						
						pass = "";	
					}
				}
			}
		}

		///////////////////////////////////////// END CHEAT CODE  //////////////////////////////////////////////////////////////

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStartGame = false;	
		boolean insideOption = false;
		boolean insideExit = false;
		boolean insideControls = false;


		if( ( mouseX >= pauseX && mouseX <= pauseX + vue.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= pauseY && mouseY <= pauseY + vue.getStartGameOption().getHeight()*0.7) ){
			insideStartGame = true;
		}else if( ( mouseX >= pauseX && mouseX <= pauseX + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= pauseY+45 && mouseY <= pauseY+45 + vue.getOptionOption().getHeight()*0.7) ){
			insideOption = true;
		}else if( ( mouseX >= pauseX&& mouseX <= pauseX + vue.getControlsOption().getWidth()*0.7) &&
				( mouseY >= pauseY+90 && mouseY <= pauseY+90 + vue.getControlsOption().getHeight()*0.7) ){
			insideControls = true;
		}else if( ( mouseX >= pauseX&& mouseX <= pauseX + vue.getExitOption().getWidth()*0.7) &&
				( mouseY >= pauseY+130 && mouseY <= pauseY+130 + vue.getExitOption().getHeight()*0.7) ){
			insideExit = true;
		}

		if(insideStartGame){
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(Main.GAMESTATE);
				gc.setPaused(!gc.isPaused());
				//vue.setMusic(3);
			}
		}

		if(insideOption){
			delayClick-= 20;
			if (delayClick<0){
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
					//vue.setMusic(0);
					vue.setOptionScale(0.7f);

					sbg.enterState(Main.OPTIONSTATE);
					//vue.setMusic(3);
				}
				delayClick = 150;
			}
		}
		
		if(insideControls){
			if(vue.getControlsScale() < 0.8f)
				vue.setControlsScale(vue.getStartGameScale()+scaleStep * 20);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				vue.setControlsScale(0.7f);
				sbg.enterState(Main.CONTROLSSTATE);
			}
		}else{
			if(vue.getControlsScale() > 0.7f)
				vue.setControlsScale(vue.getControlsScale()-scaleStep * 20);
		}

		if(insideExit)
		{

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//restCheat(); //  BUG !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! mettre cheat dans joueur ?
				vue.setMusic(0);
				vue.setExitScale(0.7f);
				vue.selectMusic(0);
				//Joueur.getInstance(1).rest();
				sbg.enterState(Main.MENUSTATE);
			}
		}


	}
	
	public void restCheat(){
		for(int i=0;i<4;i++){
			Game.cheat[i]=false;
		}
	}

	@Override
	public int getID() {
		return stateID;
	}


	

}
