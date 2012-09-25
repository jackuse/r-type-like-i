package game;

import java.util.Comparator;

/**
 * Classe ScoreComparator
 * @author Etienne Grandier-Vazeille
 * 
 * Permet de comparer les scores en utilisant l'interface comparator
 *
 */
public class ScoreComparator implements Comparator<Score> {
        public int compare(Score score1, Score score2) {

            int sc1 = score1.getScore();
            int sc2 = score2.getScore();

            if (sc1 > sc2){
                return -1;
            }else if (sc1 < sc2){
                return +1;
            }else{
                return 0;
            }
        }
}