package uniandes.dpoo.taller4.consola;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.Collection;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Top10;

public class PanelOpcionesJuego extends JPanel {
    private VentanaPrincipal principal;
    private JButton btnnuevo;
    private JButton btnreiniciar;
    private JButton btntop10;
    private JButton btnCamJugador;
    private String jugadorActual;
    private Top10 top = new Top10();
    private File archivoTop = new File("data/top10.csv");
    
    public PanelOpcionesJuego(VentanaPrincipal pPrincipal)
    {
        principal=pPrincipal;
        btnnuevo = new JButton("Nuevo");
        btnnuevo.setBackground(new Color(27, 62, 228));
        btnnuevo.setForeground(Color.WHITE);
        btnreiniciar = new JButton("Reiniciar");
        btnreiniciar.setBackground(new Color(27, 62, 228));
        btnreiniciar.setForeground(Color.WHITE);
        btntop10 = new JButton("Top-10");
        btntop10.setBackground(new Color(27, 62, 228));
        btntop10.setForeground(Color.WHITE);
        btnCamJugador = new JButton("Cambiar jugador");
        btnCamJugador.setBackground(new Color(27, 62, 228));
        btnCamJugador.setForeground(Color.WHITE);
        
        top.cargarRecords(archivoTop);
        
        
        btnnuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                principal.nuevoTablero();
            }
        });
        
        btnreiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                principal.repintarTablero();
            }
        });
        
        btntop10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verTop10();
            }
        });

        
        btnCamJugador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Cambiar jugador");
                String nuevoJugador = JOptionPane.showInputDialog(frame, "Ingrese el nuevo nombre de jugador (3 caracteres):");
                if (nuevoJugador != null && nuevoJugador.length() == 3) {
                    jugadorActual = nuevoJugador;
                    principal.jugador(jugadorActual);
                } else {
                    JOptionPane.showMessageDialog(frame, "El nombre de jugador debe tener exactamente 3 caracteres.");
                }
            }
        });
        
        this.setLayout(new GridLayout(4, 1)); 
        
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 10)); 
        buttonPanel.add(btnnuevo);
        buttonPanel.add(btnreiniciar);
        buttonPanel.add(btntop10);
        buttonPanel.add(btnCamJugador);
        
        this.add(new JLabel()); 
        this.add(buttonPanel); 
        this.add(new JLabel());

        
    }
    
    public void agregarTop10(String jugador, int puntaje) {
    	if (top.esTop10(puntaje)) {
    		System.out.println(jugador);
    		top.agregarRegistro(jugador, puntaje);
    		try {
				top.salvarRecords(archivoTop);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		JOptionPane.showMessageDialog(this, "ENTRASTE AL TOP 10!!!!");
    	}
    }
    
    public void verTop10() {
    	Collection<RegistroTop10> tops = top.darRegistros();
    	String mostrar = "";
    	int i = 1;
    	for (RegistroTop10 registro: tops) {
    		mostrar = mostrar +  i + ". " + registro.toString() + "\n";
    		i++;
    	}
    	
    	JOptionPane.showMessageDialog(this, mostrar, "Top 10", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
