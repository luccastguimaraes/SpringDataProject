package br.com.eu.spring;

import br.com.eu.spring.service.CrudCargoService;
import br.com.eu.spring.service.CrudFuncionarioService;
import br.com.eu.spring.service.CrudUnidadeTrabalhoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private Boolean system = true;
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;

	public Application(CrudCargoService cargoService, CrudFuncionarioService funcionarioService, CrudUnidadeTrabalhoService unidadeTrabalhoService) {
		this.cargoService = cargoService;
     this.funcionarioService = funcionarioService;
     this.unidadeTrabalhoService = unidadeTrabalhoService;
  }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		while (system) {
			Integer function = menu(sc);
			sc.nextLine();
			switch (function) {
				case 1 -> cargoService.start();
				case 2 -> funcionarioService.start();
				case 3 -> unidadeTrabalhoService.start();
				case 4 -> cargoService.start();
				case 5 -> cargoService.start();
				case 6 -> {
					System.out.println("Finalizando");
					sc.close();
					system = false;
					System.exit(0);
				}
				default -> System.out.println("Opcão invalida. Por favor escolha uma opção valida.");
			}

		}

	}

	private Integer menu(Scanner sc){
		System.out.println("Qual função deseja executar?");
		System.out.println("1 - Cargo");
		System.out.println("2 - Funcionario");
		System.out.println("3 - Unidade");
		System.out.println("4 - Relatorios");
		System.out.println("5 - Relatorio dinamico");
		System.out.println("6 - Sair");
    return sc.nextInt();
	}
}
