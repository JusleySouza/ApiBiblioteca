package br.com.library.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false )
	private UUID id;
	@Column(nullable = true )
	private String street;
	@Column(nullable = true )
	private String cep;
	@Column(nullable = true )
	private Integer number;
	@Column(nullable = true )
	private String neighborhood;
	@Column(nullable = true )
	private String city;
	@Column(nullable = true )
	private String state;
	
}
