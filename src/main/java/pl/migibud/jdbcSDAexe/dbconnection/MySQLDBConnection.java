package pl.migibud.jdbcSDAexe.dbconnection;

import pl.migibud.jdbcSDAexe.dbconconfig.ConfigDBSDA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBConnection {

	public static Connection getConnection(){
		return ConnectionHolder.CONNECTION;
	}

	public static void closeConnection() throws SQLException {
		ConnectionHolder.CONNECTION.close();
	}


	private static class ConnectionHolder {
		private static Connection CONNECTION = null;

		static {
			try {
				CONNECTION = DriverManager.getConnection(ConfigDBSDA.URL,ConfigDBSDA.USERNAME,ConfigDBSDA.PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
