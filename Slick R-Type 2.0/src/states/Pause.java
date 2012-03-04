package states;

import game.Main;
import game.Vue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();
	
	int pauseX = 250;
	int pauseY = 230;


	public Pause(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		vue.initPause();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderPause(gc,gr,pauseX,pauseY);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		if(Main.etatprecedent != Main.PAUSESTATE)
			Main.etatprecedent = Main.PAUSESTATE;

		
		System.out.println("etat "+sbg.getCurrentStateID()+" et "+Main.etatprecedent);
		
		Input input = gc.getInput(); // On récupére les input
		
		if (input.isKeyPressed(Input.KEY_P)){
			gc.setPaused(!gc.isPaused());
			sbg.enterState(Main.GAMESTATE);
			//vue.setMusic(3);
		}
		
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStartGame = false;	
		boolean insideOption = false;
		boolean insideExit = false;


		if( ( mouseX >= pauseX && mouseX <= pauseX + vue.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= pauseY && mouseY <= pauseY + vue.getStartGameOption().getHeight()*0.7) ){
			insideStartGame = true;
			System.out.println("on StartGame");
		}else if( ( mouseX >= pauseX && mouseX <= pauseX + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= pauseY+45 && mouseY <= pauseY+45 + vue.getOptionOption().getHeight()*0.7) ){
			insideOption = true;
			System.out.println("on option");
		}else if( ( mouseX >= pauseX&& mouseX <= pauseX + vue.getExitOption().getWidth()*0.7) &&
				( mouseY >= pauseY+90 && mouseY <= pauseY+90 + vue.getExitOption().getHeight()*0.7) ){
			insideExit = true;
			System.out.println("on Exit");
		}

		if(insideStartGame){
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				sbg.enterState(Main.GAMESTATE);
				gc.setPaused(!gc.isPaused());
				//vue.setMusic(3);
			}
		}

		if(insideOption){
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//vue.setMusic(0);
				vue.setOptionScale(0.7f);
				
				sbg.enterState(Main.OPTIONSTATE);
				//vue.setMusic(3);
			}
		}

		if(insideExit)
		{

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				vue.setExitScale(0.7f);
				vue.setMusic(0);
				vue.selectMusic(0);
				sbg.enterState(Main.MENUSTATE);
				}
		}
   
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
