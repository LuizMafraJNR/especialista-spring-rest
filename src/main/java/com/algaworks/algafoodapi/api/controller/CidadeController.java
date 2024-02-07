package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController()
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CadastroCidadeService cidadeService;

    @GetMapping()
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable Long id){
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        if(cidade.isPresent()){
            return ResponseEntity.ok(cidade.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
        try {
            cidade = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @RequestBody Cidade cidade){
        try {
            Optional<Cidade> cidadeAtual = cidadeRepository.findById(id);
            if (cidadeAtual.isPresent()) {
                BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
                Cidade cidadeSalva = cidadeService.salvar(cidadeAtual.get());
                return ResponseEntity.ok(cidadeSalva);
            }
            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        try {
            cidadeService.remover(id);
            return ResponseEntity.noContent().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }
}
