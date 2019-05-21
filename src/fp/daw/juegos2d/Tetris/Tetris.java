package fp.daw.juegos2d.Tetris;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import fp.daw.juegos2d.Main;
import fp.daw.juegos2d.Tetris.Pieza.tipos;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class Tetris extends JFrame  {
	static JLabel statusbar;
	static JLabel nextPiece=new JLabel("");
	static JButton pausa;
	static JPanel sigPieza;
	Panel panel;
	

    public Tetris() throws FontFormatException, IOException {
    	setBackground(Color.WHITE);
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Tetris.class.getResource("/iconos/tetris.png")));
    	setResizable(false);
        initUI();
    }

    private void initUI() throws FontFormatException, IOException {
                getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
                
                InputStream bitFont = getClass().getResourceAsStream("/fonts/8bit.ttf");
                InputStream tetrisFont = getClass().getResourceAsStream("/fonts/TetrisFont.ttf");
        		Font fontbit = Font.createFont(Font.PLAIN, bitFont).deriveFont(20.f);

        		Font fontTetris = Font.createFont(Font.PLAIN, tetrisFont).deriveFont(20.0f);
        	
//                Border bordeJuego = BorderFactory.createStrokeBorder(new BasicStroke(10.0f));
        		 Border bordeJuego =(new LineBorder(Color.GRAY, 10, true));
                 
                JPanel borde = new JPanel();
            	
                borde.setBorder(new MatteBorder(0, 10, 10, 10, (Color) Color.DARK_GRAY));
                getContentPane().add(borde);
                        borde.setLayout(new BorderLayout(0, 0));
                
                        panel = new Panel(this);
                        
                        borde.add(panel);
                       
                        panel.inicio();
                        
                        
                       
                JPanel control = new JPanel();
                control.setBackground(Color.WHITE);
                
                getContentPane().add(control);
                
                control.setLayout(new BorderLayout(0, 0));
               
                
                JPanel puntuacion = new JPanel();
                control.add(puntuacion, BorderLayout.NORTH);
                
                
                statusbar = new JLabel("Puntos: 0");
                statusbar.setFont(fontbit);
                puntuacion.add(statusbar);
                
                JPanel botones = new JPanel();
                control.add(botones, BorderLayout.SOUTH);
                
                botones.setLayout(new BorderLayout(0, 0));
                
                JButton reinicio = new JButton(new ImageIcon(getClass().getResource("/iconos/reiniciar.png")));
                reinicio.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		if(!Panel.iniciado) {
                    		Panel.pausado=false;
                    		Tetris.pausa.setIcon(new ImageIcon(getClass().getResource("/iconos/pauseb.png")));
                    	}
                	panel.inicio();
                	Tetris.statusbar.setText("Puntos: " + String.valueOf(Panel.numeroLineasBorradas));
                	panel.requestFocus();
                	
                	
                	}
                });
                reinicio.setOpaque(false);
                reinicio.setContentAreaFilled(false);
                reinicio.setBorderPainted(false);
                botones.add(reinicio, BorderLayout.WEST);
                
                pausa = new JButton(new ImageIcon(getClass().getResource("/iconos/pauseb.png")));
                pausa.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		panel.pausa();
                		panel.requestFocus();
                	}
                });
                pausa.setOpaque(false);
                pausa.setContentAreaFilled(false);
                pausa.setBorderPainted(false);
                botones.add(pausa, BorderLayout.EAST);
                
                JPanel contenedor = new JPanel();
                contenedor.setForeground(Color.WHITE);
                contenedor.setBackground(Color.WHITE);
                contenedor.setBorder(new EmptyBorder(30, 25, 50, 25));
                control.add(contenedor, BorderLayout.CENTER);
                contenedor.setLayout(new BorderLayout(0, 0));
                
                sigPieza = new JPanel();
                
                sigPieza.setBorder(new LineBorder(Color.DARK_GRAY, 6, true));
             
                contenedor.add(sigPieza, BorderLayout.CENTER);
                sigPieza.setLayout(new BorderLayout(0, 0));
                nextPiece.setIcon(new ImageIcon(Tetris.class.getResource("/iconos/helloThere3.jpg")));
                nextPiece.setHorizontalAlignment(SwingConstants.CENTER);
                
               
                sigPieza.add(nextPiece);
                
                JButton Volver = new JButton("Volver al menu");
                Volver.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent arg0) {
                		Main.frame.setVisible(true);
                		dispose();
                		
                	}
                });
                contenedor.add(Volver, BorderLayout.SOUTH);
                
                
              
//                JLabel Nombre = new JLabel("TETRIS");
//                Nombre.setFont(fontTetris);
//                contenedor.add(Nombre, BorderLayout.NORTH);
//        
        setTitle("Tetris");
        setSize(337, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
//    public static void figura() {
//    	if (tipos.L != null) {
//    		sigPieza.add(new JLabel("LShpae"));
//    	}
    
    

    public static JLabel getStatusBar() {

        return statusbar;
    }
    
public static void inicia2() {
	Random r = new Random();
	int x = Math.abs(r.nextInt()) % 7 + 1;
	 Pieza.Lista.addFirst(x);
	EventQueue.invokeLater(() -> {

        Tetris game = null;
		try {
			game = new Tetris();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        game.setVisible(true);
       
    });
}

//    public static void main(String[] args) {
//
//    	
//    	inicia2();
//    	Pieza.Lista.addFirst(6);
//    }
}
