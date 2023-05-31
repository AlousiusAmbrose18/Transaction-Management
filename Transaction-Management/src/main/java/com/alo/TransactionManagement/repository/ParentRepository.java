package com.alo.TransactionManagement.repository;

import com.alo.TransactionManagement.entity.Parent;
import com.alo.TransactionManagement.util.ParentWithTotalPaidAmount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    @Query(value = "SELECT p.*, COALESCE(SUM(c.paid_amount), 0) AS total_paid_amount " +
            "FROM parent p LEFT JOIN child c ON p.id = c.parent_id " +
            "GROUP BY p.id",
            countQuery = "SELECT COUNT(*) FROM parent",
            nativeQuery = true)
    Page<ParentWithTotalPaidAmount> findParentsWithTotalPaidAmount(Pageable pageable);
}
