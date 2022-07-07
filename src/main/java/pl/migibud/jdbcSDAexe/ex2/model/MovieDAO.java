package pl.migibud.jdbcSDAexe.ex2.model;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
	void createTable();
	void deleteTable();
	void createMovie(final Movie movie);
	void deleteMovie(Integer id);
	void updateMoviesTitle(Integer id, String newTitle);
	Optional<Movie> findMovieById(Integer id);
	List<Movie> findAll();
}
