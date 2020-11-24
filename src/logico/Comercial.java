package logico;

import java.util.Date;

public class Comercial extends Empleado {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String zona;
	

	public Comercial(String cedula, String nombre, String direccion, Date fechaNacimiento, Date fechaContratacion,
			float salario, String telefono, String zona) {
		super(cedula, nombre, direccion, fechaNacimiento, fechaContratacion, salario, telefono);
		this.zona = zona;
	}
	
	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}


}
