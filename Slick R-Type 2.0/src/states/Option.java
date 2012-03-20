package states;

import game.Main;
import game.Vue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.newdawn.slick.state.transition.Transition;

public class Option extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();

	int optionX = 200;
	int optionY = 200;
	float scaleStep = 0.0002f;
	int delay = 20;
	int delayClick = 150;
	private Transition[] t;
	int screenSize = 1;
	private boolean[] inside;
	private int kB = 0;
	private int maxItem = 4;
	

	public Option(int stateID) {
		this.stateID = stateID;
	}
	  
	public void enter(GameContainer gc, StateBasedGame sgb) {
		try {
			t[0] = null;
			t[1] = SelectTransition.class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vue.resetInOption();
		for(int i=0;i<maxItem;i++)
			inside[i]=false;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		
		t = new Transition[2];
		
		try {
			t[0] = null;
			t[1] = SelectTransition.class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vue.initOption();
		
		inside = new boolean[maxItem];
		for(int i=0;i<maxItem;i++)
			inside[i]=false;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		//System.out.println("avant rendu option");
		vue.renderOption(gc, gr, optionX, optionX,screenSize,inside);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("etat "+sbg.getCurrentStateID()+" et "+Main.etatprecedent);

		Input input = gc.getInput(); // On récupére les input

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		boolean insideMusic = false; // Qui est un slide
		boolean insideExit = false; // Qui est un bouton
		boolean insideFullscreen = false; // Qui est un rectangle
		boolean insideMusicSelection = false;
		boolean insideBR = false;
		boolean insideBL = false;
		boolean enterPress = false;


		// Les commandes de déplacements clavier
		if (input.isKeyPressed(Input.KEY_Z)){
			inside[kB] = false;
			kB--;
			if(kB<0)
				kB=maxItem-1;
			inside[kB] = true;
		}

		if (input.isKeyPressed(Input.KEY_S)){
			inside[kB] = false;
			kB++;
			if(kB>maxItem-1)
				kB=0;
			inside[kB] = true;
			System.out.println("kB = "+kB);
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER)){
			enterPress = true;
		}
		
		if(insideExit || insideBL || insideBR || insideFullscreen || insideMusic || insideMusicSelection){
			for(int i=0;i<maxItem;i++)
				inside[i]=false;
		}



		if( ( mouseX >= optionX+vue.getWidth()*0.20f+10 && mouseX <= optionX+vue.getWidth()*0.20f+10 + vue.getValider().getWidth()*0.7) &&
				( mouseY >= optionY+10+vue.getHeight()*0.10f && mouseY <= optionY+10+vue.getHeight()*0.10f + vue.getValider().getHeight()) ){
			insideMusic = true;
			//System.out.println("on music");
		}else if( ( mouseX >= optionX && mouseX <= optionX+vue.getWidth()*0.32f) &&
				( mouseY >= optionY+vue.getHeight()*0.20f && mouseY <= optionY+vue.getHeight()*0.10f+vue.getHeight()*0.17f) ){
			insideMusicSelection = true;
			//System.out.println("on selection");
//		}else if( ( mouseX >= optionX+vue.getWidth()*0.25f && mouseX <= optionX+vue.getWidth()*0.25f+vue.getButton(0).getWidth()) &&
//				( mouseY >= optionY+vue.getHeight()*0.22f && mouseY <= optionY+vue.getHeight()*0.22f + vue.getButton(0).getHeight()) ){
//			insideBL = true;
//			System.out.println("on screensizeL");
//		}
//		else if( ( mouseX >= optionX+vue.getWidth()*0.50f && mouseX <= optionX+vue.getWidth()*0.50f+vue.getButton(1).getWidth()) &&
//				( mouseY >= optionY+vue.getHeight()*0.22f && mouseY <= optionY+vue.getHeight()*0.22f + vue.getButton(1).getHeight()) ){
//			insideBR = true;
//			System.out.println("on screensizeR");
		}else if( ( mouseX >= optionX+vue.getWidth()*0.20f+10 && mouseX <= optionX+vue.getWidth()*0.20f+10 + vue.getValider().getWidth()*0.7) &&
				( mouseY >= optionY+vue.getHeight()*0.30+10f&& mouseY <= optionY+vue.getHeight()*0.30f+10 + vue.getValider().getHeight()*0.7) ){
			insideFullscreen = true;
			//System.out.println("on fullscreen");
		}
		else if( ( mouseX >= optionX && mouseX <= optionX + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= optionY+vue.getHeight()*0.40f && mouseY <= optionY+vue.getHeight()*0.40f + vue.getOptionOption().getHeight()*0.7) ){
			insideExit = true;
			//System.out.println("on exit");
		}
		
		vue.resetInOption();

		if(insideMusic || inside[0]){
			vue.inOption(0);
			//System.out.println("music in "+delayClick+" et delta"+ delta);
			delayClick-= 20;
			if (delayClick<0 || inside[0]){

				//System.out.println("music in delay"+delayClick);
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress ){
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
		if(insideMusicSelection || inside[1]){

			delayClick-= 20;
			if (delayClick<0  || inside[1]){
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
					sbg.enterState(Main.SELECTIONMUSICSTATE);
				}
				delayClick = 150;
			}
			
		}
		
//		if(insideBR){
//
//			delayClick-= 20;
//			if (delayClick<0){
//				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
//					if(screenSize <5)
//					screenSize++;
//				}
//				delayClick = 150;
//			}
//			
//		}
//		if(insideBR){
//			
//			delayClick-= 20;
//			if (delayClick<0){
//				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
//					if(screenSize >1)
//						screenSize--;
//				}
//				delayClick = 150;
//			}
			
//		}
//		if(insideApply){
//			
//			delayClick-= 20;
//			if (delayClick<0){
//				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
//					switch (screenSize) {
//					case 1:
//						vue.setScreen(800, 600, false);
//						break;
//					case 2:
//						vue.setScreen(1024, 768, false);
//						break;
//					case 3:
//						vue.setScreen(1280, 960, false);
//						break;
//					case 4:
//						vue.setScreen(1366, 768, false);
//						break;
//					case 5:
//						vue.setScreen(1920, 1080, false);
//						break;
//			
//					default:
//						break;
//					}
//				}
//				delayClick = 150;
//			}
//			
//		}



		if(insideFullscreen  || inside[2]){
			delayClick-= delay;
			if (delayClick<0  || inside[1]){
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
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

		if(insideExit  || inside[3])
		{
			if(vue.getExitScale() < 0.8f)
				vue.setExitScale(vue.getExitScale() + scaleStep * delay);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){

				vue.setExitScale(0.7f);
				
//				//application du mode d'affichage
//				switch (screenSize) {
//				case 1:
//					vue.setScreen(800, 600, false);
//					break;
//				case 2:
//					vue.setScreen(1024, 768, false);
//					break;
//				case 3:
//					vue.setScreen(1280, 960, false);
//					break;
//				case 4:
//					vue.setScreen(1366, 768, false);
//					break;
//				case 5:
//					vue.setScreen(1920, 1080, false);
//					break;
//		
//				default:
//					break;
//				}

				if(Main.etatprecedent == Main.PAUSESTATE){
					sbg.enterState(Main.PAUSESTATE);
					Main.etatprecedent = sbg.getCurrentStateID();
				}
				else if(Main.etatprecedent == Main.MENUSTATE){
					sbg.enterState(Main.MENUSTATE,t[0],t[1]);
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
