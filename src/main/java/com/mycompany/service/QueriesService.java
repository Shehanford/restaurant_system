package com.mycompany.service;


import com.mycompany.model.Queries;
import com.mycompany.repository.QueriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueriesService {


        @Autowired
        private QueriesRepository queriesRepo;

        public void save(Queries queries){
            queriesRepo.save(queries);
        }

        public List<Queries> getAllQueries(){
            return queriesRepo.findAll();
        }

        public void deleteById(int id){
            queriesRepo.deleteById(id);
        }

        public Queries getQueriesById(int id){
            return queriesRepo.findById(id).get();
        }

    }


