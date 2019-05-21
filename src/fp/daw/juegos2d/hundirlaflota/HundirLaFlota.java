package fp.daw.juegos2d.hundirlaflota;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import fp.daw.juegos2d.Game;
import fp.daw.juegos2d.Surface;

public class HundirLaFlota extends Game {
	
	public HundirLaFlota(Surface surface) {
		super(surface);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean next(long lapso) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getLienzo().getWidth(), getLienzo().getHeight());
		g.setColor(Color.BLACK);
		
//		//primer cuadrado
//		g.drawLine(400, 400, 50, 400); //linea abajo
//		g.drawLine(50, 400, 50, 50); //linea izq
//		g.drawLine(400, 400, 400, 50); //linea dch
//		g.drawLine(50, 50, 400, 50); //linea arriba
//		
//		//segundo cuadrado
//		g.drawLine(850, 400, 500, 400); //linea abajo
//		g.drawLine(850, 50, 500, 50); //linea arriba 
//		g.drawLine(850, 50, 850, 400); //linea dch
//		g.drawLine(500, 400, 500, 50); //linea izq
		
		//zona de barcos
		g.drawLine(50, 450, 50, 600); //linea izq
		g.drawLine(50, 600, 400, 600); //linea abajo
		g.drawLine(400, 450, 400, 600); //linea dch
		g.drawLine(50, 450, 400, 450); //linea arriba
		
		dibujarCuadricula(g, 50, 35, 35);
		dibujarCuadricula(g, 500, 35, 35);
		
//		int x1 = 50;
//		int y1 = 35;
//		int x3 = 500;
//		int y3 = 35;
//		for (int i = 1; i < 10; i++) { //lineas interior verticales
//			g.drawLine(x1 += y1, 50, x1, 400);
//			g.drawLine(x3 += y3, 50, x3, 400);
//		}
//		int x2 = 50;
//		int y2 = 35;
//		int x4 = 50;
//		int y4 = 35;
//		for (int j = 1; j < 10; j++) { //lineas interior horizontales
//			g.drawLine(50, x2 += y2, 400, x2);
//			g.drawLine(500, x4 += y4, 850, x4);
//		}
		
	}

	public void dibujarCuadricula(Graphics2D g, int x, int y, int l) {
		int xf = x + 10 * l;
		int yf = y + 10 * l;
		int xv = x;
		int yh = y;
		for (int i=0; i<11; i++) {
			g.drawLine(x, yh, xf, yh);
			g.drawLine(xv, y, xv, yf);
			xv += l;
			yh += l;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
