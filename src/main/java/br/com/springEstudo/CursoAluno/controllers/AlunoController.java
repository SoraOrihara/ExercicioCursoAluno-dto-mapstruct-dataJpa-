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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springEstudo.CursoAluno.business.AlunoService;
import br.com.springEstudo.CursoAluno.business.dto.AlunoRequestDto;
import br.com.springEstudo.CursoAluno.business.dto.AlunoResponseDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	private final AlunoService alunoService;
	
	public AlunoController(AlunoService alunoService) {
		this.alunoService=alunoService;
	}
	
	@GetMapping
	public ResponseEntity<List<AlunoResponseDto>> listarTodos(){
		List<AlunoResponseDto> alunos = alunoService.listarTudo();
		return ResponseEntity.ok().body(alunos);
	}
	@GetMapping("/listarPorEmail")
	public ResponseEntity<AlunoResponseDto> listarPorEmail(@RequestParam String email){
		AlunoResponseDto response= alunoService.listarPorEmail(email);
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/deletarPorEmail")
	public ResponseEntity<Void> deletarPorEmail(@RequestParam String email){
		alunoService.deletarByEmail(email);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	public ResponseEntity<AlunoResponseDto> criarAluno(@RequestBody @Valid AlunoRequestDto request){
		AlunoResponseDto alunoCriado = alunoService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<AlunoResponseDto> updateAluno(@PathVariable UUID id, @RequestBody @Valid AlunoRequestDto request){
		AlunoResponseDto alunoAtualizado = alunoService.update(id, request);
		return ResponseEntity.ok().body(alunoAtualizado);
	}

}
