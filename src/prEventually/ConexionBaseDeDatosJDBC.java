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
			
			System.out.println(insertBody);
			
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
	
	
	
}
