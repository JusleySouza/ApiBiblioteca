package br.com.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.library.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
	Customer findByCpf(String cpf);
}
