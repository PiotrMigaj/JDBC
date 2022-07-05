package pl.migibud.jdbcDAOexercise;

import pl.migibud.jdbcDAOexercise.dbconnection.MySQLDBConnection;
import pl.migibud.jdbcDAOexercise.task.AbstractDAOInterface;
import pl.migibud.jdbcDAOexercise.task.Task;
import pl.migibud.jdbcDAOexercise.task.TaskDAO;

import java.sql.SQLException;
import java.util.List;

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
//		abstractDAOInterface.create(new Task("cos do zrobienia 55",2L));
//		Optional<Task> read = abstractDAOInterface.read(60L);
//		System.out.println(read.get());
//		Optional<Task> read = abstractDAOInterface.read(100L);
//		System.out.println(read.orElse(new Task(null,null)));

//		List<Task> tasks = abstractDAOInterface.readAll();
//		tasks.stream().forEach(System.out::println);

//		abstractDAOInterface.update(new Task(500L,"Jo JO JO jjs",2L));

//		abstractDAOInterface.delete(80L);

		List<Task> tasks = abstractDAOInterface.readAllForUser("dn");
		tasks.forEach(System.out::println);

		MySQLDBConnection.MY_SQL.closeConnection();
	}
}
