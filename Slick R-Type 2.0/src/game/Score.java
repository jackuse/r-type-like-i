package game;

import java.io.Serializable;

/**
 * Classe Score
 * @author Etienne Grandier-Vazeille
 * 
 * Structure de donnée permettent de stocker le score du joueur pour les HighScores
 *
 */
public class Score  implements Serializable {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }
}
