package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.formaPagamento.FormaPagamentoDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.usuario.UsuarioDTOAssembler;
import com.algaworks.algafoodapi.api.model.FormaPagamentoOutput;
import com.algaworks.algafoodapi.api.model.UsuarioOutput;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/usuarios")
@RequiredArgsConstructor
public class RestauranteUsuarioController
{

	private final CadastroRestauranteService cadastroRestauranteService;

	private final UsuarioDTOAssembler usuarioDTOAssembler;

	@GetMapping()
	public List<UsuarioOutput> listar(@PathVariable Long restauranteId)
	{
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		return usuarioDTOAssembler.toCollectionDTO(restaurante.getUsuarios());
	}

	// Desassociar de uma forma de pagamento de um restaurante
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestauranteService.desassociarUsuario(restauranteId, usuarioId);
	}

	// Associar de uma forma de pagamento de um restaurante
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId)
	{
		cadastroRestauranteService.associarUsuario(restauranteId, usuarioId);
	}


}
