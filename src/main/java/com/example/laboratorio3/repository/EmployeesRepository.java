package com.example.laboratorio3.repository;


import com.example.laboratorio3.dto.EmployeesSalario;
import com.example.laboratorio3.dto.ReporteDepartamentoCiudad;
import com.example.laboratorio3.dto.ReporteGerentes;
import com.example.laboratorio3.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    @Query(value="SELECT e.first_name, e.last_name, jh.start_date, jh.end_date, j.job_title FROM employees e, job_history jh, jobs j\n" +
            "where e.employee_id = jh.employee_id and e.job_id = j.job_id and e.salary > 15000 \n", nativeQuery=true)
    List<EmployeesSalario> obtenerEmpleadoSalario();

    @Query(value="", nativeQuery=true)
    List<ReporteDepartamentoCiudad> obtenerReporteDepCiudad();

    @Query(value="", nativeQuery=true)
    List<ReporteGerentes> obtenerReporteGerente();

}
