package br.com.springEstudo.CursoAluno.business.mapstructure;

import java.util.List;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import br.com.springEstudo.CursoAluno.business.dto.AlunoRequestDto;
import br.com.springEstudo.CursoAluno.business.dto.AlunoResponseDto;
import br.com.springEstudo.CursoAluno.infraestructure.entities.AlunoEntity;

@Mapper(componentModel = "spring", uses = {CursoMapper.class,ReferenciaMapper.class},unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AlunoMapper {

	
	AlunoResponseDto paraAlunoResponseDto(AlunoEntity entity);
	
	@Mapping(target="id",ignore=true)
	AlunoEntity paraAlunoEntity(AlunoRequestDto dto);
	
	List<AlunoResponseDto> paraListaAlunoResponseDto(List<AlunoEntity>entities);
	
	@Mapping(target="id",ignore=true)
	void updateAluno(AlunoRequestDto dto,@MappingTarget AlunoEntity entity);

}
