package logico;

import java.util.Date;

public class Administrativo extends Empleado {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String puesto;

	public Administrativo(String cedula, String nombre, String direccion, Date fechaNacimiento, Date fechaContratacion, float salario, String telefono, String puesto) {
		super(cedula, nombre, direccion, fechaNacimiento,fechaContratacion, salario, telefono);
		this.puesto = puesto;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	

}
