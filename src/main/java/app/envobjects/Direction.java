package app.envobjects;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
	NORTH(0,1), EAST(1,0), SOUTH(0,-1), WEST(0,-1);
	
	public Position unitMovement; 
	
	private Direction(int x, int y) {
		unitMovement = Position.getInstance(x, y);
	}
	
	public Position getUnitMovement() {
		return unitMovement;
	}
	
	private static final Map<String, Direction> lookup = new HashMap<String, Direction>();
	
	static {
		for (Direction value : Direction.values()) {
			lookup.put(value.toString(), value);
		}
	}
	
	public static Direction getInstance(String direction) {
		return lookup.get(direction);
	}
	
	public static Direction turnLeftNinetyDegrees(Direction initialDirection) {
		switch (initialDirection) {
			case NORTH:
				return Direction.WEST;
			case EAST:
				return Direction.NORTH;
			case SOUTH:
				return Direction.EAST;
			case WEST:
				return Direction.SOUTH;
			default:
				return null;
		}
	}
	
	public static Direction turnRightNinetyDegrees(Direction initialDirection) {
		switch (initialDirection) {
			case NORTH:
				return Direction.EAST;
			case EAST:
				return Direction.SOUTH;
			case SOUTH:
				return Direction.WEST;
			case WEST:
				return Direction.NORTH;
			default:
				return null;
		}
	}
}
