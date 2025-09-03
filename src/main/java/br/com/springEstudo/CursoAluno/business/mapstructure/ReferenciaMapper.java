package br.com.springEstudo.CursoAluno.business.mapstructure;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.springEstudo.CursoAluno.infraestructure.entities.AlunoEntity;
import br.com.springEstudo.CursoAluno.infraestructure.entities.CursoEntity;
import br.com.springEstudo.CursoAluno.infraestructure.repositories.AlunoRepository;
import br.com.springEstudo.CursoAluno.infraestructure.repositories.CursoRepository;

@Mapper(componentModel = "spring")
public abstract class ReferenciaMapper {

	@Autowired
	private  AlunoRepository repository;
	@Autowired
	private CursoRepository cursoRepository;
	
	public Set<AlunoEntity> AlunoEntity(Set<UUID> id) {
		if(id==null) {
			return new HashSet<>();
		}
		return new HashSet<>(repository.findAllById(id));
	}
	
	public Set<CursoEntity> CursoEntity(Set<UUID> id) {
		if(id==null) {
			return new HashSet<>();
		}
		return new HashSet<>(cursoRepository.findAllById(id));
	}
	
	
}
