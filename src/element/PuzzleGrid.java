package element;

import java.util.List;

import game.Move;

public class PuzzleGrid<T> {

	private List<Tile<T>> tiles;
	private int size;
	private int nullIndex;

	public PuzzleGrid(int size, List<Tile<T>> tiles) {
		this(size, tiles, size * size - 1);
	}

	public PuzzleGrid(int size, List<Tile<T>> tiles, int nullIndex) {
		this.size = size;
		this.tiles = tiles;
		this.nullIndex = nullIndex;
	}

	public int size() {
		return this.size;
	}

	public int getNbTiles() {
		return this.tiles.size();
	}

	public Tile<T> getTile(int i, int j) {
		return this.tiles.get(i % size + j * size);
	}

	public Tile<T> getTile(int i) {
		return tiles.get(i);
	}

	public T getElement(int i, int j) {
		return this.getTile(i, j).getValue();
	}

	public boolean setMove(Move move) {
		switch (move.get()) {
		case Down:
			if (nullIndex + size >= getNbTiles())
				return false;
			swapIndex(nullIndex, nullIndex + size);
			break;
		case Left:
			if (nullIndex % size - 1 < 0)
				return false;
			swapIndex(nullIndex, nullIndex - 1);
			break;
		case Right:
			if (nullIndex % size + 1 >= size)
				return false;
			swapIndex(nullIndex, nullIndex + 1);
			break;
		case Up:
			if (nullIndex - size < 0)
				return false;
			swapIndex(nullIndex, nullIndex - size);
			break;
		default:
			return false;
		}
		return true;
	}

	public int[] getTilesIndexes() {
		int[] indexes = new int[this.getNbTiles()];
		for (int i = 0; i < this.getNbTiles(); i++) {
			indexes[i] = this.tiles.get(i).getSortedPosition();
		}
		return indexes;
	}

	private void swapIndex(int index1, int index2) {
		Tile<T> temp = this.tiles.get(index1);
		this.tiles.set(index1, this.tiles.get(index2));
		this.tiles.set(index2, temp);
		nullIndex = index2;
	}

	private void swapCoord(int i1, int j1, int i2, int j2) {
		Tile<T> temp = this.getTile(i1, j1);
		this.setTile(i1, j1, this.getTile(i2, j2));
		this.setTile(i2, j2, temp);
	}

	private void setTile(int i, int j, Tile<T> newTile) {
		this.tiles.set(i % size + j * size, newTile);
	}

	public int getNullIndex() {
		return nullIndex;
	}

	public boolean isSolved() {
		for (int i = 0; i < this.getNbTiles(); i++) {
			if (this.tiles.get(i).getSortedPosition() != i) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.getNbTiles(); i++) {
			if (i % this.size() == 0) {
				str += "\n";
			}
			str += tiles.get(i).getValue().toString() + "\t";

		}
		return str;
	}
}
