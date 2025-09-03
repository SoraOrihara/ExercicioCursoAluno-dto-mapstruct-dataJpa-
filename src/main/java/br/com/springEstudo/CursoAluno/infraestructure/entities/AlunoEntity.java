package br.com.springEstudo.CursoAluno.infraestructure.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_aluno")
public class AlunoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String email;

	@ManyToMany
	@JoinTable(name = "tb_aluno_curso", joinColumns = @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
	private Set<CursoEntity> cursos = new HashSet<>();

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public Set<CursoEntity> getCursos() {
		return cursos;
	}

	public void setCursos(Set<CursoEntity> cursos) {
		this.cursos = cursos;
	}

	public AlunoEntity(String nome, String email, Set<CursoEntity> cursos) {
		super();
		this.nome = nome;
		this.email = email;
		this.cursos = cursos;
	}

	public void addCurso(CursoEntity curso) {
		cursos.add(curso);
		curso.getAlunos().add(this);
	}

	public void removeCurso(CursoEntity curso) {
		cursos.remove(curso);
		curso.getAlunos().remove(this);
	}

}
