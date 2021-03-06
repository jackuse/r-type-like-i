package states;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import game.HighscoreManager;
import game.IOManager;
import game.Player;
import game.Main;
import game.View;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState{
	int stateID = -1;
	private View view = View.getInstance();
	private IOManager io = IOManager.getInstance();

	int GmOvX = (int) (view.getWidth()/2.2);
	int GmOvY = (int) (view.getHeight()/2.7);
	boolean ok = true;
	int delay = 200;
	private int[] param;
	Player j1;
	String chaine;
	HighscoreManager hm = HighscoreManager.getInstance();
	private int top;
	private int best;
	private String name = "";
	private int maxLevel = view.getMaxLevel();

	public GameOver(int stateID) {
		this.stateID = stateID;
	}

	public void enter(GameContainer gc, StateBasedGame sgb) {
		gc.getInput().clearKeyPressedRecord();
		j1=Player.getInstance(1);
		top = 0;
		best = 0;
		//System.out.println("score "+j1.getScore());
		if(hm.inTopTen(j1.getScore())){
			top = 1;
		}
		if(j1.getScore()>hm.getMaxScore()){
			//System.out.println("score j : "+j1.getScore()+"max score : "+hm.getMaxScore() );
			best = 1;
		}

		param[0]=j1.getScore();
		param[1]=top;
		param[2]=best;
		param[3]=j1.getTime();
		if(j1.getLife()<1)
			param[4]=1;
		else
			param[4]=0;
		param[5]=j1.getLevel();

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		param =new int[10];

	}

	public int getParam(int i) {
		return param[i];
	}

	public void setParam(int i, int p) {
		this.param[i] = p;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		view.renderGameOver(gc,gr,GmOvX,GmOvY,ok,param,name);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if (delta ==0) // corection de la disparition de delta
			delta = 20;
		delay-=delta;//a modifier
		if(delay < 0){
			if(ok)
				ok = false;
			else
				ok = true;
			delay = 200;
		}
		view.selectMusic(0);


		Input input = gc.getInput(); // On r�cup�re les input



		if(param[4] == 1){ // si le jeu est fini
			if(top==1){

				name = io.getKeyString(input, name,19);

				if (input.isKeyPressed(input.KEY_ENTER)){
					hm.addScore(name, j1.getScore());
					sbg.enterState(Main.MENUSTATE);
					j1.rest();
				}
			}else if (input.isKeyPressed(Input.KEY_SPACE)){
				sbg.enterState(Main.MENUSTATE);
				j1.rest();
			}

		}else{
			if (input.isKeyPressed(Input.KEY_SPACE)){
				sbg.enterState(Main.GAMESTATE);
			}
			
			if (input.isKeyPressed(Input.KEY_ESCAPE)){
				param[4] = 1;
			}
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
