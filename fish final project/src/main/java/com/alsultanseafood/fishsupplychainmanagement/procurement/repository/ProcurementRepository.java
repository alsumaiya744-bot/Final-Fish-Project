package com.alsultanseafood.fishsupplychainmanagement.procurement.repository;



import com.alsultanseafood.fishsupplychainmanagement.procurement.entity.Procurement;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;





public interface ProcurementRepository
        extends JpaRepository<Procurement, Long> {
                List<Procurement>
findByProcurementDateBetween(
        LocalDate startDate,
        LocalDate endDate);



        @Query(
        "SELECT SUM(p.purchaseQuantity) FROM Procurement p")
Double sumPurchaseQuantity();

@Query(
        "SELECT SUM(p.totalAmount) FROM Procurement p")
Double sumTotalAmount();
}