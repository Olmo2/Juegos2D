package fp.daw.juegos2d;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import fp.daw.juegos2d.Tetris.Tetris;

public abstract class Game implements MouseListener, MouseMotionListener, KeyListener{

	private Surface surface;
	private Tetris tetris;
	
	public Game(Surface surface) {
		this.surface = surface;
	}
	
	

	public Surface getLienzo() {
		return surface;
	}
	
	public abstract boolean next(long lapso);
	
	public abstract void render(Graphics2D g);
	
}
