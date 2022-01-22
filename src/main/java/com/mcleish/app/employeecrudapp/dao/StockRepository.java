package com.mcleish.app.employeecrudapp.dao;

import com.mcleish.app.employeecrudapp.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query(value = "SELECT sum(available_units) FROM stock", nativeQuery = true)
    String sumOfAvailableUnits();

    @Query(value = "SELECT sum(sold_units) FROM stock", nativeQuery = true)
    String sumOfSoldUnits();
}
