package states;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import game.Chara;
import game.HighscoreManager;
import game.Joueur;
import game.Main;
import game.Vue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();

	int GmOvX = 250;
	int GmOvY = 230;
	boolean ok = true;
	int delay = 200;
	private int[] param;
	Joueur j1;
	String chaine;
	HighscoreManager hm = HighscoreManager.getInstance();
	private int top;
	private int best;
	private String name = "";

	public GameOver(int stateID) {
		this.stateID = stateID;
	}

	public void enter(GameContainer gc, StateBasedGame sgb) {
		gc.getInput().clearKeyPressedRecord();
		j1 = Joueur.getInstance(1);
		//chaine="";
		/*lecture du fichier texte	
				try{
					InputStream ips=new FileInputStream("/score.txt"); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						System.out.println(ligne);
						chaine+=ligne+"\n";
					}
					br.close(); 
				}		
				catch (Exception e){
					System.out.println(e.toString());
				}
		// */
		top = 0;
		best = 0;
		if(hm.inTopTen(j1.getScore())){
			top = 1;
		}
		if(j1.getScore()>hm.getMaxScore()){
			System.out.println("score j : "+j1.getScore()+"max score : "+hm.getMaxScore() );
			best = 1;
		}

		param[0]=j1.getScore();
		param[1]=top;
		param[2]=best;

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

		vue.renderGameOver(gc,gr,GmOvX,GmOvY,ok,param,name);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		delay-=delta;//a modifier
		if(delay < 0){
			if(ok)
				ok = false;
			else
				ok = true;
			delay = 200;
		}
		vue.selectMusic(0);


		Input input = gc.getInput(); // On récupére les input




		if(top==1){
			
			if (input.isKeyPressed(input.KEY_BACK)){
				if(name.length()>0)
					name =name.substring(0, name.length()-1);
			}
			char c = Pause.getChar(input);
			if(c != '0' && name.length() < 42){;
				name+=c;
			}
			
			if (input.isKeyPressed(input.KEY_ENTER)){
				hm.addScore(name, j1.getScore());
				sbg.enterState(Main.MENUSTATE);
				j1.rest();
			}
		}else if (input.isKeyPressed(Input.KEY_SPACE)){

			/*PrintWriter ecrivain = null;
		    int n = 5;

		    try {
				ecrivain =  new PrintWriter(new BufferedWriter
				   (new FileWriter("score.txt")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   // ecrivain.println (chaine);
		    ecrivain.println("Score");
		    ecrivain.println("name : "+j1.getScore());
		    ecrivain.close();*/
			sbg.enterState(Main.MENUSTATE);
			j1.rest();
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
