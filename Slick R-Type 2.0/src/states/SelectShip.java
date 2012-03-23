package states;

import game.Main;
import game.ResourceManager;
import game.View;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class SelectShip extends BasicGameState{

	int stateID = -1;
	private XMLPackedSheet ship;
	private View vue = View.getInstance();

	int menuX = 30;
	int menuY = 200;
	float scaleStep = 0.0002f;
	//boolean firstLauch = true;

	private Image sheet;
	private ResourceManager rm = ResourceManager.getInstance();
	private Animation ship1MoveDown;
	private Animation ship1MoveUp;
	private int maxItemMenu = 3;
	private int posCur;


	public SelectShip(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		vue.initCharacterSelectScreen();

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		vue.renderCharacterSelectScreen(gr, menuX, menuY);

	}
	
	public void enter(GameContainer gc, StateBasedGame sgb) throws SlickException{
		posCur = 0;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//System.out.println("etat "+sbg.getCurrentStateID()+" l'autre c'est "+Main.previousState);
		if(Main.previousState == Main.MENUSTATE){
			//reset();
			Main.previousState = sbg.getCurrentStateID();	

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
		if(!vue.isMusic()&& (Main.previousState == Main.PAUSESTATE)){
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
		boolean enterPress = false;
		
		vue.setAltBouton(insideChar1,insideChar2,insideChar3,menuX, menuY);

		if( ( mouseX >= menuX && mouseX <= menuX + vue.getStartGameOption().getWidth()*0.69) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getStartGameOption().getHeight()*0.69) ){
			insideChar1 = true;
		}else if( ( mouseX >= menuX+600 && mouseX <= menuX+600 + vue.getExitOption().getWidth()*0.58) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getExitOption().getHeight()*0.5) ){
			insideChar3 = true;

		}
		else if( ( mouseX >= menuX+390 && mouseX <= menuX+390 + vue.getOptionOption().getWidth()*0.43) &&
				( mouseY >= menuY && mouseY <= menuY + vue.getOptionOption().getHeight()*0.5) ){
			insideChar2 = true;
		}

		// Déplacement au clavier
		if (input.isKeyPressed(Input.KEY_Q)){
			posCur--;
			if(posCur<1)
				posCur=maxItemMenu;
			if(posCur == 0)
				posCur = 1;
		}

		if (input.isKeyPressed(Input.KEY_D)){
			posCur++;
			if(posCur>maxItemMenu)
				posCur=1;
		}


		if (input.isKeyPressed(Input.KEY_ENTER)){
			enterPress = true;
		}
		
		if(insideChar1 || insideChar2 || insideChar3 ){
			posCur=0;
		}

		if(!insideChar1 && !insideChar2 && !insideChar3 ){
			switch (posCur) {
			case 1:
				insideChar1=true;
				break;
			case 2:
				insideChar2=true;
				break;
			case 3:
				insideChar3=true;
				break;

			default:
				break;
			}
		}
		vue.setAltBouton(insideChar1,insideChar2,insideChar3,menuX, menuY);



		if(insideChar1){
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
				//vue.setMusic(0);
				//vue.initGame(); 
				//vue.setMusicJeu(true);
				vue.setStartGameScale(0.7f);
				ship = new XMLPackedSheet ("data/ship1game.png","data/ship1game.png.xml");
				ship1MoveDown = new Animation();
				for (int i = 1; i<4; i++)
					ship1MoveDown.addFrame(ship.getSprite("v1moveDown"+i+".gif"), 35);
				ship1MoveUp = new Animation();
				for (int i = 1; i<4; i++)
					ship1MoveUp.addFrame(ship.getSprite("v1moveUp"+i+".gif"), 35);
				ship1MoveUp.setLooping(false);
				ship1MoveDown.setLooping(false);

				vue.setShip(ship);
				vue.setShip1MoveDown(ship1MoveDown);
				vue.setShip1MoveUp(ship1MoveUp);
				//				sbg.enterState(Main.GAMESTATE);
				sbg.enterState(Main.HISTORYSTATE);
			}
		}else{
			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		if(insideChar2){
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
				//vue.setMusic(0);
				//vue.initGame(); 
				//vue.setMusicJeu(true);
				ship = new XMLPackedSheet ("data/ship3game.png","data/ship3game.png.xml");
				ship1MoveDown = new Animation();
				for (int i = 1; i<4; i++)
					ship1MoveDown.addFrame(ship.getSprite("v3moveDown"+i+".gif"), 35);
				ship1MoveUp = new Animation();
				for (int i = 1; i<4; i++)
					ship1MoveUp.addFrame(ship.getSprite("v3moveUp"+i+".gif"), 35);
				ship1MoveUp.setLooping(false);
				ship1MoveDown.setLooping(false);

				vue.setShip(ship);
				vue.setShip1MoveDown(ship1MoveDown);
				vue.setShip1MoveUp(ship1MoveUp);
				//				sbg.enterState(Main.GAMESTATE);
				sbg.enterState(Main.HISTORYSTATE);
			}
		}else{


			//if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
			//gc.exit();
		}

		if(insideChar3)
		{
			if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || enterPress){
				//vue.setMusic(0);
				//vue.initGame(); 
				//vue.setMusicJeu(true);
				vue.setStartGameScale(0.7f);
				ship = new XMLPackedSheet ("data/ship2game.png","data/ship2game.png.xml");
				ship1MoveDown = new Animation();
				for (int i = 1; i<4; i++)
					ship1MoveDown.addFrame(ship.getSprite("v2moveDown"+i+".gif"), 35);
				ship1MoveUp = new Animation();
				for (int i = 1; i<4; i++)
					ship1MoveUp.addFrame(ship.getSprite("v2moveUp"+i+".gif"), 35);
				ship1MoveUp.setLooping(false);
				ship1MoveDown.setLooping(false);

				vue.setShip(ship);
				vue.setShip1MoveDown(ship1MoveDown);
				vue.setShip1MoveUp(ship1MoveUp);
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



