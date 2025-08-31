package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CadastroRestauranteService {
    public static final String MSG_NÃO_FOI_ENCONTRADO_UM_RESTAURANTE_COM_O_ID_D = "Não foi encontrado um restaurante com o ID %d";
    public static final String MSG_NÃO_FOI_ENCONTRADO_UMA_COZINHA_COM_O_ID = "Não foi encontrado uma cozinha com o ID %d";
    public static final String MSG_HÁ_UMA_RESTAURANTE_COM_UMA_COZINHA_EM_UTILIZAÇÃO = "Há uma restaurante com uma cozinha em utilização";
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CadastroCozinhaService cozinhaService;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public Restaurante buscarOuFalhar(Long id)
    {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(
                        id));
    }

    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        restaurante.setCozinha(cozinha);

       /* Long formaPagamentoId = restaurante.getFormaPagamento().getId();
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(formaPagamentoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não foi encontrada a forma de pagamento com o Id %d",formaPagamentoId)
                ));
        restaurante.setFormaPagamento(formaPagamento);*/

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        restauranteAtual.inativar();
    }

    @Transactional
    public void remover(Long id){
        try {
            restauranteRepository.deleteById(id);
            restauranteRepository.flush();
        } catch (EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_HÁ_UMA_RESTAURANTE_COM_UMA_COZINHA_EM_UTILIZAÇÃO, id)
            );
        }
    }
}
