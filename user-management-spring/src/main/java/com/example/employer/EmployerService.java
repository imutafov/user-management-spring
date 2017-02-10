/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author msol-pc
 */
@Service
public class EmployerService {

    @Autowired
    private EmployerRepository repo;

    public Employer save(Employer empl) {
        return repo.save(empl);
    }

    public List<EmployerDTO> getAllEmployers() {
        List<Employer> employers = repo.findAll();
        return EmployerMapper.mapEntitiesIntoDTOs(employers);
    }

    public Employer getByUsername(String username) {
        return repo.findByUserUserName(username);
    }

}
