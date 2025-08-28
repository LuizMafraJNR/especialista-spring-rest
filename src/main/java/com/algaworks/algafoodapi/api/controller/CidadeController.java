package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.cidade.CidadeDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.cidade.CidadeDTODisassembler;
import com.algaworks.algafoodapi.api.model.CidadeOutput;
import com.algaworks.algafoodapi.api.model.input.CidadeInput;
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
    @Autowired
    private CidadeDTOAssembler cidadeDTOAssembler;
    @Autowired
    private CidadeDTODisassembler cidadeDTODisassembler;

    @GetMapping()
    public List<CidadeOutput> listar(){
        return cidadeDTOAssembler.toCollectCidadeOutput(
            cidadeRepository.findAll()
        );
    }
    @GetMapping("/{id}")
    public CidadeOutput buscarPorId(@PathVariable Long id){
        Cidade cidade = cidadeService.buscarOuFalhar(id);
        return cidadeDTOAssembler.toCidadeOutput(cidade);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@Valid @RequestBody CidadeInput cidade){
        try
        {
            return cidadeService.salvar(cidadeDTODisassembler.toCidade(cidade));
        }
        catch (EstadoNaoEncontradoException e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public CidadeOutput atualizar(@PathVariable Long id,
                                       @Valid @RequestBody CidadeInput cidade)
    {
        try
        {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(id);
            cidadeDTODisassembler.copyToDomainObject(cidade, cidadeAtual);
            return cidadeDTOAssembler.toCidadeOutput(cidadeService.salvar(cidadeAtual));
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
