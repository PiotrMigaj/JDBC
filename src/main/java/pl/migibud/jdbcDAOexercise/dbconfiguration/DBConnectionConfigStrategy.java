package pl.migibud.jdbcDAOexercise.dbconfiguration;

public class DBConnectionConfigStrategy {

	private Configuration configuration;

	public DBConnectionConfigStrategy(Configuration configuration) {
		this.configuration = configuration;
	}


	public String getURL() {
		return configuration.getURL();
	}


	public String getUSER() {
		return configuration.getUSER();
	}


	public String getPASSWORD() {
		return configuration.getPASSWORD();
	}
}
