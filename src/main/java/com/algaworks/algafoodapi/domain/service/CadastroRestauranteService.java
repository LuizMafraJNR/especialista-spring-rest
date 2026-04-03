package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.*;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroRestauranteService {
    public static final String MSG_NÃO_FOI_ENCONTRADO_UM_RESTAURANTE_COM_O_ID_D = "Não foi encontrado um restaurante com o ID %d";
    public static final String MSG_NÃO_FOI_ENCONTRADO_UMA_COZINHA_COM_O_ID = "Não foi encontrado uma cozinha com o ID %d";
    public static final String MSG_HÁ_UMA_RESTAURANTE_COM_UMA_COZINHA_EM_UTILIZAÇÃO = "Há uma restaurante com uma cozinha em utilização";

    private final RestauranteRepository restauranteRepository;

    private final CozinhaRepository cozinhaRepository;

    private final CadastroCozinhaService cozinhaService;

    private final FormaPagamentoRepository formaPagamentoRepository;

    private final CadastroCidadeService cidadeService;

	private final CadastroCidadeService cadastroCidadeService;

    private final CadastroProdutoService cadastroProdutoService;

    private final CadastroFormaPagamentoService cadastroFormaPagamentoService;

    private final UsuarioService usuarioService;

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
        Cidade cidade =  cadastroCidadeService.buscarOuFalhar(restaurante.getEndereco().getCidade().getId());
        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

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
    public void ativar(List<Long> restaurantesIds) {
        restaurantesIds.forEach(this::ativar);
    }

    @Transactional
    public void inativar(List<Long> restaurantesIds) {
        restaurantesIds.forEach(this::inativar);
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

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoID)
    {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoID);
        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void fecharRestaurante(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.fecharRestaurante();
    }

    @Transactional
    public void abrirRestaurante(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.abrirRestaurante();
    }

    @Transactional
    public void associarUsuario(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        restaurante.associarUsuario(usuario);
    }

    @Transactional
    public void desassociarUsuario(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        restaurante.desassociarUsuario(usuario);
    }
}
