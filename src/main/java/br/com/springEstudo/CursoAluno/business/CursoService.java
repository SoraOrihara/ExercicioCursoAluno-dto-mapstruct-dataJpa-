package br.com.springEstudo.CursoAluno.business;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import br.com.springEstudo.CursoAluno.business.dto.CursoRequestDto;
import br.com.springEstudo.CursoAluno.business.dto.CursoResponseDto;
import br.com.springEstudo.CursoAluno.business.mapstructure.CursoMapper;
import br.com.springEstudo.CursoAluno.business.mapstructure.ReferenciaMapper;
import br.com.springEstudo.CursoAluno.infraestructure.entities.AlunoEntity;
import br.com.springEstudo.CursoAluno.infraestructure.entities.CursoEntity;
import br.com.springEstudo.CursoAluno.infraestructure.repositories.CursoRepository;
import jakarta.transaction.Transactional;

public class CursoService {
	private final CursoRepository cursoRepository;
	private final CursoMapper cursoMapper;
	private final ReferenciaMapper mapper;

	public CursoService(CursoRepository cursoRepository,CursoMapper cursoMapper, ReferenciaMapper mapper) {
		this.cursoRepository=cursoRepository;
		this.cursoMapper=cursoMapper;
		this.mapper = mapper;
	}

	@Transactional
	public CursoResponseDto create(CursoRequestDto request) {
		Set<AlunoEntity> alunos = new HashSet<>();
		alunos=mapper.AlunoEntity(request.getAlunos());
		CursoEntity curso = new CursoEntity(request.getNome(), alunos);
		return cursoMapper.paraCursoResponseDto(curso);

	}

	public List<CursoResponseDto> listarTudo() {
		return cursoMapper.paraListaCursoResponseDto(cursoRepository.findAll());
	}

	public CursoResponseDto listarPorNome(String nome) {
		if (!cursoRepository.existsByNome(nome)) {
			throw new RuntimeException("erro");
		}
		return cursoMapper.paraCursoResponseDto(cursoRepository.findByNome(nome));
	}

	@Transactional
	public CursoResponseDto update(UUID id, CursoRequestDto request) {
		// Pegar o id do curso e depois atualizar de acordo com o request
		CursoEntity curso= cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("erro"));
		cursoMapper.update(request, curso);

		// Pegar o aluno do request(se tiver) e transformar de uuid para a entidade e
		// depois salvar
		if (!request.getAlunos().isEmpty()) {
			Set<AlunoEntity> alunos = mapper.AlunoEntity(request.getAlunos());
			curso.setAlunos(alunos);
		}
		// Salvar e retornar o curso salvo
		CursoEntity savedCurso = cursoRepository.save(curso);
		return cursoMapper.paraCursoResponseDto(savedCurso);
	}

	@Transactional
	public void deletarByNome(String nome) {
		if (!cursoRepository.existsByNome(nome)) {
			throw new RuntimeException("erro");
		}
		cursoRepository.deleteByNome(nome);
	}

}
