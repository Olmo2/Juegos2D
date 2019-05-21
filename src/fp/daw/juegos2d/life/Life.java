package fp.daw.juegos2d.life;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import fp.daw.juegos2d.Game;
import fp.daw.juegos2d.Surface;

public class Life extends Game{
	private Random random = new Random();
	private Cell [][] cellSpace;
	
	public Life(Surface lienzo, int rows, int columns, int cells) {
		super(lienzo);
		cellSpace = new Cell[rows][columns];
		for (int r=0; r<rows; r++)
			for (int c=0; c<columns; c++)
				cellSpace[r][c] = new Cell(false, 0);
		for (int r=0; r<rows; r++)
			for (int c=0; c<columns; c++) {
				int row, col;
				do {
					row = random.nextInt(rows);
					col = random.nextInt(columns);
				} while (cellSpace[row][col].isAlive());
				cellSpace[row][col].setAlive(true);
				int neighbourCount = 0;
				for (int i=r-1; i<=r+1; i++)
					for (int j=c-1; j<=c+1; j++)
						if (i != r || j != c) {
							try {
								cellSpace[i][j].incNeighbourCount();
								if (cellSpace[i][j].isAlive())
									neighbourCount++;
							} catch (ArrayIndexOutOfBoundsException e) {
								
							}
						}
				cellSpace[row][col].setNeighbourCount(neighbourCount);
			}
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
	public boolean next(long ns) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(Graphics2D g) {
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
