package fp.daw.juegos2d.snake;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import fp.daw.juegos2d.Game;
import fp.daw.juegos2d.Surface;

public class Snake extends Game {

	private static final Point UP = new Point(0, -1);
	private static final Point LEFT = new Point(-1, 0);
	private static final Point DOWN = new Point(0, 1);
	private static final Point RIGHT = new Point(1, 0);

	private int gridSize;
	private int padding;
	private int snakeSide;
	private int hMargin;
	private int vMargin;
	
	private int w;
	private int h;

	private Point dir = DOWN;
	private long lapso = 0;

	private Deque<Point> snake = new LinkedList<Point>();
	private Point food = new Point();
	private int grow = 0;
	private int growLength = 5;

	private Random r = new Random();

	public Snake(Surface surface, int side, int margen) {
		super(surface);
		this.gridSize = side;
		this.padding = margen;
		this.snakeSide = side - margen * 2;
		w = surface.getWidth() / side;
		h = surface.getHeight() / side;
		hMargin = 0;
		vMargin = 0;
		int col = w / 2;
		int fil = 1;
		snake.add(new Point(col, fil));
		snake.add(new Point(col, fil + 1));
		snake.add(new Point(col, fil + 2));
		putFood();
	}

	private void putFood() {
		do {
			food.setLocation(r.nextInt(w), r.nextInt(h));
		} while (snake.contains(food));

	}

	@Override
	public boolean next(long lapso) {
		this.lapso += lapso;
		boolean fin = false;

		if (this.lapso >= 100000000L) {
			Point cola;
			Point cabeza = snake.getLast();
			if (grow > 0) {
				cola = new Point(cabeza.x + dir.x, cabeza.y + dir.y);
				grow--;
				if (grow == 0)
					putFood();
			} else {
				cola = snake.removeFirst();
				cola.setLocation(cabeza.x + dir.x, cabeza.y + dir.y);
				if (cola.equals(food)) {
					grow = growLength;
				}
			}
			
			if (snake.contains(cola) || cola.x < 0 || cola.x == w || cola.y < 0 || cola.y == h) {
				fin = true;
			}	
			snake.addLast(cola);
			this.lapso -= 100000000L;
		}
		return fin;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getLienzo().getWidth(), getLienzo().getHeight());
		Iterator<Point> i = snake.iterator();
		while (i.hasNext()) {
			Point p = i.next();
			if (!i.hasNext())
				g.setColor(Color.RED);
			else
				g.setColor(Color.BLACK);
			g.fillRect(p.x * gridSize + padding + hMargin, p.y * gridSize + padding + vMargin, snakeSide, snakeSide);
		}
		if (grow == 0) {
			g.setColor(Color.GREEN);
			g.fillRect(food.x * gridSize + padding + hMargin, food.y * gridSize + padding + vMargin, snakeSide, snakeSide);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_LEFT):
			dir = LEFT;
			break;
		case (KeyEvent.VK_RIGHT):
			dir = RIGHT;
			break;
		case (KeyEvent.VK_UP):
			dir = UP;
			break;
		case (KeyEvent.VK_DOWN):
			dir = DOWN;
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
