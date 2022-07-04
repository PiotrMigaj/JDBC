package pl.migibud.jdbcDAOexercise.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
public class Task implements Serializable {

	private Long id;
	private String description;
	private Long userId;

	public Task(String description, Long userId) {
		this.description = description;
		this.userId = userId;
	}

	public Task(Long id, String description, Long userId) {
		this.id = id;
		this.description = description;
		this.userId = userId;
	}
}
