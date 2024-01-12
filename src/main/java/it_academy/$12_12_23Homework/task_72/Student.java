package it_academy.$12_12_23Homework.task_72;

import java.util.Objects;

public class Student {
	private String name;

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Student)) return false;
		Student student = (Student) o;
		return getName().equals(student.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student(String name) {
		this.name = name;
	}

	@academyinfo(year = 2023)
	public void toStudyWithAnn() {
		System.out.println("Oh no....");

	}

	public void toRestWithoutOfAnn() {
		System.out.println("It is fun!");
	}
}
