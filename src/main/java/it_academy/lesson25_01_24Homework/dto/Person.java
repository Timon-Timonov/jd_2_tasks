package it_academy.lesson25_01_24Homework.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "person")
public class Person implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private int age;

	@Column
	private double salary;

	@Column
	private String passport;


	@Column
	private String address;

	@Column
	private Date dateOfBirth;

	@Column
	@CreationTimestamp
	private Timestamp dateTimeCreate;

	@Column
	private Time timeToLunch;


	@Column
	private String letter;
}