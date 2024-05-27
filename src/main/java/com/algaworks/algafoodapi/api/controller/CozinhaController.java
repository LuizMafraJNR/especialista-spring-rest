package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController
{
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	@GetMapping()
	public List<Cozinha> listar(){
		return cozinhaRepository.listar();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id){
		Cozinha cozinha = cozinhaRepository.buscar(id);
//		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		if (cozinha != null){
			return ResponseEntity.ok(cozinha);
		}

		return ResponseEntity.notFound().build();
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//
//		return ResponseEntity.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha){
		return cadastroCozinhaService.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
											 @RequestBody Cozinha cozinha){
		Cozinha cozinhaFinded = cozinhaRepository.buscar(cozinhaId);
		if (cozinhaFinded!=null) {
			BeanUtils.copyProperties(cozinha, cozinhaFinded, "id");
			cadastroCozinhaService.salvar(cozinhaFinded);

			return ResponseEntity.ok(cozinhaFinded);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<String> remover(@PathVariable Long cozinhaId){
		try {
			Cozinha cozinhaFinded = cozinhaRepository.buscar(cozinhaId);
			if (cozinhaFinded != null) {
				cozinhaRepository.remover(cozinhaFinded);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException ex){
			ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.CONFLICT);
			builder.body(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getLocalizedMessage());
		}
	}
}
