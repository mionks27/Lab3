package com.example.laboratorio3.controller;

import com.example.laboratorio3.repository.DepartmentRepository;
import com.example.laboratorio3.repository.EmployeesRepository;
import com.example.laboratorio3.repository.JobRepository;
import com.example.laboratorio3.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    LocationRepository locationRepository;

    public String listaEmployee(   ){
        return "";
    }

    public String nuevoEmployeeForm( ) {
        return "";
    }


    public String guardarEmployee() {
        return "";
    }


    public String editarEmployee() {
        return "";
    }


    public String borrarEmpleado() {

        return "";

    }

}
