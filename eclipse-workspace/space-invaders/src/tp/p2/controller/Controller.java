package tp.p2.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import tp.p2.commands.Command;
import tp.p2.commands.CommandGenerator;
import tp.p2.exceptions.*;
import tp.p2.game.Game;
import tp.p2.game.GameState;
import tp.p2.view.GamePrinter;
import tp.p2.view.PrinterTypes;


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
	
	private GamePrinter printer = PrinterTypes.BOARDPRINTER.getObject();
	
	/**
	 * Given a game, create a controller to play it
	 * @param game Game to play
	 * @param scanner Scanner where to read from
	 */
	public Controller(Game game, Scanner scanner)
	{
		this.game = game;
		in = scanner;
		printer.setGame(game);
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
		System.out.println(printer);
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

	public void displayText(String text) {
		System.out.println(text);
	}

	public String getStringified() {
		GamePrinter stringifiedPrinter = PrinterTypes.SERIALIZER.getObject();
		stringifiedPrinter.setGame(game);
		return stringifiedPrinter.toString();
	}

	public void loadGame(BufferedReader stream) throws FileContentsException, IOException {
		game.load(stream);
	}
}
