package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.model.CozinhasXmlWrapper;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")//Pode ser colocado aqui o produces tambem. Mas tem que passar o value para o request.
public class CozinhaController
{
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    // Não precisa de "{}" se tiver apenas 1 MediaType.
    public List<Cozinha> listar(){
        return cozinhaRepository.listar();
    }

    // Listar e Customizar o XML na representaçao.
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml(){
        return new CozinhasXmlWrapper(cozinhaRepository.listar());
    }

    @ResponseStatus(value = HttpStatus.CREATED) // Só exemplo não usar.
    @GetMapping(value = "/{id}")
    public Cozinha buscar(@PathVariable("id") Long id){
        return cozinhaRepository.buscar(id);
    }
}
