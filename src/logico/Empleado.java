package logico;

import java.util.Date;

public abstract class Empleado extends Persona {
	protected Date fechaContratacion;
	protected float salario;
	protected String telefono;
	public Empleado(String cedula, String nombre, String direccion, Date fechaNacimiento,
			Date fechaContratacion, float salario, String telefono) {
		super(cedula, nombre, direccion, fechaNacimiento);
		this.fechaContratacion = fechaContratacion;
		this.salario = salario;
		this.telefono = telefono;
	}
	public Date getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
