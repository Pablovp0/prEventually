package pruebasJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import prEventually.ConexionBaseDeDatosJDBC;
import prEventually.ConexionConBaseDeDatos;

class ExisteUsuarioTest {

	@Test
	public void TestExisteUsuario() {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		boolean res = accesoBD.existeUsuario("pepe");
		assertEquals(true, res);
	}
	
	@Test
	public void TestNoExisteUsuario() {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		boolean res = accesoBD.existeUsuario("pepee");
		assertEquals(false, res);
	}

}
