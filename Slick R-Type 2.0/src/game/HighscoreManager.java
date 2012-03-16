package game;

import java.util.*;
import java.io.*;

public class HighscoreManager {
	// An arraylist of the type "score" we will use to work with the scores inside the class
	private ArrayList<Score> scores;

	private static HighscoreManager _instance = new HighscoreManager();

	// The name of the file where the highscores will be saved
	private static final String HIGHSCORE_FILE = "scores.dat";

	//Initialising an in and outputStream for working with the file
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public HighscoreManager() {
		//initialising the scores-arraylist
		scores = new ArrayList<Score>();
	}

	public final static HighscoreManager getInstance(){
		return _instance;
	}


	public ArrayList<Score> getScores() {
		loadScoreFile();
		sort();
		return scores;
	}


	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}

	public void addScore(String name, int score) {
		loadScoreFile();
		scores.add(new Score(name, score));
		updateScoreFile();
	}

	public int getMaxScore() {
		loadScoreFile();
		int max = 0;
		for(int i=0;i<scores.size();i++){
			if(max<scores.get(i).getScore())
				max =scores.get(i).getScore();
		}
		return max;

	}

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
				System.out.println("hm score j1 : "+score+"max score : "+scores.get(scores.size()-1).getScore() );
				if(score > scores.get(scores.size()-1).getScore()){
					return true;
				}
			}else{
				return true;
			}
		}

		return false;

	}

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

	public void loadScoreFile() {
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
			highscoreString += (i + 1) + ". " + scores.get(i).getNaam() + "           " + scores.get(i).getScore() + "\n";
			i++;
		}
		return highscoreString;
	}

	public String[] getHighscoreStringTab() {
		int max = 10;
		int i = 0;
		int x = scores.size();
		if (x > max) {
			x = max;
		}

		String highscoreString[] = new String[x];
		for (String s : highscoreString) {
			s = "";
		}

		ArrayList<Score> scores;
		scores = getScores();



		while (i < x) {//Faire en sorte que les espace diminue si le nb de carac dans le score augmente
			highscoreString[i] += (i + 1) + ". " + scores.get(i).getNaam() + "     " + scores.get(i).getScore() + "\n";
			i++;
		}
		return highscoreString;
	}


	public void rest() {
		updateScoreFile(); // si il est pas créé on le crée
		loadScoreFile(); // on le charge
		scores.clear();
		initData();
		updateScoreFile(); // on le met a jour
	}
	
	public void initData() {
		scores.add(new Score("Dispsi", 1000));
		scores.add(new Score("Lala", 700));
		scores.add(new Score("Poe", 500));
		scores.add(new Score("John", 250));
		scores.add(new Score("Marie", 150));
		scores.add(new Score("JavaMachine", 100));
		scores.add(new Score("Le blond", 20));
		scores.add(new Score("Sarko", 0));
	}

	public void restZ() { // ne sert plus a rien
		updateScoreFile(); // si il est pas créé on le crée
		loadScoreFile(); // on le charge
		scores.clear();
		updateScoreFile(); // on le met a jour

	}
}