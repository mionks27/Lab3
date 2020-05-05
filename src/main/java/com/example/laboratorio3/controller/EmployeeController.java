package com.example.laboratorio3.controller;

import com.example.laboratorio3.entity.DepartmentEntity;
import com.example.laboratorio3.entity.Employees;
import com.example.laboratorio3.entity.JobEntity;
import com.example.laboratorio3.entity.Location;
import com.example.laboratorio3.repository.DepartmentRepository;
import com.example.laboratorio3.repository.EmployeesRepository;
import com.example.laboratorio3.repository.JobRepository;
import com.example.laboratorio3.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;



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


    @GetMapping(value = {"", "/lista"})
    public String listaEmployee(Model model) {
        model.addAttribute("lista", employeesRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("nuevo")
    public String nuevoEmployeeForm(Model model) {
        model.addAttribute("listaPuesto", jobRepository.findAll());
        model.addAttribute("listaJefes", employeesRepository.findAll());
        model.addAttribute("listaDepartamento", departmentRepository.findAll());
        return "employee/newFrm";
    }

    @PostMapping("guardar")
    public String guardarEmployee(Employees employees, RedirectAttributes attr) {
        if (employees.getEmployee_id() == 0) {
            attr.addFlashAttribute("msg", "Empleado creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Empleado actualizado exitosamente");
        }
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fechaActual = año + "-" + (mes + 1) + "-" + dia + " " + hora + ":" + minuto + ":" + segundo;
        employees.setHireDate(fechaActual);
        employeesRepository.save(employees);

        return "redirect:/lista";
    }


    @GetMapping("editar")
    public String editarEmployee(Model model, @RequestParam("id") int id) {
        Optional<Employees> empleado1 = employeesRepository.findById(id);
        if (empleado1.isPresent()) {
            Employees empleado2 = empleado1.get();
            model.addAttribute("empleado", empleado2);
            model.addAttribute("listaPuesto", jobRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartamento", departmentRepository.findAll());
            return "employee/editFrm";
        } else {
            return "redirect:/lista";
        }
    }


    @GetMapping("borrar")
    public String borrarEmpleado(@RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Employees> empleado1 = employeesRepository.findById(id);
        if (empleado1.isPresent()) {
            employeesRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Borrado exitosamente");
        }


        return "redirect:/lista";
    }


    @GetMapping("buscarEmpleado")
    public String buscarEmpleado(@RequestParam("searchField") String search, Model model) {

        List<Employees> emp = employeesRepository.findByFirstNameOrLastName(search, search);
        List<JobEntity> job = jobRepository.findByJobTitle(search);
        List<DepartmentEntity> dep = departmentRepository.findByDepartmentName(search);
        List<Location> loc = locationRepository.findByCity(search);
        if (!emp.isEmpty()) {

            model.addAttribute("lista", emp);
        }

        if (!job.isEmpty()) {
            model.addAttribute("lista", job);
        }
        if (!dep.isEmpty()) {
            model.addAttribute("lista", dep);
        }
        if (!loc.isEmpty()) {
            model.addAttribute("lista", loc);
        }

        return "employee/lista";
    }
}
