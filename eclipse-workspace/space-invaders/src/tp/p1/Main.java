package tp.p1;

import java.util.Scanner;
import tp.p1.game.*;

/**
 * @author Martín Gómez y Pedro Palacios
 * Main executable class
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
