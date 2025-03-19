package com.algaworks.algafoodapi.core.jackson;

import com.algaworks.algafoodapi.api.controller.model.mixin.CidadeMixin;
import com.algaworks.algafoodapi.api.controller.model.mixin.CozinhaMixin;
import com.algaworks.algafoodapi.api.controller.model.mixin.RestauranteMixin;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule
{
	public JacksonMixinModule()
	{
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	}
}
