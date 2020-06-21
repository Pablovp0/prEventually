package pruebasJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import prEventually.ConexionBaseDeDatosJDBC;
import prEventually.ConexionConBaseDeDatos;

class getMailUsuarioTest {

	@Test
	public void testgetMailUsuario() {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		String mail = accesoBD.getMailUsuario("pepe");
		assertEquals("pepe@", mail);
	}

	@Test
	public void testgetWrongMailUsuario() {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		String mail = accesoBD.getMailUsuario("pepe");
		boolean res = mail.equals("pepe");
		assertEquals(false, res);
	}

}
