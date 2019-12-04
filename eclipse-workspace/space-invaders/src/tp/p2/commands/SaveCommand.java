package tp.p2.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tp.p2.controller.Controller;
import tp.p2.exceptions.CommandExecuteException;
import tp.p2.exceptions.IncorrectArgumentFormatException;
import tp.p2.exceptions.IncorrectArgumentNumberException;
import tp.p2.game.IPlayerController;

public class SaveCommand extends Command {

	private String fileName;
	
	/**
	 * Constructs the key and the command of move using the first letter as key
	 */
	public SaveCommand() {
		super("save", "v", "save [filename]", "Save game state to file.");
	}
	
	@Override
	public Command parse(String[] words) throws IncorrectArgumentNumberException, IncorrectArgumentFormatException
	{	
		//Check the name of the command
		if( words.length < 1 || !words[0].equals(name) && !words[0].equals(shortcut)) {
			return null;
		}
		//It is required that the filename has no spaces
		if(words.length != 2) {
			throw new IncorrectArgumentNumberException(2);
		}
		
		fileName = words[1];
		
		return this;
	}
	
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) throws CommandExecuteException
	{	
		 try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName + ".dat"))) {
			 bufferedWriter.append(controller.getStringified());
			 controller.displayText("Game successfully saved in file \"" + fileName + ".dat\". Use the load command to reload it");
			 return false;
		 } catch (IOException e) {
				throw new CommandExecuteException("Failed to save game", e);			
		 }
	}

}
