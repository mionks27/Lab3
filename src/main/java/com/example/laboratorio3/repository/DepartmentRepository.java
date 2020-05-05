package com.example.laboratorio3.repository;

import com.example.laboratorio3.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {
    List<DepartmentEntity> findByDepartmentName(String dep);
}
