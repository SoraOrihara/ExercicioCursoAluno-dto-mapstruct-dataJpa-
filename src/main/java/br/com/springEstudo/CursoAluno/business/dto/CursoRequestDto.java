package br.com.springEstudo.CursoAluno.business.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class CursoRequestDto {
	
	@NotBlank
	private String nome;

	private Set<UUID> alunos = new HashSet<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<UUID> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<UUID> alunos) {
		this.alunos = alunos;
	}

	public CursoRequestDto() {
		super();
	}

	public CursoRequestDto(@NotBlank String nome, Set<UUID> alunos) {
		super();
		this.nome = nome;
		this.alunos = alunos;
	}

	
	
	
}
