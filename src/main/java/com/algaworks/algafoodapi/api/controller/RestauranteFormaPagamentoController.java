package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.formaPagamento.FormaPagamentoDTOAssembler;
import com.algaworks.algafoodapi.api.model.FormaPagamentoOutput;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController
{

	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;


	@GetMapping()
	public List<FormaPagamentoOutput> listar(@PathVariable Long restauranteId)
	{
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		return formaPagamentoDTOAssembler.toCollectDto(restaurante.getFormasPagamento());
	}

	// Desassociar de uma forma de pagamento de um restaurante
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}

	// Associar de uma forma de pagamento de um restaurante
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId)
	{
		cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
	}


}
