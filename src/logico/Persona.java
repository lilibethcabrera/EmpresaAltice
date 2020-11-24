package logico;

import java.io.Serializable;
import java.util.Date;

public abstract class Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String cedula;
	protected String nombre;
	protected String direccion;
	protected Date fechaNacimiento;
	
	public Persona(String cedula, String nombre, String direccion, Date fechaNacimiento) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	

}
