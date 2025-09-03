package br.com.springEstudo.CursoAluno.business;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springEstudo.CursoAluno.business.dto.AlunoRequestDto;
import br.com.springEstudo.CursoAluno.business.dto.AlunoResponseDto;
import br.com.springEstudo.CursoAluno.business.mapstructure.AlunoMapper;
import br.com.springEstudo.CursoAluno.business.mapstructure.ReferenciaMapper;
import br.com.springEstudo.CursoAluno.infraestructure.entities.AlunoEntity;
import br.com.springEstudo.CursoAluno.infraestructure.entities.CursoEntity;
import br.com.springEstudo.CursoAluno.infraestructure.repositories.AlunoRepository;
import jakarta.transaction.Transactional;

@Service
public class AlunoService {

	private final AlunoRepository alunoRepository;
	private final AlunoMapper alunoMapper;
	@Autowired
	private final ReferenciaMapper mapper;

	public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper, ReferenciaMapper mapper) {
		this.alunoRepository = alunoRepository;
		this.alunoMapper = alunoMapper;
		this.mapper = mapper;
	}

	@Transactional
	public AlunoResponseDto create(AlunoRequestDto request) {
		Set<CursoEntity> cursos = new HashSet<>();
		cursos = mapper.CursoEntity(request.getCursos());
		AlunoEntity aluno = new AlunoEntity(request.getNome(), request.getEmail(), cursos);
		return alunoMapper.paraAlunoResponseDto(alunoRepository.save(aluno));
	}

	public List<AlunoResponseDto> listarTudo() {
		return alunoMapper.paraListaAlunoResponseDto(alunoRepository.findAll());
	}

	public AlunoResponseDto listarPorEmail(String email) {
		if (!alunoRepository.existsByEmail(email)) {
			throw new RuntimeException("erro");
		}
		return alunoMapper.paraAlunoResponseDto(alunoRepository.findByEmail(email));
	}

	@Transactional
	public AlunoResponseDto update(UUID id, AlunoRequestDto request) {
		// Pegar o id do aluno e depois atualizar de acordo com o request
		AlunoEntity aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("erro"));
		alunoMapper.updateAluno(request, aluno);

		// Pegar o curso do request(se tiver) e transformar de uuid para a entidade e
		// depois salvar
		if (!request.getCursos().isEmpty()) {
			Set<CursoEntity> cursos = mapper.CursoEntity(request.getCursos());
			aluno.setCursos(cursos);
		}
		// Salvar e retornar o aluno salvo
		AlunoEntity savedAluno = alunoRepository.save(aluno);
		return alunoMapper.paraAlunoResponseDto(savedAluno);
	}

	@Transactional
	public void deletarByEmail(String email) {
		if (!alunoRepository.existsByEmail(email)) {
			throw new RuntimeException("erro");
		}
		alunoRepository.deleteByEmail(email);
	}

}
