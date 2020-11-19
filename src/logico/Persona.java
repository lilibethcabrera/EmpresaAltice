package logico;

import java.util.Date;

public abstract class Persona {
	protected String cedula;
	protected String nombre;
	protected String direccion;
	protected Date fechaNacimiento;
	protected Contacto[] contactoReferencia; //Dos contactos de referencia
	
	public Persona(String cedula, String nombre, String direccion, Date fechaNacimiento,
			Contacto[] contactoReferencia) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.contactoReferencia = contactoReferencia;
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

	public Contacto[] getContactoReferencia() {
		return contactoReferencia;
	}

	public void setContactoReferencia(Contacto[] contactoReferencia) {
		this.contactoReferencia = contactoReferencia;
	}
	
	

}
