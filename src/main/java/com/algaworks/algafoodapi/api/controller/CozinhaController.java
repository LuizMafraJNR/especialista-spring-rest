package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cozinhas")//Pode ser colocado aqui o produces tambem. Mas tem que passar o value para o request.
public class CozinhaController
{
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    // Não precisa de "{}" se tiver apenas 1 MediaType.
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    /*@ResponseStatus(value = HttpStatus.CREATED)  Só exemplo não usar.*/
    @GetMapping(value = "/{id}")
    public Cozinha buscar(@PathVariable("id") Long id){
        return cadastroCozinha.buscarOuFalhar(id);

        /*aula 8.5
        if(cozinha.isPresent()){
            *//*return ResponseEntity.status(HttpStatus.NOT_FOUND).build();*//*
            return ResponseEntity.ok(cozinha.get());

        }
        return ResponseEntity.notFound().build();*/

        /*HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();*/
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cadastroCozinha.cadastrar(cozinha);
    }

    //Atualizar com PUT
    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable Long id,
                                             @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(id);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cadastroCozinha.cadastrar(cozinhaAtual);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id){
        try {
            cadastroCozinha.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }*/

    // Em caso de sucesso no delete, o status é 204 No Content.
    // Anotar o ResponseStatus nas Exceptions.
    // Possivel fazer com o try catch, mas depende de como tu for utilizar Aula 8.3
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        cadastroCozinha.remover(id);
    }

}
