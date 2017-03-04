package net.janhorak.game.ne.basic;

/**
 *
 * @author Jan
 */
public class Score {
    
    private int score;
    private final int INCREMENT;

    public Score(int score, int increment) {
        this.score = score;
        this.INCREMENT = increment;
    }

    public int get() {
        return score;
    }
    
    public void addPoints(){
        this.score += INCREMENT;
    }
    
    public void addPointsByValue(int val){
        this.score += val;
    }
    
    public void reducePointsByValue(int val){
        this.score -= val;
    }
    
    public void reducePoints(){
        this.score -= INCREMENT;
    }
    
}
