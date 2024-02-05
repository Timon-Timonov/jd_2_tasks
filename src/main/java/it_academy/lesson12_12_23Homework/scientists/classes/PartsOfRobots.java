package it_academy.lesson12_12_23Homework.scientists.classes;

import it_academy.lesson12_12_23Homework.scientists.exceptions.InvalidCountOfParts;

import java.util.Objects;

public class PartsOfRobots {

	private int count = 1;
	private String name;


	public PartsOfRobots(String name) {
		this.name = name;
	}


	public int getCount() {
		return count;
	}


	public void incrPartCount(int incr) {
		this.count += incr;
	}


	public void decrPartCount(int decr) {
		if (decr > this.count) {
			throw new InvalidCountOfParts();
		}
		this.count -= decr;
	}


	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PartsOfRobots that = (PartsOfRobots) o;
		return name.equals(that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return name + " - " + count;
	}
}
