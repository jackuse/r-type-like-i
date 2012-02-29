package states;

import game.Main;
import game.Vue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Option extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();

	int optionX = 200;
	int optionY = 200;
	float scaleStep = 0.0002f;

	public Option(int stateID) {
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
		System.out.println("avant rendu option");
		vue.renderOption(gc, gr, optionX, optionX);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		System.out.println("etat "+sbg.getCurrentStateID()+" et "+Main.etatprecedent);
		
		Input input = gc.getInput(); // On récupére les input

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		// Les commandes de déplacements
		if(input.isKeyDown(Input.KEY_Z))
		{

		}
		if(input.isKeyDown(Input.KEY_S))
		{

		}

		boolean insideMusic = false; // Qui est un slide
		boolean insideExit = false; // Qui est un bouton
		boolean insideFullscreen = false; // Qui est un rectangle


		if( ( mouseX >= optionX && mouseX <= optionX + vue.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= optionY && mouseY <= optionY + vue.getStartGameOption().getHeight()*0.7) ){
			insideMusic = true;
			System.out.println("on music");
		}else if( ( mouseX >= optionX && mouseX <= optionX + vue.getExitOption().getWidth()*0.7) &&
				( mouseY >= optionY+90 && mouseY <= optionY+90 + vue.getExitOption().getHeight()*0.7) ){
			insideExit = true;
			System.out.println("on exit");
		}
		else if( ( mouseX >= optionX && mouseX <= optionX + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= optionY+45 && mouseY <= optionY+45 + vue.getOptionOption().getHeight()*0.7) ){
			insideFullscreen = true;
			System.out.println("on fullscreen");
		}

		if(insideMusic){
			if(vue.getStartGameScale() < 0.8f)
				vue.setStartGameScale(vue.getStartGameScale()+scaleStep * delta);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){

			}
		}else{
			if(vue.getStartGameScale() > 0.7f)
				vue.setStartGameScale(vue.getStartGameScale()-scaleStep * delta);

		}

		if(insideFullscreen){
			if(vue.getOptionScale() < 0.8f)
				vue.setOptionScale(vue.getOptionScale()+scaleStep * delta);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){

			}
		}else{
			if(vue.getOptionScale() > 0.7f)
				vue.setOptionScale(vue.getOptionScale()-scaleStep * delta);

		}

		if(insideExit)
		{
			if(vue.getExitScale() < 0.8f)
				vue.setExitScale(vue.getExitScale() + scaleStep * delta);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				
				vue.setExitScale(0.7f);
				
				if(Main.etatprecedent == Main.PAUSESTATE)
					sbg.enterState(Main.PAUSESTATE);
				else if(Main.etatprecedent == Main.MENUSTATE)
					sbg.enterState(Main.MENUSTATE);
			}
		}else{
			if(vue.getExitScale() > 0.7f)
				vue.setExitScale(vue.getExitScale() - scaleStep * delta);
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
