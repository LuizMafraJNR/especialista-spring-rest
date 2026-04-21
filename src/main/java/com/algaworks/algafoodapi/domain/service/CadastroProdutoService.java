package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.ProdutoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Produto;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.ProdutoRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CadastroProdutoService
{

	private static final String MSG_PRODUTO_EM_USO = "Produto de código %d não pode ser removida, pois está em uso";

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Transactional
	public void remover(Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);
			produtoRepository.flush();

		} catch (EntityNotFoundException e) {
			throw new FormaPagamentoNaoEncontradaException(produtoId);

		} catch (DataIntegrityViolationException e) {
			throw new com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException(
				String.format(MSG_PRODUTO_EM_USO, produtoId));
		}
	}

	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(restauranteId, produtoId)
			.orElseThrow(() -> new ProdutoNaoEncontradaException(produtoId));
	}
}
