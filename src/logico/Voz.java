package logico;

import java.util.Date;

public class Voz extends Plan {
	private String numero;
	private int minutos;

	public Voz(String id, float precio_apertura, Date fecha_apertura, String tipo, float mensualidad, Cliente cliente,
			String numero, int minutos) {
		super(id,precio_apertura, fecha_apertura, tipo, mensualidad, cliente);
		this.numero = numero;
		this.minutos = minutos;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}


}
