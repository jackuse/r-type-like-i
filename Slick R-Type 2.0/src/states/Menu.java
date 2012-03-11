package states;

import game.Main;
import game.Vue;

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

public class Menu extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();

	int menuX = 30;
	int menuY = 500;
	float scaleStep = 0.0002f;
	boolean firstLauch = true;
	int delay = 20;
	private int titreX = 0;
	private int titreY = -300;
	private Transition t[];
	private Transition to[];



	public Menu(int stateID) {
		this.stateID = stateID;
	}

	public void enter(GameContainer gc, StateBasedGame sgb) throws SlickException{
		try {
			t[0] = FadeOutTransition.class.newInstance();
			t[1] = FadeInTransition.class.newInstance();
			to[0] = null;
			to[1] = SelectTransition.class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gc.getInput().clearKeyPressedRecord();
		
//		System.out.println("debut initall");
//		try { Thread.sleep(2000); } catch (Exception e) {}
//		if(Main.init){
//			vue.initRes();
//			System.out.println("fin initall");
//			Main.init =false;
//			try { Thread.sleep(2000); } catch (Exception e) {}
//		}
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		vue.initMenu();
		System.out.println("je suis init menu");
		titreX = (int)(vue.getWidth()*0.20);
		t = new Transition[2];
		to = new Transition[2];
		


	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderMenu(gr, menuX, menuY, titreX, titreY);
		//System.out.println("passe la");

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("pause delta "+ delta);
		//System.out.println("etat "+sbg.getCurrentStateID()+" l'autre c'est "+Main.etatprecedent);
		//System.out.println("Music on: "+vue.isMusic()+" firstLauch: "+Main.etatprecedent);
		if(Main.etatprecedent == -1){
			vue.selectMusic(0);
			vue.setMusic(1);
			//Main.etatprecedent = sbg.getCurrentStateID();
		}


		if(Main.etatprecedent != sbg.getCurrentStateID()){
			
			if(titreY<200){
				titreY+=5;
			}
			else{

				if(Main.etatprecedent != Main.OPTIONSTATE){
					//vue.selectMusic(0);
					vue.setMusic(0);
					if(vue.isValiderMusic()){
						vue.setMusic(1);
					}
				}
				Main.etatprecedent = sbg.getCurrentStateID();
			}
		}

		/*
		if(!vue.isMusic()&& (Main.etatprecedent == sbg.getCurrentStateID())){
			vue.selectMusic(0);
			if(vue.isValiderMusic()){
				vue.setMusic(1);
			}

		}*/
		//if(!vue.isMusic())
		//vue.setMusic(1);


		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStartGame = false;
		boolean insideExit = false;
		boolean insideOption = false;

		if( ( mouseX >= menuX && mouseX <= menuX + vue.getStartGameOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getStartGameOption().getHeight()*0.7) ){
			insideStartGame = true;
		}else if( ( mouseX >= menuX+600 && mouseX <= menuX+600 + vue.getExitOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getExitOption().getHeight()*0.7) ){
			insideExit = true;
		}
		else if( ( mouseX >= menuX+350 && mouseX <= menuX+350 + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getOptionOption().getHeight()*0.7) ){
			insideOption = true;
		}

		if(insideStartGame){
			//System.out.println("je suis dans start game");
			if(vue.getStartGameScale() < 0.8f)
				vue.setStartGameScale(vue.getStartGameScale()+scaleStep * delay);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//vue.setMusic(0);
				//vue.initGame(); 
				//vue.setMusicJeu(true);
				vue.setStartGameScale(0.7f);
				Main.etatprecedent = Main.MENUSTATE;
				//sbg.enterState(Main.SELECTSTATE,t[0],t[1]);
				sbg.enterState(Main.SELECTSTATE);
				resetMenu();
			}
		}else{
			if(vue.getStartGameScale() > 0.7f)
				vue.setStartGameScale(vue.getStartGameScale()-scaleStep * delay);

			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		if(insideOption){
			//vue.triggerAlt();
			if(vue.getOptionScale() < 0.8f)
				vue.setOptionScale(vue.getOptionScale()+scaleStep * delay);

			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
				//vue.setMusic(0);
				vue.setOptionScale(0.7f);
				Main.etatprecedent = Main.MENUSTATE;
				sbg.enterState(Main.OPTIONSTATE,to[0],to[1]);
				resetMenu();
			}
		}else{
			if(vue.getOptionScale() > 0.7f)
				vue.setOptionScale(vue.getOptionScale()-scaleStep * delay);

			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		if(insideExit)
		{
			if(vue.getExitScale() < 0.8f)
				vue.setExitScale(vue.getExitScale() + scaleStep * delay);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
				gc.exit();
		}else{
			if(vue.getExitScale() > 0.7f)
				vue.setExitScale(vue.getExitScale() - scaleStep * delay);
		}
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			Main.app.exit();
		}


	}

	public void resetMenu(){
		titreY = -80;
	}

	@Override
	public int getID() {
		return stateID;
	}
}
