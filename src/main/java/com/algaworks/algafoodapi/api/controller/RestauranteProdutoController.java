package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.formaPagamento.FormaPagamentoDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.produtos.ProdutosDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.produtos.ProdutosDTODisassembler;
import com.algaworks.algafoodapi.api.model.FormaPagamentoOutput;
import com.algaworks.algafoodapi.api.model.ProdutoOutput;
import com.algaworks.algafoodapi.api.model.input.ProdutoInput;
import com.algaworks.algafoodapi.domain.model.Produto;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.service.CadastroProdutoService;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController
{

	@Autowired
	private ProdutosDTOAssembler produtosDTOAssembler;

	@Autowired
	private ProdutosDTODisassembler produtosDTODisassembler;

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@GetMapping()
	public List<ProdutoOutput> listar(@PathVariable Long restauranteId)
	{
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		return produtosDTOAssembler.toListProdutoDTO(restaurante.getProdutos());
	}

	@GetMapping("/{produtoId}")
	public ProdutoOutput buscarPorId(@PathVariable Long produtoId, @PathVariable Long restauranteId)
	{
		return produtosDTOAssembler.toProdutoDTO(
			cadastroProdutoService.buscarOuFalhar(produtoId, restauranteId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoOutput salvar(@Valid @RequestBody ProdutoInput produtoInput,
		@PathVariable Long restauranteId)
	{
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		Produto produto = produtosDTODisassembler.toProdutoDomain(produtoInput);
		produto.setRestaurante(restaurante);

		produto = cadastroProdutoService.salvar(produto);

		return produtosDTOAssembler.toProdutoDTO(produto);
	}

	@PutMapping("/{produtoId}")
	public ProdutoOutput atualizar(@Valid @RequestBody ProdutoInput produtoInput,
		@PathVariable Long restauranteId, @PathVariable Long produtoId)
	{
		Produto produto = cadastroProdutoService.buscarOuFalhar(produtoId, restauranteId);
		produtosDTODisassembler.copyToDomain(produtoInput, produto);

		return produtosDTOAssembler.toProdutoDTO(cadastroProdutoService.salvar(produto));
	}

}
