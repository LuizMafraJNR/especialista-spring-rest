package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.controller.exceptionhandler.Problem;
import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCidadeService;
import java.time.LocalDateTime;
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
    public Cidade buscarPorId(@PathVariable Long id){
        return cidadeService.buscarOuFalhar(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade){
        try
        {
            return cidadeService.salvar(cidade);
        }
        catch (EstadoNaoEncontradoException e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id,
                                       @RequestBody Cidade cidade)
    {
        try
        {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(id);
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            return cidadeService.salvar(cidadeAtual);
        }
        catch (EstadoNaoEncontradoException e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        cidadeService.remover(id);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEstadoNaoEncontradoException(EntidadeNaoEncontradaException e)
    {
        Problem problema = Problem.builder()
            .dateTime(LocalDateTime.now())
            .message(e.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e)
    {
        Problem problema = Problem.builder()
            .dateTime(LocalDateTime.now())
            .message(e.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(problema);
    }
}
