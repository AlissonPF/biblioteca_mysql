package front;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Cliente;
import entities.Emprestimo;
import persistencia.ClientePersistencia;

public class AppCliente {
  public AppCliente() {
    int opc;
    do {
      System.out.println("\n\n*****Cliente*****");
      System.out.println("1- Cadastrar");
      System.out.println("2- Buscar");
      System.out.println("3- Listar");
      System.out.println("4- Atualizar");
      System.out.println("5- Deletar");
      System.out.println("6- Voltar");
      opc = Console.readInt("Informe a opção: ");
      switch (opc) {
        case 1:
          incluirCliente();
          break;
        case 2:
          buscarCliente();
          break;
        case 3:
          listarClientes();
          break;
        case 4:
          atualizarCliente();
          break;
        case 5:
          deletarCliente();
          break;
        case 6:
          break;
        default:
          System.out.println("Valor inválido!");
          break;
      }

    } while (opc != 6);
  }

  // ----------------------------------------------------------------------------------------------------------------------------
  public void incluirCliente() {
    System.out.println("\n\n*****Cadastro de cliente*****");
    Cliente objCliente = new Cliente();
    ClientePersistencia objClientePersistencia = new ClientePersistencia();

    objCliente.setCpf(Console.readString("Informe o cpf: "));

    try {
      ResultSet rsCliente = objClientePersistencia.verificarCliente(objCliente);

      if (rsCliente.next()) {
        System.out.println("\n\nCliente já cadastrado!");
      } else {
        objCliente.setNome(Console.readString("Informe o nome: "));
        objCliente.setIdade(Console.readInt("Informe a idade: "));
        System.out.println("Cliente cadastrado com sucesso!");
        objClientePersistencia.cadastrarCliente(objCliente);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  public static void buscarCliente() {
    System.out.println("\n\n*****Buscar cliente*****");
    Cliente objCliente = new Cliente();
    ClientePersistencia objClientePersistencia = new ClientePersistencia();
    objCliente.setCpf(Console.readString("Informe o cpf desejado: "));

    try {
      ResultSet rsCliente = objClientePersistencia.verificarCliente(objCliente);

      if (rsCliente.next()) {
        System.out.println("\n\nNome: " + rsCliente.getString("nome"));
        System.out.println("Cpf: " + rsCliente.getString("cpf"));
        System.out.println("Idade: " + rsCliente.getInt("idade"));
      } else {
        System.out.println("Cliente não encontrado!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  public static void listarClientes() {
    ClientePersistencia objClientePersistencia = new ClientePersistencia();
    System.out.println("Clientes ---->");
    for (Cliente itemCliente : objClientePersistencia.listarClientes()) {
      System.out.println("\n--------------------");
      System.out.println("Id: " + itemCliente.getId());
      System.out.println("Nome: " + itemCliente.getNome());
      System.out.println("Cpf: " + itemCliente.getCpf());
      System.out.println("Idade: " + itemCliente.getIdade());
    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------
  public void atualizarCliente() {
    System.out.println("\n\n*****Atualizar cliente*****");
    Cliente objCliente = new Cliente();
    ClientePersistencia objClientePersistencia = new ClientePersistencia();

    objCliente.setCpf(Console.readString("Informe o cpf: "));

    try {
      ResultSet rsCliente = objClientePersistencia.verificarCliente(objCliente);

      if (rsCliente.next()) {
        objCliente = objClientePersistencia.buscarCliente(objCliente);
        List<Emprestimo> emprestimosCliente = objClientePersistencia.listarEmprestimosCliente(objCliente);

        if (!emprestimosCliente.isEmpty()) {
          System.out.println("\n\nCliente ainda com empréstimo ativo!\n\n");
        } else {
          objCliente.setNome(Console.readString("Informe o nome: "));
          objCliente.setIdade(Console.readInt("Informe a idade: "));
          System.out.println("\n\nCliente atualizado com sucesso!\n\n");
          objClientePersistencia.alterarCliente(objCliente);
        }
      } else {
        System.out.println("\n\nCliente não encontrado!\n\n");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  private static void deletarCliente() {
    System.out.println("\n\n*****Deletar cliente*****");
    Cliente objCliente = new Cliente();
    ClientePersistencia objClientePersistencia = new ClientePersistencia();

    objCliente.setCpf(Console.readString("Informe o cpf: "));

    try {
      ResultSet rsCliente = objClientePersistencia.verificarCliente(objCliente);

      if (rsCliente.next()) {
        objCliente = objClientePersistencia.buscarCliente(objCliente);
        List<Emprestimo> emprestimosCliente = objClientePersistencia.listarEmprestimosCliente(objCliente);

        if (!emprestimosCliente.isEmpty()) {
          System.out.println("\n\nCliente ainda com empréstimo ativo!\n\n");
        } else {
          System.out.println("\n\nCliente deletado com sucesso!\n\n");
          objClientePersistencia.deletarCliente(objCliente);
        }
      } else {
        System.out.println("\n\nCliente não encontrado!\n\n");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  // ----------------------------------------------------------------------------------------------------------------------------

  // private static void deletarCliente() {
  // System.out.println("\n\n*****Deletar cliente*****");
  // Cliente objCliente = new Cliente();
  // ClientePersistencia objClientePersistencia = new ClientePersistencia();

  // objCliente.setCpf(Console.readString("Informe o cpf: "));

  // try {
  // ResultSet rsCliente = objClientePersistencia.verificarCliente(objCliente);

  // if (rsCliente.next()) {
  // objCliente = objClientePersistencia.buscarCliente(objCliente);
  // List<Cliente> ClientesEmprestados =
  // objClientePersistencia.listarClientesEmprestados();
  // boolean ClienteEmprestado = false;
  // for (Cliente itemCliente : ClientesEmprestados) {
  // if (objCliente.getCpf().equals(itemCliente.getCpf())) {
  // ClienteEmprestado = true;
  // break;
  // }
  // }
  // if (ClienteEmprestado) {
  // System.out.println("\n\nCliente ainda com empréstimo ativo!\n\n");
  // } else {
  // System.out.println("\n\nCliente deletado com sucesso!\n\n");
  // objClientePersistencia.deletarCliente(objCliente);
  // }
  // } else {
  // System.out.println("\n\nCliente não encontrado!\n\n");
  // }
  // } catch (SQLException e) {
  // e.printStackTrace();
  // }
  // }
  // ----------------------------------------------------------------------------------------------------------------------------

}
