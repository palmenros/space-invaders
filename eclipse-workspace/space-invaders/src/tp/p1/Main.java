package tp.p1;

import java.util.Scanner;

import tp.p1.input.*;

public class Main {

	public static void main(String[] args)
	{
		Command[] commands = { 
				new MoveCommand(),
				new ShootCommand(), 
				new SuperpowerCommand(), 
				new ListCommand(), 
				new ResetCommand(), 
				new HelpCommand(), 
				new ExitCommand(), 
				new NoneCommand()
		};
		
		CommandList commandList = new CommandList(commands);
		
		while(true)
		{
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			
			if(!commandList.tryExecuteLine(line))
			{
				System.out.println("Error");
			}
		}
	}
	
}
