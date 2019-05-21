package fp.daw.juegos2d.crazyballs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import fp.daw.juegos2d.Game;
import fp.daw.juegos2d.Surface;
import fp.daw.juegos2d.assets.Ball;

public class CrazyBalls extends Game {

	private static final int MINSPEED = 30;
	private static final int MAXSPEED = 200;
	private static final Random r = new Random();

	private int refSize;
	private int minRadius;
	private int maxRadius;

	private LinkedList<Ball> balls;
	private ArrayList<Ball> removed;

	public CrazyBalls(Surface surface, int ballCount) {
		super(surface);
		balls = new LinkedList<Ball>();
		removed = new ArrayList<Ball>();

		refSize = Math.max(surface.getWidth(), surface.getHeight());
		minRadius = (int) (refSize * 0.02);
		maxRadius = (int) (refSize * 0.10);

		for (int i = 0; i < ballCount; i++) {
			balls.add(makeBall());
		}
	}

	private Ball makeBall() {
		Color color = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
		int radio = r.nextInt(maxRadius - minRadius + 1) + minRadius;
		int xymin = 10 + radio;
		int xmax = getLienzo().getWidth() - 10 - radio;
		int ymax = getLienzo().getHeight() - 10 - radio;
		int x = r.nextInt(xmax - xymin + 1) + xymin;
		int y = r.nextInt(ymax - xymin + 1) + xymin;
		double dir = r.nextDouble() * 2 * Math.PI;
		double speed = r.nextInt(MAXSPEED - MINSPEED + 1) + MINSPEED;
		return new Ball(color, radio, x, y, dir, speed, getLienzo().getSize());
	}

	@Override
	public boolean next(long lapso) {
		synchronized (balls) {
			Iterator<Ball> i = balls.iterator();
			while (i.hasNext())
				i.next().move(lapso);
		}
		synchronized (removed) {
			Iterator<Ball> i = removed.iterator();
			while (i.hasNext()) {
				Ball p = i.next();
				if (p.fade(lapso))
					i.remove();
			}
		}
		return false;
	}

	private static final RenderingHints RH = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_OFF);

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getLienzo().getWidth(), getLienzo().getHeight());
		RenderingHints rh = g.getRenderingHints();
		g.setRenderingHints(RH);
		synchronized (balls) {
			Iterator<Ball> i = balls.iterator();
			while (i.hasNext())
				i.next().paint(g);
			g.setColor(Color.BLACK);
			g.drawString("Número de pelotas: " + balls.size(), 50, 50);
		}
		synchronized (removed) {
			Iterator<Ball> i = removed.iterator();
			while (i.hasNext())
				i.next().fadePaint(g);
		}
		g.setRenderingHints(rh);
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
		Ball ball = balls.get(balls.size() - 1);
		int xp = ball.getX();
		int yp = ball.getY();
		int xr = e.getX();
		int yr = e.getY();
		int d = (int) Point2D.distance(xp, yp, xr, yr);
		synchronized (balls) {
			if (d < ball.getRadio()) {
				balls.remove(ball);
				synchronized (removed) {
					removed.add(ball);
				}
			} else
				balls.add(0, makeBall());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

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
