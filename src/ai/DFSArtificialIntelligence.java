package ai;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import element.PuzzleGrid;
import game.History;
import game.Move;

public class DFSArtificialIntelligence<T> extends AbstractArtificialIntelligence<T> {

	@Override
	public void silentSolve() {
		HashMap<PuzzleGrid<T>, PuzzleGrid<T>> history = new HashMap<PuzzleGrid<T>, PuzzleGrid<T>>();
		LinkedList<PuzzleGrid<T>> puzzleQueue = new LinkedList<PuzzleGrid<T>>();
		// counter (other counters should be implemented : add/remove of each
		// strucutres + max size)
		int iterationsNumber = 0;

		while (!puzzleQueue.isEmpty()) {
			PuzzleGrid<T> poppedPuzzle = puzzleQueue.pop();
			if (poppedPuzzle.isSolved()) {
				// done
				PuzzleGrid<T> searchPuzzlePredecessor = poppedPuzzle;
				// make history
				while (history.containsKey(searchPuzzlePredecessor)) {
					searchPuzzlePredecessor = history.get(searchPuzzlePredecessor);
					System.out.println(searchPuzzlePredecessor);
				}
				return;
			}

			List<PuzzleGrid<T>> adjacentsPuzzle = poppedPuzzle.getAdjacentPuzzles();
			for (PuzzleGrid<T> adjPuzzle : adjacentsPuzzle) {
				if (!history.containsKey(adjPuzzle)) {
					history.put(adjPuzzle, poppedPuzzle);
					puzzleQueue.add(adjPuzzle);
				}
			}
			iterationsNumber++;
		}
	}

	@Override
	public String toString() {
		return "DFS Solver";
	}
}
