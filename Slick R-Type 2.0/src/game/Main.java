package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.Chargement;
import states.Game;
import states.GameOver;
import states.Highscore;
import states.History;
import states.Menu;
import states.Option;
import states.Pause;
import states.Select;
import states.SelectionMusic;

public class Main extends StateBasedGame {
 
    public static final int MENUSTATE          = 0;
    public static final int OPTIONSTATE        = 1;
    //public static final int SELECTIONSTATE     = 2;
    public static final int HIGHSCORESTATE     = 3;
    public static final int SELECTIONMUSICSTATE = 5;
    public static final int GAMESTATE          = 10;
    public static final int PAUSESTATE         = 11;
    public static final int CHARGEMENTSTATE    = 20;
    public static final int GAMEOVERSTATE      = 12;
    public static final int SELECTSTATE		   =  2;
    public static final int HISTORYSTATE	   =  13;
    
    public static int WIDTH    = 800;
    public static int HEIGHT   = 600;
    
    
    public static boolean init = true;
    
    
    public static int etatprecedent = -1;
    
    private Vue vue = Vue.getInstance();
    
    public static AppGameContainer app;
    
 
    public Main()
    {
        super("R-Type Like It !");
        
        
 
        this.addState(new Menu(MENUSTATE));
        this.addState(new Game(GAMESTATE));
        this.addState(new Pause(PAUSESTATE));
        this.addState(new Option(OPTIONSTATE));
        //this.addState(new Selection(SELECTIONSTATE ));
        this.addState(new Highscore(HIGHSCORESTATE));
        this.addState(new Chargement(CHARGEMENTSTATE));
        this.addState(new GameOver(GAMEOVERSTATE));
        this.addState(new SelectionMusic(SELECTIONMUSICSTATE));
        this.addState(new Select(SELECTSTATE));
        this.addState(new History(HISTORYSTATE));
        this.enterState(CHARGEMENTSTATE);
        
        
    }
 
	/**
	 * Fonction principale qui permet de lancer l'application.
	 * @param        args
	 * @throws SlickException 
	 */
    public static void main(String[] args) throws SlickException
    {
         app = new AppGameContainer(new Main());
         app.setDisplayMode(WIDTH, HEIGHT, false);
         app.setIcon("data/icon.png");
         app.start();
         
         
    }
 
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
    	
    	this.getState(CHARGEMENTSTATE).init(gameContainer, this);
    	this.getState(GAMESTATE).init(gameContainer, this);
        this.getState(MENUSTATE).init(gameContainer, this);
        
        this.getState(PAUSESTATE).init(gameContainer, this);
        this.getState(OPTIONSTATE).init(gameContainer, this);
        this.getState(HIGHSCORESTATE).init(gameContainer, this);
        //this.getState(SELECTIONSTATE).init(gameContainer, this);
        this.getState(GAMEOVERSTATE).init(gameContainer, this);
        this.getState(SELECTIONMUSICSTATE).init(gameContainer, this);
        this.getState(SELECTSTATE).init(gameContainer, this);
        this.getState(HISTORYSTATE).init(gameContainer, this);
        
        
        gameContainer.setShowFPS(true);
        gameContainer.setTargetFrameRate(60);
        gameContainer.setMaximumLogicUpdateInterval(20);
        gameContainer.setMinimumLogicUpdateInterval(20);
        vue.setScreen(WIDTH ,HEIGHT,false);
    }
}