package pruebasJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import prEventually.ConexionBaseDeDatosJDBC;
import prEventually.ConexionConBaseDeDatos;

class ExisteEventoTest {

	@Test
	public void TestExisteEvento() {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		boolean res = accesoBD.existeEvento("Barrilada fin de ex�menes");
		assertEquals(true, res);
	}
	
	@Test
	public void TestNoExisteEvento() {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		boolean res = accesoBD.existeEvento("Barrilada fin de ex�meenes");
		assertEquals(false, res);
	}

}
