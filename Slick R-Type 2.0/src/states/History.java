package states;

import game.Main;
import game.Vue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class History extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();
	private UnicodeFont font;
	String text = "";
	int cpt = 0;
	boolean textDone[];
	//String textaff = "Dans une contré loingtaine trés lointaine des truc ignoble se passe et \n ce texte n'a aucun sens ";
	String textaff = "Dans les années 45 la France se lance secrètement dans un projet aérospatial\n" +
			"TOP SECRET appelé le projet R-Type\n" +
			"Elle lance un homme pour la première fois sur la lune en 61 soit 8 ans avant " +
			"les Américains.\n" +
			"En 2012 la branche Torchwood du projet R-type a déclaré une guerre intergalactique\n" +
			"à cause une tasse de café mal placée ... \n\n" +
			"Au secours [Votre nom ici] vous êtes notre seul espoir...";
	//	String textaff = "Elle lance un homme pour la premier fois sur la lune en 61 soit 8 ans avant les americains";
	//	String textaff = "En 2012 la branche Torchwood du projet R-type a déclaré une guerre intergalactique à cause une tasse de café mal placée ... ";
	//	String textaff = "Au secours [Votre nom ici] vous êtes notre seul espoir...";
	private int delay=50;
	boolean speedUp=false;
	private int delayC = 200;
	private boolean ok = false;

	public History(int stateID) {
		this.stateID = stateID;
	}
	public void enter(GameContainer gc, StateBasedGame sbg) {
		gc.getInput().clearKeyPressedRecord();
		vue.selectMusic(6);
		vue.setMusic(1);
		
//		if(textDone[truc.getLevel()]){
//			sbg.enterState(Main.GAMESTATE);
//			rest();
//		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame gr)
			throws SlickException {
		// TODO Auto-generated method stub
		try {
			//font = new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
			font = new UnicodeFont("data/Starjedi.ttf", 30,false,false);
			//uFont = new UnicodeFont(font, 20, false, false);
			font.getEffects().add(new ColorEffect());
			font.addAsciiGlyphs();
			font.loadGlyphs();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textDone = new boolean[10];
		for (boolean t : textDone) {
			t=false;
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		gr.setColor(new Color (0,0,0));
		float width=800;
		float height=600;
		gr.fillRect(0,0,width,height); //fond
		gr.setColor(new Color (1.0f,1.0f,1.0f));
		font.drawString(0, 0, text, new Color(0,0,0));
		//System.out.println(text);
		gr.drawString(text, 10, 20);
		if(ok)
			gr.drawString("Presse space to pass", 300, 500);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		delayC -=20;
		if(delayC < 0){
			if(ok)
				ok = false;
			else
				ok = true;
			delayC = 400;
		}
		//System.out.println("delay "+delay+" speedUp "+speedUp+"text aff "+text.length() );
		delay-=20;
		if(delay <0 || speedUp){
			if(speedUp){
				for(int i=0;i<4;i++){
					if(text.length()<textaff.length()){
						text+=textaff.charAt(cpt);
						cpt++;
					}
				}

			}
			if(text.length()<textaff.length()){
				text+=textaff.charAt(cpt);
				cpt++;
			}
			delay = 50;
		}

		Input input = gc.getInput(); // On récupére les input


		if(text.length()<textaff.length()){
			if (input.isKeyDown(Input.KEY_SPACE)){
				speedUp = true;
			}else{
				speedUp = false;
			}
		}
		else if(speedUp ==  false){
			if (input.isKeyPressed(Input.KEY_SPACE)){
				rest();
				sbg.enterState(Main.GAMESTATE);
				input.enableKeyRepeat();
			}

			//vue.setMusic(1);
		}
		if (!input.isKeyDown(Input.KEY_SPACE)){
			speedUp = false;
			input.disableKeyRepeat();
			input.clearKeyPressedRecord();
		}                

		//System.out.println("speedUp "+speedUp);
//		if (input.isKeyPressed(Input.KEY_V)){
//			cpt = 0;
//			text="";
//		}


	}
	
	public void rest() {
		cpt = 0;
		delay = 50;
		text="";
		delayC = 200;
		speedUp = false;
	}

	@Override
	public int getID() {
		return stateID;
	}

}
