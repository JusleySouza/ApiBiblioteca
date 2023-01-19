package br.com.library.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class PaginationTest {
	
	private Pagination expectedPagination;

	@BeforeEach
	void setUp() throws Exception {
		expectedPagination = ClassBuilder.paginationBuilder();
	}
	
	@Test
	void builder() {
		Pagination pagination = Pagination.builder()
				.offset(0)
				.pageSize(4)
				.pageNumber(1)
				.moreElements(true)
				.totalPages(6)
				.totalElements(2)
				.build();
		assertEquals(expectedPagination.toString(), pagination.toString());
	}

	@Test
	void setter() {
		Pagination pagination = new Pagination();
		pagination.setOffset(0);
		pagination.setPageSize(4);
		pagination.setPageNumber(1);
		pagination.setMoreElements(true);
		pagination.setTotalPages(6);
	    pagination.setTotalElements(2);
		assertEquals(expectedPagination.toString(), pagination.toString());
	}

}
