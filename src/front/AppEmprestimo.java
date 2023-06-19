package front;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Cliente;
import entities.Emprestimo;
import entities.Livro;
import persistencia.ClientePersistencia;
import persistencia.EmprestimoPersistencia;
import persistencia.LivroPersistencia;

public class AppEmprestimo {
  public AppEmprestimo() {
    int opc;
    do {
      System.out.println("\n\n*****Empréstimo*****");
      System.out.println("1- Cadastrar");
      System.out.println("2- Buscar");
      System.out.println("3- Listar");
      System.out.println("4- Renovar");
      System.out.println("5- Deletar");
      System.out.println("6- Voltar");
      opc = Console.readInt("Informe a opção: ");
      switch (opc) {
        case 1:
          incluirEmprestimo();
          break;
        case 2:
          buscarEmprestimo();
          break;
        case 3:
          listarEmprestimos();
          break;
        case 4:
          atualizarEmprestimo();
          break;
        case 5:
          // deletarCliente();
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
  public void incluirEmprestimo() {
    System.out.println("\n\n*****Cadastro de empréstimo*****");
    Emprestimo objEmprestimo = new Emprestimo();
    Cliente objCliente = new Cliente();
    Livro objLivro = new Livro();

    EmprestimoPersistencia objEmprestimoPersistencia = new EmprestimoPersistencia();
    ClientePersistencia objClientePersistencia = new ClientePersistencia();
    LivroPersistencia objLivroPersistencia = new LivroPersistencia();

    objCliente.setCpf(Console.readString("Informe o cpf: "));
    objCliente =objClientePersistencia.buscarCliente(objCliente);

    objEmprestimo.setCliente(objCliente);

    try {
      ResultSet rsEmprestimo = objEmprestimoPersistencia.buscarEmprestimoPorCliente(objEmprestimo);

      if (rsEmprestimo.next()) {
        System.out.println("\n\nCliente com empréstimo ainda ativo!");
      } else {
        objLivro.setTitulo(Console.readString("Informe o título: "));
        objLivro = objLivroPersistencia.buscarLivro(objLivro);
        objEmprestimo.setLivro(objLivro);
        System.out.println("Emprestimo cadastrado com sucesso!");
        objEmprestimoPersistencia.cadastrarEmprestimo(objEmprestimo);
      }
    } catch (SQLException e) {
      // Trate a exceção de acordo com suas necessidades
      e.printStackTrace();
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  public static void buscarEmprestimo() {
    System.out.println("\n\n*****Buscar empréstimo*****");
    Emprestimo objEmprestimo = new Emprestimo();
    EmprestimoPersistencia objEmprestimoPersistencia = new EmprestimoPersistencia();
    objEmprestimo.setId(Console.readInt("Informe o id do empréstimo: "));

    try {
      ResultSet rsEmprestimo = objEmprestimoPersistencia.buscarEmprestimo(objEmprestimo);

      if (rsEmprestimo.next()) {
        objEmprestimo = objEmprestimoPersistencia.buscarEmprestimoRetornoEmprestimo(objEmprestimo);
        System.out.println("\n\nCliente: " + objEmprestimo.getCliente().getNome());
        System.out.println("Livro: " + objEmprestimo.getLivro().getTitulo());
        System.out.println("Empréstimo: " + objEmprestimo.getDataEmpréstimo());
        System.out.println("Devolução: " + objEmprestimo.getDataDevolução());

      } else {
        System.out.println("Emprestimo não encontrado!");
      }
    } catch (SQLException e) {
      // Trate a exceção de acordo com suas necessidades
      e.printStackTrace();
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  public static void listarEmprestimos() {
    EmprestimoPersistencia objEmprestimoPersistencia = new EmprestimoPersistencia();
    System.out.println("Empréstimos ---->");
    for (Emprestimo itemEmprestimo : objEmprestimoPersistencia.listarEmprestimos()) {
      System.out.println("\n--------------------");
      System.out.println("Id: " + itemEmprestimo.getId());
      System.out.println("Cliente: " + itemEmprestimo.getCliente().getNome());
      System.out.println("Livro: " + itemEmprestimo.getLivro().getTitulo());
      System.out.println("Empréstimo: " + itemEmprestimo.getDataEmpréstimo());
      System.out.println("Devolução: " + itemEmprestimo.getDataDevolução());
    }
  }
  // ----------------------------------------------------------------------------------------------------------------------------
  public void atualizarEmprestimo() {
    System.out.println("\n\n*****Renovar empréstimo*****");
    Emprestimo objEmprestimo = new Emprestimo();
    EmprestimoPersistencia objEmprestimoPersistencia = new EmprestimoPersistencia();

    objEmprestimo.setId(Console.readInt("Informe o id do empréstimo: "));

    try {
      ResultSet rsEmprestimo = objEmprestimoPersistencia.buscarEmprestimo(objEmprestimo);

      if (rsEmprestimo.next()) {
        objEmprestimo = objEmprestimoPersistencia.buscarEmprestimoRetornoEmprestimo(objEmprestimo);
        System.out.println("\n\nEmprestimo atualizado com sucesso!\n\n");
        objEmprestimoPersistencia.renovarEmprestimo(objEmprestimo);
      } else {
        System.out.println("\n\nEmprestimo não encontrado!\n\n");
      }
    } catch (SQLException e) {
      // Trate a exceção de acordo com suas necessidades
      e.printStackTrace();
    }

  }
}
