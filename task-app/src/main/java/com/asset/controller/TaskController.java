package com.asset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asset.dto.MessageDto;
import com.asset.dto.TaskDto;
import com.asset.exception.InvalidInputException;
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

}
