package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCidadeService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Cidade adicionar(@Valid @RequestBody Cidade cidade){
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
                                       @Valid @RequestBody Cidade cidade)
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
}
