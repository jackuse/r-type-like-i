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
import org.newdawn.slick.state.transition.SelectTransition;

/**
 * Class SelectionMusic
 * @author Etienne Grandier-Vazeille
 * 
 * Idée amélioration : faire des pages de musique quand il y en a trop à afficher 
 *
 */
public class SelectMusic extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();


	private int selectX=0;
	private int selectY=0;
	private boolean[] selectMusic;
	private int delayClick=150;
	private boolean[] inside;
	private int kB;
	private int maxItem;




	public SelectMusic(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		// On charge tout les titres des musiques du fichier XML
		selectMusic=vue.initSelectionMusic(selectMusic);
		maxItem = selectMusic.length;
		inside = new boolean[maxItem];
		for(int i=0;i<maxItem;i++)
			inside[i]=false;
	}
	
	public void enter(GameContainer gc, StateBasedGame sgb) {
		vue.resetInOption();
	}
	
	/* 
	 * Quand on quitte le menu de selection de musique on remet la musique du menu
	 */
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException{
		vue.setMusic(0);
		vue.selectMusic(0);
		vue.setMusic(1);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderSelection(gc,gr, selectX, selectY,inside);
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
		boolean enterPress = false;
		
		//vue.resetInOption();
		
		// Les commandes de déplacements clavier
		/*if (input.isKeyPressed(Input.KEY_Z)){
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
		for(int i=0;i<selectMusic.length;i++){
			if(selectMusic[i]){
				for(int j=0;j<maxItem;j++)
					inside[j]=false;
			}
		}
		
		if(insideExit){
			for(int i=0;i<maxItem;i++)
				inside[i]=false;
		}*/

		// Initialisation du tableau de selection des musiques
		for(int i=1;i<selectMusic.length;i++){
			selectMusic[i] = false;
		}

		
		// On detecte si la souris et sur le titre d'une musique
		for(int i=0;i<selectMusic.length;i++){
			if( ( mouseX >= selectX+10 && mouseX <= selectX+10+vue.getValider().getWidth()*0.45f) &&
					( mouseY >= selectY+40*(i-1)+50 && mouseY <= selectY +40*(i-1)+50 + vue.getValider().getHeight()*0.45f) ){
				selectMusic[i]= true;
			}
		}
		
		// Si on est sur le titre d'une musique et qu'elle n'est pas lancée on la lance
		for(int i=0;i<selectMusic.length;i++){
			if( (( mouseX >= selectX+10 && mouseX <= selectX+10+600) &&
					( mouseY >= selectY+40*(i-1)+50 && mouseY <= selectY +40*(i-1)+50 + 34)) /*|| inside[i]*/ ){
				if(vue.getIdMusic()!=i){
				vue.setMusic(0);
				vue.selectMusic(i);
				vue.setMusic(1);
				//System.out.println("set music "+i);
				}
				//System.out.println("on music "+i);
			}
		}
		
		// On detect si on est sur le bouton exit
		if( ( mouseX >= selectX+10 && mouseX <= selectX+10 + vue.getOptionOption().getWidth()*0.7) &&
				( mouseY >= selectY+vue.getHeight()*0.80f && mouseY <= selectY+vue.getHeight()*0.80f + vue.getOptionOption().getHeight()*0.7) ){
			insideExit = true;
		}

		// Si on clique sur la case on active ou non la musique pour la lecture en playlist
		for(int i=0;i<selectMusic.length;i++){
			if(selectMusic[i] || inside[i] ){
				delayClick-= 20;
				if (delayClick<0 || inside[i] ){
					if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
						if(vue.isSelectMusic(i)){
							vue.setValiderSelectMusic(i,false);
							//System.out.println("music "+i+" off");							
						}
						else{
							vue.setValiderSelectMusic(i,true);
							//System.out.println("music "+i+" on");
						}
					}
					delayClick = 100;
				}
			}

		}
		

		// Si on est sur exit on quit le menu
		if(insideExit || inside[0])
		{
			if(vue.getExitScale() < 0.8f)
				vue.setExitScale(vue.getExitScale() + 0.00001f * 20);
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){

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

