package com.alo.TransactionManagement.controller;

import com.alo.TransactionManagement.entity.Child;
import com.alo.TransactionManagement.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/children")
@CrossOrigin("http://localhost:4200")
public class ChildController {
    private final ChildRepository childRepository;

    @Autowired
    public ChildController(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @PostMapping("/save")
    public Child saveChild(@RequestBody Child child) {
        return childRepository.save(child);
    }

    @GetMapping("/{parentId}")
    public List<Child> getChildrenByParentId(@PathVariable Long parentId) {
        return childRepository.findByParentId(parentId);
    }

    @GetMapping("/getChildren")
    public List<Child> getChildren() {
        return childRepository.findAll();
    }
}

