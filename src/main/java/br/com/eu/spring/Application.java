package br.com.eu.spring;

import br.com.eu.spring.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@EnableJpaRepositories
@SpringBootApplication
public class Application implements CommandLineRunner {

   private Boolean system = true;
   private final CrudCargoService cargoService;
   private final CrudFuncionarioService funcionarioService;
   private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
   private final RelatoriosService relatoriosService;
   private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

   public Application(CrudCargoService cargoService, CrudFuncionarioService funcionarioService, CrudUnidadeTrabalhoService unidadeTrabalhoService, RelatoriosService relatoriosService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
      this.cargoService = cargoService;
      this.funcionarioService = funcionarioService;
      this.unidadeTrabalhoService = unidadeTrabalhoService;
      this.relatoriosService = relatoriosService;
      this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
   }

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }

   @Override
   public void run(String... args) throws Exception {
      Scanner sc = new Scanner(System.in);
      while (system) {
         menu();
         Integer function = sc.nextInt();
         sc.nextLine();
         switch (function) {
            case 1 -> cargoService.start(sc);
            case 2 -> funcionarioService.start(sc);
            case 3 -> unidadeTrabalhoService.start(sc);
            case 4 -> relatoriosService.start(sc);
            case 5 -> relatorioFuncionarioDinamico.start(sc);
            case 6 -> {
               System.out.println("Finalizando");
               System.exit(0);
            }
            default -> System.out.println("Opcão invalida. Por favor escolha uma opção valida.");
         }
      }
      sc.close();
   }

   private void menu() {
      System.out.println("Qual função deseja executar?");
      System.out.println("1 - Cargo");
      System.out.println("2 - Funcionario");
      System.out.println("3 - Unidade");
      System.out.println("4 - Relatorios");
      System.out.println("5 - Relatorio dinamico");
      System.out.println("6 - Sair");
   }
}
