package com.algaworks.algafoodapi.api.assembler.formaPagamento;

import com.algaworks.algafoodapi.api.model.FormaPagamentoOutput;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class FormaPagamentoDTOAssembler
{
	@Autowired
	private ModelMapper modelMapper;

	public FormaPagamentoOutput toFormaPagamentoDTO(FormaPagamento formaPagamento)
	{
		return modelMapper.map(formaPagamento, FormaPagamentoOutput.class);
	}

	public List<FormaPagamentoOutput> toCollectDto(List<FormaPagamento> formasPagamento)
	{
		return formasPagamento.stream()
			.map(this::toFormaPagamentoDTO)
			.collect(java.util.stream.Collectors.toList());
	}
}
