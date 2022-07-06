package pl.migibud.jdbcSDAexe.ex1;

import pl.migibud.jdbcSDAexe.dbconnection.MySQLDBConnection;

import java.sql.*;

public class App {

	public static void main(String[] args) throws SQLException {

		try (Connection connection = MySQLDBConnection.getConnection()){
			dropTable(connection);
			createTable(connection);
			insertIntoTable(connection);
			update(connection);
			delete(connection);
			read(connection);
		}catch (SQLException e){
			e.printStackTrace();
		}

//
//		MySQLDBConnection.closeConnection();
	}

	public static void dropTable(Connection connection){
		try(Statement statement = connection.createStatement()) {
			String sql = "DROP TABLE IF EXISTS movies";
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTable(Connection connection){
		try(Statement statement = connection.createStatement()) {
			String sql = "CREATE TABLE movies(id INTEGER AUTO_INCREMENT primary key, title varchar(255),genre VARCHAR(255) , yearOfRelease INTEGER)";
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoTable(Connection connection){
		try(Statement statement = connection.createStatement()) {
			String insertStarWarsQuery = "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES ('Star Wars', 'Action', 2015)";
			String insertHarryPotterQuery = "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES ('Harry Potter', 'Fantasy', 2001)";
			String insertRockyQuery = "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES ('Rocky', 'Sports', 1979)";
			statement.executeUpdate(insertStarWarsQuery);
			statement.executeUpdate(insertHarryPotterQuery);
			statement.executeUpdate(insertRockyQuery);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static void update(Connection connection){
		try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE movies SET title = ? WHERE id = ?")) {
			preparedStatement.setString(1,"Star Wars Force Awakens");
			preparedStatement.setInt(2,1);
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static void delete(Connection connection){
		try (Statement statement = connection.createStatement()){
			String sql = "DELETE FROM MOVIES WHERE id = 2";
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public static void read(Connection connection){
		try(Statement statement = connection.createStatement()) {
			String sql = "SELECT * FROM movies";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String genre = rs.getString("genre");
				int yearOfRelease = rs.getInt("yearOfRelease");

				System.out.println("#####################");
				System.out.println("Id: " + id);
				System.out.println("Title: " + title);
				System.out.println("Genre: " + genre);
				System.out.println("Year of release: " + yearOfRelease);
			}

		}catch (SQLException e){
			e.printStackTrace();
		}
	}


}
