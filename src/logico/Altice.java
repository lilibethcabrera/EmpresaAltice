package logico;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import logico.Cliente;

public class Altice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Empleado> misEmpleados;
	private static Altice altice;
	private boolean facturacion_realizada;
	private Altice() {
		super();
		misClientes = new ArrayList<>();
		misPlanes = new ArrayList<>();
		misFacturas = new ArrayList<>();
		misEmpleados = new ArrayList<>();
		facturacion_realizada = false;
	}
	public static Altice getInstance() {
		if(altice == null) {
			altice = new Altice();
		}
		return altice;
	}
	public static void setInstance(Altice miAltice) {
		if(altice == null) {
			altice = miAltice;
		}
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void agregarCliente(Cliente cliente) {
		misClientes.add(cliente);
	}

	public ArrayList<Empleado> getMisEmpleados() {
		return misEmpleados;
	}
	public void agregarEmpleado(Empleado empleado) {
		misEmpleados.add(empleado);
	}
	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}

	public void agregarPlan(Plan plan) {
		misPlanes.add(plan);
	}
	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void agregarFactura(Factura factura) {
		misFacturas.add(factura);
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
	public int indiceClientePorCedula(String selecte) {
		int i;
		
		for(i = 0; i < misClientes.size(); i++) {
			if(misClientes.get(i).getCedula().equalsIgnoreCase(selecte)) {
				return i;
			}
		}
		return -1;

	}
	public void modificarCliente(Cliente client, String cedulaVieja) {
		int aux = indiceClientePorCedula(cedulaVieja);
		misClientes.set(aux, client);
	
		
	}
	public void modificarEmpleado(Empleado empleado, String cedulaVieja) {
		int aux = indiceEmpleadoPorCedula(cedulaVieja);
		misEmpleados.set(aux, empleado);
		
	}
	public void modificarPlan(Plan plan) {
		int aux = indicePlanPorId(plan.getId());
		misPlanes.set(aux, plan);
	}
	public int indiceEmpleadoPorCedula(String selecte) {
		int i;
		for(i = 0; i < misEmpleados.size(); i++) {
			if(misEmpleados.get(i).getCedula().equalsIgnoreCase(selecte)) {
				return i;
			}
		}
		return -1;
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
	public void agregarFacturaCliente(String cedula, Factura factura) {
		for(Cliente miCliente : misClientes) {
			if(miCliente.getCedula().equalsIgnoreCase(cedula)){
				miCliente.agregarFactura(factura);
				break;
			}
		}
	}
	public void agregarPlanCliente(Plan plan, String cedula) {
		for(Cliente miCliente : misClientes) {
			if(miCliente.getCedula().equalsIgnoreCase(cedula)){
				miCliente.agregarPlan(plan);
				break;
			}
		}
	}
	public void pagarFactura(String selecte) {
		for(Factura factura : misFacturas) {
			if(factura.getId().equalsIgnoreCase(selecte)) {
				factura.setPagada(true);
				break;
			}
		}
	}
	public void facturarCliente() {
		Factura factura = null;
		
		Date hoy = new Date(), fechaPlan = null, fechaRecargo;
		LocalDate localDate_hoy = hoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDate_plan;
		String id_factura;
		Calendar c = Calendar.getInstance();
		
		
		for(Cliente cliente: misClientes) {
			for(Plan plan : cliente.getMisPlanes()) {
					fechaPlan =plan.getFecha_apertura();
					localDate_plan = fechaPlan.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					if(localDate_hoy.getMonthValue() != localDate_plan.getMonthValue() || localDate_hoy.getYear() != localDate_plan.getYear() ) {
						if(misFacturas.size() == 0) {
							id_factura = "0";
						}else {
							int index = misFacturas.size();
							id_factura = Integer.toString((Integer.parseInt(misFacturas.get(index).getId()) + 1));
						}
						c.setTime(new Date());
						c.add(Calendar.DATE, 20);//Tiene 20 dias para pagar
						fechaRecargo = c.getTime();
						factura = new Factura(id_factura, new Date(), fechaRecargo, plan.getMensualidad(),plan,cliente, false);
						cliente.agregarFactura(factura);
						agregarFactura(factura);
					}
				
				
			}
		}
		setFacturacion_realizada(true);

	}
	public boolean isFacturacion_realizada() {
		return facturacion_realizada;
	}
	public void setFacturacion_realizada(boolean facturacion_realizada) {
		this.facturacion_realizada = facturacion_realizada;
	}

}
