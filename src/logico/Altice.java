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
	public static Altice getInstance() {//Singelton
		if(altice == null) {
			altice = new Altice();
		}
		return altice;
	}
	public static void setInstance(Altice miAltice) {//Agregar el objeto altice al cargar
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
	
	//Retorna un empleado y recibe su cedula como parametro para buscar
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
	//Retorna un Cliente y recibe su cedula como parametro para buscar
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
	//Retorna un Plan y recibe su id como parametro para buscar
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
	//Retorna un factura y recibe su id como parametro para buscar

	public Factura buscarFacturaPorId(String id) {
		
		for(Factura factura : misFacturas) {
			if(factura.getId().equalsIgnoreCase(id)) {
				return factura;
			}
		}
		
		return null;
	}
	//Retorna el indice del cliente y recibe su cedula como parametro para buscar
	//Retorna -1 si no se encuentra

	public int indiceClientePorCedula(String selecte) {
		int i;
		
		for(i = 0; i < misClientes.size(); i++) {
			if(misClientes.get(i).getCedula().equalsIgnoreCase(selecte)) {
				return i;
			}
		}
		return -1;

	}
	//Modifica el cliente, recibe su cedula vieja para buscar y un objeto Cliente, que sera el nuevo cliente
	public void modificarCliente(Cliente client, String cedulaVieja) {
		int aux = indiceClientePorCedula(cedulaVieja);
		misClientes.set(aux, client);
	
		
	}
	
	//Modifica el empleado, recibe su cedula vieja para buscar y un objeto Empleado, que sera el nuevo empleado
	public void modificarEmpleado(Empleado empleado, String cedulaVieja) {
		int aux = indiceEmpleadoPorCedula(cedulaVieja);
		misEmpleados.set(aux, empleado);
		
	}
	///Modifica un plan ya existen, recibe un objeto Plan con los nuevos datos
	public void modificarPlan(Plan plan) {
		int aux = indicePlanPorId(plan.getId());
		misPlanes.set(aux, plan);
	}
	
	//Retorna el indice del empleado y recibe su cedula como parametro para buscar
	//Retorna -1 si no se encuentra
	public int indiceEmpleadoPorCedula(String selecte) {
		int i;
		for(i = 0; i < misEmpleados.size(); i++) {
			if(misEmpleados.get(i).getCedula().equalsIgnoreCase(selecte)) {
				return i;
			}
		}
		return -1;
	}
	//Retorna el indice del plan y recibe su id como parametro para buscar
	//Retorna -1 si no se encuentra
	public int indicePlanPorId(String selecte) {
		int i;
		for(i = 0; i < misPlanes.size(); i++) {
			if(misPlanes.get(i).getId().equalsIgnoreCase(selecte)) {
				return i;
			}
		}
		return -1;
	}
	///Asigna una factura al cliente que coincida con la cedula recibida
	public void agregarFacturaCliente(String cedula, Factura factura) {
		Cliente cliente = buscarClientePorCedula(cedula);
		cliente.agregarFactura(factura);
	}
	///Asigna un plan al cliente que coincida con la cedula recibida. El plan se crea como un nuevo objeto
	public void agregarPlanCliente(Plan plan, String cedula) {
		
		for(Cliente miCliente : misClientes) {
			if(miCliente.getCedula().equalsIgnoreCase(cedula)){
				Plan miPlan = new Plan(plan.getId(),plan.getPrecio_apertura(),plan.getMinutos(),plan.getVelocidad_internet(),
						plan.getCanales(),plan.getMensualidad()); //Se crea una copia del plan
				miCliente.agregarPlan(miPlan);
				break;
			}
		}
	}
	///Paga la factura con el id recibido
	public void pagarFactura(String selecte) {
		for(Factura factura : misFacturas) {
			if(factura.getId().equalsIgnoreCase(selecte)) {
				factura.setPagada(true);
				break;
			}
		}
	}
	///Genera una factura para el cliente y el plan recibido y lo retorna
	public Factura facturar(Plan plan, Cliente cliente) {
		String id_actual = Integer.toString(Integer.parseInt(misFacturas.get(misFacturas.size() - 1).getId())+1);
		Factura factura = new Factura(id_actual, new Date(),plan.getMensualidad(),plan,cliente,false);
		plan.setUltimoMesFacturado(Calendar.getInstance().get(Calendar.MONTH) + 1);
		misFacturas.add(factura);
		return factura;
	}
	//Imprime un archivo txt la factura enviada por parametros
	public void imprimirFactura(Factura factura) throws IOException {
		//GetCanonicalPath, obtiene la direccion del proyecto, y se usa esta direccion para guardas las facturas imprimidas
		String directorio = new File(".").getCanonicalPath() + "\\src\\facturas\\factura-" + factura.getId() +".txt";
		File fout = new File(directorio);
		
		///Se crea y se maneja los archivos de texto
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		bw.write("Factura Id." + factura.getId());
		bw.newLine();//Se agrega  un salto de linea al archivo
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
