package br.com.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.library.model.Employee;
import br.com.library.services.implement.EmployeeServiceImplement;
import br.com.library.test.utils.ClassBuilder;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeServiceImplement services;
	private ObjectMapper objectMapper;
	private Employee employee;
	
	private final String CONTEXT_PATH = "/employees";
	private final String EMPLOYEE_CPF = "/12365478965";
	private final String PATH_PARAM_KEY = "/cep";
	private final String PATH_PARAM_VALUE = "/04447-010";
	private final String EMPLOYEE_ID = "/a54beaf5-fdb1-4cd9-85ce-36fe8b8b88fd";

	@BeforeEach
	void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		employee = ClassBuilder.employeeBuilder();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	void listEmployees() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH))
		.andExpect(status().isOk());
	}

	@Test
	void create() throws Exception {
		mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee))).andExpect(status().isOk());
	}
	
	@Test
	void findByCpf() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + EMPLOYEE_CPF))
		.andExpect(status().isOk());
	}
	
	@Test
	void findByCep() throws Exception {
		mockMvc.perform(get(CONTEXT_PATH).param(PATH_PARAM_KEY, PATH_PARAM_VALUE))
		.andExpect(status().isOk());
	}

	@Test
	void update() throws Exception {
		mockMvc.perform(put(CONTEXT_PATH + EMPLOYEE_ID).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee))).andExpect(status().isOk());
	}
	
	@Test
	void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(CONTEXT_PATH + EMPLOYEE_ID)).andExpect(status().isNoContent());
	}
	
}
