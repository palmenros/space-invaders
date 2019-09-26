package tp.p1.input;

import tp.p1.*;
import tp.p1.game.Controller;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * Represents a command list that can be executed
 */
public class CommandList {

	/**
	 * Array of commands that can be executed
	 */
	private Command[] commandList;
	
	/**
	 * Constructs command list
	 * @param commands List of executable commands
	 */
	public CommandList(Command[] commands)
	{	
		this.commandList = commands;
	}
	
	/**
	 * @param commandLine Line to try to execute
	 * @return Returns true if command could be parsed and executed, false otherwise
	 */
	public boolean tryExecuteLine(String commandLine, Controller controller)
	{

		boolean success = false;
		
		for(int i = 0; i < commandList.length; i++)
		{
			if(commandList[i].tryExecute(commandLine, controller))
			{
				success = true;
				break;
			}
		}
		
		return success;
	}
	
	/**
	 * @return Command list 
	 */
	public Command[] getCommands()
	{
		return commandList;
	}
	
}
