package com.mycompany.repository;


import com.mycompany.model.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface QueriesRepository extends JpaRepository<Queries,Integer> {
}
