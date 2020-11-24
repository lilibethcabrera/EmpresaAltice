package logico;

import java.util.Date;

public class Factura {
	private String id;
	private Date fecha_facturacion;
	private Date fecha_recargo;
	private float monto;
	private Plan plan;
	private Cliente cliente;
	private boolean pagada; //true = pagada false = no pagada
	public Factura(String id, Date fecha_facturacion, Date fecha_recargo, float monto, Plan plan, Cliente cliente, boolean pagada) {
		this.id = id;
		this.fecha_facturacion = fecha_facturacion;
		this.fecha_recargo = fecha_recargo;
		this.monto = monto;
		this.plan = plan;
		this.cliente = cliente;
		this.pagada = pagada;
	}
	public String getId() {
		return id;
	}
	public Date getFecha_facturacion() {
		return fecha_facturacion;
	}
	
	public Date getFecha_recargo() {
		return fecha_recargo;
	}
	public float getMonto() {
		return monto;
	}

	public Plan getPlan() {
		return plan;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public boolean isPagada() {
		return pagada;
	}
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

}
