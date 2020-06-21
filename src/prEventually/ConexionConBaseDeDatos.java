package prEventually;

import java.util.List;

public abstract class ConexionConBaseDeDatos {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
	static final String DB_SCHEMA = "eventuallydb";

	// Database credentials
	static final String USER = "eventual";
	static final String PASS = "citasyeventos";

	public abstract int registrarNuevoUsuario(Usuario u);

	public abstract Sesion iniciarSesion(String usuario, String contrasena);

	public abstract void eliminarCuenta(String usuario);

	public abstract int crearEvento(Evento e);

	public abstract void eliminarEvento(int id);

	public abstract List<Evento> listaEventos();

	public abstract boolean existeUsuario(String usuario);

	public abstract boolean existeEvento(String evento);

	public abstract List<String> listaParticipantesDeUnEvento(int idEv);

	public abstract int añadirParticipante(Participación p);

	public abstract void eliminarParticipacion(String nUsuario, int idEv);

	public abstract void actualizarContraseña(String nUsuario, String contraNueva);

	public abstract String getMailUsuario(String nUsuario);

	public abstract void modificarEvento(String fecha, String lugar, int id);

	public abstract void eliminarParticipantesEvento(int idEv);
}
