package com.car.part.project.repository;

import java.util.Date;

import com.car.part.project.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BillRepository extends JpaRepository<Bill, Long>{
Bill findById(long billId);

@Query("SELECT SUM(bill.totalPrice) FROM Bill bill WHERE bill.sellDate BETWEEN :startdate AND :enddate")
Integer searchTotalPrice(@Param("startdate")Date startdate, @Param("enddate")Date enddate);
}