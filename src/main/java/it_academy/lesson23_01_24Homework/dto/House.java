package it_academy.lesson23_01_24Homework.dto;

import it_academy.lesson23_01_24Homework.annotations.Column;
import it_academy.lesson23_01_24Homework.annotations.Identifier;
import it_academy.lesson23_01_24Homework.annotations.Table;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Table(name = "house")
public class House implements Serializable {

	@Identifier(name = "id")
	private int id;

	@Column(name = "size")
	private double size;

	@Column(name = "color")
	private String color;
}
