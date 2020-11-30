package logico;

import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Factura> misFacturas;
	
	public Cliente(String cedula, String nombre, String direccion, Date fechaNacimiento) {
		super(cedula, nombre, direccion, fechaNacimiento);
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
	public Factura buscarFacturaPorId(String id) {
		Factura miFactura = null;
		
		for(Factura factura : misFacturas) {
			if(factura.getId().equalsIgnoreCase(id)) {
				miFactura = factura;
				break;
			}
		}
		
		return miFactura;
	}
	public Plan buscarPlanPorId(String Id) {
		Plan miPlan = null;
		
		for(Plan plan: misPlanes) {
			if(plan.getId().equalsIgnoreCase(Id)) {
				miPlan = plan;
				break;
			}
		}
		
		return miPlan;
	}
	public int getCantFacturasActivas() {
		int cantidadFacturas = 0;
		
		for(Factura factura : misFacturas) {
			if(!factura.isPagada()) {
				cantidadFacturas++;
			}
		}
		
		return cantidadFacturas;
	}
	public int indicePlanPorId(String selecte) {
		int i;
		for(i = 0; i < misPlanes.size(); i++) {
			if(misPlanes.get(i).getId().equalsIgnoreCase(selecte)) {
				return i;
			}
		}
		return -1;
	}
	public void pagarFactura(String selecte) {
		for(Factura factura : misFacturas) {
			if(factura.getId().equalsIgnoreCase(selecte)) {
				factura.setPagada(true);
				Altice.getInstance().pagarFactura(selecte);
				break;
			}
		}
	}

}
