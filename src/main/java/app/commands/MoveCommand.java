package app.commands;

import app.envobjects.Position;
import app.envobjects.Robot;
import app.envobjects.Table;

public class MoveCommand implements Command {

	private MoveCommand() { }
	
	@Override
	public void execute(Table table) {
		if (table != null && table.isRobotPlaced()) {
			Robot robot = table.getRobot();
			
			Position currentPos = robot.getPosition();
			Position relativeMovement = robot.getDirection().getUnitMovement();
			Position newPosition = currentPos.add(relativeMovement);
			
			if (table.isOnTable(newPosition))
				robot.setPosition(newPosition);
		}
	}

	@Override
	public boolean isTerminatingCommand() {
		return false;
	}
	
	public static MoveCommand getInstance() {
		return new MoveCommand();
	}

}
