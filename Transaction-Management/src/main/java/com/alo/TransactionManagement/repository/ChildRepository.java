package com.alo.TransactionManagement.repository;


import com.alo.TransactionManagement.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {
    @Query("SELECT COALESCE(SUM(c.paidAmount), 0) FROM Child c WHERE c.parent.id = :parentId")
    double getTotalPaidAmountByParentId(@Param("parentId") Long parentId);

    List<Child> findByParentId(Long parentId);
}


