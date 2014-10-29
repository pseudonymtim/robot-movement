package app.envobjects;

public class Position {
	private final int x;
	private final int y;
	
	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Position add(Position position) {
		return Position.getInstance(this.x + position.getX(), this.y + position.getY());
	}
	
	public String toString() {
		return x + "," + y;
	}
	
	public static Position getInstance(int x, int y) {
		return new Position(x, y);
	}
	
}
