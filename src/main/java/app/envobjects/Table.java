package app.envobjects;

public class Table {
	private final Position bottomLeftCnr;
	private final Position topRightCnr;
	
	private Robot robot = null;
	
	private Table(Position bottomLeftCnr, Position topRightCnr) {
		this.bottomLeftCnr = bottomLeftCnr;
		this.topRightCnr = topRightCnr;
	}
	
	public Position getBottomLeftCnr() {
		return bottomLeftCnr;
	}

	public Position getTopRightCnr() {
		return topRightCnr;
	}

	public Robot getRobot() {
		return robot;
	}
	
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	public boolean isOnTable(Position position) {
		return bottomLeftCnr.getX() <= position.getX() && position.getX() <= topRightCnr.getX() &&
				bottomLeftCnr.getY() <= position.getY() && position.getY() <= topRightCnr.getY();
	}
	
	public boolean isRobotPlaced() {
		return robot != null;
	}
	
	public static Table getInstance(int width, int height) {
		Table table = null;
		if (width > 0 && height > 0) {
			Position bottomLeftCnr = Position.getInstance(0, 0);
			Position topRightCnr = Position.getInstance(width-1, height-1);
			table = new Table(bottomLeftCnr, topRightCnr);
		}
		return table;
	}
	
	public static Table getInstance(Position corner1, Position corner2) {
		Table table = null;
		if (corner1 != null && corner2 != null) {
			int minX = Math.min(corner1.getX(), corner2.getX());
			int minY = Math.min(corner1.getY(), corner2.getY());
			int maxX = Math.max(corner1.getX(), corner2.getX());
			int maxY = Math.max(corner1.getY(), corner2.getY());
			table = new Table(
					Position.getInstance(minX, minY), 
					Position.getInstance(maxX, maxY));
		}
		return table;
	}
}
