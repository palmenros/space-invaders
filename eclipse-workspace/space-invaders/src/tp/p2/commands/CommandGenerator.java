package tp.p2.commands;

import tp.p2.exceptions.IncorrectArgumentFormatException;
import tp.p2.exceptions.IncorrectArgumentNumberException;

/**
 * Represents a command list that can be executed
 * @author Martín Gómez y Pedro Palacios
 */
public class CommandGenerator {

	/**
	 * List of available commands
	 */
	private static Command[] availableCommands = {
			new MoveCommand(),
			new ShootCommand(), 
			new SuperpowerCommand(), 
			new ListCommand(),
			new ListPrintersCommand(),
			new BuyCommand(),
			new StringifyCommand(),
			new ResetCommand(), 
			new HelpCommand(), 
			new ExitCommand(), 
			new NoneCommand()
	};	
	
	/**
	 * Tries to parse words array into a command
	 * @param words Words to parse
	 * @return Command if found one that parses, null otherwise
	 * @throws IncorrectArgumentNumberException
	 * @throws IncorrectArgumentFormatException
	 */
	public static Command parseCommand(String[] words) throws IncorrectArgumentNumberException, IncorrectArgumentFormatException
	{
		int i = 0; 
		while(i < availableCommands.length)
		{
			Command command = availableCommands[i].parse(words);
			if(command != null) {
				return command;
			}
			
			i++;
		}
		
		return null;
	}
	
	public static String commandHelp()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Command command : availableCommands)
		{
			stringBuilder.append(command.getHelpText() + "\n");
		}
		
		return stringBuilder.toString();
	}	
}
