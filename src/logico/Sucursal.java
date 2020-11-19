package logico;

import java.util.ArrayList;

public class Sucursal {
	private String id;
	private String provincia;
	private String zona;
	private String direccion;
	private ArrayList<Comercial> empleados;
	public Sucursal(String id, String provincia, String zona, String direccion) {
		this.id = id;
		this.provincia = provincia;
		this.zona = zona;
		this.direccion = direccion;
		this.empleados = new ArrayList<>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public ArrayList<Comercial> getEmpleados() {
		return empleados;
	}
	public void agregarEmpleado(Comercial empleado) {
		empleados.add(empleado);
	}
	

}
