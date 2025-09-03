package br.com.springEstudo.CursoAluno.business.dto;

import java.util.Set;
import java.util.UUID;

public record CursoResponseDto(UUID id, String nome, Set<AlunoSimples> alunos) {
	
}
