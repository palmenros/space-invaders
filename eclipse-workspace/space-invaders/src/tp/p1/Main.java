package tp.p1;

import tp.p1.game.Controller;
import tp.p1.game.Game;
import tp.p1.input.ArgumentParser;

public class Main {

	public static void main(String[] args)
	{		
		ArgumentParser parser = new ArgumentParser(args);
		
		if(!parser.tryParse()) { return; }
			
		Game game = new Game(parser.getLevel(), parser.getSeed());
		Controller controller = new Controller(game);
		controller.run();

		controller.close();		
	}
	
}
