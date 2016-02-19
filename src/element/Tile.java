package element;

public class Tile<T extends Comparable<T>> implements Comparable<Tile<T>> {
	private T value;
	private int sortedPosition;
	
	public Tile(T value, int sortedPosition) {
		this.value = value;
		this.sortedPosition = sortedPosition;
	}
	public T getValue() {
		return value;
	}
	
	/**
	 * @deprecated
	 */
	public int getGoalIndex() {
		return this.sortedPosition;
	}
	
	public int getSortedPosition() {
		return this.sortedPosition;
	}
	
	public void setSortedPosition(int sortedPosition) {
		this.sortedPosition = sortedPosition;
	}
	
	@Override
	public int compareTo(Tile<T> o) {
		return this.value.compareTo(o.getValue());
	}
}
