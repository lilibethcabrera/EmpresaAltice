package logico;

import java.util.ArrayList;

public class Altice {
	
	private ArrayList<Cliente> misClientes;
	private ArrayList<Plan> misPlanes;
	private ArrayList<String> numerosActivos;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Sucursal> misSucursales;
	private ArrayList<Empleado> misEmpleados;

	public Altice() {
		misClientes = new ArrayList<>();
		misPlanes = new ArrayList<>();
		numerosActivos = new ArrayList<>();
		misFacturas = new ArrayList<>();
		misSucursales = new ArrayList<>();
		misEmpleados = new ArrayList<>();
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void agregarCliente(Cliente cliente) {
		misClientes.add(cliente);
	}

	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}

	public void agregarPlan(Plan plan) {
		misPlanes.add(plan);
	}

	public ArrayList<String> getNumerosActivos() {
		return numerosActivos;
	}

	public void agregarNumeroActivo(String numero) {
		numerosActivos.add(numero);
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void agregarFactura(Factura factura) {
		misFacturas.add(factura);
	}

	public ArrayList<Sucursal> getMisSucursales() {
		return misSucursales;
	}

	public void agregarSucursal(Sucursal sucursal) {
		misSucursales.add(sucursal);
	}
	
	public Empleado buscarEmpleadoPorCedula(String cedula) {
		Empleado miEmpleado = null;
		
		for(Empleado empleado : misEmpleados) {
			if(empleado.getCedula().equalsIgnoreCase(cedula)) {
				miEmpleado = empleado;
				break;
			}
		}
		
		return miEmpleado;
	}
	
	public Cliente buscarClientePorCedula(String cedula) {
		Cliente miCliente = null;
		
		for(Cliente cliente : misClientes) {
			if(cliente.getCedula().equalsIgnoreCase(cedula)) {
				miCliente = cliente;
				break;
			}
		}
		
		return miCliente;
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

}
