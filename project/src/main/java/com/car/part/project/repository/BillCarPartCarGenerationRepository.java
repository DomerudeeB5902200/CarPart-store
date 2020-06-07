package com.car.part.project.repository;

import java.util.Collection;
import java.util.List;

import com.car.part.project.entity.BillCarPartCarGeneration;
import com.car.part.project.entity.CarPart;
import com.car.part.project.entity.CarPartCarGeneration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BillCarPartCarGenerationRepository extends JpaRepository<BillCarPartCarGeneration, Long> {
    @Query("SELECT bilCarPart.carPartCarGeneration.carPart FROM BillCarPartCarGeneration bilCarPart WHERE bilCarPart.bill.billId = :billId")
    Collection<CarPart> findCarPartByBillId(@Param("billId") long billId);

    @Query("SELECT SUM(CarPart.amount) AS sumAmount, CarPart.carPartCarGeneration.carPart.passOld, CarPart.carPartCarGeneration.carPart.passNew,CarPart.carPartCarGeneration.carPart.carPartName FROM BillCarPartCarGeneration CarPart GROUP BY CarPart.carPartCarGeneration.carPartCarGenerationId ORDER BY sumAmount DESC")
    List<Object[]> getBestSeller();
}