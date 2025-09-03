package br.com.springEstudo.CursoAluno.business.dto;

import java.util.Set;
import java.util.UUID;

public record AlunoResponseDto(UUID id, String nome, String email,Set<CursoSimples> cursos ) {

}
