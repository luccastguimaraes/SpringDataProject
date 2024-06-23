package br.com.eu.spring.repository;

import br.com.eu.spring.orm.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

}
