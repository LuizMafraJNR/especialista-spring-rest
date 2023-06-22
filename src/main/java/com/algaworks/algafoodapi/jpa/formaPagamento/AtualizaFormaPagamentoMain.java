package com.algaworks.algafoodapi.jpa.formaPagamento;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.infrastructure.repository.FormaPagamentoRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AtualizaFormaPagamentoMain
{
	public static void main(String[] args)
	{
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

		FormaPagamentoRepositoryImpl formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepositoryImpl.class);
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setDescricao("Boleto");
		formaPagamento.setId(1L);

		formaPagamentoRepository.salvar(formaPagamento);
		System.out.printf("Foi atualizado a primeira forma de pagamento para %s\n",formaPagamento.getDescricao());
	}
}
