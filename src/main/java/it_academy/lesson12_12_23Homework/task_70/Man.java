package it_academy.lesson12_12_23Homework.task_70;

import java.util.Objects;

public class Man {
	private String name;
	private String surName;
	private final long id;
	private int age;


	public Man(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getSurName() {
		return surName;
	}

	public long getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void printMe() {
		System.out.println("HelloWorld from " + this.toString());
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Man)) return false;
		Man man = (Man) o;
		return getId() == man.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "Man{" +
				"name='" + name + '\'' +
				", surName='" + surName + '\'' +
				", id=" + id +
				", age=" + age +
				'}';
	}
}
