package br.com.springEstudo.CursoAluno.infraestructure.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_curso")
public class CursoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	
	private String nome;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(mappedBy ="cursos" )
	private Set<AlunoEntity> alunos = new HashSet<>();

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

	public Set<AlunoEntity> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<AlunoEntity> alunos) {
		this.alunos = alunos;
	}

	public CursoEntity() {
		super();
	}

	public CursoEntity(String nome, Set<AlunoEntity> alunos) {
		super();
		this.nome = nome;
		this.alunos = alunos;
	}
	
	public void addCurso(AlunoEntity aluno) {
		alunos.add(aluno);
		aluno.getCursos().add(this);
	}
	
	public void removeCurso(AlunoEntity aluno) {
		alunos.remove(aluno);
		aluno.getCursos().remove(this);
	}
	
	
	
}
