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
	
	@Override
	public List<Evento> listaEventos() {
		ArrayList<Evento> lEventos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM eventos";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {

					String nombre = rs.getString(1);
					String fecha = rs.getString(2);
					String lugar = rs.getString(3);
					String organizador = rs.getString(4);
					int id = rs.getInt(5);
					
					Evento ev = new Evento(id, nombre, fecha, lugar, organizador);
					lEventos.add(ev);
					List<String> participantesEvento = listaParticipantesDeUnEvento(ev.getId());
					for (String participante : participantesEvento) {
						ev.inscribirUsuario(participante);
					}

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lEventos;
	}
	
	@Override
	public List<String> listaParticipantesDeUnEvento(int idEv) {
		ArrayList<String> lParticipantes = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM participaciones WHERE IdEvento=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(selectQueryBody);
			preparedStatement.setInt(1, idEv);
			ResultSet rs = preparedStatement.executeQuery();
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String nParticipante = rs.getString(2);
					lParticipantes.add(nParticipante);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lParticipantes;
	}
	
	public String getMailUsuario(String nU) {
		String selectQueryBody = "SELECT mail FROM users WHERE user=?";
		String nUsuario = nU;
		String uMail = "noooooooooo";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(selectQueryBody);
			preparedStatement.setString(1, nUsuario);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				uMail = rs.getString("mail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uMail;
	}
	
	@Override
	public int registrarNuevoUsuario(Usuario u) {
		int userID = 0;
		String insertBody = "INSERT INTO users (user, password, mail ) VALUES (?, ?, ?)";
		try {
			
			
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, u.getUser());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getMail());
			
			@SuppressWarnings("unused")
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
	
	@Override
	public int crearEvento(Evento e) {
		int eventID = 0;
		String insertBody = "INSERT INTO eventos (nombre, fecha, lugar, organizador) VALUES (?, ?, ?, ?)";
		try {
			
			
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, e.getNombre());
			preparedStatement.setString(2, e.getFecha());
			preparedStatement.setString(3, e.getLugar());
			preparedStatement.setString(4, e.getOrganizador());
			
			@SuppressWarnings("unused")
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
	
	@Override
	public void eliminarEvento(int idEvento) {
		int n = idEvento;
		String deleteBody = "DELETE FROM eventos WHERE (id = ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(deleteBody);
			preparedStatement.setInt(1, n);
			@SuppressWarnings("unused")
			int res = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	@Override
	public Sesion iniciarSesion(String usuario, String contrasena) {
		Sesion s = null;
		String insertBody = "SELECT * FROM users where user=? and password=?";
		try {
			PreparedStatement estatamentoPreparado = conn.prepareStatement(insertBody);
			estatamentoPreparado.setString(1, usuario);
			estatamentoPreparado.setString(2, contrasena);
			ResultSet rs = estatamentoPreparado.executeQuery();
			if(rs.next()) {
				s = new Sesion(usuario, contrasena);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public boolean participarEvento(String usuario, String evento) {
		boolean ok = false;
			String insertBody = "SELECT * FROM users where user=? and password=?";
			try {
				PreparedStatement estatamentoPreparado = conn.prepareStatement(insertBody);
				estatamentoPreparado.setString(1, usuario);
				estatamentoPreparado.setString(2, evento);
				ResultSet rs = estatamentoPreparado.executeQuery();
				if(rs.next()) {
					ok = true;
				}
			}catch(SQLException e) {
				e.printStackTrace();
		}
		return ok;
	}
	
	
	
	@Override
	public int añadirParticipante(Participación p) {
		String insertBody = "INSERT INTO participaciones (IdEvento, NombreParticipante) VALUES (?, ?)";
		int participacionID = 0;
		try {
			
			
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, p.getIdEv());
			preparedStatement.setString(2, p.getnU());
			
			@SuppressWarnings("unused")
			int res = preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			while(rs.next()) {
				participacionID = rs.getInt(1);
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		return participacionID;

	}
	
	@Override
	public void eliminarParticipacion(String nUsuario, int idEv) {
		String deleteBody = "DELETE FROM participaciones WHERE (NombreParticipante = ?) AND (IdEvento = ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(deleteBody);
			preparedStatement.setString(1, nUsuario);
			preparedStatement.setInt(2, idEv);
			@SuppressWarnings("unused")
			int res = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public void eliminarCuenta(String nUsuario) {
		String user = nUsuario;
		String deleteBody = "DELETE FROM users WHERE (user = ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(deleteBody);
			preparedStatement.setString(1, user);
			@SuppressWarnings("unused")
			int res = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}
	
	public void actualizarContraseña(String nUsuario, String contraseñaNueva) {
		String user = nUsuario;
		String pass = contraseñaNueva;
		String updateBody = "UPDATE users SET password = ? WHERE user = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(updateBody);
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, user);
			
			@SuppressWarnings("unused")
			int res = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	
	
	@Override
	public boolean existeUsuario(String usuario) {
		boolean existe=false;
		
		String insertBody = "SELECT * FROM users where user=?";
		try {
			PreparedStatement estatamentoPreparado = conn.prepareStatement(insertBody);
			estatamentoPreparado.setString(1, usuario);
			ResultSet rs = estatamentoPreparado.executeQuery();
			if(rs.next()) {
				existe = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	@Override
	public boolean existeEvento(String evento) {
		boolean existe=false;
		
		String insertBody = "SELECT * FROM eventos where nombre=?";
		try {
			PreparedStatement estatamentoPreparado = conn.prepareStatement(insertBody);
			estatamentoPreparado.setString(1, evento);
			ResultSet rs = estatamentoPreparado.executeQuery();
			if(rs.next()) {
				existe = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}

	
	
}
