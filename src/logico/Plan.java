package logico;

import java.util.Date;

public abstract class Plan {
	protected float precio_apertura;
	protected Cliente cliente;
	protected Date fecha_apertura;
	protected String tipo;
	protected float mensualidad;
	
	public Plan(float precio_apertura, Date fecha_apertura, String tipo, float mensualidad, Cliente cliente) {
		this.precio_apertura = precio_apertura;
		this.fecha_apertura = fecha_apertura;
		this.tipo = tipo;
		this.mensualidad = mensualidad;
		this.cliente = cliente;
	}

	public float getPrecio_apertura() {
		return precio_apertura;
	}

	public void setPrecio_apertura(float precio_apertura) {
		this.precio_apertura = precio_apertura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha_apertura() {
		return fecha_apertura;
	}

	public String getTipo() {
		return tipo;
	}

	public float getMensualidad() {
		return mensualidad;
	}

	public void setMensualidad(float mensualidad) {
		this.mensualidad = mensualidad;
	}
	
	

}
