package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {
    public static final String MSG_ESTADO_DE_ID_D_NÃO_ENCONTRADO = "Estado de id %d não encontrado";
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
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_ESTADO_DE_ID_D_NÃO_ENCONTRADO, estadoId)
                ));
    }

    public void remover(Long id){
        try{
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_ESTADO_DE_ID_D_NÃO_ENCONTRADO, id)
            );
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_VINCULADO_A_CIDADE, id)
            );
        }
    }
}
