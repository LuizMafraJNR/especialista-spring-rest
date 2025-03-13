package com.algaworks.algafoodapi.core.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
@PositiveOrZero
public @interface TaxaFrete
{
	// Para substituir a message do PositiveOrZero
	@OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
	String message() default "{TaxaFrete.invalida}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
