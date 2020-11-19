package logico;

import java.util.Date;

public class TV extends Plan {
	private int cantidad_canales;

	public TV(float precio_apertura, Date fecha_apertura, String tipo, float mensualidad, Cliente cliente,
			int cantidad_canales) {
		super(precio_apertura, fecha_apertura, tipo, mensualidad, cliente);
		this.cantidad_canales = cantidad_canales;
	}

	public int getCantidad_canales() {
		return cantidad_canales;
	}

	public void setCantidad_canales(int cantidad_canales) {
		this.cantidad_canales = cantidad_canales;
	}
	
	

}
