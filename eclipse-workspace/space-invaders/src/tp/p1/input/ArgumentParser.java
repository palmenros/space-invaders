package tp.p1.input;

import tp.p1.game.Level;

/**
 * @author Martín Gómez y Pedro Palacios
 * Class that parses arguments
 */
public class ArgumentParser {

	/**
	 * Argument List
	 */
	private String[] args;
	
	/**
	 * Random seed
	 */
	private int seed = (int) System.currentTimeMillis();

	/**
	 * Game Level
	 */
	private Level level = Level.EASY;
	
	/**
	 * @param args Argument list
	 */
	public ArgumentParser(String[] args)
	{
		this.args = args;
	}
	
	/**
	 * Try to parse the argument list and fill the fields
	 * @return true if parsed correctly, false otherwise
	 */
	public boolean tryParse()
	{
		if(args.length < 1  || args.length > 2) {
			System.out.println("Error: Invalid number of arguments");
			return false;
		}
		
		String levelStr = args[0].toLowerCase();
		
		if(levelStr.equals("easy")) {
			level = Level.EASY;
		} else if (levelStr.equals("hard")) {
			level = Level.HARD;
		} else if (levelStr.equals("insane")) {
			level = Level.INSANE;
		} else {
			System.out.println("Error: Invalid level");
			return false;
		}
		
		if(args.length == 2)
		{
			try { 
			  int seedNum = Integer.parseInt(args[1]); 
			  this.seed = seedNum;
			} catch(NumberFormatException e) {
				System.out.println("Error: seed is not a valid number");
				return false; 
			}
		}
		
		return true;
	}
	
	/**
	 * If correctly parsed, this method will return the argument value, otherwise it will return the default value
	 * @return seed
	 */
	public int getSeed() {
		return seed;
	}

	/**
	 * If correctly parsed, this method will return the argument value, otherwise it will return the default value
	 * @return level
	 */
	public Level getLevel() {
		return level;
	}
		
}
