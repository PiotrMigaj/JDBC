package pl.migibud.jdbcDAOexercise.dbconnection;

import pl.migibud.jdbcDAOexercise.configuration.DBConnectionConfigStrategy;
import pl.migibud.jdbcDAOexercise.configuration.DatabaseAtHomeConfiguration;
import pl.migibud.jdbcDAOexercise.configuration.DatabaseAtWorkConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum MySQLDBConnection {

	MY_SQL(new DBConnectionConfigStrategy(new DatabaseAtHomeConfiguration()));

	MySQLDBConnection(DBConnectionConfigStrategy strategy) {
		this.connection = buildConnection(strategy.getURL(),strategy.getUSER(),strategy.getPASSWORD());
	}

	private final Connection connection;

	private Connection buildConnection(String url,String user,String password){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public Connection getConnection(){
		return this.connection;
	}

	public void closeConnection(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
