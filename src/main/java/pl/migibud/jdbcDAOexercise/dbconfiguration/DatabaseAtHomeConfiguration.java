package pl.migibud.jdbcDAOexercise.dbconfiguration;

public class DatabaseAtHomeConfiguration implements Configuration{
	@Override
	public String getURL() {
		return "jdbc:mysql://localhost:3306/jdbc_schema";
	}

	@Override
	public String getUSER() {
		return "root";
	}

	@Override
	public String getPASSWORD() {
		return "Stefan0987";
	}
}
