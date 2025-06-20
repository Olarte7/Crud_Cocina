package Modelo;

public class Comida {
	private int id;
	private String nombre;
	private String descripcion;
	private int nivelDificultad;
	private int tiempoEstimado;
	private String rutaImagen;
	
	public Comida (int id, String nombre, String descripcion, int nivelDificultad, int tiempoEstimado, String rutaImagen) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivelDificultad = nivelDificultad;
		this.tiempoEstimado = tiempoEstimado;
		this.rutaImagen = rutaImagen;
	}
	
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    
    public int getIdNivelDificultad() {return nivelDificultad;}
    public void setIdNivelDificultad (int nivelDificultad) {this.nivelDificultad = nivelDificultad;}
    
    public int getTiempoEstimado() {return tiempoEstimado;}
    public void setTiempoEstimado(int tiempoEstimado) {this.tiempoEstimado = tiempoEstimado;}
    
    public String getRutaImagen() {return rutaImagen;}
    public void setRutaImagen(String rutaImagen) {this.rutaImagen = rutaImagen;}
}
