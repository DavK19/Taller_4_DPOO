package uniandes.dpoo.taller4.consola;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.FlowLayout;

public class PanelAjustesJuego extends JPanel {
    
    private VentanaPrincipal principal;

    public PanelAjustesJuego(VentanaPrincipal pPrincipal) {
        principal = pPrincipal;
        setBackground(new Color(27, 62, 228));
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); 

        JTextField txtTamano = new JTextField("Tamano:");
        txtTamano.setBackground(new Color(27, 62, 228));
        txtTamano.setForeground(Color.WHITE);
        txtTamano.setBorder(null);
        add(txtTamano);

        String[] sizes = {"9x9", "8x8", "7x7", "6x6", "5x5", "4x4", "3x3"};
        JComboBox<String> sizeComboBox = new JComboBox<>(sizes);
        add(sizeComboBox);

        JTextField txtDificultad = new JTextField("Dificultad:");
        txtDificultad.setBackground(new Color(27, 62, 228));
        txtDificultad.setForeground(Color.WHITE);
        txtDificultad.setBorder(null);
        add(txtDificultad);

        JCheckBox facilCheckBox = new JCheckBox("Facil");
        facilCheckBox.setBackground(new Color(27, 62, 228));
        facilCheckBox.setForeground(Color.WHITE);
        facilCheckBox.setBorder(null);

        add(facilCheckBox);

        JCheckBox normalCheckBox = new JCheckBox("Normal");
        normalCheckBox.setBackground(new Color(27, 62, 228));
        normalCheckBox.setForeground(Color.WHITE);
        normalCheckBox.setBorder(null);
        add(normalCheckBox);

        JCheckBox dificilCheckBox = new JCheckBox("dificil");
        dificilCheckBox.setBackground(new Color(27, 62, 228));
        dificilCheckBox.setForeground(Color.WHITE);
        dificilCheckBox.setBorder(null);
        add(dificilCheckBox);

        facilCheckBox.addActionListener(e -> {
            if (facilCheckBox.isSelected()) {
                deselectOtherCheckBoxes(facilCheckBox, normalCheckBox, dificilCheckBox);
                principal.dificultad(1);
            }
        });

        normalCheckBox.addActionListener(e -> {
            if (normalCheckBox.isSelected()) {
                deselectOtherCheckBoxes(normalCheckBox, facilCheckBox, dificilCheckBox);
                principal.dificultad(5);
            }
        });

        dificilCheckBox.addActionListener(e -> {
            if (dificilCheckBox.isSelected()) {
                deselectOtherCheckBoxes(dificilCheckBox, normalCheckBox, facilCheckBox);
                principal.dificultad(10);
            }
        });

        sizeComboBox.addActionListener(e -> {
            String selectedSize = (String) sizeComboBox.getSelectedItem();
            int size = Integer.parseInt(selectedSize.substring(0, 1));
            principal.tamano(size);
        });

        txtTamano.setEditable(false);
        txtDificultad.setEditable(false);
        
        deselectOtherCheckBoxes(facilCheckBox, normalCheckBox, dificilCheckBox);
    }

    private void deselectOtherCheckBoxes(JCheckBox selectedCheckBox, JCheckBox checkBox1, JCheckBox checkBox2) {
        selectedCheckBox.setSelected(true);
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
    }
}
