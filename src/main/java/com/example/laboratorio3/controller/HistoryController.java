package com.example.laboratorio3.controller;


import com.example.laboratorio3.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping("historial")
    public String listarHistorial(Model model){
        model.addAttribute("listaHistorial", employeesRepository.obtenerHistorial());
        return "history/historial";
    }


}
