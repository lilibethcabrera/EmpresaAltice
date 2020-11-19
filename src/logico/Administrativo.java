package logico;

import java.util.Date;

public class Administrativo extends Empleado {
	private String puesto;

	public Administrativo(String cedula, String nombre, String direccion, Date fechaNacimiento,
			Contacto[] contactoReferencia, Date fechaContratacion, float salario, String telefono, String puesto) {
		super(cedula, nombre, direccion, fechaNacimiento, contactoReferencia, fechaContratacion, salario, telefono);
		this.puesto = puesto;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	

}
