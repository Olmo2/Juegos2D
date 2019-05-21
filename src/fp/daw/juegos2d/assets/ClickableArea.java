package fp.daw.juegos2d.assets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ClickableArea {

	private BufferedImage image;
	private String text;
	private Font font;
	private Color color;
	private int x;
	private int y;
	Rectangle2D bounds;
	
	
	public ClickableArea(BufferedImage image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
		bounds = new Rectangle2D.Double(x, y, image.getWidth(), image.getHeight());
	}


	public ClickableArea(String text, Font font, Color color, int x, int y) {
		this.text = text;
		this.font = font;
		this.color = color;
		this.x = x;
		this.y = y;
		FontRenderContext frc = new FontRenderContext(null, true, false);
		Rectangle2D r = font.getStringBounds(text, frc);
		bounds = new Rectangle2D.Double(x + r.getX(), y + r.getY(), r.getWidth(), r.getHeight());
	}
	
	public boolean isInside(int x, int y) {
		return bounds.contains(x, y);
	}
	
	public void paint(Graphics2D g) {
		
		if (image == null) {
//			RenderingHints rh = g.getRenderingHints();
//			g.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
//			g.setColor(Color.black);
//			g.draw(bounds);
			g.setFont(font);
			g.setColor(Color.GRAY);
			g.drawString(text, x + 2, y + 2);
			g.setColor(color);
			g.drawString(text, x, y);
//			g.setRenderingHints(rh);
		}
		else
			g.drawImage(image, x, y, null);
	}
	
	public int getWidth() {
		return (int) bounds.getWidth();
	}
	
	public int getHeight() {
		return (int) bounds.getHeight();
	}
	
}
