package com.reactive.poc.employee.service;

import com.reactive.poc.employee.dao.EmployeeRepository;
import com.reactive.poc.employee.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void create(Employee employee) {
        employeeRepository.save(employee).subscribe();
    }

    @Override
    public Mono<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Flux<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Mono<Employee> update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return employeeRepository.deleteById(id);
    }
}
