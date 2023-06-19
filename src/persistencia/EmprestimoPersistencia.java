package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import entities.Cliente;
import entities.Emprestimo;
import entities.Livro;

public class EmprestimoPersistencia {
  Connection conn;
  PreparedStatement pstm;
  ResultSet rs;
  ArrayList<Emprestimo> lista = new ArrayList<>();

  // -------------------------------------------------------------------------------------------------------------------------------
  public void cadastrarEmprestimo(Emprestimo objEmprestimo) {
    String sql = "insert into emprestimo (livro_id, cliente_id, data_emprestimo, data_devolucao) values(?, ?, ?, ?)";

    LocalDate data1 = LocalDate.now();
    LocalDate data2 = data1.plusDays(14);

    Date dataEmprestimoSql = Date.valueOf(data1);
    Date dataDevolucaoSql = Date.valueOf(data2);

    conn = new Conexao().conectaBD();

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setInt(1, objEmprestimo.getLivro().getId());
      pstm.setInt(2, objEmprestimo.getCliente().getId());
      pstm.setDate(3, dataEmprestimoSql);
      pstm.setDate(4, dataDevolucaoSql);

      pstm.execute();
      pstm.close();

    } catch (

    Exception erro) {
      System.out.println("EmprestimoPersistencia: " + erro.getMessage());
    }
  }

  // --------------------------------------------------------------------------------------------------------------------------
  public ResultSet buscarEmprestimo(Emprestimo objEmprestimo) {
    conn = new Conexao().conectaBD();
    String sql = "select * from emprestimo where id = ?";

    try {

      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, objEmprestimo.getId());

      rs = pstm.executeQuery();
      return rs;
    } catch (Exception erro) {
      System.out.println("EmprestimoPersistencia: " + erro.getMessage());
      return null;
    }

  }

  // --------------------------------------------------------------------------------------------------------------------------
  public ResultSet buscarEmprestimoPorCliente(Emprestimo objEmprestimo) {
    conn = new Conexao().conectaBD();
    String sql = "select * from emprestimo where cliente_id = ?";

    try {

      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, objEmprestimo.getCliente().getId());

      rs = pstm.executeQuery();
      return rs;
    } catch (Exception erro) {
      System.out.println("EmprestimoPersistencia: " + erro.getMessage());
      return null;
    }

  }

  // ---------------------------------------------------------------------------------------------------------------------------
  public ArrayList<Emprestimo> listarEmprestimos() {
    conn = new Conexao().conectaBD();
    String sql = "select * from emprestimo";

    try {
      pstm = conn.prepareStatement(sql);
      rs = pstm.executeQuery();

      while (rs.next()) {
        Emprestimo objEmprestimo = new Emprestimo();
        Cliente objCliente = new Cliente();
        Livro objLivro = new Livro();

        LivroPersistencia objLivroPersistencia = new LivroPersistencia();
        ClientePersistencia objClientePersistencia = new ClientePersistencia();

        objCliente.setId(rs.getInt("cliente_id"));
        objLivro.setId(rs.getInt("livro_id"));

        objEmprestimo.setId(rs.getInt("id"));
        objEmprestimo.setCliente(objClientePersistencia.buscarClientePorId(objCliente.getId()));
        objEmprestimo.setLivro(objLivroPersistencia.buscarLivroPorId(objLivro.getId()));
        objEmprestimo.setDataEmpréstimo(rs.getDate("data_emprestimo"));
        objEmprestimo.setDataDevolução(rs.getDate("data_devolucao"));

        lista.add(objEmprestimo);
      }
    } catch (Exception erro) {
      System.out.println("EmprestimoPersistencia: " + erro.getMessage());
    }
    return lista;

  }
  // ---------------------------------------------------------------------------------------------------------------------------

  public void alterarEmprestimo(Emprestimo objEmprestimo) {
    String sql = "update emprestimo set data_devolucao = ? where id = ?";

    conn = new Conexao().conectaBD();

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setDate(1, objEmprestimo.getDataDevolução());
      pstm.setInt(2, objEmprestimo.getId());

      pstm.execute();
      pstm.close();

    } catch (

    Exception erro) {
      System.out.println("ClientePersistencia: " + erro.getMessage());
    }
  }

  // ---------------------------------------------------------------------------------------------------------------------------
  public void deletarEmprestimo(Emprestimo objEmprestimo) {
    String sql = "delete from emprestimo where id = ?";

    conn = new Conexao().conectaBD();

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setInt(1, objEmprestimo.getId());

      pstm.execute();
      pstm.close();

    } catch (

    Exception erro) {
      System.out.println("ClientePersistencia: " + erro.getMessage());
    }
  }
}
