package pl.migibud.jdbc;

import java.sql.*;

import static pl.migibud.jdbc.Configuration.*;

public class Main3StatementExercise {

	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement statement = connection.createStatement();

		boolean execute = statement.execute("CREATE TABLE IF NOT EXISTS user(" +
				"id BIGINT AUTO_INCREMENT PRIMARY KEY," +
				" username VARCHAR(50) UNIQUE," +
				" password VARCHAR(50))");
		System.out.println(execute);

		int executeUpdate = statement.executeUpdate("INSERT INTO user(username,password) VALUES " +
				"('Piotr123','123qwe')," +
				"('Ania123','123qwe321')," +
				"('Piotr56','123qwe00')," +
				"('Marcin11','123qwess')," +
				"('Olek11','123qwe4444')," +
				"('Piotr007','123qwe007')");
		System.out.println(executeUpdate);

		ResultSet resultSet = statement.executeQuery("SELECT username FROM user");
		while (resultSet.next()){
			System.out.println(resultSet.getString("username"));
		}
		resultSet.close();

		int truncate_table_user = statement.executeUpdate("TRUNCATE TABLE user");
		System.out.println(truncate_table_user);


		statement.close();
		connection.close();

	}

}
