package pl.migibud.jdbcDAOexercise;

import pl.migibud.jdbcDAOexercise.configuration.DBConnectionConfigStrategy;
import pl.migibud.jdbcDAOexercise.configuration.DatabaseAtHomeConfiguration;
import pl.migibud.jdbcDAOexercise.dbconnection.MySQLDBConnection;
import pl.migibud.jdbcDAOexercise.task.AbstractDAOInterface;
import pl.migibud.jdbcDAOexercise.task.Task;
import pl.migibud.jdbcDAOexercise.task.TaskDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class App {


	public static void main(String[] args) throws SQLException {
//		DBConnectionConfigStrategy dbConnectionConfigStrategy = new DBConnectionConfigStrategy(new DatabaseAtHomeConfiguration());
//
//		Connection connection = MySQLDBConnection.MY_SQL.getConnection();
//		MySQLDBConnection.MY_SQL.closeConnection();
//
//
//		System.out.println(dbConnectionConfigStrategy.getURL());

		AbstractDAOInterface<Task> abstractDAOInterface = new TaskDAO();
		abstractDAOInterface.create(new Task("cos do zrobienia 55",2L));
		abstractDAOInterface.create(new Task(60L,"cos do zrobienia 60",1L));

		MySQLDBConnection.MY_SQL.closeConnection();
	}
}
