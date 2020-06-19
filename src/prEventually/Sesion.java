package prEventually;

public class Sesion{
	String nusuario;
	String contraseña;
	
	public Sesion(String n, String c) {
		nusuario = n;
		contraseña = c;
	}

	public String getNusuario() {
		return nusuario;
	}

	public String getContraseña() {
		return contraseña;
	}
	
}
