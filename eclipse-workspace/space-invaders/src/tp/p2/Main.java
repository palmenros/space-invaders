package tp.p2;

import java.util.Scanner;

import tp.p2.controller.Controller;
import tp.p2.game.*;

/**
 * Main executable class
 * @author Martín Gómez y Pedro Palacios
 */
public class Main {

	/**
	 * Entry point
	 * @param args First argument is level (EASY, HARD, INSANE), second is seed for the RNG 
	 */
	public static void main(String[] args)
	{	
		ArgumentParser parser = new ArgumentParser(args);
		
		//If the arguments are incorrect, exit the program
		if(!parser.tryParse()) { return; }
			
		//Otherwise, create a new game
		Game game = new Game(parser.getLevel(), parser.getSeed());
		Controller controller = new Controller(game, new Scanner(System.in));
		controller.run();

		//Avoid leaking resources
		controller.close();				
	}
	
}
