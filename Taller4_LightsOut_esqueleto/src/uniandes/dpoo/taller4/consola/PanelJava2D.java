package uniandes.dpoo.taller4.consola;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;

public class PanelJava2D extends JPanel implements MouseListener {
    private VentanaPrincipal principal;
    private Tablero tablero;
    private int tamano = 9;
    private boolean reiniciar = false;
    private int dificultad = 1;

    public PanelJava2D(VentanaPrincipal ventanaPrincipal) {
        principal = ventanaPrincipal;
        add(new JLabel("                                   "));
        addMouseListener(this);
        tablero = new Tablero(tamano); 
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int anchoPanelTablero = getWidth();
        int altoPanelTablero = getHeight();
        double anchoCasilla = (anchoPanelTablero / tamano);
        double altoCasilla = (altoPanelTablero / tamano);
        double margen = 1;
        double x = ((getWidth() - anchoPanelTablero) / 2);
        double y = ((getHeight() - altoPanelTablero) / 2);
        if (tablero.darJugadas() == 0 && !reiniciar) {
            tablero.desordenar(dificultad);
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    Rectangle rect = new Rectangle((int) (x + i * anchoCasilla + margen),
                            (int) (y + j * altoCasilla + margen), (int) (anchoCasilla - 2 * margen),
                            (int) (altoCasilla - 2 * margen));
                    
                    if (tablero.darTablero()[i][j] == true) {
                        g2d.setColor(Color.YELLOW);
                    } else {
                        g2d.setColor(Color.BLACK);
                    }
                    g2d.fill(rect);
                    g2d.draw(rect);
                    tablero.salvar_tablero();
                }
            }
        } else {
            boolean[][] tablero_a = tablero.darTablero();
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    Rectangle rect = new Rectangle((int) (x + i * anchoCasilla + margen),
                            (int) (y + j * altoCasilla + margen), (int) (anchoCasilla - 2 * margen),
                            (int) (altoCasilla - 2 * margen));
                    if (tablero_a[i][j] == true) {
                        g2d.setColor(Color.YELLOW);
                    } else {
                        g2d.setColor(Color.BLACK);
                    }
                    g2d.fill(rect);
                    g2d.draw(rect);
                }
            }
        }
        if(tablero.tableroIluminado() == true){
        	int puntos = tablero.calcularPuntaje();
            JOptionPane.showMessageDialog(this, "Ganaste con " + puntos);
            principal.agregarGanador(puntos);
            principal.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int click_x = e.getX();
        int click_y = e.getY();
        int[] casilla = convertirCoordenadasACasilla(click_y, click_x);
        tablero.jugar(casilla[0], casilla[1]);
        int jugadas = tablero.darJugadas(); 
        principal.jugadas(jugadas);
        repaint();
    }

    private int[] convertirCoordenadasACasilla(int x, int y) {
        int altoPanelTablero = this.getHeight();
        int anchoPanelTablero = this.getWidth();
        int anchoCasilla = (anchoPanelTablero / tamano);
        int altoCasilla = (altoPanelTablero / tamano);
        int fila = (y / altoCasilla);
        int columna = ( x  / anchoCasilla);
        
        
        return new int[] { fila, columna };
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // no se implementa
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // no se implementa

    }
    
    public void reiniciarTablero() {
        tablero.reiniciar();
        reiniciar = true;
        paint(getGraphics());
    }

    public void nuevoTablero() {
        reiniciar = false;
        tablero.reiniciar();
        tablero.desordenar(dificultad);
        
        paint(getGraphics());
    }

    public void dificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public void tamano(int tamano) {
        this.tamano = tamano;
        tablero = new Tablero(tamano);
    }


}
