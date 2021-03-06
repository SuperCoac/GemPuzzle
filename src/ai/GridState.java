package ai;

import element.PuzzleGrid;
import game.Move;

public class GridState<T> implements Comparable<GridState<T>> {
	private PuzzleGrid<T> grid;
	private int cost;
	private Move move;
	
	public GridState(PuzzleGrid<T> grid, int cost, Move move) {
		this.grid = grid;
		this.cost = cost;
		this.move = move;
	}
	
	public void refreshCost(int cost) {
		this.cost = cost;
	}
	
	public void refreshMove(Move move) {
		this.move = move;
	}
	
	public Move getMove() {
		return this.move;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public PuzzleGrid<T> getGrid() {
		return this.grid;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof GridState)) {
			return false;
		}
		
		return ((GridState<T>) obj).getGrid().equals(this.grid);
	}

	@Override
	public int compareTo(GridState<T> grid) {
		if(this.cost > grid.getCost()) {
			return 1;
		} else if (this.cost < grid.getCost()) {
			return -1;
		}
		
		return 0;
	}
}
