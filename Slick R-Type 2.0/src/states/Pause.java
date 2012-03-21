package states;

import game.Chara;

import java.util.ArrayList;

import game.IOManager;
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
				//pass.clear();
				pass = "";
				//System.out.println("mot de passe a l'écoute");
			}
			else
				cheatModOn = false;

		}

		if(cheatModOn){
//			if (input.isKeyPressed(input.KEY_BACK)){
//				if(pass.length()>0)
////					pass.remove(pass.length()-1);	
//					pass
//			}
//			char c = io.getChar(input);
//			if(c != '\0' && pass.length() < 42){
////				Chara ch = new Chara(c);
////				pass.add(ch);
//				pass+=c;
//			}
			pass = io.getKeyString(input, pass,42);

			if (input.isKeyPressed(input.KEY_ENTER)){

				

				cheatCodeString[0]= "dnkrow";
				cheatCodeString[1]= "allyourbasearebelongtous";
				cheatCodeString[2]= "dnsduff";
				
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
				

				
//				char[][] charArr= new char[nbPassword][42];
//				for(int i=0;i<nbPassword;i++){
//					char[] ca = cheatCodeString[i].toCharArray();
//					for(int j=0;j<cheatCodeString[i].length();j++)
//						charArr[i][j] = ca[j];
//				}	
//				int mdp[] = new int[nbPassword];
//				for(int i=0;i<nbPassword;i++){
//					mdp[i] = 0;
//				}
//				for(int i=0;i<pass.length();i++){
//					if(pass.charAt(i) == charArr[0][i])
//						mdp[0]+=1;
//				}
//				for(int i=0;i<nbPassword;i++){
//					if(mdp[i] == cheatCodeString[i].length()){
//						if(!Game.cheat[i])
//							Game.cheat[i] = true;
//						else
//							Game.cheat[i] = false;
//						//System.out.println("Password accepted");
//						if(i==0){
//							if(Game.cheat[i]){
//								vue.setMessage("GodMod : ON",1000);
//								vue.nextMusic();
//							}
//							else{
//								vue.setMessage("GodMod : OFF",1000);
//								vue.nextMusic();
//							}
//						}
//						pass = "";
//					}
//				}

				System.out.println("valider");

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
			//System.out.println("on StartGame");
		}else if( ( mouseX >= pauseX && mouseX <= pauseX + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= pauseY+45 && mouseY <= pauseY+45 + vue.getOptionOption().getHeight()*0.7) ){
			insideOption = true;
			//System.out.println("on option");
		}else if( ( mouseX >= pauseX&& mouseX <= pauseX + vue.getControlsOption().getWidth()*0.7) &&
				( mouseY >= pauseY+90 && mouseY <= pauseY+90 + vue.getControlsOption().getHeight()*0.7) ){
			insideControls = true;
			//System.out.println("on Exit");
		}else if( ( mouseX >= pauseX&& mouseX <= pauseX + vue.getExitOption().getWidth()*0.7) &&
				( mouseY >= pauseY+130 && mouseY <= pauseY+130 + vue.getExitOption().getHeight()*0.7) ){
			insideExit = true;
			//System.out.println("on Exit");
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
//			delayClick-= 20;
//			if (delayClick<0){
//				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
//					//vue.setMusic(0);
//					vue.setControlsScale(0.7f);
//
//					sbg.enterState(Main.CONTROLSSTATE);
//					//vue.setMusic(3);
//				}
//				delayClick = 150;
//			}
//		}

		if(insideExit)
		{

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//restCheat(); //  BUG !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! mettre cheat dans joueur ?
				vue.setMusic(0);
				vue.setExitScale(0.7f);
				vue.selectMusic(0);
				sbg.enterState(Main.MENUSTATE);
			}
		}


	}
	
	// pas trés propre
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
