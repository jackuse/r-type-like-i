package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Controls extends BasicGameState {
	int stateID = -1;
	private View vue = View.getInstance();
	private int ctrY=50;
	private int ctrX=50;
	private int posTxt[][];
	private boolean anyKeyPressed = false;
	
	public Controls(int stateID) {
		this.stateID = stateID;
	}



	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		posTxt = new int[8][2];
		posTxt[0][0] = 0;
		posTxt[0][1] = 0;
		posTxt[1][0] = 0;
		posTxt[1][1] = 40;
		posTxt[2][0] = 0;
		posTxt[2][1] = 80;
		posTxt[3][0] = 0;
		posTxt[3][1] = 120;
		posTxt[3][0] = 0;
		posTxt[4][1] = 160;
		posTxt[4][0] = 0;
		posTxt[5][1] = 200;
		posTxt[5][0] = 0;
		posTxt[6][1] = 240;
		posTxt[7][0] = 0;
		posTxt[7][1] = 280;

	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		anyKeyPressed = false;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderControls(gc,gr,ctrX,ctrY,posTxt);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		Input input = gc.getInput(); // On récupére les input
		
		
		if ( anyKeyPressed){
			sbg.enterState(Main.previousState);
			Main.previousState = Main.CONTROLSSTATE;
		}

	}
	
	public void keyPressed(int key, char c){
	       this.anyKeyPressed  = true;
	}

}
