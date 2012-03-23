package states;

import game.Main;
import game.ResourceManager;
import game.View;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Select extends BasicGameState{

	int stateID = -1;
	private View view = View.getInstance();
	
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
		view.initCharacterSelectScreen();
		System.out.println("je suis character select screen");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		view.renderCharacterSelectScreen(gr, menuX, menuY);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		System.out.println("etat "+sbg.getCurrentStateID()+" l'autre c'est "+Main.previousState);
		if(Main.previousState == Main.MENUSTATE){
			//reset();
			Main.previousState = sbg.getCurrentStateID();	
			
			//view.setMusic(1);
			view.setMusic(0);
			view.selectMusic(7);
			//view.principale.setPosition(120.0f);
			if(view.isValiderMusic()){	
				view.setMusic(4);
			}
			view.principale.setVolume(1.5f);
			
			
		}
		
		if(view.isValiderMusic()){
			if(!view.isMusic()){
				view.setMusic(0);
				view.nextMusic();
				view.setMusic(4);		
			}
		}
		//System.out.println("etat "+sbg.getCurrentStateID());
		//System.out.println("Music on: "+view.isMusic()+" firstLauch: "+Main.etatprecedent);
		if(!view.isMusic()&& (Main.previousState == Main.PAUSESTATE)){
			//view.selectMusic(0);
			//view.setMusic(1);
		}
		//if(!view.isMusic())
			//view.setMusic(1);

			
		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideChar1 = false;
		boolean insideChar3 = false;
		boolean insideChar2 = false;

		if( ( mouseX >= menuX && mouseX <= menuX + view.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + view.getStartGameOption().getHeight()*0.7) ){
			insideChar1 = true;
		}else if( ( mouseX >= menuX+600 && mouseX <= menuX+600 + view.getExitOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + view.getExitOption().getHeight()*0.7) ){
			insideChar3 = true;
		}
		else if( ( mouseX >= menuX+350 && mouseX <= menuX+350 + view.getOptionOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + view.getOptionOption().getHeight()*0.7) ){
			insideChar2 = true;
		}
		
		if(!insideChar1 && !insideChar2 && !insideChar3)
			view.setAltBouton(0,menuX,menuY);

		if(insideChar1){
			view.setAltBouton(1, menuX, menuY);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//view.setMusic(0);
				//view.initGame(); 
				//view.setMusicJeu(true);
				view.setStartGameScale(0.7f);
				view.shipname="VAISSEAU_1.gif";
//				sbg.enterState(Main.GAMESTATE);
				sbg.enterState(Main.HISTORYSTATE);
			}
		}else{
			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		if(insideChar2){
			view.setAltBouton(2,menuX, menuY);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//view.setMusic(0);
				//view.initGame(); 
				//view.setMusicJeu(true);
				view.setStartGameScale(0.7f);
				view.shipname="VAISSEAU_2.gif";
//				sbg.enterState(Main.GAMESTATE);
				sbg.enterState(Main.HISTORYSTATE);
			}
		}else{
			
			
			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		if(insideChar3)
		{
			view.setAltBouton(3,menuX, menuY);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//view.setMusic(0);
				//view.initGame(); 
				//view.setMusicJeu(true);
				view.setStartGameScale(0.7f);
				view.shipname="VAISSEAU_3.gif";
				
				sbg.enterState(Main.GAMESTATE);
			}
		}else{
			
			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		
	}

	@Override
	public int getID() {
		return stateID;
	}
}



