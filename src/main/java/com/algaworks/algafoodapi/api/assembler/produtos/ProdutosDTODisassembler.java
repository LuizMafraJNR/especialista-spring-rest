package com.algaworks.algafoodapi.api.assembler.produtos;

import com.algaworks.algafoodapi.api.model.input.ProdutoInput;
import com.algaworks.algafoodapi.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutosDTODisassembler
{
	@Autowired
	private ModelMapper modelMapper;

	public Produto toProdutoDomain(ProdutoInput produtoInput) {
		return
			modelMapper.map(produtoInput, Produto.class);
	}
	public void copyToDomain(ProdutoInput produtoInput, Produto produto) {
		modelMapper.map(produtoInput, produto);
	}
}
