package states;


import game.Main;
import game.Vue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();
	
	int GmOvX = 250;
	int GmOvY = 230;

	public GameOver(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderGameOver(gc,gr,GmOvX,GmOvY);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput(); // On récupére les input
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(Main.MENUSTATE);
			//vue.setMusic(1);
		}
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
