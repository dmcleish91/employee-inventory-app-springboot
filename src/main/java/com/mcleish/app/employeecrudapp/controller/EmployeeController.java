package com.mcleish.app.employeecrudapp.controller;

import com.mcleish.app.employeecrudapp.entity.Employee;
import com.mcleish.app.employeecrudapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/page/{pageNum}")
    public ResponseEntity<Page<Employee>> pageAndSortEmployee(@PathVariable(value = "pageNum") int pageNum,
                                              @RequestParam("sortField") String sortField,
                                              @RequestParam("sortDir") String sortDir) {
        return new ResponseEntity<>(employeeService.pageAndSortEmployee(pageNum, sortField, sortDir), HttpStatus.OK);

    }

    @GetMapping(value = "/total")
    public ResponseEntity<Long> getTotalEmployees() {
        return new ResponseEntity<>(employeeService.getTotalEmployees(), HttpStatus.OK);
    }

    @GetMapping(value = "/firstName/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name) {
        return new ResponseEntity<>(employeeService.findByFirstName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/{theId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long theId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(theId), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/employees/save").toUriString());
        return ResponseEntity.created(uri).body(employeeService.saveEmployee(employee));
    }

    @PutMapping(value = "/update/{theId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long theId, @RequestBody Employee theEmployee) {
        return ResponseEntity.ok(employeeService.updateEmployee(theId, theEmployee));
    }

    @DeleteMapping(value = "/{theId}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long theId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(theId), HttpStatus.OK) ;

    }
}
