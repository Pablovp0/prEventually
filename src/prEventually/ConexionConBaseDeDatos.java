package prEventually;

public abstract class ConexionConBaseDeDatos {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
	static final String DB_SCHEMA = "eventuallydb";
	
	//  Database credentials
	static final String USER = "eventual";
	static final String PASS = "citasyeventos";
	
	public abstract int registrarNuevoUsuario(Usuario u);
	
}
