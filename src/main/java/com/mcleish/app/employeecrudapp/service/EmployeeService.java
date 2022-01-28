package com.mcleish.app.employeecrudapp.service;

import com.mcleish.app.employeecrudapp.dao.EmployeeRepository;
import com.mcleish.app.employeecrudapp.entity.Employee;
import com.mcleish.app.employeecrudapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findByFirstName(@PathVariable String name) {
        return employeeRepository.findByFirstName(name);
    }

    public Page<Employee> pageAndSortEmployee(@PathVariable(value = "pageNum") int pageNum,
                                              @RequestParam("sortField") String sortField,
                                              @RequestParam("sortDir") String sortDir) {
        int pageSize = 5;

        Page<Employee> page = findPaginated(pageNum, pageSize, sortField, sortDir);

        //http://localhost:8080/api/employees/page/2?sortField=firstName&sortDir=asc
        return page;

    }

    public Long getTotalEmployees() {
        return employeeRepository.count();
    }

    public Employee getEmployeeById(@PathVariable long theId) {

        Employee employee = employeeRepository.findById(theId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: " + theId));

        // returns a formatted salary field IE: 999,999,999
        Long numberParsed = Long.parseLong(employee.getSalary());
        employee.setSalary(String.format("%,d",numberParsed));

        return employee;
    }

    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    public ResponseEntity<Employee> updateEmployee(@PathVariable Long theId, @RequestBody Employee theEmployee) {

        Employee employee = employeeRepository.findById(theId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: " + theId));

        // remove commas from salary field
        String salary = theEmployee.getSalary();
        salary = salary.replaceAll(",", "");

        employee.setFirstName(theEmployee.getFirstName());
        employee.setLastName(theEmployee.getLastName());
        employee.setEmail(theEmployee.getEmail());
        employee.setSalary(salary);
        employee.setDepartment(theEmployee.getDepartment());
        employee = employeeRepository.save(employee);

        return ResponseEntity.ok(employee);
    }

    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long theId) {
        Employee employee = employeeRepository.findById(theId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: " + theId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return ResponseEntity.ok(response);

    }

    public Page<Employee> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }

}
