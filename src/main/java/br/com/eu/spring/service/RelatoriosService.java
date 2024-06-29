package br.com.eu.spring.service;

import br.com.eu.spring.orm.Funcionario;
import br.com.eu.spring.orm.FuncionarioProjection;
import br.com.eu.spring.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {
   private Boolean system = true;

   private final FuncionarioRepository funcionarioRepository;

   public RelatoriosService(FuncionarioRepository funcionarioRepository) {
      this.funcionarioRepository = funcionarioRepository;
   }

   public void start(Scanner sc) {
      system = true;
      while (system) {
         System.out.println("Qual relatorio deseja executar");
         System.out.println("0 - Sair");
         System.out.println("1 - Buscar funcionario pelo CPF");
         System.out.println("2 - Relatorio dos Salarios dos Funcionarios");

         Integer action = sc.nextInt();
         sc.nextLine();

         switch (action) {
            case 1 -> buscaFuncionarioCpf(sc);
            case 2 -> relatorioSalarioFuncionario();
            default -> {
               system = false;
            }
         }
      }
   }

   private void buscaFuncionarioCpf(Scanner sc) {
      System.out.println("Qual CPF deseja pesquisar");
      String cpf = sc.nextLine();
      List<Funcionario> list = funcionarioRepository.findByDadosCpf(cpf);
      list.forEach(System.out::println);
   }

   private void relatorioSalarioFuncionario() {
      List<FuncionarioProjection> list = funcionarioRepository.findFuncionarioSalario();
      list.forEach(f -> System.out.println("Funcionario: id: " + f.getId()
            + "  nome: " + f.getNome() + "  salario: " + f.getSalario()
            + "  Cargo: " + f.getCargo()));
   }
}
