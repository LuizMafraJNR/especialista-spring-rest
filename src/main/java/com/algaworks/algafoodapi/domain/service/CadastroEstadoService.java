package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroEstadoService {
    public static final String MSG_ESTADO_VINCULADO_A_CIDADE = "Parece que esse estado de id %d está vinculada a uma cidade e" +
            " não pode ser excluid";
    @Autowired
    private EstadoRepository estadoRepository;
    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    public Estado buscarOuFalhar(Long estadoId)
    {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

    @Transactional
    public void remover(Long id){
        try{
            estadoRepository.deleteById(id);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException e){
            throw new EstadoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_VINCULADO_A_CIDADE, id)
            );
        }
    }
}
