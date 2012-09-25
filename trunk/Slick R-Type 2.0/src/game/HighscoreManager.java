package game;

import java.util.*;
import java.io.*;

/**
 * Classe HighscoreManager
 * Singleton
 * @author Etienne Grandier-Vazeille
 *
 */
public class HighscoreManager {
	// An arraylist of the type "score" we will use to work with the scores inside the class
	private ArrayList<Score> scores;

	private static HighscoreManager _instance = new HighscoreManager();

	// Le nom du fichier ou les highscores serons sauvegardés
	private static final String HIGHSCORE_FILE = "scores.dat";

	// Initialisation du in et outputStream 
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public HighscoreManager() {
		// Initialisation du tableau des scores
		scores = new ArrayList<Score>();
	}

	public final static HighscoreManager getInstance(){
		return _instance;
	}


	/**
	 * Retourne la liste des scores
	 * @return scores
	 */
	public ArrayList<Score> getScores() {
		loadScoreFile();
		sort();
		return scores;
	}


	/**
	 * Trie les scores à l'aide de la classe ScoreComparator
	 */
	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}

	/**
	 * Ajout un score 
	 * 
	 * @param name
	 * @param score
	 */
	public void addScore(String name, int score) {
		loadScoreFile();
		scores.add(new Score(name, score));
		updateScoreFile();
	}

	/**
	 * Retourne le score maximal
	 * 
	 * @return score maximal
	 */
	public int getMaxScore() {
		loadScoreFile();
		int max = 0;
		for(int i=0;i<scores.size();i++){
			if(max<scores.get(i).getScore())
				max =scores.get(i).getScore();
		}
		return max;

	}

	/**
	 * Permet de savoir si un score est dans le top 10
	 * 
	 * @param score
	 * @return true si le score est dans le top 10 et false sinon
	 */
	public boolean inTopTen(int score) {
		loadScoreFile();
		sort();
		if(scores.size()>=10){

			if(score > scores.get(9).getScore()){
				return true;
			}
		}
		else{
			if(scores.size()>0){
				//System.out.println("hm score j1 : "+score+"max score : "+scores.get(scores.size()-1).getScore() );
				if(score > scores.get(scores.size()-1).getScore()){
					return true;
				}
			}else{
				return true;
			}
		}

		return false;

	}

	/**
	 * Retourne le dernier score du top 10
	 * 
	 * @return score
	 */
	public int getMaxMinScore() {
		loadScoreFile();
		sort();
		int maxMin = 0;
		if(scores.size()<=10)
			maxMin =scores.get(scores.size()-1).getScore();
		else
			maxMin = scores.get(9).getScore();

		return maxMin;

	}

	/**
	 * Charge le fichier de sauvegarde et si il n'existe pas on en crée un avec un jeu de base
	 */
	public void loadScoreFile() {
		if(outputStream == null)
		updateScoreFile();
		try {
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			scores = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[Laad] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Laad] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
		if(scores.size()<1)
			initData();
		
	}


	/**
	 * Enregistrement du fichier de sauvegarde
	 */
	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(scores);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	/**
	 * Retourne le contenu du fichier HighScore sous la forme d'un string
	 * 
	 * @return String
	 */
	public String getHighscoreString() {
		String highscoreString = "";
		int max = 10;

		ArrayList<Score> scores;
		scores = getScores();

		int i = 0;
		int x = scores.size();
		if (x > max) {
			x = max;
		}
		while (i < x) {
			highscoreString += (i + 1) + ". " + scores.get(i).getName() + "           " + scores.get(i).getScore() + "\n";
			i++;
		}
		return highscoreString;
	}

	/**
	 * Retourne le contenu du fichier HighScore sous la forme d'un tableau de string
	 * 
	 * @return String[]
	 */
	public String[] getHighscoreStringTab() {
		int max = 10;
		int i = 0;
		int x = scores.size();
		if (x > max) {
			x = max;
		}
		//System.out.println("size "+x);
		String highscoreString[] = new String[x];
		for (String s : highscoreString) {
			s = "";
		}

		ArrayList<Score> scores;
		scores = getScores();

		
		while (i < x ) {
			highscoreString[i] = " "+(i + 1) + ". " + scores.get(i).getName() + makeMeSpace(scores.get(i).getName().length(),scores.get(i).getScore()) + scores.get(i).getScore() + "\n";
			//System.out.println("add "+i);
			i++;
		}
		//System.out.println("size i "+i);
		if(i == 10){
			//System.out.println("add i "+i);
			i--;
		highscoreString[i] = (i + 1) + ". " + scores.get(i).getName() + makeMeSpace(scores.get(i).getName().length(),scores.get(i).getScore()) + scores.get(i).getScore() + "\n";
		}
		return highscoreString;
	}
	
	/**
	 * Calcule le nombre d'espace necessaire entre le nom et le score pour avoir un affichage aligné
	 * 
	 * @param name
	 * @param score
	 * @return nombre d'espace
	 */
	public String makeMeSpace(int nameSize,int score){
		String sp = "";
		int i = nameSize+String.valueOf(score).length();
		for(;i<23;i++){
			sp += " ";
		}
		return sp;
	}


	/**
	 * Réinitialisation du fichier de sauvegarde
	 */
	public void rest() {
		updateScoreFile(); // si il est pas créé on le crée
		loadScoreFile(); // on le charge
		scores.clear();
		initData();
		updateScoreFile(); // on le met a jour
	}
	
	/**
	 * Mise en place du jeu de donnée de base
	 */
	public void initData() {
		scores.add(new Score("Dispsi", 1000));
		scores.add(new Score("Lala", 700));
		scores.add(new Score("Poe", 500));
		scores.add(new Score("John", 250));
		scores.add(new Score("Marie", 150));
		scores.add(new Score("JavaMachine", 100));
		scores.add(new Score("Le blond", 20));
		scores.add(new Score("Sarko", 0));
//		scores.add(new Score("Sarko", 10));
//		scores.add(new Score("Sarko", 15));
//		scores.add(new Score("Sarko", 18));
	}

	/**
	 * Remise a zero du fichier de sauvegarde
	 */
	public void restZ() { // ne sert plus a rien
		updateScoreFile(); // si il est pas créé on le crée
		loadScoreFile(); // on le charge
		scores.clear();
		updateScoreFile(); // on le met a jour

	}
}