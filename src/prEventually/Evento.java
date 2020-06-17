package prEventually;

import java.util.ArrayList;
import java.util.List;



public class Evento {
	
	private String nombre;
	private String fecha;
	private String lugar;
	private String organizador;
	private int id;
	List<Usuario> participantes;
	
	public List<Usuario> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Usuario> participantes) {
		this.participantes = participantes;
	}
	
	public void inscribirUsuario(Usuario u) {
		participantes.add(u);
	}
	
	public void dardeBajaUsuario(Usuario u) {
		participantes.remove(u);
	}

	public Evento(int i, String n, String f, String l, String o) {
		id = i;
		nombre = n;
		fecha = f;
		lugar = l;
		organizador = o;
		participantes = new ArrayList<>();
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
