package br.com.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.library.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
