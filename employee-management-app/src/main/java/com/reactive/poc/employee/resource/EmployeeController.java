package com.reactive.poc.employee.resource;

import com.reactive.poc.employee.model.Employee;
import com.reactive.poc.employee.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping(value = {"/create", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final Employee employee){
        employeeService.create(employee);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id){
        final Mono<Employee> employeeMono = employeeService.findById(id);
        HttpStatus httpStatus = employeeMono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(employeeMono, httpStatus);
    }

    @GetMapping(value = "/name/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name){
        return employeeService.findByName(name);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll(){
        Flux<Employee> emps = employeeService.findAll();
        return emps;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update")
    public Mono<Employee> update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        employeeService.delete(id).subscribe();
    }
}
