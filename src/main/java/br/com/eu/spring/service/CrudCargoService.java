package br.com.eu.spring.service;

import br.com.eu.spring.orm.Cargo;
import br.com.eu.spring.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

  private Boolean system = true;
  private final CargoRepository repository;

  public CrudCargoService(CargoRepository cargoRepository) {
    this.repository = cargoRepository;
  }

  public void start(){
    Scanner sc = new Scanner(System.in);
    while (system){
      System.out.println("Qual acao de cargo deseja executar");
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
    System.out.println("Digite o cargo que deseja salvar: ");
    String nome = sc.next();
    repository.save(new Cargo(nome));
    System.out.println("Salvo");
  }

  private void atualizar(Scanner sc){
    System.out.println("Id");
    int id = sc.nextInt();
    System.out.println("Nome do Cargo");
    String nome = sc.next();

    Cargo cargo = new Cargo(nome);
    cargo.setId(id);
    repository.save(cargo);
    System.out.println("Atualizado");
  }

  private void visualizar() {
    Iterable<Cargo> cargos = repository.findAll();
    cargos.forEach(cargo -> System.out.println(cargo));
  }

  private void deletar(Scanner sc) {
    System.out.println("Id");
    int id = sc.nextInt();
    repository.deleteById(id);
    System.out.println("Deletado");
  }


}