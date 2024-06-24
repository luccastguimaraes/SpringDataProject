package br.com.eu.spring.orm;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class DadosPessoais implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private String nome;
  private String cpf;
  private Double salario;

  public DadosPessoais() {
  }

  public DadosPessoais(String nome, String cpf, Double salario) {
    this.nome = nome;
    this.cpf = cpf;
    this.salario = salario;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Double getSalario() {
    return salario;
  }

  public void setSalario(Double salario) {
    this.salario = salario;
  }

  @Override
  public String toString() {
    return "DadosPessoais{" +
          "nome='" + nome + '\'' +
          ", cpf='" + cpf + '\'' +
          ", salario=" + salario +
          '}';
  }
}
