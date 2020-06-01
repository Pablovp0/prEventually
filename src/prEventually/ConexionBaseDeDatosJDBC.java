package prEventually;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ConexionBaseDeDatosJDBC extends ConexionConBaseDeDatos {
	
	private Connection conn;
	
	private static ConexionBaseDeDatosJDBC instanciaInterfaz = null;
	
	private ConexionBaseDeDatosJDBC() {
		try {
			// create connection for database called DBB_SCHEMA in a server installed in
			// DB_URL, with a user USER with password PASS
			conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA, USER, PASS);
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ConexionBaseDeDatosJDBC getInstance() {
		if (instanciaInterfaz == null) {
			instanciaInterfaz = new ConexionBaseDeDatosJDBC();
		}
		return instanciaInterfaz;
	}
	
	public int registrarNuevoUsuario(Usuario u) {
		int userID = 0;
		String insertBody = "INSERT INTO users (user, password, mail ) VALUES (?, ?, ?)";
		try {
			
			
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, u.getUser());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getMail());
			
			int res = preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			while(rs.next()) {
				userID = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userID;
	}
	
	public int crearEvento(Evento e) {
		int eventID = 0;
		String insertBody = "INSERT INTO eventos (nombre, fecha, lugar, organizador) VALUES (?, ?, ?, ?)";
		try {
			
			
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, e.getNombre());
			preparedStatement.setString(2, e.getFecha());
			preparedStatement.setString(3, e.getLugar());
			preparedStatement.setString(4, e.getOrganizador());
			
			int res = preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			while(rs.next()) {
				eventID = rs.getInt(1);
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		return eventID;
	}
	
	public boolean iniciarSesion(String usuario, String contrasena) {
		boolean ok = false;
		String insertBody = "SELECT * FROM users where user=? and password=?";
		try {
			PreparedStatement estatamentoPreparado = conn.prepareStatement(insertBody);
			estatamentoPreparado.setString(1, usuario);
			estatamentoPreparado.setString(2, contrasena);
			ResultSet rs = estatamentoPreparado.executeQuery();
			if(rs.next()) {
				ok = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	public void eliminarCuenta(String nUsuario) {
		String user = nUsuario;
		String deleteBody = "DELETE FROM users WHERE (user = ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(deleteBody);
			preparedStatement.setString(1, user);
			int res = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}

	
	
}
