package states;

import game.Chara;
import game.Main;
import game.ResourceManager;
import game.Vue;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SelectionMusic extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();


	private int selectX=0;
	private int selectY=0;
	private boolean[] selectMusic;
	private int delayClick=150;




	public SelectionMusic(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		selectMusic=vue.initSelectionMusic(selectMusic);
		//System.out.println("select init "+selectMusic[0]);

		//on charge tout les noms du fichier XML



	}
	
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException{
		vue.setMusic(0);
		vue.selectMusic(0);
		vue.setMusic(1);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderSelection(gc,gr, selectX, selectY);
		//System.out.println("du texte ici");



	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		//System.out.println("etat "+sbg.getCurrentStateID()+" et "+Main.etatprecedent);

		Input input = gc.getInput(); // On récupére les input

		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(Main.OPTIONSTATE);
		}





		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		boolean insideExit = false;

		for(int i=1;i<selectMusic.length;i++){
			selectMusic[i] = false;
		}


		for(int i=0;i<selectMusic.length;i++){
			if( ( mouseX >= selectX+10 && mouseX <= selectX+10+vue.getValider().getWidth()*0.45f) &&
					( mouseY >= selectY+40*(i-1)+50 && mouseY <= selectY +40*(i-1)+50 + vue.getValider().getHeight()*0.45f) ){
				selectMusic[i]= true;
				//System.out.println("on music "+i);
			}
		}
		for(int i=0;i<selectMusic.length;i++){
			if( ( mouseX >= selectX+10 && mouseX <= selectX+10+600) &&
					( mouseY >= selectY+40*(i-1)+50 && mouseY <= selectY +40*(i-1)+50 + 34) ){
				if(vue.getIdMusic()!=i){
				vue.setMusic(0);
				vue.selectMusic(i);
				vue.setMusic(1);
				System.out.println("set music "+i);
				}
				System.out.println("on music "+i);
			}
		}
		if( ( mouseX >= selectX+10 && mouseX <= selectX+10 + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= selectY+vue.getHeight()*0.80f && mouseY <= selectY+vue.getHeight()*0.80f + vue.getOptionOption().getHeight()*0.7) ){
			insideExit = true;
			//System.out.println("on exit");
		}

		for(int i=0;i<selectMusic.length;i++){
			if(selectMusic[i]){
				delayClick-= 20;
				if (delayClick<0){
					if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
						if(vue.isSelectMusic(i)){
							vue.setValiderSelectMusic(i,false);
							System.out.println("music "+i+" off");							
						}
						else{
							vue.setValiderSelectMusic(i,true);
							System.out.println("music "+i+" on");
						}
					}
					delayClick = 100;
				}
			}

		}
		


		if(insideExit)
		{
			if(vue.getExitScale() < 0.8f)
				vue.setExitScale(vue.getExitScale() + 0.00001f * 20);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){

				vue.setExitScale(0.7f);

				sbg.enterState(Main.OPTIONSTATE);

			}
		}else{
			if(vue.getExitScale() > 0.7f)
				vue.setExitScale(vue.getExitScale() - 0.00001f * 20);
		}


	}

	@Override
	public int getID() {
		return stateID;
	}




}

