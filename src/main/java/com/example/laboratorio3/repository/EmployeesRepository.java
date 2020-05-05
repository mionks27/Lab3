package com.example.laboratorio3.repository;


import com.example.laboratorio3.dto.EmployeesSalario;
import com.example.laboratorio3.dto.HistorialEmployee;
import com.example.laboratorio3.dto.ReporteDepartamentoCiudad;
import com.example.laboratorio3.dto.ReporteGerentes;
import com.example.laboratorio3.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.List;


@Repository

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    @Query(value="SELECT e.first_name as firstname, e.last_name as lastname, jh.start_date as hiredate, jh.end_date as enddate, j.job_title as jobtitle FROM employees e, job_history jh, jobs j\n" +
            "where e.employee_id = jh.employee_id and e.job_id = j.job_id and e.salary > 15000 \n", nativeQuery=true)
    List<EmployeesSalario> obtenerEmpleadoSalario();

    @Query(value="SELECT c.country_name as countryname, l.city as regionname, count(d.department_id) as cantidad FROM employees e, departments d, countries c, locations l\n" +
            "where e.department_id = d.department_id and d.location_id = l.location_id and l.country_id = c.country_id\n" +
            "GROUP BY d.department_id\n" +
            "HAVING COUNT(e.first_name)>3;\n ", nativeQuery=true)
    List<ReporteDepartamentoCiudad> obtenerReporteDepCiudad();

    @Query(value="select e.first_name as nombre, e.last_name as apellido, j.job_title as puesto, d.department_name as departamento, e.hire_date as fechainicio from employees e\n" +
            "left join jobs j on e.job_id = j.job_id \n" +
            "left join departments d on e.department_id = d.department_id\n",nativeQuery=true)
    List<HistorialEmployee> obtenerHistorial();

    @Query(value="select d.department_name, e.first_name, e.last_name, e.salary from departments d, employees e\n" +
            "where e.employee_id = d.manager_id and floor((sysdate() - e.hire_date)/365) > 5\n", nativeQuery=true)
    List<ReporteGerentes> obtenerReporteGerente();

    List<Employees> findByFirstNameOrLastName(String nombre,String apellido);

}
