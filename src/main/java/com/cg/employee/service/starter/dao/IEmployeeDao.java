package com.cg.employee.service.starter.dao;

import com.cg.employee.service.starter.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeDao extends JpaRepository<Employee, Integer> {
}
