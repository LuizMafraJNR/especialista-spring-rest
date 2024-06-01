package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import com.algaworks.algafoodapi.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CadastroEstadoService estadoService;
    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }
    @GetMapping("/{id}")
    public Estado listarPorId(@PathVariable Long id){
        return estadoService.buscarOuFalhar(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody Estado estado){
       return estadoService.salvar(estado);
    }

    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id,@RequestBody Estado estado)
    {
        Estado estadoAtual = estadoService.buscarOuFalhar(id);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoService.salvar(estadoAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        estadoService.remover(id);
    }

}
