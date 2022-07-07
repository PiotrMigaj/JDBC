package pl.migibud.jdbcSDAexe.ex2.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

	private Integer id;
	private String title;
	private String genre;
	private Integer yearOfRelease;

	public Movie(String title, String genre, Integer yearOfRelease) {
		this.title = title;
		this.genre = genre;
		this.yearOfRelease = yearOfRelease;
	}
}
