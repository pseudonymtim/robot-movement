package app.commands;

import java.io.IOException;
import java.io.Writer;

import app.envobjects.Table;

public class ReportCommand implements Command {
	Writer writer;
	
	private ReportCommand(Writer writer) { 
		this.writer = writer;
	}

	@Override
	public void execute(Table table) {
		String robotState = "";
		if (table != null && table.isRobotPlaced()) {
			try {
				robotState = "\nOutput: " + table.getRobot().toString() + "\n\n";
				writer.append(robotState);
			} 
			catch (IOException e) {
				// TODO: What should I do here
				System.out.println("ERROR: Exception thrown when writing output: " + 
						table.getRobot().toString());
			}
			finally {
				try { writer.flush(); } 
				catch (IOException e) { }
			}
		}
	}

	@Override
	public boolean isTerminatingCommand() {
		return false;
	}
	
	public static ReportCommand getInstance(Writer writer) {
		if (writer != null)
			return new ReportCommand(writer);
		else
			return null;
	}

}
