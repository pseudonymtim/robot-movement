package app.commands;

import app.envobjects.Table;

public interface Command {

	public void execute(Table table);
	public boolean isTerminatingCommand();
	
}
