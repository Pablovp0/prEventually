package pruebasJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import prEventually.Evento;

class EventoTest {

	@Test
	public void testUsuarioParticipa() {
		Evento e = new Evento(10, "evento", "hoy", "aqui", "yo");
		e.inscribirUsuario("pepe");
		boolean res = e.usuarioParticipa("pepe");
		assertEquals(true, res);

	}

	@Test
	public void testUsuarioNoParticipa() {
		Evento e = new Evento(10, "evento", "hoy", "aqui", "yo");
		e.inscribirUsuario("pepe");
		boolean res = e.usuarioParticipa("Pepe");
		assertEquals(false, res);

	}

}
