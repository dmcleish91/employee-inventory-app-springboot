package com.mcleish.app.employeecrudapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcleish.app.employeecrudapp.domain.employee.controller.EmployeeController;
import com.mcleish.app.employeecrudapp.domain.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    //Employee employee1 = new Employee("John", "Doe", "sales", "john@bigsales.com", "45000");
    //Employee employee2 = new Employee("Mary", "Jane", "intern", "mary@bigsales.com", "45000");
    ///Employee employee3 = new Employee("Tony", "Stark", "ceo", "tony@bigsales.com", "200000");

    @Test
    void getAllEmployees() throws Exception {
        //Page employeePage = mock(Page.class);
        //when(employeeService.pageAndSortEmployee(1, "firstName","asc")).thenReturn(employeePage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(status().isOk());
    }

    @Test
    void pageAndSortEmployee() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/page/1?sortField=firstName&sortDir=asc"))
                .andExpect(status().isOk());
    }

    @Test
    void getTotalEmployees() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/count"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeeByName() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/firstName/alberto"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeeById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    void saveEmployee() throws Exception{

    }

    @Test
    void updateEmployee() throws Exception{

    }

    @Test
    void deleteEmployee() throws Exception{

    }
}