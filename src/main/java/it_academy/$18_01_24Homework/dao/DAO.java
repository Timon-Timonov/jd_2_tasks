package it_academy.$18_01_24Homework.dao;

import java.sql.SQLException;

public interface DAO<T> {
	T save(T t) throws SQLException;

	T get(long id) throws SQLException;

	void update(T t) throws SQLException;

	int delete(long id) throws SQLException;
}
