package com.mcleish.app.employeecrudapp.domain.employee.repository;

import com.mcleish.app.employeecrudapp.domain.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    long count();

    Employee findByFirstName(String name);

}
