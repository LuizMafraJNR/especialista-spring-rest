package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {
    public static final String MSG_CIDADE_NAO_ENCONTRADA = "Cidade com o Id %d não foi encontrada";
    public static final String MSG_CIDADE_ESTA_SENDO_UTILIZADA = "Cidade com id %d está sentod utilizada por outra entidade, por isso " +
            "não pode ser removida";
    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Estado com o id %d não foi encontrado.";
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade){
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)
                ));

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public Cidade buscarOuFalhar(Long cidadeId)
    {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)
                ));
    }

    public void remover(Long id){
        try{
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADA, id)
            );
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_ESTA_SENDO_UTILIZADA, id)
            );
        }
    }
}
