package states;

import game.Main;
import game.ResourceManager;
import game.Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Chargement extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();
	private ResourceManager rm = ResourceManager.getInstance();

	public Chargement(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		vue.chargemementGame();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderChargement(gc, gr);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if (vue.nextResource != null) { 
			System.out.println(vue.nextResource.getDescription());
			try { 
				vue.nextResource.load(); 
				// slow down loading for example purposes 
				//try { Thread.sleep(500); } catch (Exception e) {} 
			} catch (IOException e) { 
				try {
					throw new SlickException("Failed to load: "+vue.nextResource.getDescription(), e);
				} catch (SlickException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			} 

			vue.nextResource = null; 
		} 

		if (LoadingList.get().getRemainingResources() > 0) { 
			vue.nextResource = LoadingList.get().getNext(); 
		} else { 
			sbg.enterState(Main.GAMESTATE);
			vue.setMusic(1);
		} 
		
	}

	@Override
	public int getID() {
		return stateID;
	}
}
