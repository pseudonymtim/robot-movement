package app.commands;

import app.envobjects.Direction;
import app.envobjects.Table;

public class RightCommand implements Command {

	@Override
	public void execute(Table table) {
		if (table != null && table.getRobot() != null) {
			table.getRobot().setDirection(
					Direction.turnRightNinetyDegrees(table.getRobot().getDirection()));
		}
	}

	@Override
	public boolean isTerminatingCommand() {
		return false;
	}
	
	public static RightCommand getInstance() {
		return new RightCommand();
	}

}
