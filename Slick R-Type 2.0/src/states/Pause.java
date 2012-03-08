package states;

import game.Chara;

import java.util.ArrayList;

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

public class Pause extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();

	int pauseX = 250;
	int pauseY = 230;
	private boolean cheatModOn = false;
	//private char[] charPass; 
	private ArrayList<Chara> pass;
	int nbPassword = 2;
	private int delayClick = 150;

	/* Ajouter un curseur pour de déplacer au clavier
	 * 
	 * 
	 * 
	 * 
	 */


	public Pause(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		vue.initPause();
		//charPass = new char[42];
		pass = new ArrayList<Chara>();



	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderPause(gc,gr,pauseX,pauseY,cheatModOn,pass);
		//		if(Game.cheat[0])
		//			gr.drawString("GODMOD : ON :", 10,10);



	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("pause delta"+ delta);
		if(Main.etatprecedent != Main.PAUSESTATE)
			Main.etatprecedent = Main.PAUSESTATE;


		//System.out.println("etat "+sbg.getCurrentStateID()+" et "+Main.etatprecedent);

		Input input = gc.getInput(); // On récupére les input

		if (input.isKeyPressed(Input.KEY_P) || input.isKeyPressed(Input.KEY_ESCAPE)){
			gc.setPaused(!gc.isPaused());
			sbg.enterState(Main.GAMESTATE);
			//vue.setMusic(3);
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
				pass.clear();
				//System.out.println("mot de passe a l'écoute");
			}
			else
				cheatModOn = false;

		}

		if(cheatModOn){
			if (input.isKeyPressed(input.KEY_BACK)){
				if(pass.size()>0)
					pass.remove(pass.size()-1);	
				//System.out.println("on recule "+pass.size());
			}
			char c = getChar(input);
			if(c != '0' && pass.size() < 42){
				Chara ch = new Chara(c);
				pass.add(ch);
				//System.out.println("Je crois que tu a tapper un : "+ch.c);
			}

			if (input.isKeyPressed(input.KEY_ENTER)){

				String cheatCodeString[] = new String[nbPassword];

				cheatCodeString[0]= "bonnenote";
				cheatCodeString[1]= "allyourbasearebelongtous";
				char[][] charArr= new char[nbPassword][42];
				for(int i=0;i<nbPassword;i++){
					char[] ca = cheatCodeString[i].toCharArray();
					for(int j=0;j<cheatCodeString[i].length();j++)
						charArr[i][j] = ca[j];
				}	
				int mdp[] = new int[nbPassword];
				for(int i=0;i<nbPassword;i++){
					mdp[i] = 0;
				}
				for(int i=0;i<pass.size();i++){
					if(pass.get(i).c == charArr[0][i])
						mdp[0]+=1;
				}
				for(int i=0;i<nbPassword;i++){
					if(mdp[i] == cheatCodeString[i].length()){
						if(!Game.cheat[i])
							Game.cheat[i] = true;
						else
							Game.cheat[i] = false;
						System.out.println("Password accepted");
						if(i==0){
							if(Game.cheat[i]){
								//System.out.println("Invincibilité : ON");
								vue.setMessage("GodMod : ON",1000);
								vue.nextMusic();
							}
							else{
								//System.out.println("Invincibilité : OFF");
								vue.setMessage("GodMod : OFF",1000);
								vue.nextMusic();
							}
						}
						pass.clear();
					}
				}

				System.out.println("valider");

			}


			//System.out.println("Godmod ONNNNN");
		}

		///////////////////////////////////////// END CHEAT CODE  //////////////////////////////////////////////////////////////

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStartGame = false;	
		boolean insideOption = false;
		boolean insideExit = false;


		if( ( mouseX >= pauseX && mouseX <= pauseX + vue.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= pauseY && mouseY <= pauseY + vue.getStartGameOption().getHeight()*0.7) ){
			insideStartGame = true;
			//System.out.println("on StartGame");
		}else if( ( mouseX >= pauseX && mouseX <= pauseX + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= pauseY+45 && mouseY <= pauseY+45 + vue.getOptionOption().getHeight()*0.7) ){
			insideOption = true;
			//System.out.println("on option");
		}else if( ( mouseX >= pauseX&& mouseX <= pauseX + vue.getExitOption().getWidth()*0.7) &&
				( mouseY >= pauseY+90 && mouseY <= pauseY+90 + vue.getExitOption().getHeight()*0.7) ){
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

		if(insideExit)
		{

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				vue.setMusic(0);
				vue.setExitScale(0.7f);
				vue.selectMusic(0);
				sbg.enterState(Main.MENUSTATE);
			}
		}


	}

	@Override
	public int getID() {
		return stateID;
	}


	public char getChar(Input input){
		if(input.isKeyPressed(Input.KEY_A))
			return 'a';
		else if(input.isKeyPressed(Input.KEY_B))
			return 'b';
		else if(input.isKeyPressed(Input.KEY_C))
			return 'c';
		else if(input.isKeyPressed(Input.KEY_D))
			return 'd';
		else if(input.isKeyPressed(Input.KEY_E))
			return 'e';
		else if(input.isKeyPressed(Input.KEY_F))
			return 'f';
		else if(input.isKeyPressed(Input.KEY_G))
			return 'g';
		else if(input.isKeyPressed(Input.KEY_H))
			return 'h';
		else if(input.isKeyPressed(Input.KEY_I))
			return 'i';
		else if(input.isKeyPressed(Input.KEY_J))
			return 'j';
		else if(input.isKeyPressed(Input.KEY_K))
			return 'k';
		else if(input.isKeyPressed(Input.KEY_L))
			return 'l';
		else if(input.isKeyPressed(Input.KEY_M))
			return 'm';
		else if(input.isKeyPressed(Input.KEY_N))
			return 'n';
		else if(input.isKeyPressed(Input.KEY_O))
			return 'o';
		else if(input.isKeyPressed(Input.KEY_P))
			return 'p';
		else if(input.isKeyPressed(Input.KEY_Q))
			return 'q';
		else if(input.isKeyPressed(Input.KEY_R))
			return 'r';
		else if(input.isKeyPressed(Input.KEY_S))
			return 's';
		else if(input.isKeyPressed(Input.KEY_T))
			return 't';
		else if(input.isKeyPressed(Input.KEY_U))
			return 'u';
		else if(input.isKeyPressed(Input.KEY_V))
			return 'v';
		else if(input.isKeyPressed(Input.KEY_W))
			return 'w';
		else if(input.isKeyPressed(Input.KEY_X))
			return 'x';
		else if(input.isKeyPressed(Input.KEY_Y))
			return 'y';
		else if(input.isKeyPressed(Input.KEY_Z))
			return 'z';
		else
			return '0';

	}

}
