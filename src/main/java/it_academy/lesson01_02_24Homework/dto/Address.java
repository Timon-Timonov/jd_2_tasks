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
@EqualsAndHashCode(exclude = "people")
@ToString(exclude = "people")
@Entity
@Table(name = "address")
public class Address {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "street")
	private String street;

	@Column(name = "house")
	private int house;

	@ManyToMany(mappedBy = "addresses",
			fetch = FetchType.EAGER)
	private Set<People> people = new HashSet<>();
}
