package game;

import java.util.ArrayList;
import java.util.List;

public class Historic {
	
	private List<Move> moves;
	
	public Historic() {
		this.moves = new ArrayList<Move>();
	}
	
	public Move get(int i) {
		return this.moves.get(i);
	}
	
	public int size() {
		return this.moves.size();
	}

}
