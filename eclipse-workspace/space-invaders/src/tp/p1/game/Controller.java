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
	 * Flag that indicates if the program should exit
	 */
	private boolean shouldExit;

	/**
	 * Message displayed when user quits
	 */
	private final String gameOverMessage = "Game Over";
	
	/**
	 * Message displayed when user resets game
	 */
	private final String resetGameMessage = "Game Reset!";
	
	/**
	 * Given a game, create a controller to play it
	 * @param game Game to play
	 * @param scanner Scanner where to read from
	 */
	public Controller(Game game, Scanner scanner)
	{
		this.game = game;
		in = scanner;
	}
	
	/**
	 * Execute the game
	 */
	public void run()
	{	
		shouldExit = false;
		
		draw();
		
		while(!shouldExit)
		{	
			String[] words = in.nextLine().toLowerCase().trim().split ("\\s+");
			
			try {
				Command command = CommandGenerator.parseCommand(words);
				
				if(command != null) {
					if(command.execute(game, this)) {
						tick();
					}
				} else {
					throw new UnknownCommandException();
				}
				
			} catch(CommandException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	/**
	 * Function called by commands if successfully executed that tells the game to run another cycle and determines if should exit
	 */
	public void tick()
	{
		GameState state = game.tick();
		draw();
	
		if(state == GameState.ALIEN_WIN) {
			System.out.println("Aliens win");
			shouldExit = true;
		} else if(state == GameState.PLAYER_WIN) {
			System.out.println("Player win");
			shouldExit = true;
		}		

	}
	
	/**
	 * Draw game on screen
	 */
	public void draw()
	{
		System.out.println(game);
	}
	
	/**
	 * Close all resources 
	 */
	public void close()
	{
		in.close();
	}
		
	/**
	 * Exit the current game 
	 */
	public void exit()
	{
		System.out.println(gameOverMessage);
		shouldExit = true;
	}

	/**
	 * Reset game
	 */
	public void resetGame() {
		System.out.println(resetGameMessage);
		game.reset();
		draw();
	}

	/**
	 * Display list of ship stats
	 * @param strings List of strings to list
	 */
	public void displayShipList(String[] strings) {
		for(String s : strings) {
			System.out.println(s);
		}
	}

	/**
	 * Displays command help
	 * @param commandHelp Help string
	 */
	public void displayCommandHelp(String commandHelp) {
		System.out.println(commandHelp);
	}
}
