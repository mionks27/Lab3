package com.example.laboratorio3.entity;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class DepartmentEntity {

    @Id
    private
    int department_id;
    @Column(nullable = false)
    private String departmentName;
    private Integer managerId;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private
    Location locacion;


    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }


    public Location getLocacion() {
        return locacion;
    }

    public void setLocacion(Location locacion) {
        this.locacion = locacion;
    }
}
