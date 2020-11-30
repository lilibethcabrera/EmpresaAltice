package logico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import logico.Cliente;

public class Altice implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Empleado> misEmpleados;
	private static Altice altice;
	private Altice() {
		super();
		misClientes = new ArrayList<>();
		misPlanes = new ArrayList<>();
		misFacturas = new ArrayList<>();
		misEmpleados = new ArrayList<>();
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
		
		for(Factura factura : misFacturas) {
			if(factura.getId().equalsIgnoreCase(id)) {
				return factura;
			}
		}
		
		return null;
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
	public Factura facturar(Plan plan, Cliente cliente) {
		String id_actual = Integer.toString(Integer.parseInt(misFacturas.get(misFacturas.size() - 1).getId())+1);
		Factura factura = new Factura(id_actual, new Date(),plan.getMensualidad(),plan,cliente,false);
		plan.setUltimoMesFacturado(Calendar.getInstance().get(Calendar.MONTH) + 1);
		misFacturas.add(factura);
		return factura;
	}
	public void imprimirFactura(Factura factura) throws IOException {
		String directorio = new File(".").getCanonicalPath() + "\\src\\facturas\\"+"factura-" + factura.getId() +".txt";
		File fout = new File(directorio);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		bw.write("Factura Id." + factura.getId());
		bw.newLine();
		bw.write("Fecha de Facturacion: " + format.format(factura.getFecha_facturacion()));
		bw.newLine();
		bw.write("---------------------------------------------");
		bw.newLine();
		bw.write("A nombre de: " + factura.getCliente().getNombre() + " | Cedula: " + factura.getCliente().getCedula());
		bw.newLine();
		bw.write("Plan de ID: " + factura.getPlan().getId());
		bw.newLine();
		bw.write("---------------------------------------------");
		bw.newLine();
		bw.write("Monto: " + factura.getMonto());
		bw.newLine();
		bw.write("Estado Factura: " + (factura.isPagada() ? "Pagada" : "No pagada"));
		bw.close();
		
	}

}
