package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.estado.EstadoDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.estado.EstadoDTODisassembler;
import com.algaworks.algafoodapi.api.model.EstadoOutput;
import com.algaworks.algafoodapi.api.model.input.EstadoInput;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import com.algaworks.algafoodapi.domain.service.CadastroEstadoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CadastroEstadoService estadoService;
    @Autowired
    private EstadoDTOAssembler estadoDTOAssembler;
    @Autowired
    private EstadoDTODisassembler estadoDTODisassembler;

    @GetMapping
    public List<EstadoOutput> listar(){
        return estadoDTOAssembler.toCollectEstadoOutput(
            estadoRepository.findAll()
        );
    }

    @GetMapping("/{id}")
    public EstadoOutput listarPorId(@PathVariable Long id){
        return estadoDTOAssembler.toEstadoOutput(
            estadoService.buscarOuFalhar(id)
        );
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoOutput salvar(@Valid @RequestBody Estado estado){
       return estadoDTOAssembler.toEstadoOutput(
           estadoService.salvar(estado)
       );
    }

    @PutMapping("/{id}")
    public EstadoOutput atualizar(@PathVariable Long id,@Valid @RequestBody EstadoInput estado)
    {
        Estado estadoAtual = estadoService.buscarOuFalhar(id);
        estadoDTODisassembler.copyToDomainObject(estado, estadoAtual);
        return estadoDTOAssembler.toEstadoOutput(
            estadoService.salvar(estadoAtual)
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        estadoService.remover(id);
    }

}
