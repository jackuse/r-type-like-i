package states;

import game.Main;
import game.View;

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
	private View view = View.getInstance();

	int optionX = 200;
	int optionY = 200;
	float scaleStep = 0.0002f;
	int delay = 20;
	int delayClick = 150;
	private Transition[] t;
	int screenSize = 1;
	private boolean[] inside;
	private int kB = 0;
	private int maxItem = 3;
	private int posTxt[][];
	

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
		view.resetInOption();
		for(int i=0;i<maxItem;i++)
			inside[i]=false;
		
		//System.out.println("je suis entre");
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
		view.initOption();
		
		inside = new boolean[maxItem];
		for(int i=0;i<maxItem;i++)
			inside[i]=false;
		
		posTxt = new int[3][2];
		posTxt[0][0] = 160;
		posTxt[0][1] = 60;
		posTxt[1][0] = 0;
		posTxt[1][1] = 120;
		posTxt[2][0] = 0;
		posTxt[2][1] = 180;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		//System.out.println("avant rendu option");
		view.renderOption(gc, gr, optionX, optionX,screenSize,inside,posTxt);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("etat "+sbg.getCurrentStateID()+" l'autre c'est "+Main.previousState);

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
		
		



		if( ( mouseX >= optionX+posTxt[0][0] && mouseX <= optionX+posTxt[0][0] + view.getValider().getWidth()*0.7) &&
				( mouseY >= optionY+posTxt[0][1]&& mouseY <= optionY+posTxt[0][1] + view.getValider().getHeight()) ){
			insideMusic = true;
			System.out.println("on music");
		}else if( ( mouseX >= optionX+posTxt[1][0] && mouseX <= optionX+posTxt[1][0]+256) &&
				( mouseY >= optionY+posTxt[1][1] && mouseY <= optionY+posTxt[1][1]+42) ){
			insideMusicSelection = true;
			System.out.println("on selection");
//		}else if( ( mouseX >= optionX+view.getWidth()*0.25f && mouseX <= optionX+view.getWidth()*0.25f+view.getButton(0).getWidth()) &&
//				( mouseY >= optionY+view.getHeight()*0.22f && mouseY <= optionY+view.getHeight()*0.22f + view.getButton(0).getHeight()) ){
//			insideBL = true;
//			System.out.println("on screensizeL");
//		}
//		else if( ( mouseX >= optionX+view.getWidth()*0.50f && mouseX <= optionX+view.getWidth()*0.50f+view.getButton(1).getWidth()) &&
//				( mouseY >= optionY+view.getHeight()*0.22f && mouseY <= optionY+view.getHeight()*0.22f + view.getButton(1).getHeight()) ){
//			insideBR = true;
//			System.out.println("on screensizeR");
//		}else if( ( mouseX >= optionX+view.getWidth()*0.20f+10 && mouseX <= optionX+view.getWidth()*0.20f+10 + view.getValider().getWidth()*0.7) &&
//				( mouseY >= optionY+view.getHeight()*0.30+10f&& mouseY <= optionY+view.getHeight()*0.30f+10 + view.getValider().getHeight()*0.7) ){
//			insideFullscreen = true;
//			//System.out.println("on fullscreen");
		}
		else if( ( mouseX >= optionX+posTxt[2][0] && mouseX <= optionX+posTxt[2][0] + view.getOptionOption().getWidth()*0.7) &&
				( mouseY >= optionY+posTxt[2][1] && mouseY <= optionY+posTxt[2][1] + view.getOptionOption().getHeight()*0.7) ){
			insideExit = true;
			System.out.println("on exit");
		}
		
		if(insideExit || insideBL || insideBR || insideFullscreen || insideMusic || insideMusicSelection){
			for(int i=0;i<maxItem;i++)
				inside[i]=false;
		}
		
		view.resetInOption();

		if(insideMusic || inside[0]){
			view.inOption(0);
			//System.out.println("music in "+delayClick+" et delta"+ delta);
			delayClick-= 20;
			if (delayClick<0 || inside[0]){

				//System.out.println("music in delay"+delayClick);
				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress ){
					if(!view.isMusic()){
						view.setValiderSetMusic(true);
						view.setMusic(3);
						System.out.println("music on");
					}
					else{
						view.setValiderSetMusic(false);
						view.setMusic(2);
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
					sbg.enterState(Main.SELECTMUSICSTATE);
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
//						view.setScreen(800, 600, false);
//						break;
//					case 2:
//						view.setScreen(1024, 768, false);
//						break;
//					case 3:
//						view.setScreen(1280, 960, false);
//						break;
//					case 4:
//						view.setScreen(1366, 768, false);
//						break;
//					case 5:
//						view.setScreen(1920, 1080, false);
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



//		if(insideFullscreen  || inside[2]){
//			delayClick-= delay;
//			if (delayClick<0  || inside[1]){
//				if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
//					if(!view.getValiderFullScreen()){
//						view.setValiderFullScreen(true);
//						//view.setScreen(1280,720,true);
//						System.out.println("Fullscreen ON");
//					}
//					else{
//						view.setValiderFullScreen(false);
//						view.setScreen(Main.WIDTH, Main.HEIGHT,false);
//					}
//				}
//				delayClick = 100;
//			}
//
//		}

		if(insideExit  || inside[2])
		{
			if(view.getExitScale() < 0.8f)
				view.setExitScale(view.getExitScale() + scaleStep * delay);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){

				view.setExitScale(0.7f);
				
//				//application du mode d'affichage
//				switch (screenSize) {
//				case 1:
//					view.setScreen(800, 600, false);
//					break;
//				case 2:
//					view.setScreen(1024, 768, false);
//					break;
//				case 3:
//					view.setScreen(1280, 960, false);
//					break;
//				case 4:
//					view.setScreen(1366, 768, false);
//					break;
//				case 5:
//					view.setScreen(1920, 1080, false);
//					break;
//		
//				default:
//					break;
//				}

				if(Main.previousState == Main.PAUSESTATE){
					sbg.enterState(Main.PAUSESTATE);
					Main.previousState = sbg.getCurrentStateID();
				}
				else if(Main.previousState == Main.MENUSTATE){
					//sbg.enterState(Main.MENUSTATE,t[0],t[1]);// Bug de transition si on quit dans le jeu
					sbg.enterState(Main.MENUSTATE);// Bug de transition si on quit dans le jeu
					Main.previousState = sbg.getCurrentStateID();
				}
			}
		}else{
			if(view.getExitScale() > 0.7f)
				view.setExitScale(view.getExitScale() - scaleStep * delay);
		}
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			if(Main.previousState == Main.PAUSESTATE){
				sbg.enterState(Main.PAUSESTATE);
				Main.previousState = sbg.getCurrentStateID();
			}
			else if(Main.previousState == Main.MENUSTATE){
				sbg.enterState(Main.MENUSTATE);
				Main.previousState = sbg.getCurrentStateID();
			}
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
