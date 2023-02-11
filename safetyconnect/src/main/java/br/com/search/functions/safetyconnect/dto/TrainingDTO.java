package br.com.search.functions.safetyconnect.dto;

import java.util.List;

public class TrainingDTO {
	
	private Long id;
	private String name;
	private List<ProfessionDTO> prof;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProfessionDTO> getProf() {
		return prof;
	}
	public void setProf(List<ProfessionDTO> prof) {
		this.prof = prof;
	}

}
