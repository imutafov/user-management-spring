/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Teodor Todorov
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public Page<Task> findByAssigneesId(Long id, Pageable pageRequest);

}
