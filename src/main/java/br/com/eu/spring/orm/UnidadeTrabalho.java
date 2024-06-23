package br.com.eu.spring.orm;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unidade_trabalho")
public class UnidadeTrabalho {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
  private String endereco;

  @OneToMany(mappedBy = "unidadeTrabalho")
  private List<Funcionario> funcionarioList;

  public UnidadeTrabalho() {
  }

  public UnidadeTrabalho(String nome, String endereco) {
    this.nome = nome;
    this.endereco = endereco;
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

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public List<Funcionario> getFuncionarioList() {
    return funcionarioList;
  }

  public void setFuncionarioList(List<Funcionario> funcionarioList) {
    this.funcionarioList = funcionarioList;
  }

  @Override
  public String toString() {
    return "UnidadeTrabalho{" +
      "id=" + id +
      ", nome='" + nome + '\'' +
      ", endereco='" + endereco + '\'' +
      '}';
  }
}
