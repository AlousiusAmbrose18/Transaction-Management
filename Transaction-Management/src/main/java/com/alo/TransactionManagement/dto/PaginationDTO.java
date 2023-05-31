package com.alo.TransactionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
    private List<ParentDTO> parentDTO;

    private Integer totalPage;

}
