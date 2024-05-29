package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    public static final String COZINHA_DE_ID_D_NÃO_FOI_ENCONTRADA = "Cozinha de id %d,  não foi encontrada.";
    public static final String MSG_COZINHA_EM_USO = "Essa entidade de id %d já está em uso";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha cadastrar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void remover(Long id){
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format(COZINHA_DE_ID_D_NÃO_FOI_ENCONTRADA, id)
            );
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, id)
            );
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId)
    {
        return cozinhaRepository.findById(cozinhaId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(COZINHA_DE_ID_D_NÃO_FOI_ENCONTRADA, cozinhaId)));
    }
}
