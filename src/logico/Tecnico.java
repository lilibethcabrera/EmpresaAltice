package logico;

import java.util.Date;

public class Tecnico extends Empleado {
	private String zonaAsignada;

	public Tecnico(String cedula, String nombre, String direccion, Date fechaNacimiento, Contacto[] contactoReferencia,
			Date fechaContratacion, float salario, String telefono, String zonaAsignada) {
		super(cedula, nombre, direccion, fechaNacimiento, contactoReferencia, fechaContratacion, salario, telefono);
		this.zonaAsignada = zonaAsignada;
	}

	public String getZonaAsignada() {
		return zonaAsignada;
	}

	public void setZonaAsignada(String zonaAsignada) {
		this.zonaAsignada = zonaAsignada;
	}

}
