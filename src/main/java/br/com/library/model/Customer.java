package br.com.library.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.library.constants.Conf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
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
