package com.algaworks.algafoodapi.core.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class})
public @interface ValorZeroIncluiDescricao
{
	String message() default "Descrição obrigatória inválida";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String valorField();
	String descricaoField();
	String descricaoObrigatoria();
}
