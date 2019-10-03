package tp.p1.game;

public class GameObject {

	private int r;
	private int c;

	public GameObject(int r, int c)
	{
		this.r = r;
		this.c = c;
	}

	protected int getRow() {
		return r;
	}

	protected void setRow(int r) {
		this.r = r;
	}

	protected int getCol() {
		return c;
	}

	protected void setCol(int c) {
		this.c = c;
	}
	
	public boolean isAt(int r, int c)
	{
		return getCol() == c && getRow() == r;
	}	
}
