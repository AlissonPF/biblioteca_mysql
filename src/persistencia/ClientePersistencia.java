package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entities.Cliente;

public class ClientePersistencia {
  Connection conn;
  PreparedStatement pstm;
  ResultSet rs;
  ArrayList<Cliente> lista = new ArrayList<>();

  // -------------------------------------------------------------------------------------------------------------------------------
  public void cadastrarCliente(Cliente objCliente) {
    String sql = "insert into cliente (nome, cpf, idade) values(?, ?, ?)";

    conn = new Conexao().conectaBD();

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setString(1, objCliente.getNome());
      pstm.setString(2, objCliente.getCpf());
      pstm.setInt(3, objCliente.getIdade());

      pstm.execute();
      pstm.close();

    } catch (

    Exception erro) {
      System.out.println("ClientePersistencia: " + erro.getMessage());
    }
  }
  // --------------------------------------------------------------------------------------------------------------------------

  public ResultSet verificarCliente(Cliente objCliente) {
    conn = new Conexao().conectaBD();

    try {
      String sql = "select * from cliente where cpf = ?";

      pstm = conn.prepareStatement(sql);
      pstm.setString(1, objCliente.getCpf());

      ResultSet rs = pstm.executeQuery();
      return rs;
    } catch (Exception erro) {
      System.out.println("ClientePersistencia: " + erro.getMessage());
      return null;
    }

  }

  // ---------------------------------------------------------------------------------------------------------------------------
  public Cliente buscarCliente(Cliente objCliente) {
    conn = new Conexao().conectaBD();
    String sql = "select * from cliente where cpf = ?";

    try {

      pstm = conn.prepareStatement(sql);
      pstm.setString(1, objCliente.getCpf());
      rs = pstm.executeQuery();

      if (rs.next()) {
        Cliente clienteEncontrado = new Cliente();
        clienteEncontrado.setId(rs.getInt("id"));
        clienteEncontrado.setNome(rs.getString("nome"));
        clienteEncontrado.setCpf(rs.getString("cpf"));
        return clienteEncontrado;
      } else {
          return null; // Cliente não encontrado
        }
    } catch (Exception erro) {
      System.out.println("ClientePersistencia: " + erro.getMessage());
      return null;
    }

  }

  // ---------------------------------------------------------------------------------------------------------------------------
  public ArrayList<Cliente> listarClientes() {
    conn = new Conexao().conectaBD();
    String sql = "select * from cliente";

    try {
      pstm = conn.prepareStatement(sql);
      rs = pstm.executeQuery();

      while (rs.next()) {
        Cliente objCliente = new Cliente();
        objCliente.setId(rs.getInt("id"));
        objCliente.setNome(rs.getString("nome"));
        objCliente.setCpf(rs.getString("cpf"));
        objCliente.setIdade(rs.getInt("idade"));
        lista.add(objCliente);
      }
    } catch (Exception erro) {
      System.out.println("ClientePersistencia: " + erro.getMessage());
    }
    return lista;

  }
  // ---------------------------------------------------------------------------------------------------------------------------

  public void alterarCliente(Cliente objCliente) {
    String sql = "update cliente set nome = ?, idade = ? where cpf = ?";

    conn = new Conexao().conectaBD();

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setString(1, objCliente.getNome());
      pstm.setInt(2, objCliente.getIdade());
      pstm.setString(3, objCliente.getCpf());

      pstm.execute();
      pstm.close();

    } catch (

    Exception erro) {
      System.out.println("ClientePersistencia: " + erro.getMessage());
    }
  }

  // ---------------------------------------------------------------------------------------------------------------------------
  public void deletarCliente(Cliente objCliente) {
    String sql = "delete from cliente where cpf = ?";

    conn = new Conexao().conectaBD();

    try {
      pstm = conn.prepareStatement(sql);

      pstm.setString(1, objCliente.getCpf());

      pstm.execute();
      pstm.close();

    } catch (

    Exception erro) {
      System.out.println("ClientePersistencia: " + erro.getMessage());
    }
  }

  // ---------------------------------------------------------------------------------------------------------------------------
  public Cliente buscarClientePorId(int id) {
    conn = new Conexao().conectaBD();
    String sql = "select * from cliente where id = ?";

    try {
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, id);

      rs = pstm.executeQuery();

      if (rs.next()) {
        Cliente objCliente = new Cliente();
        objCliente.setId(rs.getInt("id"));
        objCliente.setNome(rs.getString("nome"));
        objCliente.setCpf(rs.getString("cpf"));
        objCliente.setIdade(rs.getInt("idade"));
        return objCliente;
      }
    } catch (Exception erro) {
      System.out.println("ClientePersistencia: " + erro.getMessage());
    }

    return null;
  }
}
