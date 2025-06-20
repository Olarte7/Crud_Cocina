package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/crud_cocina";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    public static Connection getConexion() {
        try {
            // Asegura que el driver esté disponible
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: no se encontró el driver de MySQL.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return null;
    }
}
