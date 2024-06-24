package br.com.eu.spring.specification;

import br.com.eu.spring.orm.Funcionario;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationFuncionario {

   public static Specification<Funcionario> nome(String nome) {
      return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.like(root.get("dados").get("nome"), "%" + nome + "%");
   }

   public static Specification<Funcionario> cpf(String cpf) {
      return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("dados").get("cpf"), cpf);
   }

   public static Specification<Funcionario> salario(Double salario) {
      return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.greaterThan(root.get("dados").get("salario"), salario);
   }
}
