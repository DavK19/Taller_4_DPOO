package uniandes.dpoo.taller4.consola;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.event.MouseEvent;


public class VentanaPrincipal extends JFrame {
    private PanelJugadasJugador panelSur;
    private PanelJava2D panelEste;
    private PanelAjustesJuego panelNorte;
    private PanelOpcionesJuego panelOeste;
    

    public VentanaPrincipal() {
        super("LightsOut"); 
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        panelSur = new PanelJugadasJugador(this);
        add(panelSur, BorderLayout.SOUTH);

        panelNorte = new PanelAjustesJuego(this);
        add(panelNorte, BorderLayout.NORTH);

        panelOeste = new PanelOpcionesJuego(this);
        add(panelOeste, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new BorderLayout());
        panelEste = new PanelJava2D(this);
        centerPanel.add(panelEste, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        
        

        setVisible(true);
        
        
    }

    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.iniciarJugador();
    }

    public void repintarTablero()
    {
        panelEste.reiniciarTablero();
    }

    public void nuevoTablero()
    {
        panelEste.nuevoTablero();
    }

    public void tamano(int a)
    {
        panelEste.tamano(a);
    }

    public void dificultad(int a)
    {
        panelEste.dificultad(a);
    }

    public void jugador(String jugador)
    {
        panelSur.jugador(jugador);
    }

    public void jugadas(int jugadas)
    {
        panelSur.jugadas(jugadas);
    }
    
    public void iniciarJugador() {
    	panelSur.iniciarJugador();
    }
    
    public void agregarGanador(int puntaje) {
    	String jugador = panelSur.getJugador();
    	panelOeste.agregarTop10(jugador, puntaje);
    }
}
