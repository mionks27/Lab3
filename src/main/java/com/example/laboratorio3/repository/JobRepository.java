package com.example.laboratorio3.repository;

import com.example.laboratorio3.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity,String> {

    List<JobEntity> findByJobTitle(String puesto);
}
