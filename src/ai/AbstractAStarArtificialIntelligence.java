package ai;

import java.util.List;
import element.PuzzleGrid;
import game.Move;
import utils.HashMapWithCounters;
import utils.Pair;
import utils.PriorityQueueWithCounters;

public abstract class AbstractAStarArtificialIntelligence<T> extends AbstractArtificialIntelligence<T> {
	
	@Override
	public void silentSolve() {
		HashMapWithCounters<GridState<T>, GridState<T>> parent = new HashMapWithCounters<GridState<T>, GridState<T>>();
	  	PriorityQueueWithCounters<GridState<T>> gridStateQueue = new PriorityQueueWithCounters<GridState<T>>();	  	
		GridState<T> currentState = new GridState<T>(this.grid, 0, null);
		gridStateQueue.add(currentState);
	  	
	  	while(!gridStateQueue.isEmpty()) {
	  		GridState<T> polledGridState = gridStateQueue.poll();
 		  		
	  		if(polledGridState.getGrid().isSolved()) {
  				System.out.println("WINNNEEEER");
  				
  				GridState<T> previous = polledGridState;
  				while (parent.containsKey(previous)) {
  					this.history.addHead(previous.getMove());
  					previous = parent.get(previous);
  				}
  				this.stats.add(parent.getStatistics());
  				this.stats.add(gridStateQueue.getStatistics());
  				return;
  			}
	  		
	  		List<Pair<PuzzleGrid<T>, Move>> adjacentsPuzzle = polledGridState.getGrid().getAdjacentPuzzles();

  			
	  		for(Pair<PuzzleGrid<T>, Move> adjPuzzle : adjacentsPuzzle) {
	  			
	  			int adjacentCost = polledGridState.getCost() + 1 + getHeuristic(adjPuzzle.getFirst());

	  			GridState<T> adjacentState = new GridState<T>(adjPuzzle.getFirst(), adjacentCost, adjPuzzle.getSecond());
	  			
	  			if(parent.containsKey(adjacentState)) {
	  				
	  				GridState<T> existingState = parent.get(adjacentState);
	  					
	  				
	  				if(adjacentCost < existingState.getCost()) {
	  					existingState.refreshCost(adjacentCost);
	  					existingState.refreshMove(adjPuzzle.getSecond());

	  					gridStateQueue.add(adjacentState);
	  				}
	  				
	  			} else {
	  				parent.put(adjacentState, polledGridState);
  					gridStateQueue.add(adjacentState);
	  			}
	  		}

	  		this.iterationsNumber++;
	  	}		
	}
	
	public abstract int getHeuristic(PuzzleGrid<T> puzzle);

	

}