package fp.daw.juegos2d.Tetris;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import fp.daw.juegos2d.Surface;
import fp.daw.juegos2d.Tetris.Pieza.tipos;

@SuppressWarnings("serial")
public class Panel extends JPanel implements ActionListener {

	
	public final static int columnasPanel = 10;
	public final static int filasPanel = 22;
	private final int velocidad = 400;
	private Timer timer;
	private boolean callendoFin = false;
	static boolean iniciado = false;
	public static boolean pausado = false;
	static int numeroLineasBorradas = 0;
	private static int curX = 0;
	private static int curY = 0;

	private static Pieza curPiece;
	private tipos[] panel;

	public Panel(Tetris tetris) {

		iniciarPanel(tetris);
		// TODO Auto-generated constructor stub
	}

	void iniciarPanel(Tetris tetris) {

		setFocusable(true);
		curPiece = new Pieza();
		panel = new tipos[columnasPanel * filasPanel];
		timer = new Timer(velocidad, (ActionListener) this);
		timer.start();
		addKeyListener(new Controles());
		Tetris.statusbar = Tetris.getStatusBar();
		limpiarPanel();
	}

	private int anchoCuadrado() {
		return (int) getSize().getWidth() / columnasPanel;
	}

	private int altoCuadrado() {
		return (int) getSize().getHeight() / filasPanel;
	}

	private tipos tipoEn(int x, int y) {
		return panel[(y * columnasPanel) + x];
	}

