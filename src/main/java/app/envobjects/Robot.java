package app.envobjects;

public class Robot {
	private Position position;
	private Direction direction;
	
	private Robot(Position position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public String toString() {
		return position + "," + direction;
	}
	
	public static Robot getInstance(Position position, Direction direction) {
		if (position != null && direction != null)
			return new Robot(position, direction);
		else
			return null;
	}
}
