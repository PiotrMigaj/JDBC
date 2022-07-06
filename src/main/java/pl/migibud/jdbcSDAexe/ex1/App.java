package pl.migibud.jdbcSDAexe.ex1;

import pl.migibud.jdbcSDAexe.dbconnection.MySQLDBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) throws SQLException {

		Connection connection = MySQLDBConnection.getConnection();
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE IF EXISTS movies");
		String sql = "CREATE TABLE movies(id int auto_increment primary key, title varchar(255), yearOfRelease int)";
		statement.execute(sql);
		statement.close();
		connection.commit();
		MySQLDBConnection.closeConnection();

	}

}
