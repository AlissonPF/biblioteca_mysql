package entities;

import java.sql.Date;

public class Emprestimo {
  private int id;
  private Livro livro;
  private Cliente cliente;
  private Date dataEmpréstimo;
  private Date dataDevolução;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Date getDataEmpréstimo() {
    return dataEmpréstimo;
  }

  public void setDataEmpréstimo(Date dataEmpréstimo) {
    this.dataEmpréstimo = dataEmpréstimo;
  }

  public Date getDataDevolução() {
    return dataDevolução;
  }

  public void setDataDevolução(Date dataDevolução) {
    this.dataDevolução = dataDevolução;
  }
}
