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

@Table(name = "door")
public class Door implements Serializable {

	@Identifier(name = "id")
	private int id;

	@Column(name = "size")
	private double size;

	@Column(name = "type")
	private String type;
}


