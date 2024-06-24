package br.com.eu.spring.repository;

import br.com.eu.spring.orm.Funcionario;
import br.com.eu.spring.orm.FuncionarioProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {
   List<Funcionario> findByDadosCpf(String cpf);

   @Query("SELECT f.id AS id, f.dados.nome AS nome, f.dados.salario AS salario, f.cargo.nome AS cargo FROM Funcionario f")
   List<FuncionarioProjection> findFuncionarioSalario();
}
