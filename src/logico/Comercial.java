package logico;

import java.util.Date;

public class Comercial extends Empleado {
	private Sucursal miSucursal;

	public Comercial(String cedula, String nombre, String direccion, Date fechaNacimiento,
			Contacto[] contactoReferencia, Date fechaContratacion, float salario, String telefono,
			Sucursal miSucursal) {
		super(cedula, nombre, direccion, fechaNacimiento, contactoReferencia, fechaContratacion, salario, telefono);
		this.miSucursal = miSucursal;
	}

	public Sucursal getMiSucursal() {
		return miSucursal;
	}

	public void setMiSucursal(Sucursal miSucursal) {
		this.miSucursal = miSucursal;
	}
	

}
