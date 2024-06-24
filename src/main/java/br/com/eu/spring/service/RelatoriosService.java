package br.com.eu.spring.service;

import br.com.eu.spring.orm.Funcionario;
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

   public void start() {
      Scanner sc = new Scanner(System.in);
      while (system) {
         System.out.println("Qual acao de cargo deseja executar");
         System.out.println("0 - Sair");
         System.out.println("1 - Busca funcionario pelo CPF");

         int action = sc.nextInt();
         sc.nextLine();

         switch (action) {
            case 1 -> buscaFuncionarioCpf(sc);
            case 2 -> buscaFuncionarioCpf(sc);
            default -> {
               sc.close();
               system = false;
               System.exit(0);
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
}
