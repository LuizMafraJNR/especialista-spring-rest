package com.algaworks.algafoodapi.core.validation;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number>
{
	private int numeroMultiplo;

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext)
	{
		boolean isValid = true;
		if (Objects.nonNull(value))
		{
			BigDecimal valor = BigDecimal.valueOf(value.doubleValue());
			BigDecimal multiplo = BigDecimal.valueOf(this.numeroMultiplo);
			var rest = valor.remainder(multiplo);
			isValid = BigDecimal.ZERO.compareTo(rest) == 0;
		}
		return isValid;
	}

	@Override
	public void initialize(Multiplo constraintAnnotation)
	{
		this.numeroMultiplo = constraintAnnotation.numero();
	}
}
