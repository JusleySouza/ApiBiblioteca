package br.com.library.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.library.services.implement.AddressServiceImplement;
import br.com.library.services.implement.CustomerServiceImplement;
import br.com.library.services.implement.EmployeeServiceImplement;
import lombok.Generated;

@Generated
public class LoggerConfig {

	public static final Logger LOGGER_CUSTOMER = LoggerFactory.getLogger(CustomerServiceImplement.class);
	public static final Logger LOGGER_EMPLOYEE = LoggerFactory.getLogger(EmployeeServiceImplement.class);
	public static final Logger LOGGER_ADDRESS = LoggerFactory.getLogger(AddressServiceImplement.class);
	
}
