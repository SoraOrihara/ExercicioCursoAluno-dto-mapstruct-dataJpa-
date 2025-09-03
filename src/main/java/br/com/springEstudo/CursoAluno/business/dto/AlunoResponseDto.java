package br.com.springEstudo.CursoAluno.business.dto;

import java.util.List;
import java.util.UUID;

public record AlunoResponseDto(UUID id, String nome, String email,List<CursoResponseDto> curso ) {

}
