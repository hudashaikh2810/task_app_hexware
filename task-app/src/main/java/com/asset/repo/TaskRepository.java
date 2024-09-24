package com.asset.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asset.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
@Query("select t from Task t where t.id=?1")
	Optional<Task> findTaskById(int id);

}
