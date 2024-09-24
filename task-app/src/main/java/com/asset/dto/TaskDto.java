package com.asset.dto;

public class TaskDto {
private String title;
private String description;
public TaskDto() {
	super();
	// TODO Auto-generated constructor stub
}
public TaskDto(String title, String description, String priority, String status) {
	super();
	this.title = title;
	this.description = description;
	this.priority = priority;
	this.status = status;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getPriority() {
	return priority;
}
public void setPriority(String priority) {
	this.priority = priority;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
private String priority;
private String status;

}
