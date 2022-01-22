package com.mcleish.app.employeecrudapp.controller;

import com.mcleish.app.employeecrudapp.entity.Stock;
import com.mcleish.app.employeecrudapp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping(value = "/all")
    public List<Stock> getAllStock() {
        return stockService.findAllStock();
    }

    @GetMapping(value = "/")
    public Page<Stock> getStockPage() {
        return stockService.pageAndSortStock(1, "stockName", "asc");
    }

    @GetMapping(value = "/page/{pageNum}")
    public Page<Stock> pageAndSortStock(@PathVariable(value = "pageNum") int pageNum,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir) {
        return stockService.pageAndSortStock(pageNum, sortField, sortDir);

    }

    @GetMapping(value = "/availableSum")
    public String getSumOfAvailableUnits() {
        return stockService.sumOfAvailableUnits();
    }

    @GetMapping(value = "/soldSum")
    public String getSumOfSoldUnits() {
        return stockService.sumOfSoldUnits();
    }

    @GetMapping(value = "/{theId}")
    public Stock getStockById(@PathVariable long theId) {
        return stockService.getStockById(theId);
    }

    @PostMapping(value = "/")
    public Stock saveStock(@RequestBody Stock stock) {
        return stockService.saveStock(stock);
    }

    @PutMapping(value = "/{theId}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long theId, @RequestBody Stock theStock) {
        return stockService.updateStock(theId, theStock);
    }

    @DeleteMapping(value = "/{theId}")
    public ResponseEntity<Map<String, Boolean>> deleteStock(@PathVariable Long theId) {
        return stockService.deleteStock(theId);
    }

}
