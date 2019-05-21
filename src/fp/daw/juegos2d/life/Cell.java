package fp.daw.juegos2d.life;

public class Cell {
	
	private boolean alive;
	private int neighbourCount;
	
	public Cell(boolean alive, int neighbourCount) {
		this.alive = alive;
		this.neighbourCount = neighbourCount;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public int getNeighbourCount() {
		return neighbourCount;
	}
	
	public void setNeighbourCount(int neighbourCount) {
		this.neighbourCount = neighbourCount;
	}
	
	public void incNeighbourCount() {
		neighbourCount++;
	}
	
	public void decNeighbourCount() {
		neighbourCount--;
	}
	
}
