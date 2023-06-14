package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
  public ResultSet buscarLivro(Livro objLivro) {
    conn = new Conexao().conectaBD();
    String sql = "select * from livro where titulo = ?";

    try {

      pstm = conn.prepareStatement(sql);
      pstm.setString(1, objLivro.getAutor());

      rs = pstm.executeQuery();
      return rs;
    } catch (Exception erro) {
      System.out.println("LivroPersistencia: " + erro.getMessage());
      return null;
    }

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
}
