package br.com.springEstudo.CursoAluno.infraestructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springEstudo.CursoAluno.infraestructure.entities.AlunoEntity;


@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, UUID> {

	AlunoEntity findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	void deleteByEmail(String email);
}
