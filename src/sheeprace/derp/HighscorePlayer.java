package sheeprace.derp;

/**
 * Contains names and scores of the players on the highscore list and contains an comparator to sort the list
 *
 */

public class HighscorePlayer implements Comparable<HighscorePlayer>{
	
	private String name;
	private int score;
	
	public HighscorePlayer(String name, int score){
		this.name = name;
		this.score = score;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public int compareTo(HighscorePlayer hsp) {
		if(hsp.getScore() == this.score)
			return 0;
		else if(hsp.getScore()> this.score)
			return 1;
		else
			return -1;
	}
	@Override
	public String toString(){
		return ("Highscorespiller "+name+" med score "+score);
	}

}
