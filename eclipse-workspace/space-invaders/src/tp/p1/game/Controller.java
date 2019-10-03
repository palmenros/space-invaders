package tp.p1.game;

import java.util.Scanner;

import tp.p1.input.*;

public class Controller {
	
	private Game game;
	private Scanner in;
	private CommandList commandList;
	private boolean shouldExit;
	
	public Controller(Game game)
	{
		this.game = game;
		in = new Scanner(System.in);
		
		commandList = new CommandList(new Command[]{ 
				new MoveCommand(),
				new ShootCommand(), 
				new SuperpowerCommand(), 
				new ListCommand(), 
				new ResetCommand(), 
				new HelpCommand(), 
				new ExitCommand(), 
				new NoneCommand()
		});
	}
	
	public void run()
	{	
		shouldExit = false;
		
		game.draw();
		
		while(!shouldExit)
		{	
			String line = in.nextLine();
			
			if(commandList.tryExecuteLine(line, this) && !shouldExit) {
				
				//TODO: CALL FROM COMMANDS Controller.Tick()
				
			} else if(!shouldExit) {
				System.out.println("Error: Invalid command");
			}
		}
	}
	
	public void tick()
	{
		game.computerAction();
		game.update();
		game.draw();		
	}
	
	public void close()
	{
		in.close();
	}
	
	public CommandList getCommandList()
	{
		return commandList;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void exit()
	{
		shouldExit = true;
	}
}
