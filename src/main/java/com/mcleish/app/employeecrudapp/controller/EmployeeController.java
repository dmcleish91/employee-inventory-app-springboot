package com.mcleish.app.employeecrudapp.controller;

import com.mcleish.app.employeecrudapp.entity.Employee;
import com.mcleish.app.employeecrudapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return employeeService.findAll();
    }

    //todo - employee pageandsort test method can be removed
    @GetMapping(value = "/")
    public Page<Employee> getEmployeesPage() {
        return employeeService.pageAndSortEmployee(1, "firstName", "asc");
    }

    @GetMapping(value = "/page/{pageNum}")
    public Page<Employee> pageAndSortEmployee(@PathVariable(value = "pageNum") int pageNum,
                                              @RequestParam("sortField") String sortField,
                                              @RequestParam("sortDir") String sortDir) {
        return employeeService.pageAndSortEmployee(pageNum, sortField, sortDir);

    }

    @GetMapping(value = "/total")
    public Long getTotalEmployees() {
        return employeeService.getTotalEmployees();
    }

    @GetMapping(value = "/firstName/{name}")
    public Employee getEmployeeByName(@PathVariable String name) {
        return employeeService.findByFirstName(name);
    }

    @GetMapping(value = "/{theId}")
    public Employee getEmployeeById(@PathVariable long theId) {
        return employeeService.getEmployeeById(theId);
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
        return employeeService.deleteEmployee(theId);

    }
}
