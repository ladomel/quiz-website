package model;

/**
 * This class stores DB configuration.
 * 
 * @author sergi
 *
 */
public class DBConfig {

	public String DB_USER_NAME;

	public String DB_PASSWORD;

	public String DB_URL;

	public String DB_DRIVER;

	public Integer DB_MAX_CONNECTIONS;

	public String DB_DATABASE_NAME;

	private DBConfig() {
		init();
	}

	// untestable?! - testable
	private static DBConfig configuration = new DBConfig();

	/**
	 * Returns DB configuration object which contains DB related
	 * information.
	 * 
	 * @return
	 */
	public static DBConfig getInstance(){ 
		return configuration;
	}

	private void init() {
		DB_USER_NAME = "root";
		DB_PASSWORD = "nuca";
		DB_URL = "jdbc:mysql://localhost:3306";
		DB_DRIVER = "com.mysql.jdbc.Driver";
		DB_DATABASE_NAME = "oop";
		DB_MAX_CONNECTIONS = 5;
	}

}