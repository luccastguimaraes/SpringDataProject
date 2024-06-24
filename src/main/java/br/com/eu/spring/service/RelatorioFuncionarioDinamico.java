package br.com.eu.spring.service;

import br.com.eu.spring.orm.Funcionario;
import br.com.eu.spring.repository.FuncionarioRepository;
import br.com.eu.spring.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

   private final FuncionarioRepository funcionarioRepository;

   private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

   public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
      this.funcionarioRepository = funcionarioRepository;
   }

   public void start() {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite o nome");
      String nome = scanner.nextLine();

      if (nome.equalsIgnoreCase("NULL")) {
         nome = null;
      }

      System.out.println("Digite o cpf");
      String cpf = scanner.nextLine();

      if (cpf.equalsIgnoreCase("NULL")) {
         cpf = null;
      }

      System.out.println("Digite o Salario");
      Double salario = scanner.nextDouble();
      scanner.nextLine();

      if (salario==0) {
         salario = null;
      }


      List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification
            .where(
                  SpecificationFuncionario.nome(nome))
            .or(SpecificationFuncionario.cpf(cpf))
            .or(SpecificationFuncionario.salario(salario))
      );
      funcionarios.forEach(System.out::println);
   }
}
