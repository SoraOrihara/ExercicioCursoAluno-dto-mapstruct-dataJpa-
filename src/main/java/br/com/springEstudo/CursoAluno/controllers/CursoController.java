package br.com.springEstudo.CursoAluno.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springEstudo.CursoAluno.business.CursoService;
import br.com.springEstudo.CursoAluno.business.dto.CursoRequestDto;
import br.com.springEstudo.CursoAluno.business.dto.CursoResponseDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
public class CursoController {

	private final CursoService cursoService;

	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	@GetMapping
	public ResponseEntity<List<CursoResponseDto>> listarTodos() {
		List<CursoResponseDto> cursos = cursoService.listarTudo();
		return ResponseEntity.ok().body(cursos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CursoResponseDto> listarPorNome(@PathVariable UUID id) {
		CursoResponseDto curso = cursoService.listarPorId(id);
		return ResponseEntity.ok().body(curso);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CursoResponseDto> deletarById(@PathVariable UUID id) {
		cursoService.deletarById(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/create")
	public ResponseEntity<CursoResponseDto> create(@RequestBody @Valid CursoRequestDto request) {
		CursoResponseDto cursoCriado = cursoService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoCriado);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CursoResponseDto> update(@PathVariable UUID id, @RequestBody @Valid CursoRequestDto request) {
		CursoResponseDto cursoAtualizado = cursoService.update(id, request);
		return ResponseEntity.ok().body(cursoAtualizado);
	}

}
