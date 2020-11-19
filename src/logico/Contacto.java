package logico;

public class Contacto {
	private String nombre;
	private String parentesco;
	private String telefono;
	public Contacto(String nombre, String parentesco, String telefono) {
		this.nombre = nombre;
		this.parentesco = parentesco;
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getParentesco() {
		return parentesco;
	}
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

}
