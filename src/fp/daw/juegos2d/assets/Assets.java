package fp.daw.juegos2d.assets;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

public class Assets {
	
	private static final float [] DASH = {3.0f, 5.0f};
	
	public static final BasicStroke DASHED =
	        new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, DASH, 0.0f);
	
	public static final BasicStroke SOLID =
	        new BasicStroke(3.0f,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_MITER,
                    10.0f);

	public static Font font1;
	public static Font font2;
	public static Font font3;
	public static Font font4;
	public static Font font5;
	public static Font font6;
	
	private static SVGUniverse universe = new SVGUniverse();
	public static SVGDiagram pinturas;
	public static SVGDiagram mad;
	public static SVGDiagram coche;
	public static SVGDiagram snake;
	public static SVGDiagram barco;
	public static SVGDiagram tetris;
	
	public static BufferedImage texture1;
	public static BufferedImage texture2;
	public static BufferedImage pattern1;
	public static BufferedImage pattern2;
	public static BufferedImage pattern3;
	public static BufferedImage pattern4;

	static {
		try {
			font1 = loadFont("/fonts/Plank.ttf");
			font2 = loadFont("/fonts/C.A. Gatintas.ttf");
			font3 = loadFont("/fonts/Coulson.otf");
			font4 = loadFont("/fonts/WindshieldMassacre.ttf");
			font5 = loadFont("/fonts/Snake.ttf");
			font6 = loadFont("/fonts/Capture it.ttf");
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		pinturas = loadSVGDiagram("/svg/pinturas.svg");
		mad = loadSVGDiagram("/svg/mad.svg");
		coche = loadSVGDiagram("/svg/coche.svg");
		snake = loadSVGDiagram("/svg/snake.svg");
		barco = loadSVGDiagram("/svg/barco.svg");
		tetris= loadSVGDiagram("/svg/Tetris.svg");
		
		try {
			texture1 = ImageIO.read(Assets.class.getResourceAsStream("/img/textura1.jpg"));
			texture2 = ImageIO.read(Assets.class.getResourceAsStream("/img/textura2.png"));
			pattern1 = ImageIO.read(Assets.class.getResourceAsStream("/img/pattern-one.png"));
			pattern2 = ImageIO.read(Assets.class.getResourceAsStream("/img/pattern-two.png"));
			pattern3 = ImageIO.read(Assets.class.getResourceAsStream("/img/pattern-three.png"));
			pattern4 = ImageIO.read(Assets.class.getResourceAsStream("/img/pattern-four.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static Font loadFont(String resource) throws FontFormatException, IOException {
		Font font;
		InputStream s = Assets.class.getResourceAsStream(resource);
		try {
			font = Font.createFont(Font.PLAIN, s);
		} finally {
			s.close();
		}
		return font;
	}
	
	private static SVGDiagram loadSVGDiagram(String resource) {
		InputStream s = Assets.class.getResourceAsStream(resource);
		SVGDiagram diagram = universe.getDiagram(universe.loadSVG(Assets.class.getResource(resource)));
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return diagram;
	}

}
