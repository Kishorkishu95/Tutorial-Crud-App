package com.kishor.tutrialapp.Dto;

public class TutorialDto {

	private Long id;
	private String title;
	private String description;
	private boolean published;
	
	public TutorialDto(String title, String description, boolean published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public TutorialDto() {
		// TODO Auto-generated constructor stub
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

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
