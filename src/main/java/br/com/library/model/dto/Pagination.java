package br.com.library.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Pagination implements Serializable{
    private static final long serialVersionUID = 1L;
    private long offset;
    private int pageSize;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private Boolean moreElements;
}
