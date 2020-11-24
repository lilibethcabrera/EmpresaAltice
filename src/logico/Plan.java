package logico;

import java.util.Date;

public class Plan {
	private String id;
	private float precio_apertura;
	private Date fecha_apertura;
	private int minutos;
	private int velocidad_internet;
	private int canales;
	private float mensualidad;
	
	
	public Plan(String id, float precio_apertura, int minutos, int velocidad_internet, int canales, float mensualidad) {
		this.id = id;
		this.precio_apertura = precio_apertura;
		this.minutos = minutos;
		this.velocidad_internet = velocidad_internet;
		this.canales = canales;
		this.mensualidad = mensualidad;
	}

	public float getPrecio_apertura() {
		return precio_apertura;
	}

	public void setPrecio_apertura(float precio_apertura) {
		this.precio_apertura = precio_apertura;
	}

	public Date getFecha_apertura() {
		return fecha_apertura;
	}

	public void setFecha_apertura(Date fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getVelocidad_internet() {
		return velocidad_internet;
	}

	public void setVelocidad_internet(int velocidad_internet) {
		this.velocidad_internet = velocidad_internet;
	}

	public int getCanales() {
		return canales;
	}

	public void setCanales(int canales) {
		this.canales = canales;
	}

	public float getMensualidad() {
		return mensualidad;
	}

	public void setMensualidad(float mensualidad) {
		this.mensualidad = mensualidad;
	}

	public String getId() {
		return id;
	}


	
	

}
