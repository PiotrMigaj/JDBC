package pl.migibud.jdbcDAOexercise.task;

import pl.migibud.jdbcDAOexercise.dbconnection.MySQLDBConnection;
import pl.migibud.jdbcDAOexercise.exceptions.DuplicatedIDException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAO implements AbstractDAOInterface<Task> {

	@Override
	public void create(Task task) throws SQLException {
		Connection connection = MySQLDBConnection.MY_SQL.getConnection();
		Long id = task.getId();
		String description = task.getDescription();
		Long userId = task.getUserId();

		if (id == null) {
			this.insertTaskWithoutIdIntoDB(connection, description, userId);
		} else {
			this.insertTaskWithNotNullIdIntoDB(connection, id, description, userId);
		}
	}

	private void insertTaskWithoutIdIntoDB(Connection connection, String description, Long userId) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO task(description,user_id) VALUES(?,?)");
		preparedStatement.setString(1, description);
		preparedStatement.setLong(2, userId);
		int i = preparedStatement.executeUpdate();
		System.out.println("Rows affected: " + i);
		preparedStatement.close();
	}

	private void insertTaskWithNotNullIdIntoDB(Connection connection, Long id, String description, Long userId) throws SQLException {
		if (isUserWithNotNullIDInDB(connection, id)) {
			throw new DuplicatedIDException(id);
		}
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO task VALUES(?,?,?)");
		preparedStatement.setLong(1, id);
		preparedStatement.setString(2, description);
		preparedStatement.setLong(3, userId);
		int i = preparedStatement.executeUpdate();
		System.out.println("Rows affected: " + i);
		preparedStatement.close();
	}

	private boolean isUserWithNotNullIDInDB(Connection connection, Long id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE id=?");
		preparedStatement.setLong(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet.next();
	}

	@Override
	public Optional<Task> read(Long id) throws SQLException {
		Connection connection = MySQLDBConnection.MY_SQL.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE id=?");
		preparedStatement.setLong(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		Task task = null;

		while (resultSet.next()) {
			String description = resultSet.getString("description");
			Long userId = resultSet.getLong("user_id");
			task = new Task(id, description, userId);
		}
		resultSet.close();
		preparedStatement.close();
		return Optional.ofNullable(task);
	}

	@Override
	public List<Task> readAll() throws SQLException {
		Connection connection = MySQLDBConnection.MY_SQL.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM task");
		ResultSet resultSet = preparedStatement.executeQuery();

		List<Task> tasks = new ArrayList<>();

		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			String description = resultSet.getString("description");
			Long userId = resultSet.getLong("user_id");
			tasks.add(new Task(id, description, userId));
		}
		return tasks;
	}

	@Override
	public void update(Task task) throws SQLException {
		Connection connection = MySQLDBConnection.MY_SQL.getConnection();
		Long id = task.getId();
		isTaskIdCorrect(connection, id);
		connection.setAutoCommit(false);

		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE task SET description=? WHERE id=?");
		preparedStatement.setString(1, task.getDescription());
		preparedStatement.setLong(2, id);
		preparedStatement.executeUpdate();
		preparedStatement.close();

		PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE task SET user_id=? WHERE id=?");
		preparedStatement2.setLong(1, task.getUserId());
		preparedStatement2.setLong(2, id);
		preparedStatement2.executeUpdate();
		preparedStatement2.close();

		connection.commit();
	}

	private boolean isTaskIdCorrect(Connection connection, Long id) throws SQLException {
		if (id == null) {
			throw new IllegalArgumentException("Task cant have null id");
		}
		if (!isUserWithNotNullIDInDB(connection, id)) {
			throw new IllegalArgumentException("There is no such an user in DB");
		}
		return true;
	}

	@Override
	public void delete(Long id) throws SQLException {
		Connection connection = MySQLDBConnection.MY_SQL.getConnection();
		isTaskIdCorrect(connection, id);
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM task WHERE id=?");
		preparedStatement.setLong(1, id);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	@Override
	public List<Task> readAllForUser(String username) throws SQLException {
		Connection connection = MySQLDBConnection.MY_SQL.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT task.id,task.description,task.user_id FROM task JOIN user ON task.user_id = user.id WHERE user.username = ?");
		preparedStatement.setString(1,username);
		ResultSet resultSet = preparedStatement.executeQuery();

		List<Task> tasks = new ArrayList<>();

		while (resultSet.next()){
			Long id = resultSet.getLong("id");
			String description = resultSet.getString("description");
			Long userId = resultSet.getLong("user_id");
			tasks.add(new Task(id, description, userId));
		}
		return tasks;
	}
}
