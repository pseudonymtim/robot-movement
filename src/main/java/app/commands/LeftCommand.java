package app.commands;

import app.envobjects.Direction;
import app.envobjects.Robot;
import app.envobjects.Table;

public class LeftCommand implements Command {

	@Override
	public void execute(Table table) {
		if (table != null && table.isRobotPlaced()) {
			Robot robot = table.getRobot(); 
			robot.setDirection(Direction.turnLeftNinetyDegrees(robot.getDirection()));
		}
	}

	@Override
	public boolean isTerminatingCommand() {
		return false;
	}
	
	public static LeftCommand getInstance() {
		return new LeftCommand();
	}

}
