package fp.daw.juegos2d.spyhunter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import fp.daw.juegos2d.Game;
import fp.daw.juegos2d.Surface;
import fp.daw.juegos2d.assets.Assets;
import fp.daw.juegos2d.assets.ClickableArea;

public class SpyHunter extends Game {
	
	private BufferedImage fondo;
	private BufferedImage buffer;
//	private static  BufferedImage texture1;
//	private Graphics2D g;
		
	//no encontramos solucion al problema
//	static {
//		try {
//			texture1 = ImageIO.read(SpyHunter.class.getResourceAsStream("/img/spyhunter.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.exit(-1);
//		}
//	}
	

	int dificultad;
	int estado = 0; // 0: menu, 1: jugando, 2: finjuego
	 ClickableArea facil;
	 ClickableArea intermedio;
	 ClickableArea dificil;
	 ClickableArea extremo;
	 
	double s;
	int ancho;
	 
	
	public SpyHunter(Surface surface , int w , int h){
		super(surface);
		
		try {
			fondo = ImageIO.read(getClass().getResourceAsStream("/img/spyhunter.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		s = (double) getLienzo().getHeight() / (double) fondo.getHeight();
		int anchoFondo = (int) (fondo.getWidth() * s);
		ancho = (int) (getLienzo().getWidth() - anchoFondo);
		
		facil = new  ClickableArea("FACIL",Assets.font3.deriveFont(50f), Color.BLACK, 10 + anchoFondo , 150 );
		intermedio = new  ClickableArea("INTERMEDIO",Assets.font3.deriveFont(50f), Color.BLACK, 10 + anchoFondo , 250 );
		dificil = new  ClickableArea("DIFICIL",Assets.font3.deriveFont(50f), Color.BLACK, 10 + anchoFondo , 350 );
		extremo = new  ClickableArea("EXTREMO",Assets.font3.deriveFont(50f), Color.BLACK, 10 + anchoFondo , 450 );
//		buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//		g = buffer.createGraphics();
		
	}
	
	@Override
	public boolean next(long lapso) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getLienzo().getWidth(), getLienzo().getHeight());
		switch (estado) {
		case 0:
			AffineTransform t = g2d.getTransform();
			g2d.scale(s, s);
			g2d.drawImage(fondo, 0, 0, getLienzo());
			g2d.setTransform(t);
			int anchoOpcion = facil.getWidth();
			double s; 
			t = g2d.getTransform();
			s = 2;
			g2d.scale(s, s);
			facil.paint((Graphics2D) g);	
			intermedio.paint((Graphics2D) g);
			dificil.paint((Graphics2D) g);
			extremo.paint((Graphics2D) g);
			g2d.setTransform(t);
			
			
			//drawPatternBackground((Graphics2D) g, texture1);
			
			break;
		case 1:
			
			
			
			break;
		case 2:
			
			
			break;
		}
	}
	
		
	@Override
	public void mouseClicked(MouseEvent e) {
		if (estado == 0 && facil.isInside(e.getX(), e.getY())) {
			dificultad = 0;
			estado = 1;
		}

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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	


	

}
