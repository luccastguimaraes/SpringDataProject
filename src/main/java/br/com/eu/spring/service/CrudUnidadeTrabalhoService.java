package br.com.eu.spring.service;

import br.com.eu.spring.orm.Cargo;
import br.com.eu.spring.orm.UnidadeTrabalho;
import br.com.eu.spring.repository.CargoRepository;
import br.com.eu.spring.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

  private Boolean system = true;
  private final UnidadeTrabalhoRepository repository;

  public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
    this.repository = repository;
  }

  public void start(){
    Scanner sc = new Scanner(System.in);
    while (system){
      System.out.println("Qual acao de unidade de trabalho deseja executar");
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
    System.out.println("Digite o nome da unidade que deseja salvar: ");
    String nome = sc.next();
    System.out.println("Digite o nome da unidade que deseja salvar: ");
    String endereco = sc.next();
    repository.save(new UnidadeTrabalho(nome, endereco));
    System.out.println("Salvo");
  }

  private void atualizar(Scanner sc){
    System.out.println("Digite o Id da unidade que deseja atualizar: ");
    int id = sc.nextInt();
    System.out.println("Digite o novo nome da unidade: ");
    String nome = sc.next();
    System.out.println("Digite o novo endereco da unidade: ");
    String endereco = sc.next();

    UnidadeTrabalho u = new UnidadeTrabalho(nome, endereco);
    u.setId(id);
    repository.save(u);
    System.out.println("Atualizado");
  }

  private void visualizar() {
    Iterable<UnidadeTrabalho> u = repository.findAll();
    u.forEach(System.out::println);
  }

  private void deletar(Scanner sc) {
    System.out.println("Digite o Id da unidade que deseja deletar: ");
    int id = sc.nextInt();
    repository.deleteById(id);
    System.out.println("Deletado");
  }


}
