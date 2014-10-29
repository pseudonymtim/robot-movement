package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;

import app.commands.Command;
import app.commands.LeftCommand;
import app.commands.MoveCommand;
import app.commands.PlaceCommand;
import app.commands.ReportCommand;
import app.commands.RightCommand;
import app.envobjects.Table;

public class RobotMovement {
	
	public static void main(String[] args) {
		final int tableWidth = 5;
		final int tableHeight = 5;
		
		// If reading and writing to files 
		File inputFile = null;
		File outputFile = null;
		FileReader fileReader = null;
		FileWriter fileWriter = null;
		
		// If reading and writing from standard in/out
		InputStreamReader inputStreamReader = null;
		PrintWriter printWriter = null;
		
		// Generic readers and writers
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			if (args == null || args.length == 0) {
				// read commands from and write output to standard in/out
				inputStreamReader = new InputStreamReader(System.in);
				bufferedReader = new BufferedReader(inputStreamReader);
				printWriter = new PrintWriter(System.out);
				bufferedWriter = new BufferedWriter(printWriter);
			}
			
			if (args.length >= 1) {
				// read commands from a file
				inputFile = getFile(args[0], false);
				fileReader = new FileReader(inputFile);
				bufferedReader = new BufferedReader(fileReader);
			}
			
			if (args.length >= 2) {
				// write output to a file
				inputFile = getFile(args[1], true);
				fileWriter = new FileWriter(outputFile);
				bufferedWriter = new BufferedWriter(bufferedWriter);
			}
			
			Table table = Table.getInstance(tableWidth, tableHeight);
			
			if (table == null) {
				System.out.println("ERROR: Could not create table of width [" + tableWidth + 
						"] and height [" + tableHeight + "]. Program will exit.");
			}
			else {
				RobotMovement rm = RobotMovement.getInstance(table, bufferedReader, bufferedWriter);
				if (rm != null)
					rm.process();
			}
		}
		catch (FileNotFoundException e1) {
			System.out.println("ERROR: FileNotFoundException caught during creation of input and output channels. " +
					"Application will exit. Message is: " + e1.getMessage());
		}
		catch (IOException e2) {
			System.out.println("ERROR: IOException caught during creation of input and output channels. " +
					"Application will exit. Message is: " + e2.getMessage());
		}
		finally {
			// Close resources
			
			if (bufferedReader != null) {
				try { bufferedReader.close(); } catch (IOException e) { }
			}
			
			if (bufferedWriter != null) {
				try { bufferedWriter.flush(); } catch (IOException e) { }
				try { bufferedWriter.close(); } catch (IOException e) { }
			}
			
			if (inputStreamReader != null) {
				try { inputStreamReader.close(); } catch (IOException e) { }
			}
			
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
			
			if (fileReader != null) {
				try { fileReader.close(); } catch (IOException e) { }
			}
			
			if (fileWriter != null) {
				try { fileWriter.flush(); } catch (IOException e) { }
				try { fileWriter.close(); } catch (IOException e) { }
			}
		}
	}
	
	/** Returns null if there is a problem */
	private static File getFile(String filePath, boolean createFile) {
		File file = new File(filePath);
		if (file.exists()) {
			return file;
		}
		else {
			if (createFile) {
				try {
					file.createNewFile();
					return file;
				} catch (IOException e) {
					return null;
				}
			}
			else { 
				return null;
			}
		}
	}
	
	private static final String ARG_DELIMITER = ",";
	
	private final Table table;
	private final BufferedReader reader;
	private final BufferedWriter writer;
	
	private RobotMovement(Table table, BufferedReader reader, BufferedWriter writer) {
		this.reader = reader;
		this.writer = writer;
		this.table = table;
	}
	
	public void process() {
		boolean stopProcessing = false;
		Command command = null;
		
		while (!stopProcessing) {
			try {
				command = getCommand(reader.readLine(), writer);
				if (command != null) {
					command.execute(table);
					stopProcessing = command.isTerminatingCommand();
				}
			}
			catch (IOException e) {
				// Could not read command - ignore and move on to next command
			}
		}
	}
	
	private Command getCommand(String commandStr, Writer writer) {
		Command command = null;
		
		if (commandStr != null && !commandStr.isEmpty()) {
			String[] cmdAndArgs = commandStr.split(" ");
			
			if ("LEFT".equalsIgnoreCase(cmdAndArgs[0])) {
				command = LeftCommand.getInstance();
			}
			else if ("MOVE".equalsIgnoreCase(cmdAndArgs[0])) {
				command = MoveCommand.getInstance();
			}
			else if ("PLACE".equalsIgnoreCase(cmdAndArgs[0]) && cmdAndArgs.length >= 2) {
				command = PlaceCommand.getInstance(cmdAndArgs[1], ARG_DELIMITER);
			}
			else if ("REPORT".equalsIgnoreCase(cmdAndArgs[0])) {
				command = ReportCommand.getInstance(writer);
			}
			else if ("RIGHT".equalsIgnoreCase(cmdAndArgs[0])) {
				command = RightCommand.getInstance();
			}
			else {
				// Not a valid command - ignore
			}
		}
		
		return command;
	}
	
	public static RobotMovement getInstance(Table table, BufferedReader reader, BufferedWriter writer) {
		
		if (table != null && reader != null && writer != null)
			return new RobotMovement(table, reader, writer);
		else 
			return null;
	}
	
}
