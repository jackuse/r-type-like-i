package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.Chargement;
import states.Game;
import states.Highscore;
import states.Menu;
import states.Option;
import states.Pause;
import states.Selection;

public class Main extends StateBasedGame {
 
    public static final int MENUSTATE          = 0;
    public static final int OPTIONSTATE        = 1;
    public static final int SELECTIONSTATE     = 2;
    public static final int HIGHSCORESTATE     = 3;
    public static final int GAMESTATE          = 10;
    public static final int PAUSESTATE         = 11;
    public static final int CHARGEMENTSTATE    = 20;
    
    public static int etatprecedent = MENUSTATE;
    
    private Vue vue = Vue.getInstance();
    
 
    public Main()
    {
        super("R-Type Like It !");
 
        this.addState(new Menu(MENUSTATE));
        this.addState(new Game(GAMESTATE));
        this.addState(new Pause(PAUSESTATE));
        this.addState(new Option(OPTIONSTATE));
        this.addState(new Selection(SELECTIONSTATE ));
        this.addState(new Highscore(HIGHSCORESTATE));
        this.addState(new Chargement(CHARGEMENTSTATE));
        this.enterState(MENUSTATE);
        
    }
 
	/**
	 * Fonction principale qui permet de lancer l'application.
	 * @param        args
	 * @throws SlickException 
	 */
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new Main());
         app.setDisplayMode(800, 600, false);
         app.start();
    }
 
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
 
        this.getState(MENUSTATE).init(gameContainer, this);
        vue.setMusic(1);
        this.getState(CHARGEMENTSTATE).init(gameContainer, this);
        this.getState(GAMESTATE).init(gameContainer, this);
        this.getState(PAUSESTATE).init(gameContainer, this);
        this.getState(OPTIONSTATE).init(gameContainer, this);
        this.getState(HIGHSCORESTATE).init(gameContainer, this);
        this.getState(SELECTIONSTATE).init(gameContainer, this);
        
        
        gameContainer.setTargetFrameRate(60);
        gameContainer.setMaximumLogicUpdateInterval(20);
        gameContainer.setMinimumLogicUpdateInterval(20);
    }
}