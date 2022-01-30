package com.mcleish.app.employeecrudapp.service;

import com.mcleish.app.employeecrudapp.dao.StockRepository;
import com.mcleish.app.employeecrudapp.entity.Stock;
import com.mcleish.app.employeecrudapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> findAllStock() {
        return stockRepository.findAll();
    }

    public String sumOfAvailableUnits() {
        return stockRepository.sumOfAvailableUnits();
    }

    public String sumOfSoldUnits() {
        return stockRepository.sumOfSoldUnits();
    }

    public Stock getStockById(@PathVariable long theId) {

        Stock stock = stockRepository.findById(theId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock does not exist with id: " + theId));

        Long parsedAvailableUnits = Long.parseLong(stock.getAvailableUnits());
        Long parsedSoldUnits = Long.parseLong(stock.getSoldUnits());
        stock.setAvailableUnits(String.format("%,d",parsedAvailableUnits));
        stock.setSoldUnits(String.format("%,d",parsedSoldUnits));

        return stock;
    }

    public Stock saveStock(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }

    public ResponseEntity<Stock> updateStock(@PathVariable Long theId, @RequestBody Stock theStock) {
        Stock stock = stockRepository.findById(theId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock does not exist with id: " + theId));

        // remove commas from fields
        String availableUnits = theStock.getAvailableUnits();
        String soldUnits = theStock.getSoldUnits();
        availableUnits = availableUnits.replaceAll(",","");
        soldUnits = soldUnits.replaceAll(",","");

        stock.setStockName(theStock.getStockName());
        stock.setAvailableUnits(availableUnits);
        stock.setSoldUnits(soldUnits);
        stock = stockRepository.save(stock);

        return ResponseEntity.ok(stock);
    }

    public ResponseEntity<Map<String, Boolean>> deleteStock(@PathVariable Long theId) {
        Stock stock = stockRepository.findById(theId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock does not exist with id: " + theId));

        stockRepository.delete(stock);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

    public Page<Stock> pageAndSortStock(@PathVariable(value = "pageNum") int pageNum, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
        int pageSize = 5;

        Page<Stock> page = findPaginated(pageNum, pageSize, sortField, sortDir);

        return page;
    }

    public Page<Stock> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);
        return this.stockRepository.findAll(pageable);
    }

}
