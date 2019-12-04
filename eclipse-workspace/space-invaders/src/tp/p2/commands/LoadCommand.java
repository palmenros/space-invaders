package tp.p2.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tp.p2.controller.Controller;
import tp.p2.exceptions.CommandExecuteException;
import tp.p2.exceptions.FileContentsException;
import tp.p2.exceptions.IncorrectArgumentFormatException;
import tp.p2.exceptions.IncorrectArgumentNumberException;
import tp.p2.game.IPlayerController;
import tp.p2.view.Serializer;

public class LoadCommand extends Command {

	private String fileName;
	
	/**
	 * Constructs the key and the command of load using the third letter as key
	 */
	public LoadCommand() {
		super("load", "a", "load [filename]", "Load game state from file.");
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
		 try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName + ".dat"))) {
			 //Read the header
			 String line = bufferedReader.readLine();
			 if(line == null || !line.equals( Serializer.getHeader() )) {
				 throw new FileContentsException("Header not recognized");
			 }
			 
			 line = bufferedReader.readLine();
			 if(line == null || !line.contentEquals("")) {
				 throw new FileContentsException("Header not recognized");				 
			 }
			 
			 controller.loadGame(bufferedReader);
			 controller.displayText("Game successfully loaded from file \"" + fileName + ".dat\".");
			 controller.draw();
			 return false;
		 } catch (IOException e) {
			throw new CommandExecuteException("Failed to load game", e);
		 } catch (FileContentsException e) {
			 throw new CommandExecuteException("Failed to load game", e);
		 } catch (NumberFormatException e) {
			 throw new CommandExecuteException("Failed to load game", e);
		 }
	}

}
