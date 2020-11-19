package logico;

import java.util.Date;

public class Internet extends Plan {
	private int velocidad_subida;
	private int velocidad_bajada;
	public Internet(float precio_apertura, Date fecha_apertura, String tipo, float mensualidad, Cliente cliente,
			int velocidad_subida, int velocidad_bajada) {
		super(precio_apertura, fecha_apertura, tipo, mensualidad, cliente);
		this.velocidad_subida = velocidad_subida;
		this.velocidad_bajada = velocidad_bajada;
	}
	public int getVelocidad_subida() {
		return velocidad_subida;
	}
	public void setVelocidad_subida(int velocidad_subida) {
		this.velocidad_subida = velocidad_subida;
	}
	public int getVelocidad_bajada() {
		return velocidad_bajada;
	}
	public void setVelocidad_bajada(int velocidad_bajada) {
		this.velocidad_bajada = velocidad_bajada;
	}
	

}