	private void dibujarCuadrado(Graphics g, int x, int y, tipos tipo) {

		Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), new Color(102, 204, 102),
				new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), new Color(102, 204, 204),
				new Color(218, 170, 0) };

		Color color = colors[tipo.ordinal()];

		g.setColor(color);
		g.fillRect(x + 1, y + 1, anchoCuadrado() - 2, altoCuadrado() - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + altoCuadrado() - 1, x, y);
		g.drawLine(x, y, x + anchoCuadrado() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + altoCuadrado() - 1, x + anchoCuadrado() - 1, y + altoCuadrado() - 1);
		g.drawLine(x + anchoCuadrado() - 1, y + altoCuadrado() - 1, x + anchoCuadrado() - 1, y + 1);

	}

	public void inicio() {
		
         
		if (pausado)
			return;

		iniciado = true;
		callendoFin = false;
		numeroLineasBorradas = 0;
		limpiarPanel();
		nuevaPieza();
		timer.start();
	}

	protected void pausa() {

		if (!iniciado)
			return;

		pausado = !pausado;

		if (pausado) {

			timer.stop();
			Tetris.statusbar.setText("Pausa");
			Tetris.pausa.setIcon(new ImageIcon(getClass().getResource("/iconos/play.png")));
		} else {

			timer.start();
			Tetris.statusbar.setText("Puntos: " + String.valueOf(numeroLineasBorradas));
			Tetris.pausa.setIcon(new ImageIcon(getClass().getResource("/iconos/pauseb.png")));
		}

		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		hacerDibujo(g);
	}

	private void hacerDibujo(Graphics g) {

		Dimension size = getSize();
		int topeLienzo = (int) size.getHeight() - filasPanel * altoCuadrado();
		
		for (int i = 0; i < filasPanel; i++) {

			for (int j = 0; j < columnasPanel; j++) {

				tipos tipo = tipoEn(j, filasPanel - i - 1);

				if (tipo != tipos.V)
					dibujarCuadrado(g, 0 + j * anchoCuadrado(), topeLienzo + i * altoCuadrado(), tipo);
				
//				else {
//					g.setColor(Color.BLUE);
//					for(int y=0; y<150 ; y++) {
//						for(int x =0; x<100 ;x++) {
//					g.drawRect(x, y, 50, 50);
//						}
//					}
//			}
				}
		}

		if (curPiece.getShape() != tipos.V) {

			for (int i = 0; i < 4; ++i) {

				int x = curX + curPiece.x(i);
				int y = curY - curPiece.y(i);
				dibujarCuadrado(g, 0 + x * anchoCuadrado(), topeLienzo + (filasPanel - y - 1) * altoCuadrado(),
						curPiece.getShape());
			}
		}
	}

	

	private void dejarCaer() {

		int newY = curY;

		while (newY > 0) {

			if (!tryMove(curPiece, curX, newY - 1))
				break;
			--newY;
		}

		pieceDropped();
	}

	private void bajarLinea() {

		if (!tryMove(curPiece, curX, curY - 1))
			pieceDropped();
	}

	private void limpiarPanel() {

		for (int i = 0; i < filasPanel * columnasPanel; ++i)
			panel[i] = tipos.V;
	}

	private void pieceDropped() {

		for (int i = 0; i < 4; ++i) {

			int x = curX + curPiece.x(i);
			int y = curY - curPiece.y(i);
			panel[(y * columnasPanel) + x] = curPiece.getShape();
		}

		borrarLineasLlenas();

		if (!callendoFin)
			nuevaPieza();
	}

	private void nuevaPieza() {

		curPiece.defFormaAleatoria();
		curX = columnasPanel / 2 - 1;
		curY = filasPanel - 1 + curPiece.minY();

		if (!tryMove(curPiece, curX, curY)) {

			curPiece.definirForma(tipos.V);
			
			pausa();
			iniciado = false;
			Tetris.statusbar.setText("Game Over");
			Tetris.nextPiece.setIcon(new ImageIcon(Tetris.class.getResource("/fp.daw.juegos2d.Tetris.iconos/LogoTetris_2.png")));
		}
//	        return curPiece.getShape();
	}

	private boolean tryMove(Pieza newPiece, int newX, int newY) {

		for (int i = 0; i < 4; ++i) {

			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);

			if (x < 0 || x >= columnasPanel || y < 0 || y >= filasPanel)
				return false;

			if (tipoEn(x, y) != tipos.V)
				return false;
		}

		curPiece = newPiece;
		curX = newX;
		curY = newY;

		repaint();

		return true;
	}

	private void borrarLineasLlenas() {

		int numeroLineasLlenas = 0;

		for (int i = filasPanel - 1; i >= 0; --i) {
			boolean lineaLlena = true;

			for (int j = 0; j < columnasPanel; ++j) {
				if (tipoEn(j, i) == tipos.V) {
					lineaLlena = false;
					break;
				}
			}

			if (lineaLlena) {
				numeroLineasLlenas++;
				for (int k = i; k < filasPanel - 1; k++) {
					for (int j = 0; j < columnasPanel; j++)
						panel[(k * columnasPanel) + j] = tipoEn(j, k + 1);
				}
			}
		}

		if (numeroLineasLlenas > 0) {

			numeroLineasBorradas += numeroLineasLlenas;
			 Tetris.statusbar.setText("Puntos: " + String.valueOf(numeroLineasBorradas));
			callendoFin = true;
			curPiece.definirForma(tipos.V);
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (callendoFin) {

			callendoFin = false;
			nuevaPieza();

		} else {

			bajarLinea();
		}
	}

	class Controles extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			if (!iniciado || curPiece.getShape() == tipos.V) {
				return;
			}

			int keycode = e.getKeyCode();

			if (keycode == 'P') {
				pausa();
				return;
			}

			if (pausado)
				return;

			switch (keycode) {

			case KeyEvent.VK_LEFT:
				tryMove(curPiece, curX - 1, curY);
				break;

			case KeyEvent.VK_RIGHT:
				tryMove(curPiece, curX + 1, curY);
				break;

			case KeyEvent.VK_DOWN:
				tryMove(curPiece.girarDcha(), curX, curY);
				break;

			case KeyEvent.VK_UP:
				tryMove(curPiece.girarIzda(), curX, curY);
				break;

			case KeyEvent.VK_SPACE:
				dejarCaer();
				break;

			case 'D':
				bajarLinea();
				break;
			}
		}
	}
}
