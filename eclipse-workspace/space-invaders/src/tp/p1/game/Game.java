package tp.p1.game;

import java.util.Random;

public class Game {

	private final Level level;
	private Random rand;
	private int cycleCount = 0;
	private int score = 0;
	
	public Game(Level level, int seed)
	{
		this.level = level;
		rand = new Random(seed);
	}
}
