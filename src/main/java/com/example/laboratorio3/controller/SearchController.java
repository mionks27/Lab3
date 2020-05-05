package com.example.laboratorio3.controller;

import com.example.laboratorio3.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping(value = {"","/"})
    public String indice(){

        return "Search/indice";
    }
    @GetMapping("reporteEmployee")
    public String employeeSalario(Model model){
        model.addAttribute("listaEmployees", employeesRepository.obtenerEmpleadoSalario());
        return "Search/reporteEmployee";
    }
    @GetMapping("reporteDepartemanto")
    public String reporteDepartamento(Model model){
        model.addAttribute("listaDepartamentos", employeesRepository.obtenerReporteDepCiudad());
        return "Search/reporteDepartamento";
    }
    @GetMapping("reporteGerentes")
    public String reporteGerentes(Model model){
        model.addAttribute("listaGerentes", employeesRepository.obtenerReporteGerente());
        return "Search/reporteGerentes";
    }


}
