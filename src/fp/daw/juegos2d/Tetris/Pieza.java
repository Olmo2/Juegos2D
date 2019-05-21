package fp.daw.juegos2d.Tetris;



import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Pieza {

	static LinkedList<Integer> Lista = new LinkedList<Integer>();
	
	int x;
	int y;

	/*
	 * v=vacio I=linea L=formaL J=formal invertida S=formaS z=formaS inventida
	 * o=Cuadarado T=Forma T
	 */
	enum tipos {
		V, Z, S, I, T, O, L, J
	}

	private tipos tipoPieza;
	int coordenadas[][];
	int coordenadasTabla[][][];

	// Constructor de la pieza, por defecto es vac�a
	public Pieza() {

		coordenadas = new int[4][2];
		definirForma(tipos.V);
	}

	public void definirForma(tipos forma) {
		coordenadasTabla = new int[][][] {
			//Vacio-0
			{ { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },
			// Zeta-1
			{ { -1,0 }, { 0,0}, { 0,1 }, { 1,1 } },
			// Ese-2**
			{ { -1,1 }, { 0,1 }, { 0, 0 }, { 1, 0 } },
			// Linea-3
			{ {-2,  0 }, { -1, 0 }, { 0, 0 }, { 1, 0 } },
			// Te-4
			{ { -1, 0 }, { 0,0 }, { 1, 0 }, { 0, 1 } },
			// Cuadrado-5
			{ { 0,0 }, { 0,1 }, { 1,0 }, { 1,1 } },
			// Ele-6
			{ {  -1,1 }, { -1, 0 }, { 0, 0 }, { 1, 0 } },
			// Jota-7
			{ { -1, 0 }, { 0, 0 }, { 1, 0 }, { 1, 1 } } };
			
//				// Vacio-0
//				{ { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },
//				// Zeta-1
//				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } },
//				// Ese-2
//				{ { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } },
//				// Linea-3
//				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } },
//				// Te-4
//				{ { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } },
//				// Cuadrado-5
//				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } },
//				// Ele-6
//				{ { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } },
//				// Jota-7
//				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } } };

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; ++j) {
				// dibujar las piezas en su matriz
				coordenadas[i][j] = coordenadasTabla[forma.ordinal()][i][j];
			}
		}
		tipoPieza = forma;

	}

	private void setX(int index, int x) {
		coordenadas[index][0] = x;
	}

	private void setY(int index, int y) {
		coordenadas[index][1] = y;
	}

	public int x(int index) {
		return coordenadas[index][0];
	}

	public int y(int index) {
		return coordenadas[index][1];
	}

	public tipos getShape() {
		return tipoPieza;
	}

	public void defFormaAleatoria() {
		
		Random r = new Random();
		Random rn = new Random();
		int x = Math.abs(r.nextInt()) % 7 + 1;
		int y = Math.abs(r.nextInt()) % 7 + 1;
		tipos[] valores = tipos.values();
		if(x==Lista.getFirst()) {
			Lista.addLast(y);

		Tetris.nextPiece.setIcon(new ImageIcon(getClass().getResource(("/iconos/" + Lista.getLast()+ ".PNG"))));
		
		}
		else { 
			Lista.addLast(x);
			Tetris.nextPiece.setIcon(new ImageIcon(getClass().getResource(("/iconos/" + Lista.getLast()+ ".PNG"))));
//				Tetris.nextPiece.setText(""+ Lista.getLast());
			
		}
		definirForma(valores[Lista.getFirst()]);
//		System.out.println(Lista);
		Lista.removeFirst();
		
	}

	public int minX() {

		int m = coordenadas[0][0];

		for (int i = 0; i < 4; i++) {

			m = Math.min(m, coordenadas[i][0]);
		}

		return m;
	}

	public int minY() {

		int m = coordenadas[0][1];

		for (int i = 0; i < 4; i++) {

			m = Math.min(m, coordenadas[i][1]);
		}

		return m;
	}

	public Pieza girarIzda() {

		if (tipoPieza == tipos.O)
			return this;

		Pieza result = new Pieza();
		result.tipoPieza = tipoPieza;

		for (int i = 0; i < 4; ++i) {

			result.setX(i, y(i));
			result.setY(i, -x(i));
			
			
		}

		return result;
	}

	public Pieza girarDcha() {

		if (tipoPieza == tipos.O)
			return this;

		Pieza result = new Pieza();
		result.tipoPieza = tipoPieza;

		for (int i = 0; i < 4; ++i) {

			result.setX(i, -y(i));
			result.setY(i, x(i));
			
		}

		return result;
	}

}
