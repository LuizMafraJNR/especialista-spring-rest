package com.algaworks.algafoodapi.core.validation;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanUtils;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object>
{
	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;

	@Override
	public void initialize(ValorZeroIncluiDescricao constraintAnnotation)
	{
		this.valorField = constraintAnnotation.valorField();
		this.descricaoField = constraintAnnotation.descricaoField();
		this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
	}

	@Override
	public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext)
	{
		boolean isValid = true;
		try
		{
			BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(o.getClass(), this.valorField)
				.getReadMethod()
				.invoke(o);
			String description = (String) BeanUtils.getPropertyDescriptor(o.getClass(), this.descricaoField)
				.getReadMethod()
				.invoke(o);

			if (Objects.nonNull(value) && BigDecimal.ZERO.compareTo(value) == 0 && Objects.nonNull(description))
			{
				isValid = description.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return isValid;
	}
}
