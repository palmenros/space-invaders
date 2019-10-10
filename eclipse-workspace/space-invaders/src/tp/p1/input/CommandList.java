package tp.p1.input;

import tp.p1.game.Controller;

/**
 * Represents a command list that can be executed
 * @author Martín Gómez y Pedro Palacios
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
	 * @param controller Controller
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
