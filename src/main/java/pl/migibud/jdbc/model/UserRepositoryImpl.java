package pl.migibud.jdbc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static pl.migibud.jdbc.Configuration.*;

public class UserRepositoryImpl implements UserRepository{

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	@Override
	public User getUser(Long id) throws SQLException {
		Connection connection = this.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id=?");
		preparedStatement.setLong(1,id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()){
			return null;
		}
		String userName = resultSet.getString("username");
		String password = resultSet.getString("password");
		resultSet.close();
		preparedStatement.close();
		connection.close();
		return new User(id,userName,password);
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		Connection connection = this.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
		while (resultSet.next()){
			Long id = resultSet.getLong(1);
			String userName = resultSet.getString("username");
			String password = resultSet.getString("password");
			users.add(new User(id,userName,password));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return users;
	}
}
