package br.com.library.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ListCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    private Pagination pageable;
    private List<ResponseDTO> content;
}
