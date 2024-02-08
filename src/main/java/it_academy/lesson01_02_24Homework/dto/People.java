package it_academy.lesson01_02_24Homework.dto;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "people")
public class People {

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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "people_addresses",
			joinColumns = {@JoinColumn(name = "people_id")},
			inverseJoinColumns = {@JoinColumn(name = "address_id")})
	private Set<Address> addresses = new HashSet<>();
}
