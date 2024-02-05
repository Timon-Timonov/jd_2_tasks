package it_academy.lesson23_01_24Homework.dao;

import java.sql.SQLException;

public interface DAO<T> {
	T save(T t) throws SQLException;

	T get(int id) throws SQLException;

	T update(T t) throws SQLException;

	T delete(int id) throws SQLException;
}
