package fp.daw.juegos2d;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;

import fp.daw.juegos2d.Tetris.Tetris;
import fp.daw.juegos2d.assets.Assets;
import fp.daw.juegos2d.assets.ClickableArea;
import fp.daw.juegos2d.crazyballs.CrazyBalls;
import fp.daw.juegos2d.life.Life;
import fp.daw.juegos2d.snake.Snake;

public class Surface extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	private class GameInfo {
		private boolean mouseOver;
		private Game game;
		BufferedImage fondo;
		
		public GameInfo(Game game, SVGDiagram svg) {
			
			this.game = game;
			double width = d.getWidth();
			double height = d.getHeight();
			double svgWidth = svg.getWidth();
			double svgHeight = svg.getHeight();
			fondo = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = fondo.createGraphics();
			double sx = width / svgWidth;
			double sy = height / svgHeight;
			double s = sx < sy ? sx : sy;
			double dx = (width - svgWidth * s) / 2;
			double dy = (height - svgHeight * s) / 2;
			g.translate(dx, dy);
			g.scale(s, s);
			g.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			try {svg.render(g);} catch (SVGException e) {}
		}
	}
	
	private static final long serialVersionUID = 1L;
	
	private Dimension d;
	private volatile Thread t;
	Map<ClickableArea, GameInfo> games = new HashMap<>();
	private Game game;
	private BufferedImage gameBuffer;
	private Graphics2D gameGraphics;
	private BufferedImage transBuffer;
	private Graphics2D transGraphics;
	private BufferedImage fondo;
	

	private int y = 100; 
	
	public Surface(int w, int h) {
		d = new Dimension(w, h);
		gameBuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		gameGraphics = gameBuffer.createGraphics();
		transBuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		transGraphics = transBuffer.createGraphics();
		RenderingHints rh = gameGraphics.getRenderingHints();
		rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gameGraphics.setRenderingHints(rh);
		fondo = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		makeBackground(2);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void addgame(BufferedImage image, Game game, SVGDiagram svg) {
		ClickableArea area = new ClickableArea(image, 50, y);
		games.put(area, new GameInfo(game, svg));
		y += area.getHeight() + 50;
	}
	
	public void addgame(String text, Font font, Color color, Game game, SVGDiagram svg) {
		ClickableArea area = new ClickableArea(text, font, color, 50, y);
		games.put(area, new GameInfo(game, svg));
		y += area.getHeight() + 50;
	}
	
	public void addgame(String text, Font font, Color color, String txt, SVGDiagram svg) {
		ClickableArea tetris = new ClickableArea(text, font, color, 50, y);
		games.put(tetris, new GameInfo(null, svg));
		y += tetris.getHeight() + 50;
		
	}
	
	
	
	@Override
	public Dimension getPreferredSize() {
		return d;
	}
	
	private void startGame(Game game) {
		
//		startGameThread(this.game = new Transition());
//		try {
//			while (true) {
//				t.join();
//				break;
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		startGameThread(this.game = game);
	}
	
	private void startGameThread(Game game) {
		t = new Thread(() -> {
			long t0 = System.nanoTime(), t1, lapso;
			boolean finished = false;
			while (!finished) {
				t1 = System.nanoTime();
				lapso = t1 - t0;
				t0 = t1;
				finished = game.next(lapso);
				paintComponent(getGraphics());
//				repaint();
			}
			t = null;
			repaint();
		});
		t.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (t != null)
			game.render(gameGraphics);
		else {
			this.gameGraphics.drawImage(fondo, 0, 0, this);
			for (ClickableArea area: games.keySet()) {
				GameInfo info = games.get(area);
				if (info.mouseOver) {
					Composite c = this.gameGraphics.getComposite();
					gameGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
					gameGraphics.drawImage(info.fondo, 0, 0, this);
					gameGraphics.setComposite(c);
				}
				area.paint(gameGraphics);
			}
		}
		g.drawImage(gameBuffer, 0, 0, this);
	}
	
	private void makeBackground(int n) {
		Graphics2D g = fondo.createGraphics();
		g.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		switch (n) {
		case 1:
			drawPatternBackground(g, Assets.texture1);
			break;
		case 2:
			drawPatternBackground(g, Assets.pattern1);
			break;
		}
	}
	
	private void drawPatternBackground(Graphics2D g, BufferedImage pattern) {
		int w = pattern.getWidth();
		int h = pattern.getHeight();
		int hz = fondo.getWidth() / w + 1;
		int vt = fondo.getHeight() / h + 1;
		for (int i=0; i<hz; i++)
			for (int j=0; j<vt; j++)
				g.drawImage(pattern, i * w, j * h, this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (t != null)
			game.mouseClicked(e);
		else {
			for (ClickableArea area: games.keySet())
				if (area.isInside(e.getX(), e.getY())) { 
					if(games.get(area).game==null) {
						Tetris.inicia2();
						Main.frame.setVisible(false);
					}else
					startGame(games.get(area).game);
					
				}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (t != null)
			game.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (t != null)
			game.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (t != null)
			game.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (t != null)
			game.mouseReleased(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (t != null)
			game.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (t != null)
			game.mouseMoved(e);
		else {
			for (ClickableArea area: games.keySet()) {
				GameInfo info = games.get(area);
				if (area.isInside(e.getX(), e.getY())) {
					if (!info.mouseOver) {
						info.mouseOver = true;
						repaint();
					}
				}
				else if (info.mouseOver) {
					info.mouseOver = false;
					repaint();
					
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (t != null)
			game.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (t != null)
			game.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (t != null)
			game.keyTyped(e);
	}

	
	
}
