package br.com.eu.spring.service;

import br.com.eu.spring.orm.Cargo;
import br.com.eu.spring.orm.Funcionario;
import br.com.eu.spring.orm.UnidadeTrabalho;
import br.com.eu.spring.repository.CargoRepository;
import br.com.eu.spring.repository.FuncionarioRepository;
import br.com.eu.spring.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

  private Boolean system = true;
  private final FuncionarioRepository repository;
  private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
  private final CargoRepository cargoRepository;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


  public CrudFuncionarioService(FuncionarioRepository repository, UnidadeTrabalhoRepository unidadeTrabalhoRepository, CargoRepository cargoRepository) {
    this.repository = repository;
    this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    this.cargoRepository = cargoRepository;
  }

  public void start(){
    Scanner sc = new Scanner(System.in);
    while (system){
      System.out.println("Qual acao de funcionario deseja executar: ");
      System.out.println("1 - Salvar");
      System.out.println("2 - Atualizar");
      System.out.println("3 - Visualizar");
      System.out.println("4 - Deletar");
      System.out.println("5 - Sair");

      int action = sc.nextInt();
      switch (action) {
        case 1 -> salvar(sc);
        case 2 -> atualizar(sc);
        case 3 -> visualizar();
        case 4 -> deletar(sc);
        case 5 -> {
          sc.close();
          system = false;
          System.exit(0);
        }
        default -> System.out.println("Opcão invalida. Por favor escolha uma opção valida.");
      }


    }
  }

  private void salvar(Scanner sc){
    System.out.println("Digite o nome do Funcionario que deseja salvar: ");
    String nome = sc.next();
    System.out.println("Digite o CPF do Funcionario: ");
    String cpf = sc.next();
    System.out.println("Digite o Salario: ");
    Double salario = sc.nextDouble();
    System.out.println("Digite a data de contracao: ");
    String dataContratacao = sc.next();
    System.out.println("Digite o Id do cargo:");
    Integer cargoId = sc.nextInt();
    System.out.println("Digite o Id da unidade de trabalho:");
    Integer unidadeId = sc.nextInt();

    Optional<Cargo> cargo = cargoRepository.findById(cargoId);
    Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);

    Funcionario funcionario = new Funcionario(nome, cpf, salario);
    funcionario.setDataDeContratacao(LocalDate.parse(dataContratacao, formatter));
    funcionario.setCargo(cargo.get());
    funcionario.setUnidadeTrabalho(unidade.get());
    repository.save(funcionario);
    System.out.println("Salvo");
  }

  private void atualizar(Scanner sc){
    menuAtualizar(sc);
  }

  private void menuAtualizar(Scanner sc) {
    System.out.println("Escolha uma opcao de atualização de Funcionario");
    System.out.println("1 - Atualizar salario");
    System.out.println("2 - Atualizar cargo");
    System.out.println("3 - Atualizar unidade");
    int action = sc.nextInt();

    switch (action) {
      case 1 -> atualizarSalario(sc);
      case 2 -> atualizar(sc);
      case 3 -> visualizar();
      default -> System.out.println("Opcão invalida. Por favor escolha uma opção valida.");
    }


  }

  private void atualizarSalario(Scanner sc) {
    System.out.println("Digite o Id do Funcionario que deseja atualizar: ");
    int id = sc.nextInt();
    Optional<Funcionario> funcionario1 = repository.findById(id);
    Funcionario funcionario = funcionario1.get();
    System.out.println(funcionario);
    System.out.println("Digite o novo salario do funcionario: ");
    Double novoSalario = sc.nextDouble();
    System.out.println("Confirmar alteração?");
    System.out.println("1 - Sim");
    System.out.println("2 - Nao");
    Integer opcao = sc.nextInt();
    if (opcao==1){
      funcionario.setSalario(novoSalario);
      repository.save(funcionario);
      System.out.println("Atualição de salario realizada com sucesso!");
    } else {
      System.out.println("Cancelando atualização");
    }
  }

  private void visualizar() {

  }

  private void deletar(Scanner sc) {
    System.out.println("Digite o Id do Funcionario que deseja deletar: ");
    int id = sc.nextInt();
    repository.deleteById(id);
    System.out.println("Deletado");
  }


}
