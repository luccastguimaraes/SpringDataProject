package br.com.eu.spring.orm;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Embedded
  private DadosPessoais dados;
  private LocalDate dataDeContratacao;
  @ManyToOne
  @JoinColumn(name = "cargo_id", nullable = false)
  private Cargo cargo;
  @ManyToOne
  @JoinColumn(name = "unidade_id", nullable = false)
  private UnidadeTrabalho unidadeTrabalho;

  public Funcionario() {
  }

  public Funcionario(String nome, String cpf, Double salario) {
    this.dados = new DadosPessoais(nome, cpf, salario);
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public UnidadeTrabalho getUnidadeTrabalho() {
    return unidadeTrabalho;
  }

  public void setUnidadeTrabalho(UnidadeTrabalho unidadeTrabalho) {
    this.unidadeTrabalho = unidadeTrabalho;
  }

  public String getCpf() {
    return this.dados.getCpf();
  }

  public Double getSalario() {
    return this.dados.getSalario();
  }

  public LocalDate getDataDeContratacao() {
    return dataDeContratacao;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return this.dados.getNome();
  }

  public void setNome(String nome) {
    this.dados.setNome(nome);
  }

  public void setCpf(String cpf) {
    this.dados.setCpf(cpf);
  }

  public void setSalario(Double salario) {
    this.dados.setSalario(salario);
  }

  public void setDataDeContratacao(LocalDate dataDeContratacao) {
    this.dataDeContratacao = dataDeContratacao;
  }

  @Override
  public String toString() {
    return "Funcionario{" +
      "id=" + id +
      ", dataDeContratacao=" + dataDeContratacao +
      ", cargo=" + cargo +
      ", unidadeTrabalho=" + unidadeTrabalho +
      ", dados=" + dados +
      '}';
  }
}
