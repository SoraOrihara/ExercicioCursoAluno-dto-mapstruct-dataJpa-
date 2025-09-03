package br.com.springEstudo.CursoAluno.business.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;


public class AlunoRequestDto {

	@NotBlank
	private String nome;
	@NotBlank
	private String email;
	
	private Set<UUID> cursos = new HashSet<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UUID> getCursos() {
		return cursos;
	}

	public void setCursos(Set<UUID> cursos) {
		this.cursos = cursos;
	}

	public AlunoRequestDto() {
		super();
	}

	public AlunoRequestDto(@NotBlank String nome, @NotBlank String email, Set<UUID> cursos) {
		super();
		this.nome = nome;
		this.email = email;
		this.cursos = cursos;
	}

	
}
