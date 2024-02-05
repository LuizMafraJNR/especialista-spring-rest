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
        return cozinhaRepository.listar();
    }

    /*@ResponseStatus(value = HttpStatus.CREATED)  Só exemplo não usar.*/
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("id") Long id){
        Cozinha cozinha = cozinhaRepository.buscar(id);

        /*return ResponseEntity.status(HttpStatus.OK).body(cozinha);*/

        if(cozinha == null){
            /*return ResponseEntity.status(HttpStatus.NOT_FOUND).build();*/
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cozinha);

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
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id,
                                             @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual = cozinhaRepository.buscar(id);
        if (cozinha == null){
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        cozinhaAtual = cozinhaRepository.cadastrar(cozinhaAtual);
        return ResponseEntity.ok(cozinhaAtual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id){
        try {
            cadastroCozinha.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
