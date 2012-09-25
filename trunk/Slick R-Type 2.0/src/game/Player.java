package game;

import java.util.*;


/**
 * Class Player
 * Multiton
 * @author Etienne Grandier-Vazeille
 *
 */
public class Player {

  //
  // Fields
  //

  private int score;
  private PlayerShip ship;
  private int life;
  private int kill;
  private int totalKill;
  private int time;
  private int totalTime;
  private int level;
  private static final Map<Object, Player> instances = new HashMap<Object, Player>();
  
  //
  // Constructors
  //
  private Player (int id) { 
	  score = 0;
	  life = 3;
	  kill = 0;
	  ship = new PlayerShip(id);
	  
  };
  
  public static Player getInstance(int key) {
      synchronized (instances) {

          // On cherche si le joueur de clé key existe
    	  Player instance = instances.get(key);

          if (instance == null) { // Si il n'existe pas on le crée

              // Création de l'instance
              instance = new Player(key);

              // Ajout a la map  
              instances.put(key, instance);
          }

          return instance;
      }
  }
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of score
   * @param newVar the new value of score
   */
  public void setScore ( int newVar ) {
    score = newVar;
  }

  /**
   * Get the value of score
   * @return the value of score
   */
  public int getScore ( ) {
    return score;
  }
  
  /**
   * Get the value of score
   * @return the value of score
   */
  public PlayerShip getShip ( ) {
	  return ship;
  }

/**
 * @return life
 */
public int getLife() {
	return life;
}

/**
 * @param life
 */
public void setLife(int l) {
	life = l;
	
}

/**
 * @return kill
 */
public int getKill() {
	return kill;
}


/**
 * @return totalKill
 */
public int getTotalKill(){
	return totalKill;
}


/**
 * Incermente le nombre d'ennemi tués
 */
public void incKill() {
	kill++;
	totalKill++;
}


/**
 * Réinitialise le nombre d'ennemi tués dans la partie (statistique)
 */
public void restKill() {
	kill = 0;
}

/**
 * Réinitialise le nombre total d'ennemi tués (statistique)
 */
public void restTotalKill() {
	kill = 0;
}

/**
 * Réinitialise le joueur
 */
public void rest() {
	score = 0;
	life = 3;
	restKill();
	restTotalKill();
	ship.rest();
	time = 0;
}


/**
 * Permet le préparé le joueur au prochain niveaux
 */
public void prepare() {
	restKill();
	ship.rest();
	time = 0;
	//setLife(100); A voire
}

/**
 * Change le timer du joueur
 * @param t
 */
public void setTime(int t) {
	time = t;
}

/**
 * Retourne le timer du joueur
 * @return  time
 */
public int getTime() {
	return time;
}

/**
 * Retourne le niveaux dans lequel est le joueur
 * @return level
 */
public int getLevel() {
	return level;
}

/**
 * Change le niveaux dans lequel est le joueur
 * @param level
 */
public void setLevel(int level) {
	this.level = level;
}

  //
  // Other methods
  //

}
