package prEventually;

public class Usuario {

	String user;
	String mail;
	String password;
	int identificador;

	public Usuario() {
		identificador = 0;
		user = null;
		mail = null;
		password = null;
	}

	public Usuario(int id, String u, String m, String p) {
		identificador = id;
		user = u;
		mail = m;
		password = p;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getUser() {
		return user;
	}

	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}

	public void setUser(String s) {
		user = s;
	}

	public void setPassword(String p) {
		password = p;
	}

}
