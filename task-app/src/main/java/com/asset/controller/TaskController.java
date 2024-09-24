package com.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asset.dto.MessageDto;
import com.asset.dto.TaskDto;
import com.asset.exception.InvalidIdException;
import com.asset.exception.InvalidInputException;
import com.asset.model.Task;
import com.asset.service.TaskService;
@Controller
@RequestMapping("/task")
public class TaskController {
@Autowired
private TaskService taskService;
@PostMapping("/add")
public ResponseEntity<?> addTask(@RequestBody TaskDto t,MessageDto dto)
{
	try {
		taskService.validateTask(t);
		return ResponseEntity.ok(taskService.addTask(t));
	} catch (InvalidInputException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
}
@GetMapping("/all")
public ResponseEntity<?> getAll(MessageDto dto)
{
	try {
		List<Task> task=taskService.getAll();
		return ResponseEntity.ok(task);

	} catch (InvalidIdException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
}
@GetMapping("/byId/{id}")
public ResponseEntity<?> getTaskById(@PathVariable int id,MessageDto dto)
{
	try {
		Task t=taskService.getTaskById(id);
		return ResponseEntity.ok(t);

	} catch (InvalidIdException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);

	}
}
@DeleteMapping("/delete/{id}")
public ResponseEntity<?> deleteTaskById(@PathVariable int id,MessageDto dto)
{
	try {
		Task t=taskService.deleteTaskById(id);
		return ResponseEntity.ok("Task "+t+" has been deleted");

	} catch (InvalidIdException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);

	}
}
@PutMapping("/update/{id}")
public ResponseEntity<?> updateTaskById(@RequestBody TaskDto taskDto,@PathVariable int id,MessageDto dto)
{
	try {
		taskService.validateTask(taskDto);
		Task t=taskService.updateTask(id,taskDto);
		return ResponseEntity.ok("Task "+t+" has been updated");

	} catch (InvalidIdException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);

	} catch (InvalidInputException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
}


}
