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

   public void start(Scanner sc) {
      system = true;
      while (system) {
         System.out.println("Qual acao de funcionario deseja executar: ");
         System.out.println("1 - Salvar");
         System.out.println("2 - Atualizar");
         System.out.println("3 - Visualizar");
         System.out.println("4 - Deletar");
         System.out.println("5 - Sair");

         Integer action = sc.nextInt();
         sc.nextLine();
         switch (action) {
            case 1 -> salvar(sc);
            case 2 -> atualizar(sc);
            case 3 -> visualizar();
            case 4 -> deletar(sc);
            case 5 -> {
               system = false;
            }
            default -> System.out.println("Opcão invalida. Por favor escolha uma opção valida.");
         }
      }
   }

   private void salvar(Scanner sc) {
      System.out.println("Digite o nome do Funcionario que deseja salvar: ");
      String nome = sc.nextLine();
      System.out.println("Digite o CPF do Funcionario: ");
      String cpf = sc.nextLine();
      System.out.println("Digite o Salario: ");
      Double salario = sc.nextDouble();
      sc.nextLine();
      System.out.println("Digite a data de contracao: ");
      String dataContratacao = sc.nextLine();
      Integer cargoId = buscarCargoId(sc);
      Integer unidadeId = buscarUnidadeId(sc);
      if (cargoRepository.existsById(cargoId) && unidadeTrabalhoRepository.existsById(unidadeId)) {
         Optional<Cargo> cargo = cargoRepository.findById(cargoId);
         Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);

         Funcionario funcionario = new Funcionario(nome, cpf, salario);
         funcionario.setDataDeContratacao(LocalDate.parse(dataContratacao, formatter));
         funcionario.setCargo(cargo.get());
         funcionario.setUnidadeTrabalho(unidade.get());
         repository.save(funcionario);
         System.out.println("Salvo");
      } else {
         System.out.println("Id de cargo ou da unidade não existem.");
         System.out.println("Erro");
      }
   }

   private void atualizar(Scanner sc) {
      menuAtualizar(sc);
   }

   private void menuAtualizar(Scanner sc) {
      System.out.println("Escolha uma opcao de atualização de Funcionario");
      System.out.println("1 - Atualizar salario");
      System.out.println("2 - Atualizar cargo");
      int action = sc.nextInt();
      sc.nextLine();

      switch (action) {
         case 1 -> atualizarSalario(sc);
         case 2 -> atualizarCargo(sc);
         default -> System.out.println("Opcão invalida. Por favor escolha uma opção valida.");
      }

   }

   private void atualizarSalario(Scanner sc) {
      System.out.println("Digite o Id do Funcionario que deseja atualizar: ");
      int id = sc.nextInt();
      sc.nextLine();
      if (repository.existsById(id)) {
         Optional<Funcionario> funcionario1 = repository.findById(id);
         Funcionario funcionario = funcionario1.get();
         System.out.println(funcionario);
         System.out.println("Digite o novo salario do funcionario: ");
         Double novoSalario = sc.nextDouble();
         sc.nextLine();
         System.out.println("Confirmar alteração?");
         System.out.println("1 - Sim");
         System.out.println("2 - Nao");
         Integer opcao = sc.nextInt();
         sc.nextLine();
         if (opcao==1) {
            funcionario.setSalario(novoSalario);
            repository.save(funcionario);
            System.out.println("Atualição de salario realizada com sucesso!");
         } else {
            System.out.println("Cancelando atualização");
         }
      } else {
         System.out.println("Id do funcionario nao existe!");
      }
   }

   private void atualizarCargo(Scanner sc) {
      System.out.println("Digite o Id do Funcionario que deseja atualizar: ");
      int id = sc.nextInt();
      sc.nextLine();
      if (repository.existsById(id)) {
         Optional<Funcionario> funcionario1 = repository.findById(id);
         Funcionario funcionario = funcionario1.get();
         System.out.println(funcionario);
         System.out.println("Digite o id do novo cargo do funcionario: ");
         int cargoId = sc.nextInt();
         sc.nextLine();
         if (cargoRepository.existsById(cargoId)) {
            Optional<Cargo> cargoOptional = cargoRepository.findById(cargoId);
            Cargo cargo = cargoOptional.get();
            System.out.println(cargo);
            System.out.println("Confirmar alteração?");
            System.out.println("1 - Sim");
            System.out.println("2 - Nao");
            Integer opcao = sc.nextInt();
            sc.nextLine();
            if (opcao==1) {
               funcionario.setCargo(cargo);
               repository.save(funcionario);
               System.out.println("Atualição de cargo realizada com sucesso!");
            } else {
               System.out.println("Cancelando atualização");
            }
         } else {
            System.out.println("Id do cargo nao existe!");
         }
      } else {
         System.out.println("Id do funcionario nao existe!");
      }
   }

   private void visualizar() {
      Iterable<Funcionario> u = repository.findAll();
      u.forEach(System.out::println);
   }

   private void deletar(Scanner sc) {
      System.out.println("Digite o Id do Funcionario que deseja deletar: ");
      int id = sc.nextInt();
      sc.nextLine();
      repository.deleteById(id);
      System.out.println("Deletado");
   }

   private Integer buscarCargoId(Scanner sc) {
      Iterable<Cargo> cargos = cargoRepository.findAll();
      cargos.forEach(cargo -> System.out.println("Id_Cargo: " + cargo.getId() + "_" + cargo.getNome()));
      System.out.println("Digite o Id do cargo:");
      Integer id = sc.nextInt();
      sc.nextLine();
      return id;
   }

   private Integer buscarUnidadeId(Scanner sc) {
      Iterable<UnidadeTrabalho> unidadeTrabalhos = unidadeTrabalhoRepository.findAll();
      unidadeTrabalhos.forEach(unidadeTrabalho -> System.out.println("Id_Unidade: " + unidadeTrabalho.getId() + "_" + unidadeTrabalho.getNome()));
      System.out.println("Digite o Id da Unidade:");
      Integer id = sc.nextInt();
      sc.nextLine();
      return id;
   }


}
