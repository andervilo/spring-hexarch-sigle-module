package org.health.pacientes.infra.db.repository;

import org.health.pacientes.infra.db.entity.PessoaEntity;
import org.health.pacientes.infra.db.entity.QPessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface SpringDataPessoaRepository extends JpaRepository<PessoaEntity, Long>, 
	QuerydslPredicateExecutor<PessoaEntity>, QuerydslBinderCustomizer<QPessoaEntity>{

	@Override
    default public void customize(
      QuerydslBindings bindings, QPessoaEntity root) {
        bindings.bind(String.class)
          .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
      }
}
