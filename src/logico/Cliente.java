package logico;

import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Persona {
	private ArrayList<Plan> misPlanes;
	private ArrayList<Factura> misFacturas;
	public Cliente(String cedula, String nombre, String direccion, Date fechaNacimiento,
			Contacto[] contactoReferencia) {
		super(cedula, nombre, direccion, fechaNacimiento, contactoReferencia);
		misPlanes = new ArrayList<>();
		misFacturas = new ArrayList<>();
	}
	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}
	public void agregarPlan(Plan nuevoPlan) {
		misPlanes.add(nuevoPlan);
	}
	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}
	public void agregarFactura(Factura nuevaFactura) {
		misFacturas.add(nuevaFactura);
	}

}
