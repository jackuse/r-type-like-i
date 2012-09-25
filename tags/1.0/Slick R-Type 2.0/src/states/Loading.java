package states;

//import game.LoadThread;
import game.Main;
import game.ResourceManager;
import game.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.newdawn.slick.state.transition.Transition;

public class Loading extends BasicGameState{
	int stateID = -1;
	private View view = View.getInstance();
	private ResourceManager rm = ResourceManager.getInstance();
	boolean ok= true;
	int nbProc = Runtime.getRuntime().availableProcessors();
	private Transition t[];


	public Loading(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		view.loadGame();		
		t = new Transition[2];

		try {
			t[0] = FadeOutTransition.class.newInstance();
			t[1] = FadeInTransition.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		//view.renderChargement(gc, gr);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(Main.previousState == -1){
			if(ok){
				if (view.nextResource != null) { 
					view.loadNext();
					view.nextResource = null; 
				} 
			}


			if (LoadingList.get().getRemainingResources() > 0) { 
				view.nextResource = LoadingList.get().getNext(); 
			} else { 
				sbg.enterState(Main.MENUSTATE,t[0],t[1]);
				System.out.println("Fin chargement");
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
}
