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
    private Button btnEliminar;

    public VentanaPrincipal() {
        setTitle("Menú de Comidas");
        setSize(600, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setBackground(new Color(245, 245, 245));

        // Título
        Label titulo = new Label("Menú");
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
        
        Panel panelBotones = new Panel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));  // Centrado horizontal
        panelBotones.setBackground(new Color(245, 245, 245));

        // Botón Agregar Comida
        btnAgregarComida = new Button("Agregar Comida");
        btnAgregarComida.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnAgregarComida.setBackground(new Color(33, 150, 243));
        btnAgregarComida.setForeground(Color.WHITE);
        btnAgregarComida.setPreferredSize(new Dimension(200, 40));
        
        btnEliminar = new Button("Eliminar");
        btnEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnEliminar.setBackground(new Color(255, 87, 34));  // Color naranja
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setPreferredSize(new Dimension(200, 40));
        
        panelBotones.add(btnAgregarComida);
        panelBotones.add(btnEliminar);  // Añadir el botón Eliminar al lado del botón Agregar

        // Añadir el panel de botones al pie de la ventana
        add(panelBotones, BorderLayout.SOUTH);

        // Acción al hacer clic en "Agregar Comida"
        btnAgregarComida.addActionListener(e -> {
            new VentanaAgregarComida();
            dispose();  // Cierra la ventana actual
        });
        
        // Cargar las comidas
        cargarComidas(panelComidas);

        setVisible(true);
        
        btnEliminar.addActionListener(e -> {
            // Mostrar un cuadro de entrada para que el usuario ingrese el nombre de la comida
            String nombreComida = JOptionPane.showInputDialog(this, "Ingrese el nombre de la comida a eliminar:");

            if (nombreComida != null && !nombreComida.isEmpty()) {
                // Crear una instancia de ComidaDAO
                ComidaDAO daoEliminacion = new ComidaDAO();

                // Acción para eliminar la comida por nombre
                boolean eliminado = daoEliminacion.eliminarComida(nombreComida);  // Llamamos al nuevo método para eliminar por nombre
                
                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Comida '" + nombreComida + "' eliminada exitosamente.");
                    // Recargar el menú después de eliminar la comida
                    panelComidas.removeAll();  // Limpiar el panel de comidas
                    cargarComidas(panelComidas);  // Recargar las comidas actualizadas
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la comida. Asegúrese de que el nombre sea correcto.");
                }
            } else {
                // Si el nombre está vacío o se canceló la operación
                JOptionPane.showMessageDialog(this, "Operación cancelada o nombre inválido.");
            }
        });
    }

    private void cargarComidas(Panel panelComidas) {
        // Recuperamos las comidas de la base de datos
        ComidaDAO dao = new ComidaDAO();
        List<Comida> comidas = dao.obtenerComidas();

        // Por cada comida, mostramos su nombre y foto
        for (Comida comida : comidas) {
            Panel comidaPanel = new Panel();
            comidaPanel.setLayout(new BoxLayout(comidaPanel, BoxLayout.Y_AXIS)); // Organiza en columna
            comidaPanel.setBackground(new Color(245, 245, 245));

            Panel subPanel = new Panel();
            subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Centrar imagen y texto
            subPanel.setBackground(new Color(245, 245, 245));

            // Mostrar imagen
            byte[] imagenBytes = comida.getImagen();
            if (imagenBytes != null) {
                try {
                    // Convertimos los bytes en imagen
                    Image imagen = ImageIO.read(new ByteArrayInputStream(imagenBytes));
                    imagen = imagen.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
                    JLabel imagenLabel = new JLabel(new ImageIcon(imagen));
                    subPanel.add(imagenLabel);  // Añadir imagen al subPanel

                    // Agregar acción de clic en la imagen para mostrar detalles
                    imagenLabel.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            // Crear la nueva ventana con la información de la comida
                            new VentanaDetallesComida(comida);
                        }
                    });
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            Label nombre = new Label(comida.getNombre());
            nombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            nombre.setAlignment(Label.CENTER);
            subPanel.add(nombre);  // Añadir nombre debajo de la imagen
            
            // Añadir subPanel al comidaPanel
            comidaPanel.add(subPanel);
           
            // Añadir el panel de comida a la ventana
            panelComidas.add(comidaPanel);
        }
        
        

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
