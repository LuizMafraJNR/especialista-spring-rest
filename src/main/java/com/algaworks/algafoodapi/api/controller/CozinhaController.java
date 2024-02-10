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
    public ResponseEntity<Cozinha> buscar(@PathVariable("id") Long id){
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        /*return ResponseEntity.status(HttpStatus.OK).body(cozinha);*/

        if(cozinha.isPresent()){
            /*return ResponseEntity.status(HttpStatus.NOT_FOUND).build();*/
            return ResponseEntity.ok(cozinha.get());

        }
        return ResponseEntity.notFound().build();

        // OR
       /* return cozinha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());*/

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
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
        if (cozinhaAtual.isPresent()){
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            Cozinha cozinhaSalva = cadastroCozinha.cadastrar(cozinhaAtual.get());
            return ResponseEntity.ok(cozinhaSalva);
        }
        return ResponseEntity.notFound().build();
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
