package br.com.library.model;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.library.constants.Conf;
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
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate created;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate changed;
	
}
