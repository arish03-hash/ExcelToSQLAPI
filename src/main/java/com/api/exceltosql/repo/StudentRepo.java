package com.api.exceltosql.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.exceltosql.model.Students;

public interface StudentRepo extends JpaRepository<Students, Integer>{

}
