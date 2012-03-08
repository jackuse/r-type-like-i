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
	int delayClick = 150;
	

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
		boolean insideMusicSelection = false;


		if( ( mouseX >= optionX+vue.getWidth()*0.20f+10 && mouseX <= optionX+vue.getWidth()*0.20f+10 + vue.getValider().getWidth()*0.7) &&
				( mouseY >= optionY+10 && mouseY <= optionY+10 + vue.getValider().getHeight()) ){
			insideMusic = true;
			System.out.println("on music");
		}else if( ( mouseX >= optionX && mouseX <= optionX+vue.getWidth()*0.32f) &&
				( mouseY >= optionY+vue.getHeight()*0.10f && mouseY <= optionY+vue.getHeight()*0.17f) ){
			insideMusicSelection = true;
			//System.out.println("on selection");
		}else if( ( mouseX >= optionX+vue.getWidth()*0.20f+10 && mouseX <= optionX+vue.getWidth()*0.20f+10 + vue.getValider().getWidth()*0.7) &&
				( mouseY >= optionY+vue.getHeight()*0.20+10f&& mouseY <= optionY+vue.getHeight()*0.20f+10 + vue.getValider().getHeight()*0.7) ){
			insideFullscreen = true;
			//System.out.println("on fullscreen");
		}
		else if( ( mouseX >= optionX && mouseX <= optionX + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= optionY+vue.getHeight()*0.30f && mouseY <= optionY+vue.getHeight()*0.30f + vue.getOptionOption().getHeight()*0.7) ){
			insideExit = true;
			//System.out.println("on exit");
		}

		if(insideMusic){
			//System.out.println("music in "+delayClick+" et delta"+ delta);
			delayClick-= 20;
			if (delayClick<0){

				//System.out.println("music in delay"+delayClick);
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
					if(!vue.isMusic()){
						vue.setValiderSetMusic(true);
						vue.setMusic(3);
						System.out.println("music on");
					}
					else{
						vue.setValiderSetMusic(false);
						vue.setMusic(2);
						//System.out.println("music off");
					}
				}
				delayClick = 100;
			}

		}
		if(insideMusicSelection){

			delayClick-= 20;
			if (delayClick<0){
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
					System.out.println("click");
					sbg.enterState(Main.SELECTIONMUSICSTATE);
					System.out.println("click");
				}
				delayClick = 150;
			}
			
		}



		if(insideFullscreen){
			delayClick-= delay;
			if (delayClick<0){
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
					if(!vue.getValiderFullScreen()){
						vue.setValiderFullScreen(true);
						//vue.setScreen(1280,720,true);
						System.out.println("Fullscreen ON");
					}
					else{
						vue.setValiderFullScreen(false);
						vue.setScreen(Main.WIDTH, Main.HEIGHT,false);
					}
				}
				delayClick = 100;
			}

		}

		if(insideExit)
		{
			if(vue.getExitScale() < 0.8f)
				vue.setExitScale(vue.getExitScale() + scaleStep * delay);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){

				vue.setExitScale(0.7f);

				if(Main.etatprecedent == Main.PAUSESTATE){
					sbg.enterState(Main.PAUSESTATE);
					Main.etatprecedent = sbg.getCurrentStateID();
				}
				else if(Main.etatprecedent == Main.MENUSTATE){
					sbg.enterState(Main.MENUSTATE);
					Main.etatprecedent = sbg.getCurrentStateID();
				}
			}
		}else{
			if(vue.getExitScale() > 0.7f)
				vue.setExitScale(vue.getExitScale() - scaleStep * delay);
		}
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			if(Main.etatprecedent == Main.PAUSESTATE){
				sbg.enterState(Main.PAUSESTATE);
				Main.etatprecedent = sbg.getCurrentStateID();
			}
			else if(Main.etatprecedent == Main.MENUSTATE){
				sbg.enterState(Main.MENUSTATE);
				Main.etatprecedent = sbg.getCurrentStateID();
			}
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
