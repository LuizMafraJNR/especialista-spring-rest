package com.algaworks.algafoodapi.core.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MultiploValidator.class})
// Iremos remover depois
public @interface Multiplo
{
	String message() default "Multiplo invalido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int numero();
}
