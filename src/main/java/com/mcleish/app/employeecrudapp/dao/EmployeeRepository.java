package com.mcleish.app.employeecrudapp.dao;

import com.mcleish.app.employeecrudapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    long count();

    Employee findByFirstName(String name);

    List<Employee> findAllByOrderByLastNameAsc();

    List<Employee> findAllByOrderByFirstNameAsc();
}
