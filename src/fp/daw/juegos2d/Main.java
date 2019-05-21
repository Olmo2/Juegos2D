package fp.daw.juegos2d;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fp.daw.juegos2d.Tetris.Panel;
import fp.daw.juegos2d.Tetris.Tetris;
import fp.daw.juegos2d.assets.Assets;
import fp.daw.juegos2d.crazyballs.CrazyBalls;
import fp.daw.juegos2d.hundirlaflota.HundirLaFlota;
import fp.daw.juegos2d.snake.Snake;
import fp.daw.juegos2d.spyhunter.SpyHunter;

public class Main{
	public static JFrame frame;
	
	public static void main(String[] args) {
		Surface surface;
		
		frame = new JFrame("Juegos 2D");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		surface = new Surface(900, 650);
		frame.add(surface);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		surface.addgame("Pelotas Locas", Assets.font4.deriveFont(50f), Color.BLACK, new CrazyBalls(surface, 100), Assets.mad);
		surface.addgame("Snake", Assets.font5.deriveFont(90f), Color.BLACK, new Snake(surface, 20, 1), Assets.snake);
		surface.addgame("SpyHunter", Assets.font2.deriveFont(50f), Color.BLACK, new SpyHunter(surface, 20, 1), Assets.coche);
		surface.addgame("Hundir la Flota", Assets.font6.deriveFont(50f), Color.BLACK, new HundirLaFlota(surface), Assets.barco);
		surface.addgame("Tetris", Assets.font6.deriveFont(50f), Color.BLACK,"", Assets.tetris);
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}

}
