package com.asset.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asset.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

}
