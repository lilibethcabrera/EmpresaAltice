package logico;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Date fecha_facturacion;
	private float monto;
	private Plan plan;
	private Cliente cliente;
	private boolean pagada; //true = pagada false = no pagada
	public Factura(String id, Date fecha_facturacion,float monto, Plan plan, Cliente cliente, boolean pagada) {
		this.id = id;
		this.fecha_facturacion = fecha_facturacion;
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
