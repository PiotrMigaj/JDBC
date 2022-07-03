package pl.migibud.jdbc;

import java.sql.*;

import static pl.migibud.jdbc.Configuration.*;

public class Main6Injection {
	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

		System.out.println(login(connection,"Ala","password2"));
		System.out.println(login(connection,"Ala","password2334"));
		System.out.println(secureLogin(connection,"Ala","password2"));



		connection.close();
	}

	private static boolean secureLogin(Connection connection, String username, String password) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
		preparedStatement.setString(1,username);
		preparedStatement.setString(2,password);
		ResultSet resultSet = preparedStatement.executeQuery();
		boolean isLogged = resultSet.next();
		resultSet.close();
		preparedStatement.close();
		return isLogged;
	}

	private static boolean login(Connection connection, String username, String password) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username=?");
		preparedStatement.setString(1,username);
		ResultSet resultSet = preparedStatement.executeQuery();
		boolean test = resultSet.next();
		String resultPass = null;
		if (test){
			resultPass = resultSet.getString("password");
		}
		resultSet.close();
		preparedStatement.close();
		return password.equals(resultPass);
	}
}
