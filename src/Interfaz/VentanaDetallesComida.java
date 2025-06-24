package Interfaz;

import Modelo.Comida;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class VentanaDetallesComida extends JFrame {

    public VentanaDetallesComida(Comida comida) {
        // Configuración de la ventana
        setTitle("Detalles de la Comida");
        setSize(500, 500);  // Tamaño ajustado para ver toda la información
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana al hacer clic en X

        // Panel de contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        // Título de la comida
        JLabel labelNombre = new JLabel("Nombre: " + comida.getNombre(), JLabel.LEFT);
        labelNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(labelNombre, BorderLayout.NORTH);

        // Panel de detalles (donde pondremos los otros detalles)
        JPanel detallesPanel = new JPanel();
        detallesPanel.setLayout(new GridLayout(4, 1, 10, 10));  // Layout para los detalles
        detallesPanel.setBackground(new Color(245, 245, 245));

        // Mostrar los detalles de la comida
        JLabel descripcionLabel = new JLabel("<html><b>Descripción:</b> " + comida.getDescripcion() + "</html>");
        descripcionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel dificultadLabel = new JLabel("<html><b>Nivel de dificultad:</b> " + comida.getIdNivelDificultad() + "</html>");
        dificultadLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel tiempoLabel = new JLabel("<html><b>Tiempo estimado:</b> " + comida.getTiempoEstimado() + " minutos</html>");
        tiempoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Añadir los detalles al panel
        detallesPanel.add(descripcionLabel);
        detallesPanel.add(dificultadLabel);
        detallesPanel.add(tiempoLabel);

        // Mostrar imagen de la comida
        byte[] imagenBytes = comida.getImagen();
        if (imagenBytes != null) {
            ImageIcon imageIcon = new ImageIcon(imagenBytes);
            Image imagen = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel imagenLabel = new JLabel(new ImageIcon(imagen));
            panel.add(imagenLabel, BorderLayout.CENTER);  // Añadir imagen al panel principal
        }

        // Añadir el panel de detalles al panel principal
        panel.add(detallesPanel, BorderLayout.SOUTH);

        // Hacer la ventana visible
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Crear un ejemplo de comida
        Comida comidaEjemplo = new Comida(1, "Pizza", "Deliciosa pizza con queso", 3, 30, new byte[0]);
        new VentanaDetallesComida(comidaEjemplo);
    }
}
