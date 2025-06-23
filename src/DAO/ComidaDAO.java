package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modelo.Comida;
import Modelo.Conexion;

public class ComidaDAO {

	public boolean agregarComida (Comida comida) {
		String sql = "INSERT INTO comidas (nombre, descripcion, id_nivel_dificultad, tiempo_estimado, Imagen) VALUES (?,?,?,?,?)";
		
		try (Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql)) {
			
	        stmt.setString(1, comida.getNombre());
	        stmt.setString(2, comida.getDescripcion());
	        stmt.setInt(3, comida.getIdNivelDificultad());
	        stmt.setInt(4, comida.getTiempoEstimado());
	        stmt.setBytes(5, comida.getRutaImagen());

	        stmt.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        System.out.println("Error al agregar comida: " + e.getMessage());
	        return false;
	    }
	}
	
    public List<Comida> obtenerComidas() {
        List<Comida> listaComidas = new ArrayList<>();
        String sql = "SELECT * FROM comidas";  // Consulta SQL para obtener todas las comidas

        try (Connection con = Conexion.getConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Crear un objeto Comida con los resultados de la base de datos
                Comida comida = new Comida(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_nivel_dificultad"),
                        rs.getInt("tiempo_estimado"),
                        rs.getBytes("imagen")  // Obtener la imagen como bytes
                );
                listaComidas.add(comida);  // Agregar la comida a la lista
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las comidas: " + e.getMessage());
        }
        return listaComidas;  // Devolver la lista de comidas
    }
}	
