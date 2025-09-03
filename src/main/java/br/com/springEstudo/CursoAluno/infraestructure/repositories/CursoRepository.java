package br.com.springEstudo.CursoAluno.infraestructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springEstudo.CursoAluno.infraestructure.entities.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {

	CursoEntity findByNome(String nome);
	boolean existsByNome(String nome);

}
