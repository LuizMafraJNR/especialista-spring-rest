package com.algaworks.algafoodapi.jpa.formaPagamento;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.infrastructure.repository.FormaPagamentoRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeletaFormaDePagamentoMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		FormaPagamentoRepositoryImpl formaPagamentoRepository = applicationContext.getBean(
			FormaPagamentoRepositoryImpl.class);

		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento = formaPagamentoRepository.buscarPorId(1L);
		System.out.printf("\nForma de pagamento %s removida\n", formaPagamento.getDescricao());
		formaPagamentoRepository.remover(formaPagamento);
	}
}
