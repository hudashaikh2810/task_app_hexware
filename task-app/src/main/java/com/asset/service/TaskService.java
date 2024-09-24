package com.asset.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asset.dto.TaskDto;
import com.asset.enums.Priority;
import com.asset.enums.Status;
import com.asset.exception.InvalidIdException;
import com.asset.exception.InvalidInputException;
import com.asset.model.Task;
import com.asset.repo.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	public Task addTask(TaskDto dto)
	{
		Task t=new Task();
	t.setTitle(dto.getTitle());
	t.setDescription(dto.getDescription());
	if(dto.getPriority().equalsIgnoreCase("Low"))
		t.setPriority(Priority.Low);
	if(dto.getPriority().equalsIgnoreCase("High"))
		t.setPriority(Priority.High);
	if(dto.getPriority().equalsIgnoreCase("Medium"))
		t.setPriority(Priority.Medium);
	if(dto.getStatus().equalsIgnoreCase("Pending"))
		t.setStatus(Status.Pending);
	if(dto.getStatus().equalsIgnoreCase("InProgress"))
		t.setStatus(Status.InProgress);
	if(dto.getStatus().equalsIgnoreCase("Completed"))
		t.setStatus(Status.Completed);
		t.setDueDate(LocalDate.now());
		return taskRepository.save(t);
	}
	public void validateTask(TaskDto t) throws InvalidInputException
	{
		if(t==null)
			throw new InvalidInputException("Task cannot be null");
		if(t.getTitle()==null||t.getTitle().equals(""))
		{
			throw new InvalidInputException("Task title should have valid value");

		}
		if(t.getDescription()==null||t.getDescription().equals(""))
		{
			throw new InvalidInputException("Task description should have valid value");

		}
		String priority=t.getPriority();
		if(priority==null||priority.equals(""))
		{
			throw new InvalidInputException("Task priority should have valid value");

		}
		if(priority.equalsIgnoreCase("low")||priority.equalsIgnoreCase("meidum")||priority.equalsIgnoreCase("high"))
		{
			
		}
		else
		{
			throw new InvalidInputException("Task priority can be medium,high or low");
		}
		String status=t.getStatus();
		if(status==null||status.equals(""))
		{
			throw new InvalidInputException("Task status should have valid value");

		}
		if(status.equalsIgnoreCase("pending")||status.equalsIgnoreCase("inprogress")||priority.equalsIgnoreCase("completed"))
		{
			
		}
		else
		{
			throw new InvalidInputException("Task status can be medium,inprogress,complted");
		}
	}
	public List<Task> getAll() throws InvalidIdException {
		// TODO Auto-generated method stub
		List<Task> allTask=taskRepository.findAll();
		if(allTask.isEmpty())
		{
			throw new InvalidIdException("No task to show");
		}
		return allTask;
	}

}
