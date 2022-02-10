package com.reactive.poc.employee.service;

import com.reactive.poc.employee.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {
    void create(Employee employee);
    Mono<Employee> findById(Integer id);
    Flux<Employee> findByName(String name);
    Flux<Employee> findAll();
    Mono<Employee> update(Employee employee);
    Mono<Void> delete(Integer id);
}

