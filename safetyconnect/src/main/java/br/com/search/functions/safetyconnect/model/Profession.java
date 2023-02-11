package br.com.search.functions.safetyconnect.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_profession")
public class Profession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cbo;
	private String description;

	@Column(name = "id_training")
	private long id_training;

	public Profession() {

	}

	public Profession(String cbo, String description, Long id_training) {
		super();
		this.cbo = cbo;
		this.description = description;
		this.id_training = id_training;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId_training() {
		return id_training;
	}

	public void setId_training(long id_training) {
		this.id_training = id_training;
	}

}
