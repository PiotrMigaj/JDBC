package pl.migibud.jdbcSDAexe.ex2;

import pl.migibud.jdbcSDAexe.dbconnection.MySQLDBConnection;
import pl.migibud.jdbcSDAexe.ex2.model.MovieDAO;
import pl.migibud.jdbcSDAexe.ex2.model.MovieDAOImpl;

import java.sql.SQLException;

public class App {
	public static void main(String[] args) throws SQLException {
		MovieDAO movieDAO = new MovieDAOImpl(MySQLDBConnection.getConnection());
		movieDAO.createTable();
		MySQLDBConnection.closeConnection();
	}
}
