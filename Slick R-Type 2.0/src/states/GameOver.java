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
	boolean ok = true;
	int delay = 200;
	private int[] param;

	public GameOver(int stateID) {
		this.stateID = stateID;
	}

	public void enter(GameContainer gc, StateBasedGame sgb) {
		gc.getInput().clearKeyPressedRecord();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		param =new int[10];
		
	}

	public int getParam(int i) {
		return param[i];
	}

	public void setParam(int i, int p) {
		this.param[i] = p;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderGameOver(gc,gr,GmOvX,GmOvY,ok,param);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		delay-=delta;//a modifier
		if(delay < 0){
			if(ok)
				ok = false;
			else
				ok = true;
			delay = 200;
		}
		vue.selectMusic(0);

		
		Input input = gc.getInput(); // On récupére les input
		
		if (input.isKeyPressed(Input.KEY_SPACE)){
			sbg.enterState(Main.MENUSTATE);
			//vue.setMusic(1);
		}
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
