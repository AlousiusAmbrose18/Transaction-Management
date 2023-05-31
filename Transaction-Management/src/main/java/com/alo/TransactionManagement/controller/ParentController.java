package com.alo.TransactionManagement.controller;

import com.alo.TransactionManagement.dto.PaginationDTO;
import com.alo.TransactionManagement.dto.ParentDTO;
import com.alo.TransactionManagement.entity.Child;
import com.alo.TransactionManagement.entity.Parent;
import com.alo.TransactionManagement.repository.ChildRepository;
import com.alo.TransactionManagement.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/parents")
@CrossOrigin("http://localhost:4200")
public class ParentController {
    private final ParentRepository parentRepository;

    private final ChildRepository childRepository;

    @Autowired
    public ParentController(ParentRepository parentRepository, ChildRepository childRepository) {
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
    }

    @PostMapping("/save")
    public Parent saveParent(@RequestBody Parent parent) {
        return parentRepository.save(parent);
    }

    @PostMapping("/{parentId}/children")
    public Child saveChild(@PathVariable Long parentId, @RequestBody Child child) {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Parent not found with id: " + parentId));
        child.setParent(parent);
        return childRepository.save(child);
    }

    @GetMapping("/getParents")
    public PaginationDTO getParents(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "2") int pageSize,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sortBy));

        Page<Parent> parentPage = parentRepository.findAll(pageRequest);

        List<ParentDTO> parentDTOList = parentPage.getContent().stream().map(parent -> {
            double totalPaidAmount = childRepository.getTotalPaidAmountByParentId(parent.getId());
            ParentDTO parentDTO = new ParentDTO();
            parentDTO.setId(parent.getId());
            parentDTO.setSender(parent.getSender());
            parentDTO.setReceiver(parent.getReceiver());
            parentDTO.setTotalAmount(parent.getTotalAmount());
            parentDTO.setTotalPaidAmount(totalPaidAmount);
            return parentDTO;
        }).collect(Collectors.toList());


        return new PaginationDTO(parentDTOList, parentPage.getTotalPages());
    }

}


