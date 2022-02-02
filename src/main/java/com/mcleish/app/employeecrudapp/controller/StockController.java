package com.mcleish.app.employeecrudapp.controller;

import com.mcleish.app.employeecrudapp.entity.Stock;
import com.mcleish.app.employeecrudapp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Stock>> getAllStock() {
        return new ResponseEntity<>(stockService.findAllStock(), HttpStatus.OK);
    }

    @GetMapping(value = "/page/{pageNum}")
    public ResponseEntity<Page<Stock>> pageAndSortStock(@PathVariable(value = "pageNum") int pageNum,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir) {
        return new ResponseEntity<>(stockService.pageAndSortStock(pageNum, sortField, sortDir), HttpStatus.OK);

    }

    @GetMapping(value = "/totals/availableSum")
    public ResponseEntity<String> getSumOfAvailableUnits() {
        return new ResponseEntity<>(stockService.sumOfAvailableUnits(), HttpStatus.OK);
    }

    @GetMapping(value = "/totals/soldSum")
    public ResponseEntity<String> getSumOfSoldUnits() {
        return new ResponseEntity<>(stockService.sumOfSoldUnits(), HttpStatus.OK);
    }

    @GetMapping(value = "/{theId}")
    public ResponseEntity<Stock> getStockById(@PathVariable long theId) {
        return new ResponseEntity<>(stockService.getStockById(theId), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Stock> saveStock(@RequestBody Stock stock) {
        return new ResponseEntity<>(stockService.saveStock(stock), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{theId}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long theId, @RequestBody Stock theStock) {
        return new ResponseEntity<>(stockService.updateStock(theId, theStock), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{theId}")
    public ResponseEntity<Map<String, Boolean>> deleteStock(@PathVariable Long theId) {
        return new ResponseEntity<>(stockService.deleteStock(theId), HttpStatus.OK);
    }

}
