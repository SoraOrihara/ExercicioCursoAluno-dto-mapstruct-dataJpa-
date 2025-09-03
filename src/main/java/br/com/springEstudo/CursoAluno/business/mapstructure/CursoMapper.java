package br.com.springEstudo.CursoAluno.business.mapstructure;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import br.com.springEstudo.CursoAluno.business.dto.CursoRequestDto;
import br.com.springEstudo.CursoAluno.business.dto.CursoResponseDto;
import br.com.springEstudo.CursoAluno.infraestructure.entities.CursoEntity;

@Mapper(componentModel = "spring", uses = { AlunoMapper.class,
		ReferenciaMapper.class }, unmappedSourcePolicy = ReportingPolicy.WARN)
public interface CursoMapper {

	@Mapping(target = "id", ignore = true)
	CursoEntity paraCursoEntity(CursoRequestDto dto);

	
	CursoResponseDto paraCursoResponseDto (CursoEntity curso);

	List<CursoResponseDto> paraListaCursoResponseDto(List<CursoEntity> entities);
	
	@Mapping(target="id",ignore=true)
	void update(CursoRequestDto dto, @MappingTarget CursoEntity entity);
}
