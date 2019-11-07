package tp.p1.game;

public class BoardInitializer {
	
	/**
	 * Initial regular aliens per row
	 */
	private static final int INITIAL_COMMON_ALIENS_PER_ROW = 4;
	
	/**
	 * Row where regular aliens are initially spawned 
	 */
	private static final int INITIAL_COMMON_ALIENS_ROW = 1;
	
	/**
	 * Column where regular aliens are initially spawned 
	 */
	private static final int INITIAL_COMMON_ALIENS_COLUMN = 3;	
	
	
	public BoardInitializer()
	{
		
	}
	
	public GameObjectBoard initialize(Game game)
	{
		GameObjectBoard board = new GameObjectBoard();
		Level level = game.getLevel();
		
		//Initialize default position of aliens
		int regularShipNum = level.getCommonShipNum();
		int row;
		for(row = 0; row < regularShipNum / INITIAL_COMMON_ALIENS_PER_ROW; row ++)
		{
			for(int col = 0; col  < INITIAL_COMMON_ALIENS_PER_ROW; col++)
			{
				board.add(new RegularShip( game, INITIAL_COMMON_ALIENS_ROW + row, INITIAL_COMMON_ALIENS_COLUMN + col ));
			}
		}
		
		int destroyerShipNum = level.getDestroyerNum();
		int columnOffset = (INITIAL_COMMON_ALIENS_PER_ROW - destroyerShipNum) / 2;
		for (int i = 0; i < destroyerShipNum; i++)
		{
			board.add(new DestroyerShip(game, INITIAL_COMMON_ALIENS_ROW + row, INITIAL_COMMON_ALIENS_COLUMN + columnOffset + i));
		}
		
		return board;
	}
}
