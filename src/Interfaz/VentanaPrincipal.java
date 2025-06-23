package Interfaz;

import DAO.ComidaDAO;
import Modelo.Comida;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import java.io.IOException;

public class VentanaPrincipal extends Frame {

    private Button btnAgregarComida;

    public VentanaPrincipal() {
        setTitle("Menú de Comidas");
        setSize(600, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setBackground(new Color(245, 245, 245));

        // Título
        Label titulo = new Label("Comidas Disponibles");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setAlignment(Label.CENTER);
        titulo.setBackground(new Color(33, 150, 243));
        titulo.setForeground(Color.WHITE);
        titulo.setPreferredSize(new Dimension(600, 60));
        add(titulo, BorderLayout.NORTH);

        // Panel para mostrar comidas
        Panel panelComidas = new Panel();
        panelComidas.setLayout(new GridLayout(0, 1, 10, 10));  // Lista vertical de comidas
        panelComidas.setBackground(new Color(245, 245, 245));
        JScrollPane scrollComidas = new JScrollPane(panelComidas);
        add(scrollComidas, BorderLayout.CENTER);

        // Botón Agregar Comida
        btnAgregarComida = new Button("Agregar Comida");
        btnAgregarComida.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnAgregarComida.setBackground(new Color(33, 150, 243));
        btnAgregarComida.setForeground(Color.WHITE);
        btnAgregarComida.setPreferredSize(new Dimension(200, 40));

        // Panel para el botón
        Panel panelBoton = new Panel();
        panelBoton.add(btnAgregarComida);
        panelBoton.setBackground(new Color(245, 245, 245));
        add(panelBoton, BorderLayout.SOUTH);

        // Acción al hacer clic en "Agregar Comida"
        btnAgregarComida.addActionListener(e -> {
            new VentanaAgregarComida();
            dispose();  // Cierra la ventana actual
        });

        // Cargar las comidas
        cargarComidas(panelComidas);

        setVisible(true);
    }

    private void cargarComidas(Panel panelComidas) {
        // Recuperamos las comidas de la base de datos
        ComidaDAO dao = new ComidaDAO();
        List<Comida> comidas = dao.obtenerComidas();

        // Por cada comida, mostramos su nombre y foto
        for (Comida comida : comidas) {
            Panel comidaPanel = new Panel();
            comidaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            comidaPanel.setBackground(new Color(245, 245, 245));

            // Mostrar nombre
            Label nombre = new Label(comida.getNombre());
            nombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            comidaPanel.add(nombre);

            // Mostrar imagen
            byte[] imagenBytes = comida.getImagen();
            if (imagenBytes != null) {
                try {
                    // Convertimos los bytes en imagen
                    Image imagen = ImageIO.read(new ByteArrayInputStream(imagenBytes));
                    imagen = imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    JLabel imagenLabel = new JLabel(new ImageIcon(imagen));
                    comidaPanel.add(imagenLabel);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            // Añadir el panel de comida a la ventana
            panelComidas.add(comidaPanel);
        }
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
