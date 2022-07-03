package pl.migibud.jdbc;

import java.sql.*;

import static pl.migibud.jdbc.Configuration.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		System.out.println("Hello world");
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement statement = connection.createStatement();

		int amount = statement.executeUpdate("UPDATE animal SET name = 'Jasio' WHERE id = 2");
		System.out.printf("Amount: "+amount);

		ResultSet resultSet = statement.executeQuery("SELECT * FROM animal");

		while (resultSet.next()){
			String name = resultSet.getString("name");
			System.out.println(name);
		}
		resultSet.close();

		boolean hasResult = statement.execute("TRUNCATE TABLE animal");
		System.out.println(hasResult);

		statement.close();
		connection.close();
	}
}
