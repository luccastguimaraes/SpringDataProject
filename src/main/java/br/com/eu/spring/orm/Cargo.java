package br.com.eu.spring.orm;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cargos")
public class Cargo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
  @OneToMany(mappedBy = "cargo")
  private List<Funcionario> funcionarioList;

  public Cargo() {
  }

  public Cargo(String nome) {
    this.nome = nome;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return "Cargo{" +
      "id=" + id +
      ", nome='" + nome + '\'' +
      '}';
  }
}
