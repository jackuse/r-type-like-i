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
	int delay = 20;
	int delayClick = 100;

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
		//System.out.println("avant rendu option");
		vue.renderOption(gc, gr, optionX, optionX);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("etat "+sbg.getCurrentStateID()+" et "+Main.etatprecedent);

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


		if( ( mouseX >= optionX+vue.getWidth()*0.20f && mouseX <= optionX+vue.getWidth()*0.20f + vue.getValider().getWidth()*0.7) &&
				( mouseY >= optionY && mouseY <= optionY + vue.getValider().getHeight()) ){
			insideMusic = true;
			System.out.println("on music");
		}else if( ( mouseX >= optionX+vue.getWidth()*0.20f && mouseX <= optionX+vue.getWidth()*0.20f + vue.getValider().getWidth()*0.7) &&
				( mouseY >= optionY+vue.getHeight()*0.10f&& mouseY <= optionY+vue.getHeight()*0.10f + vue.getValider().getHeight()*0.7) ){
			insideFullscreen = true;
			System.out.println("on fullscreen");
		}
		else if( ( mouseX >= optionX && mouseX <= optionX + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= optionY+vue.getHeight()*0.25f && mouseY <= optionY+vue.getHeight()*0.25f + vue.getOptionOption().getHeight()*0.7) ){
			insideExit = true;
			System.out.println("on exit");
		}

		if(insideMusic){
			//System.out.println("music in "+delayClick+" et delta"+ delta);
			delayClick-= 20;
			if (delayClick<0){
				
				//System.out.println("music in delay"+delayClick);
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
					if(!vue.isMusic()){
						vue.setValiderMusic(true);
						vue.setMusic(3);
						System.out.println("music on");
					}
					else{
						vue.setValiderMusic(false);
						vue.setMusic(2);
						//System.out.println("music off");
					}
				}
				delayClick = 100;
			}
		}else{
			if(vue.getStartGameScale() > 0.7f)
				vue.setStartGameScale(vue.getStartGameScale()-scaleStep * delay);

		}

		if(insideFullscreen){
			delayClick-= delay;
			if (delayClick<0){
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
					if(!vue.getValiderFullScreen()){
						vue.setValiderFullScreen(true);
						vue.setScreen(1280,720,true);
						System.out.println("Fullscreen ON");
					}
					else{
						vue.setValiderFullScreen(false);
						vue.setScreen(Main.WIDTH, Main.HEIGHT,false);
					}
				}
				delayClick = 100;
			}
		}else{
			if(vue.getOptionScale() > 0.7f)
				vue.setOptionScale(vue.getOptionScale()-scaleStep * delay);

		}

		if(insideExit)
		{
			if(vue.getExitScale() < 0.8f)
				vue.setExitScale(vue.getExitScale() + scaleStep * delay);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){

				vue.setExitScale(0.7f);

				if(Main.etatprecedent == Main.PAUSESTATE)
					sbg.enterState(Main.PAUSESTATE);
				else if(Main.etatprecedent == Main.MENUSTATE)
					sbg.enterState(Main.MENUSTATE);
			}
		}else{
			if(vue.getExitScale() > 0.7f)
				vue.setExitScale(vue.getExitScale() - scaleStep * delay);
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
