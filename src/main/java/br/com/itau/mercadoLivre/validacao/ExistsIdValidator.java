package br.com.itau.mercadoLivre.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

	private String domainAtribute;
	private Class<?> Klass;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void initialize(ExistsId constraintAnnotation) {
		domainAtribute = constraintAnnotation.fieldName();
		Klass = constraintAnnotation.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == "" || value == null) {
			return true;
		}
		Query query = entityManager
				.createQuery("SELECT 1 FROM " + Klass.getName() + " WHERE " + domainAtribute + " = :value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		if(list.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
}