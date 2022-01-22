package com.mcleish.app.employeecrudapp.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "stockName")
    private String stockName;

    @Column(name = "availableUnits")
    private String availableUnits;

    @Column(name = "soldUnits")
    private String soldUnits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id) &&
                Objects.equals(stockName, stock.stockName) &&
                Objects.equals(availableUnits, stock.availableUnits) &&
                Objects.equals(soldUnits, stock.soldUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stockName, availableUnits, soldUnits);
    }

    public Stock() {

    }

    public Stock(String stockName, String availableUnits, String soldUnits) {
        this.stockName = stockName;
        this.availableUnits = availableUnits;
        this.soldUnits = soldUnits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(String availableUnits) {
        this.availableUnits = availableUnits;
    }

    public String getSoldUnits() {
        return soldUnits;
    }

    public void setSoldUnits(String soldUnits) {
        this.soldUnits = soldUnits;
    }
}
