package org.health.pacientes.infra.db.predicates;

import org.health.pacientes.infra.db.entity.PessoaEntity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PessoaPredicate {
	
	private SearchCriteria criteria;
	
	public BooleanExpression getPredicate() {
		PathBuilder<PessoaEntity> entityPath = new PathBuilder<>(PessoaEntity.class, "pessoaEntity");
		
		if (isNumeric(criteria.getValue().toString())) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.goe(value);
                case "<":
                    return path.loe(value);
            }
        }
		else {
            StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
	}
	
	public void setCriteria(final SearchCriteria criteria) {
        this.criteria = criteria;
	}
	
	public static boolean isNumeric(final String str) {
        try {
            Integer.parseInt(str);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }
	
	

}
