package prEventually;

import java.util.Date;

public class Sesion{
	Date fecha;
	Usuario us = new Usuario();
	
	public Sesion(Usuario u,Date f) {
		fecha = f;
		us = u;
	}
	
}
