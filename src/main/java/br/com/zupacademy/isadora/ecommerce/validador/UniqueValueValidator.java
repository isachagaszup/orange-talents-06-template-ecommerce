package br.com.zupacademy.isadora.ecommerce.validador;

import br.com.zupacademy.isadora.ecommerce.validador.anotacao.UniqueValue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> domainClass;
    private String fieldName;

    @Override
    public void initialize(UniqueValue param) {
        domainClass = param.domainClass();
        fieldName = param.fieldName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from " + domainClass.getName() + " where "+ fieldName + "=:value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();

        return list.isEmpty();
    }
}
