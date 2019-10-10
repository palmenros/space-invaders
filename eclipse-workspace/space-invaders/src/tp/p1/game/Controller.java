package tp.p1.game;

import java.util.Scanner;

import tp.p1.input.*;


/**
 * Class that represents the controller, the interaction between the game and player
 * @author Martín Gómez y Pedro Palacios
 */
public class Controller {
	
	/**
	 * Game which the player is playing
	 */
	private Game game;
	
	/**
	 *	Scanner from which the user inputs data 
	 */
	private Scanner in;
	
	/**
	 * List of commands that the player can execute
	 */
	private CommandList commandList;
	
	/**
	 * Flag that indicates if the program should exit
	 */
	private boolean shouldExit;
	
	
	/**
	 * Given a game, create a controller to play it
	 * @param game Game to play
	 * @param scanner Scanner where to read from
	 */
	public Controller(Game game, Scanner scanner)
	{
		this.game = game;
		in = scanner;
		
		//Commands that the user can execute
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
	
	/**
	 * Execute the game
	 */
	public void run()
	{	
		shouldExit = false;
		
		game.draw();
		
		while(!shouldExit)
		{	
			String line = in.nextLine();
			
			if(!commandList.tryExecuteLine(line, this) && !shouldExit) {
				System.out.println("Error: Invalid command");				
			}
		}
	}
	
	/**
	 * Function called by commands if successfully executed that tells the game to run another cycle and determines if should exit
	 */
	public void tick()
	{
		GameState state = game.tick();
	
		if(state == GameState.ALIEN_WIN) {
			System.out.println("Aliens win");
			shouldExit = true;
		} else if(state == GameState.PLAYER_WIN) {
			System.out.println("Player win");
			shouldExit = true;
		}		

	}
	
	/**
	 * Close all resources 
	 */
	public void close()
	{
		in.close();
	}
	
	/**
	 * Get command list
	 * @return Command list
	 */
	public CommandList getCommandList()
	{
		return commandList;
	}
	
	/**
	 * Get current game instance
	 * @return Game
	 */
	public Game getGame()
	{
		return game;
	}
		
	/**
	 * Exit the current game 
	 */
	public void exit()
	{
		shouldExit = true;
	}
}
