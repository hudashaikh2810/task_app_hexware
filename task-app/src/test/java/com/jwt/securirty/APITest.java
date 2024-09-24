package com.jwt.securirty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.asset.dto.TaskDto;
import com.asset.enums.Priority;
import com.asset.enums.Status;
import com.asset.exception.InvalidIdException;
import com.asset.model.Task;
import com.asset.repo.TaskRepository;
import com.asset.service.TaskService;

@SpringBootTest
public class APITest {
	@InjectMocks
	private TaskService taskService;
	@Mock
	private TaskRepository taskRepository;
	
	@Test
	public void saveTask()
	{
		TaskDto dto=new TaskDto();
		dto.setTitle("ABC");
		dto.setDescription("abcdefg");
		dto.setPriority("Low");
		dto.setStatus("Completed");
		dto.setDate("2024-09-24");
		Task t=new Task();
		t.setId(1);
		t.setTitle(dto.getTitle());
		t.setDescription(dto.getDescription());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateTime = LocalDate.parse("2024-09-24", formatter);
		t.setDueDate(dateTime);
		t.setPriority(Priority.Low);
		t.setStatus(Status.Completed);
		
		when(taskService.addTask(dto)).thenReturn(t);
		Task tActual=taskService.addTask(dto);
		assertNotNull(tActual);
		
	}
	
	@Test
	public void getAllTask()
	{
	List<Task> list=new ArrayList<>();
	Task t=new Task();
	t.setId(1);
	t.setTitle("ABC");
	t.setDescription("123");
	t.setPriority(Priority.High);
	t.setStatus(Status.Completed);
	t.setDueDate(LocalDate.now());
	list.add(t);
	t=new Task();
	t.setId(2);
	t.setTitle("do it");
	t.setDescription("have lot to do");
	t.setPriority(Priority.High);
	t.setStatus(Status.Pending);
	t.setDueDate(LocalDate.now());
	list.add(t);
	try {
		when(taskService.getAll()).thenReturn(list);
		int expectedSize=2;
		int actualSize=taskService.getAll().size();
		assertEquals(expectedSize,actualSize);

	} catch (InvalidIdException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	@Test
	public void getSpeicifcTask() throws InvalidIdException
	{
		List<Task> list=new ArrayList<>();
		Task t=new Task();
		t.setId(1);
		t.setTitle("ABC");
		t.setDescription("123");
		t.setPriority(Priority.High);
		t.setStatus(Status.Completed);
		t.setDueDate(LocalDate.now());
		list.add(t);
		t=new Task();
		t.setId(2);
		t.setTitle("do it");
		t.setDescription("have lot to do");
		t.setPriority(Priority.High);
		t.setStatus(Status.Pending);
		t.setDueDate(LocalDate.now());
		list.add(t);
		when(taskService.getTaskById(2)).thenReturn(t);
		Task actual=taskService.getTaskById(2);
		assertEquals(t.getId(),actual.getId());
		
		
	}
	@Test
	public void Delete() throws InvalidIdException
	{
		Task t=new Task();
		t.setId(1);
		t.setTitle("ABC");
		t.setDescription("123");
		t.setPriority(Priority.High);
		t.setStatus(Status.Completed);
		t.setDueDate(LocalDate.now());
		
		when(taskService.deleteTaskById(1)).thenReturn(t);
		Task actual=taskService.getTaskById(2);
		assertEquals(t.getId(),actual.getId());
		
		
	}
	@Test
	public void Update() throws InvalidIdException
	{
		TaskDto dto=new TaskDto();
		dto.setTitle("ABC");
		dto.setDescription("abcdefg");
		dto.setPriority("Low");
		dto.setStatus("Completed");
		dto.setDate("2024-09-24");
		Task t=new Task();
		t.setId(1);
		t.setTitle(dto.getTitle());
		t.setDescription(dto.getDescription());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateTime = LocalDate.parse("2024-09-24", formatter);
		t.setDueDate(dateTime);
		t.setPriority(Priority.valueOf(dto.getPriority()));
		t.setStatus(Status.valueOf(dto.getStatus()));
		
		when(taskService.updateTask(1, dto)).thenReturn(t);
		Task actual=taskService.getTaskById(1);
		assertEquals(t,actual);
		
		
	}
	

}

