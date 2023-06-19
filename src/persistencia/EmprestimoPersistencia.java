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

  public Emprestimo buscarEmprestimoRetornoEmprestimo(Emprestimo objEmprestimo) {
    conn = new Conexao().conectaBD();
    String sql = "SELECT * FROM emprestimo WHERE id = ?";

    try {
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, objEmprestimo.getId());
        rs = pstm.executeQuery();

        if (rs.next()) {
            Date sqlDateEmprestimo = rs.getDate("data_emprestimo");
            Date sqlDateDevolucao = rs.getDate("data_devolucao");

            // Date dataEmprestimoSql = Date.valueOf(data1);
            // Date dataDevolucaoSql = Date.valueOf(data2);
            ClientePersistencia objClientePersistencia = new ClientePersistencia();
            Emprestimo emprestimoEncontrado = new Emprestimo();
            LivroPersistencia objLivroPersistencia = new LivroPersistencia();
            emprestimoEncontrado.setId(rs.getInt("id"));
            emprestimoEncontrado.setCliente(objClientePersistencia.buscarClientePorId(rs.getInt("cliente_id")));
            emprestimoEncontrado.setLivro(objLivroPersistencia.buscarLivroPorId(rs.getInt("livro_id")));
            emprestimoEncontrado.setDataEmpréstimo(sqlDateEmprestimo.toLocalDate());
            emprestimoEncontrado.setDataDevolução(sqlDateDevolucao.toLocalDate());
            return emprestimoEncontrado;
        } else {
            return null; // Empréstimo não encontrado
        }
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

        Date sqlDateEmprestimo = rs.getDate("data_emprestimo");
        Date sqlDateDevolucao = rs.getDate("data_devolucao");

        LivroPersistencia objLivroPersistencia = new LivroPersistencia();
        ClientePersistencia objClientePersistencia = new ClientePersistencia();

        objCliente.setId(rs.getInt("cliente_id"));
        objLivro.setId(rs.getInt("livro_id"));

        objEmprestimo.setId(rs.getInt("id"));
        objEmprestimo.setCliente(objClientePersistencia.buscarClientePorId(objCliente.getId()));
        objEmprestimo.setLivro(objLivroPersistencia.buscarLivroPorId(objLivro.getId()));
        objEmprestimo.setDataEmpréstimo(sqlDateEmprestimo.toLocalDate());
        objEmprestimo.setDataDevolução(sqlDateDevolucao.toLocalDate());

        lista.add(objEmprestimo);
      }
    } catch (Exception erro) {
      System.out.println("EmprestimoPersistencia: " + erro.getMessage());
    }
    return lista;

  }
  // ---------------------------------------------------------------------------------------------------------------------------

  public void renovarEmprestimo(Emprestimo objEmprestimo) {
    String sql = "update emprestimo set data_devolucao = ? where id = ?";

    conn = new Conexao().conectaBD();

    LocalDate data1 = objEmprestimo.getDataDevolução();
    LocalDate data2 = data1.plusDays(14);

    Date dataDevolucaoSql = Date.valueOf(data2);

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setDate(1, dataDevolucaoSql);
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
