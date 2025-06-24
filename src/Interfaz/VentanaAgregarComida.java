package Interfaz;

import DAO.ComidaDAO;
import Modelo.Comida;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class VentanaAgregarComida extends Frame {

    private TextField txtNombre;
    private TextArea txtDescripcion;
    private TextField txtNivelDificultad;
    private TextField txtTiempoEstimado;
    private TextField txtRutaImagen;
    private Button btnSeleccionarImagen;
    private Button btnGuardar;
    private Button btnAtras;

    public VentanaAgregarComida() {
        setTitle("Agregar Comida");
        setSize(500, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setBackground(new Color(245, 245, 245)); // Fondo claro

        // Fuente general
        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);

        // Título
        Label titulo = new Label("Registrar Comida");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setAlignment(Label.CENTER);
        titulo.setBackground(new Color(33, 150, 243));
        titulo.setForeground(Color.WHITE);
        titulo.setPreferredSize(new Dimension(500, 60));
        add(titulo, BorderLayout.NORTH);

        // Panel de formulario con GridLayout
        Panel formulario = new Panel(new GridLayout(6, 2, 20, 20));
        formulario.setBackground(new Color(245, 245, 245));
        formulario.setFont(fuente);
        formulario.setPreferredSize(new Dimension(400, 300));
        formulario.setLayout(new GridLayout(10, 10, 20, 20));
  

        // Campos
        txtNombre = new TextField(10);
        txtNombre.setFont(fuente);

        txtDescripcion = new TextArea(3, 30);
        txtDescripcion.setFont(fuente);

        txtNivelDificultad = new TextField(10);
        txtNivelDificultad.setFont(fuente);

        txtTiempoEstimado = new TextField(10);
        txtTiempoEstimado.setFont(fuente);

        txtRutaImagen = new TextField(25);
        txtRutaImagen.setEditable(false);
        txtRutaImagen.setFont(fuente);

        btnSeleccionarImagen = new Button("Seleccionar Imagen");
        btnSeleccionarImagen.setBackground(new Color(76, 175, 80));
        btnSeleccionarImagen.setForeground(Color.WHITE);
        btnSeleccionarImagen.setFont(fuente);
        
        btnGuardar = new Button("Guardar Comida");
        btnGuardar.setFont(fuente);
        btnGuardar.setBackground(new Color(33, 150, 243));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(fuente);
        
        btnAtras = new Button("Atrás");
        btnAtras.setFont(fuente);
        btnAtras.setPreferredSize(new Dimension(250, 40));
        btnAtras.setBackground(new Color(255, 87, 34));  // Color naranja
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setFont(fuente);


        // Añadir componentes al formulario
        formulario.add(new Label("Nombre:")); formulario.add(txtNombre);
        formulario.add(new Label("Descripción:")); formulario.add(txtDescripcion);
        formulario.add(new Label("Nivel de Dificultad:")); formulario.add(txtNivelDificultad);
        formulario.add(new Label("Tiempo Estimado (min):")); formulario.add(txtTiempoEstimado);
        formulario.add(new Label("Imagen:")); formulario.add(txtRutaImagen);
        formulario.add(new Label("")); formulario.add(btnSeleccionarImagen);
        formulario.add(new Label("")); formulario.add(btnGuardar);
        formulario.add(new Label("")); formulario.add(btnAtras);

        add(formulario, BorderLayout.CENTER);

        btnSeleccionarImagen.addActionListener(e -> {
            // Crear un cuadro de diálogo para seleccionar un archivo de imagen
            FileDialog fileDialog = new FileDialog(this, "Seleccionar Imagen", FileDialog.LOAD);
            fileDialog.setFile("*.jpg;*.png;*.gif");  // Filtro de tipo de archivo
            fileDialog.setVisible(true);

            // Obtener el archivo seleccionado
            String archivoSeleccionado = fileDialog.getDirectory() + fileDialog.getFile();
            if (archivoSeleccionado != null && !archivoSeleccionado.isEmpty()) {
                txtRutaImagen.setText(archivoSeleccionado);  // Mostrar la ruta del archivo en el TextField
            }
        });
        
        // Acción del botón "Atrás"
        btnAtras.addActionListener(e -> {
            new VentanaPrincipal();  // Abre la ventana principal
            dispose();  // Cierra la ventana actual
        });

        // Acción: guardar comida
        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                int nivel = Integer.parseInt(txtNivelDificultad.getText());
                int tiempo = Integer.parseInt(txtTiempoEstimado.getText());

                File archivoImagen = new File(txtRutaImagen.getText());
                byte[] imagenBytes = Files.readAllBytes(archivoImagen.toPath());

                Comida comida = new Comida(0, nombre, descripcion, nivel, tiempo, imagenBytes);
                ComidaDAO dao = new ComidaDAO();

                boolean resultado = dao.agregarComida(comida);
                if (resultado) {
                    JOptionPane.showMessageDialog(this, "✅ Comida guardada exitosamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Error al guardar comida.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            }
        });

        // Cerrar ventana al hacer clic en la X
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtNivelDificultad.setText("");
        txtTiempoEstimado.setText("");
        txtRutaImagen.setText("");
    }
}
