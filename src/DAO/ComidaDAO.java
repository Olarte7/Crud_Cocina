package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modelo.Comida;
import Modelo.Conexion;

public class ComidaDAO {

	public boolean agregarComida (Comida comida) {
		String sql = "INSERT INTO comidas (nombre, descripcion, id_nivel_dificultad, tiempo_estimado, ruta_imagen) VALUES (?,?,?,?,?)";
		
		try (Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql)) {
			
	        stmt.setString(1, comida.getNombre());
	        stmt.setString(2, comida.getDescripcion());
	        stmt.setInt(3, comida.getIdNivelDificultad());
	        stmt.setInt(4, comida.getTiempoEstimado());
	        stmt.setString(5, comida.getRutaImagen());

	        stmt.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        System.out.println("Error al agregar comida: " + e.getMessage());
	        return false;
	    }
	}
}
