package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.formaPagamento.FormaPagamentoDTOAssembler;
import com.algaworks.algafoodapi.api.assembler.formaPagamento.FormaPagamentoDTODisassembler;
import com.algaworks.algafoodapi.api.model.FormaPagamentoOutput;
import com.algaworks.algafoodapi.api.model.input.FormaPagamentoInput;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafoodapi.domain.service.CadastroFormaPagamentoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController
{
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
	@Autowired
	private FormaPagamentoDTODisassembler formaPagamentoDTODisassembler;

	@GetMapping
	public List<FormaPagamentoOutput> listar() {
		return formaPagamentoDTOAssembler.toCollectDto(formaPagamentoRepository.findAll());
	}

	@GetMapping("/{id}")
	public FormaPagamentoOutput buscar(@PathVariable Long id) {
		return formaPagamentoDTOAssembler.toFormaPagamentoDTO(cadastroFormaPagamentoService.buscarOuFalhar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoOutput salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput)
	{
		var formaPagamento = formaPagamentoDTODisassembler.toFormaPagamento(formaPagamentoInput);
		formaPagamento = cadastroFormaPagamentoService.salvar(formaPagamento);

		return formaPagamentoDTOAssembler.toFormaPagamentoDTO(formaPagamento);
	}

	@PutMapping("/{id}")
	public FormaPagamentoOutput atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput)
	{
		var formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(id);
		formaPagamentoDTODisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		return formaPagamentoDTOAssembler.toFormaPagamentoDTO(cadastroFormaPagamentoService.salvar(formaPagamentoAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		cadastroFormaPagamentoService.excluir(id);
	}
}
