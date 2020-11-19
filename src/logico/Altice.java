package logico;

import java.util.ArrayList;

public class Altice {
	
	private ArrayList<Cliente> misClientes;
	private ArrayList<Plan> misPlanes;
	private ArrayList<String> numerosActivos;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Sucursal> misSucursales;

	public Altice() {
		misClientes = new ArrayList<>();
		misPlanes = new ArrayList<>();
		numerosActivos = new ArrayList<>();
		misFacturas = new ArrayList<>();
		misSucursales = new ArrayList<>();
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

}
