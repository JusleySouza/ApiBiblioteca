package br.com.library.model;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.library.constants.Conf;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false )
	private UUID id;
	@Column(nullable = false )
	private String name;
	@Column(nullable = false, unique = true )
	private String cpf;
	@Column(nullable = false )
	private String phone;
	@Column(nullable = false )
	private String email;
	@Column(nullable = false )
	private Boolean active;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate created;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate changed;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId")
	private Address address;

}
