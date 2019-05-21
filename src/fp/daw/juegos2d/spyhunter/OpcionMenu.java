package fp.daw.juegos2d.spyhunter;

import java.awt.Color;
import java.awt.Graphics2D;

public class OpcionMenu {

	String texto;
	int x;
	int y;
	int w;
	int h;
	public OpcionMenu(String texto, int x, int y, int w, int h) {
		this.texto = texto;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public boolean dentro(int x, int y) {
		return (x >= this.x && y >= this.y && x <= this.x + w && y <= this.y + h);
	}
	
	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawString(texto, x, y);
	}
	
}
