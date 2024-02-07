package it_academy.lesson01_02_24Homework.dao;

public interface DAO<T> {

	T get(int id);

	void delete(int id);

	T save(T t);

	void update(T t);

}
