package prEventually;

import java.util.Date;

public class Evento {
	
	private String nombre;
	private String fecha;
	private String lugar;
	private String organizador;
	private int id;
	
	public Evento(int i, String n, String f, String l, String o) {
		id = i;
		nombre = n;
		fecha = f;
		lugar = l;
		organizador = o;
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}
	
	
}
