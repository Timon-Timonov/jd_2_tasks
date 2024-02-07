package it_academy.lesson01_02_24Homework.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "people")
public class People implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "age")
	private int age;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;
}
