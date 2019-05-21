package fp.daw.juegos2d.assets;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball {

	private double speedX;
	private double speedY;
	private double x;
	private double y;
	private int radius;
	private int diameter;
	private Color color;
	private int xmax;
	private int ymax;
	private int rx;
	private int ry;
	private AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
	
	public Ball(Color color, int radius, int x, int y, double d, double v, Dimension dim) {
		this.color = color;
		this.radius = radius;
		this.x = x - radius;
		this.y = y - radius;
		speedX = v * Math.cos(d);
		speedY = v * Math.sin(d);
		diameter = 2 * radius;
		xmax = dim.width - diameter - 1;
		ymax = dim.height - diameter - 1;
		rx = dim.width + dim.width - diameter - diameter;
		ry = dim.height + dim.height - diameter - diameter;
	}
	
	public void move(long lapso) {
		double dx = lapso * speedX / 1000000000d;
		double dy = lapso * speedY / 1000000000d;
		x += dx;
		y += dy;
		if (x < 0) {
			x = Math.abs(dx) - x;
			speedX *= -1;
		}
		else if (x > xmax) {
			x = rx - x;
			speedX *= -1;
		}
		if (y < 0) {
			y = Math.abs(dy) - y;
			speedY *= -1;
		}
		else if (y > ymax) {
			y = ry - y;
			speedY *= -1;
		}
	}
	
	public boolean fade(long lapso) {
		double alpha = composite.getAlpha();
		if (alpha > 0) {
			alpha -= (double) lapso / 2000000000d;
			if (alpha >= 0)
				composite = composite.derive((float) alpha);
		}
		return alpha <= 0;
	}
	
	public int getX() {
		return (int) x + radius;
	}
	
	public int getY() {
		return (int) y + radius;
	}
	
	public int getRadio() {
		return radius;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, diameter, diameter);
		g.setColor(Color.BLACK);
		g.drawOval((int) x, (int) y, diameter, diameter);
	}
	
	public void fadePaint(Graphics g) {
		Composite aux = ((Graphics2D) g).getComposite();
		((Graphics2D) g).setComposite(composite);
		paint(g);
		((Graphics2D) g).setComposite(aux);
	}
	
}