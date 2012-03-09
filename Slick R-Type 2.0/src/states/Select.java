package states;

import game.Main;
import game.ResourceManager;
import game.Vue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Select extends BasicGameState{

	int stateID = -1;
	private Vue vue = Vue.getInstance();
	
	int menuX = 30;
	int menuY = 200;
	float scaleStep = 0.0002f;
    //boolean firstLauch = true;

    private Image sheet;
    private ResourceManager rm = ResourceManager.getInstance();
    

	public Select(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		vue.initCharacterSelectScreen();
		System.out.println("je suis character select screen");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderCharacterSelectScreen(gr, menuX, menuY);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		System.out.println("etat "+sbg.getCurrentStateID()+" l'autre c'est "+Main.etatprecedent);
		if(Main.etatprecedent == Main.MENUSTATE){
			//reset();
			Main.etatprecedent = sbg.getCurrentStateID();	
			
			//vue.setMusic(1);
			vue.setMusic(0);
			vue.selectMusic(7);
			//vue.principale.setPosition(120.0f);
			if(vue.isValiderMusic()){	
				vue.setMusic(4);
			}
			vue.principale.setVolume(1.5f);
			
			
		}
		
		if(vue.isValiderMusic()){
			if(!vue.isMusic()){
				vue.setMusic(0);
				vue.nextMusic();
				vue.setMusic(4);		
			}
		}
		//System.out.println("etat "+sbg.getCurrentStateID());
		//System.out.println("Music on: "+vue.isMusic()+" firstLauch: "+Main.etatprecedent);
		if(!vue.isMusic()&& (Main.etatprecedent == Main.PAUSESTATE)){
			//vue.selectMusic(0);
			//vue.setMusic(1);
		}
		//if(!vue.isMusic())
			//vue.setMusic(1);

			
		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideChar1 = false;
		boolean insideChar3 = false;
		boolean insideChar2 = false;

		if( ( mouseX >= menuX && mouseX <= menuX + vue.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getStartGameOption().getHeight()*0.7) ){
			insideChar1 = true;
		}else if( ( mouseX >= menuX+600 && mouseX <= menuX+600 + vue.getExitOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getExitOption().getHeight()*0.7) ){
			insideChar3 = true;
		}
		else if( ( mouseX >= menuX+350 && mouseX <= menuX+350 + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getOptionOption().getHeight()*0.7) ){
			insideChar2 = true;
		}

		if(insideChar1){
			if(vue.getStartGameScale() < 0.8f)
				vue.setStartGameScale(vue.getStartGameScale()+scaleStep * delta);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//vue.setMusic(0);
				//vue.initGame(); 
				//vue.setMusicJeu(true);
				vue.setStartGameScale(0.7f);
				vue.shipname="VAISSEAU_1.gif";
				sbg.enterState(Main.GAMESTATE);
			}
		}else{
			if(vue.getStartGameScale() > 0.7f)
				vue.setStartGameScale(vue.getStartGameScale()-scaleStep * delta);

			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		if(insideChar2){
			if(vue.getStartGameScale() < 0.8f)
				vue.setStartGameScale(vue.getStartGameScale()+scaleStep * delta);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//vue.setMusic(0);
				//vue.initGame(); 
				//vue.setMusicJeu(true);
				vue.setStartGameScale(0.7f);
				vue.shipname="VAISSEAU_2.gif";
				sbg.enterState(Main.GAMESTATE);
			}
		}else{
			if(vue.getStartGameScale() > 0.7f)
				vue.setStartGameScale(vue.getStartGameScale()-scaleStep * delta);

			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		if(insideChar3)
		{
			if(vue.getStartGameScale() < 0.8f)
				vue.setStartGameScale(vue.getStartGameScale()+scaleStep * delta);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//vue.setMusic(0);
				//vue.initGame(); 
				//vue.setMusicJeu(true);
				vue.setStartGameScale(0.7f);
				vue.shipname="VAISSEAU_3.gif";
				
				sbg.enterState(Main.GAMESTATE);
			}
		}else{
			if(vue.getStartGameScale() > 0.7f)
				vue.setStartGameScale(vue.getStartGameScale()-scaleStep * delta);

			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		
	}

	@Override
	public int getID() {
		return stateID;
	}
}



