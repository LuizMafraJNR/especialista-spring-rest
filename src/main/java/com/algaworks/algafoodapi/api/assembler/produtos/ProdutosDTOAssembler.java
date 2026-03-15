package com.algaworks.algafoodapi.api.assembler.produtos;

import com.algaworks.algafoodapi.api.model.ProdutoOutput;
import com.algaworks.algafoodapi.domain.model.Produto;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutosDTOAssembler
{
	@Autowired
	private ModelMapper modelMapper;

	public ProdutoOutput toProdutoDTO(Produto produto) {
		return
			modelMapper.map(produto, ProdutoOutput.class);
	}

	public List<ProdutoOutput> toListProdutoDTO(List<Produto> produtos) {
		return produtos.stream()
			.map(this::toProdutoDTO)
			.collect(Collectors.toList());
	}
}
