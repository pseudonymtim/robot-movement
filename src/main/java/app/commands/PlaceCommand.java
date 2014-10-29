package app.commands;

import app.envobjects.Direction;
import app.envobjects.Position;
import app.envobjects.Robot;
import app.envobjects.Table;

/**
 * Command to create a robot and place it on the table
 */
public class PlaceCommand implements Command {
	
	public static final String ARG_DELIMITER = ",";

	private final Position position;
	private final Direction direction;
	
	private PlaceCommand(Position position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}
	
	@Override
	public void execute(Table table) {
		if (table != null && table.isOnTable(position)) {
			Robot robot = null;
			if (table.isRobotPlaced()) {
				robot = table.getRobot();
				robot.setPosition(position);
				robot.setDirection(direction);
			}
			else {
				robot = Robot.getInstance(position, direction);
				table.setRobot(robot);
			}
		}
	}

	@Override
	public boolean isTerminatingCommand() {
		return false;
	}

	public static PlaceCommand getInstance(String args, String argDelim) {
		PlaceCommand placeCommand = null;
		
		if (args != null && argDelim != null && !args.isEmpty()) {
			String arguments[] = args.trim().split(argDelim);
			
			if (arguments.length == 3) {
				int x = Integer.parseInt(arguments[0]);
				int y = Integer.parseInt(arguments[1]);

				Direction d = Direction.getInstance(arguments[2]);
				Position p = Position.getInstance(x, y);
				
				placeCommand = new PlaceCommand(p, d);
			}
			else {
				// incorrect number of args passed in
			}
		}
		else {
			// no args or delimter passed in - cannot get args
		}
		
		return placeCommand;
	}
}
