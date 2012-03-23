package states;

import java.util.ArrayList;

import game.IOManager;
import game.Player;
import game.Main;
import game.Objet;
import game.View;

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
	private View view = View.getInstance();
	private IOManager io = IOManager.getInstance();

	int pauseX = 250;
	int pauseY = 230;
	private boolean cheatModOn = false; 
	String cheatCodeString[];
	private String pass = "";
	int nbPassword = 3;
	private int delayClick = 150;
	float scaleStep = 0.0002f;
	private int posTxt[][];
	private int maxItemMenu = 4;
	int delay = 20;
	private boolean[] inside;
	private int kB=0;



	public Pause(int stateID) {
		this.stateID = stateID;
	}

	public void enter(GameContainer gc, StateBasedGame sgb) {
		gc.getInput().clearKeyPressedRecord();
		cheatModOn = false;

		// On réinitialise la selection au clavier
		for(int i=0;i<maxItemMenu;i++)
			inside[i]=false;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		view.initPause();
		cheatCodeString = new String[nbPassword];
		cheatCodeString[0]= "dnkrow";
		cheatCodeString[1]= "allyourbasearebelongtous";
		cheatCodeString[2]= "dnsduff";

		inside = new boolean[maxItemMenu];
		for(int i=0;i<maxItemMenu;i++)
			inside[i]=false;

		posTxt = new int[4][2];// position du text en x et y
		posTxt[0][0] = 0;
		posTxt[0][1] = 0;
		posTxt[1][0] = 0;
		posTxt[1][1] = 45;
		posTxt[2][0] = 0;
		posTxt[2][1] = 90;
		posTxt[3][0] = 0;
		posTxt[3][1] = 130;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {

		view.renderPause(gc,gr,pauseX,pauseY,cheatModOn,pass,posTxt);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		if(Main.previousState != Main.PAUSESTATE)
			Main.previousState = Main.PAUSESTATE;


		//System.out.println("etat "+sbg.getCurrentStateID()+" et "+Main.etatprecedent);

		Input input = gc.getInput(); // On récupére les input

		if (input.isKeyPressed(Input.KEY_P) || input.isKeyPressed(Input.KEY_SPACE)){
			gc.setPaused(!gc.isPaused());
			sbg.enterState(Main.GAMESTATE);
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(Main.MENUSTATE); // ou
			//sbg.enterState(Main.GAMEOVERSTATE);
		}

		if(view.isValiderMusic()){ // Si il n'y a pas de musique on la lance
			if(!view.isMusic()){
				view.setMusic(0);
				view.nextMusic();
				view.setMusic(4);		
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
								view.setMessage("GodMod : OFF",1000);
								view.nextMusic();
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
								view.setMessage("GodMod : ON",1000);
								view.nextMusic();
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
		boolean enterPress = false;


		if( ( mouseX >= pauseX+posTxt[0][0] && mouseX <= pauseX+posTxt[0][0] + view.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= pauseY+posTxt[0][1] && mouseY <= pauseY+posTxt[0][1] + view.getStartGameOption().getHeight()*0.7) ){
			insideStartGame = true;
		}else if( ( mouseX >= pauseX+posTxt[1][0] && mouseX <= pauseX+posTxt[1][0] + view.getOptionOption().getWidth()*0.7) &&
				( mouseY >= pauseY+posTxt[1][1] && mouseY <= pauseY+posTxt[1][1] + view.getOptionOption().getHeight()*0.7) ){
			insideOption = true;
		}else if( ( mouseX >= pauseX+posTxt[2][0] && mouseX <= pauseX+posTxt[2][0] + view.getControlsOption().getWidth()*0.7) &&
				( mouseY >= pauseY+posTxt[2][1] && mouseY <= pauseY+posTxt[2][1] + view.getControlsOption().getHeight()*0.7) ){
			insideControls = true;
		}else if( ( mouseX >= pauseX+posTxt[3][0] && mouseX <= pauseX+posTxt[3][0] + view.getExitOption().getWidth()*0.7) &&
				( mouseY >= pauseY+posTxt[3][1] && mouseY <= pauseY+posTxt[3][1] + view.getExitOption().getHeight()*0.7) ){
			insideExit = true;
		}

		// Déplacement au clavier
		if (input.isKeyPressed(Input.KEY_Z)){
			inside[kB] = false;
			kB--;
			if(kB<0)
				kB=maxItemMenu-1;
			inside[kB] = true;
		}

		if (input.isKeyPressed(Input.KEY_S)){
			inside[kB] = false;
			kB++;
			if(kB>maxItemMenu-1)
				kB=0;
			inside[kB] = true;
		}

		if (input.isKeyPressed(Input.KEY_ENTER)){
			enterPress = true;
		}

		// On réinitilalise le déplacement clavier si on utilise la souris
		if(insideExit || insideOption || insideStartGame || insideControls){
			for(int i=0;i<maxItemMenu;i++)
				inside[i]=false;
		}


		if(insideStartGame || inside[0]){ // Retour au jeu
			if(view.getStartGameScale() < 0.8f)
				view.setStartGameScale(view.getStartGameScale()+scaleStep * delay);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress ){
				view.setStartGameScale(0.7f);
				sbg.enterState(Main.GAMESTATE);
				gc.setPaused(!gc.isPaused());
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

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
				view.setMusic(0);
				view.setExitScale(0.7f);
				view.selectMusic(0);
				sbg.enterState(Main.MENUSTATE);
			}
		}else{
			if(view.getExitScale() > 0.7f)
				view.setExitScale(view.getExitScale() - scaleStep * delay);
		}


	}


	@Override
	public int getID() {
		return stateID;
	}




}
