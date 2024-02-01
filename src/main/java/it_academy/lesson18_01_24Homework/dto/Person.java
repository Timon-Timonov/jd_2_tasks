package it_academy.lesson18_01_24Homework.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person implements Serializable {
	private long id;
	private int age;
	private double salary;
	private String passport;
	private String address;
	private Date dateOfBirth;
	private Timestamp dateTimeCreate;
	private Time timeToLunch;
	private String letter;
}


