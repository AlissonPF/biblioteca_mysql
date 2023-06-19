package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Livro;

public class LivroPersistencia {
  Connection conn;
  PreparedStatement pstm;
  ResultSet rs;
  ArrayList<Livro> lista = new ArrayList<>();

  // -------------------------------------------------------------------------------------------------------------------------------
  public void cadastrarLivro(Livro objLivro) {
    String sql = "insert into livro (titulo, autor) values(?, ?)";

    conn = new Conexao().conectaBD();

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setString(1, objLivro.getTitulo());
      pstm.setString(2, objLivro.getAutor());

      pstm.execute();
      pstm.close();

    } catch (

    Exception erro) {
      System.out.println("LivroPersistencia: " + erro.getMessage());
    }
  }
  // --------------------------------------------------------------------------------------------------------------------------

  public ResultSet verificarLivro(Livro objLivro) {
    conn = new Conexao().conectaBD();

    try {
      String sql = "select * from livro where titulo = ?";

      pstm = conn.prepareStatement(sql);
      pstm.setString(1, objLivro.getTitulo());

      ResultSet rs = pstm.executeQuery();
      return rs;
    } catch (Exception erro) {
      System.out.println("LivroPersistencia: " + erro.getMessage());
      return null;
    }

  }

  // ---------------------------------------------------------------------------------------------------------------------------
  // public ResultSet buscarLivro(Livro objLivro) {
  // conn = new Conexao().conectaBD();
  // String sql = "select * from livro where titulo = ?";

  // try {

  // pstm = conn.prepareStatement(sql);
  // pstm.setString(1, objLivro.getAutor());

  // rs = pstm.executeQuery();
  // return rs;
  // } catch (Exception erro) {
  // System.out.println("LivroPersistencia: " + erro.getMessage());
  // return null;
  // }

  // }
  public Livro buscarLivro(Livro objLivro) {
    conn = new Conexao().conectaBD();
    String sql = "SELECT * FROM livro WHERE titulo = ?";

    try {
      pstm = conn.prepareStatement(sql);
      pstm.setString(1, objLivro.getTitulo());
      rs = pstm.executeQuery();

      if (rs.next()) {
        Livro livroEncontrado = new Livro();
        livroEncontrado.setId(rs.getInt("id"));
        livroEncontrado.setTitulo(rs.getString("titulo"));
        livroEncontrado.setAutor(rs.getString("autor"));
        return livroEncontrado;
      } else {
        return null; // Livro não encontrado
      }
    } catch (Exception erro) {
      System.out.println("LivroPersistencia: " + erro.getMessage());
      return null;
    }
  }

  // ---------------------------------------------------------------------------------------------------------------------------
  public List<Livro> listarLivrosEmprestados() {
    List<Livro> livrosEmprestados = new ArrayList<>();

    try {
      conn = new Conexao().conectaBD();
      String sql = "SELECT livro.* FROM emprestimo JOIN livro ON emprestimo.livro_id = livro.id";

      PreparedStatement pstm = conn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();

      while (rs.next()) {
        Livro livro = new Livro();
        livro.setId(rs.getInt("id"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutor(rs.getString("autor"));
        livrosEmprestados.add(livro);
      }

      rs.close();
      pstm.close();
    } catch (SQLException e) {
      System.out.println("EmprestimoPersistencia: " + e.getMessage());
    }

    return livrosEmprestados;
  }
  // ---------------------------------------------------------------------------------------------------------------------------

  public ArrayList<Livro> listarLivros() {
    conn = new Conexao().conectaBD();
    String sql = "select * from livro";

    try {
      pstm = conn.prepareStatement(sql);
      rs = pstm.executeQuery();

      while (rs.next()) {
        Livro objLivro = new Livro();
        objLivro.setId(rs.getInt("id"));
        objLivro.setTitulo(rs.getString("titulo"));
        objLivro.setAutor(rs.getString("autor"));

        lista.add(objLivro);
      }
    } catch (Exception erro) {
      System.out.println("LivroPersistencia: " + erro.getMessage());
    }
    return lista;

  }
  // ---------------------------------------------------------------------------------------------------------------------------

  public void alterarLivro(Livro objLivro) {
    String sql = "update livro set autor = ? where titulo = ?";

    conn = new Conexao().conectaBD();

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setString(1, objLivro.getAutor());
      pstm.setString(2, objLivro.getTitulo());

      pstm.execute();
      pstm.close();

    } catch (

    Exception erro) {
      System.out.println("LivroPersistencia: " + erro.getMessage());
    }
  }

  // ---------------------------------------------------------------------------------------------------------------------------
  public void deletarLivro(Livro objLivro) {
    String sql = "delete from livro where titulo = ?";

    conn = new Conexao().conectaBD();

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setString(1, objLivro.getTitulo());

      pstm.execute();
      pstm.close();

    } catch (

    Exception erro) {
      System.out.println("LivroPersistencia: " + erro.getMessage());
    }
  }

  // ---------------------------------------------------------------------------------------------------------------------------
  public Livro buscarLivroPorId(int id) {
    conn = new Conexao().conectaBD();
    String sql = "select * from livro where id = ?";

    try {
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, id);

      rs = pstm.executeQuery();

      if (rs.next()) {
        Livro objLivro = new Livro();
        objLivro.setId(rs.getInt("id"));
        objLivro.setTitulo(rs.getString("titulo"));
        objLivro.setAutor(rs.getString("autor"));
        return objLivro;
      }
    } catch (Exception erro) {
      System.out.println("LivroPersistencia: " + erro.getMessage());
    }

    return null;
  }
}
