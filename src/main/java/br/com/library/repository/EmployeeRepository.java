package br.com.library.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.library.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	Employee findByCpf(String cpf);
	Page<Employee> findAllByActiveTrue(Pageable pageable);
	Page<Employee> findAllByAddressCepAndActiveTrue(String cep, Pageable pageable);
	
}
