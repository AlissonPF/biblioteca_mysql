package front;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Livro;
import persistencia.LivroPersistencia;

public class AppLivro {
  public AppLivro() {
    int opc;
    do {
      System.out.println("\n\n*****Livro*****");
      System.out.println("1- Cadastrar");
      System.out.println("2- Buscar");
      System.out.println("3- Listar");
      System.out.println("4- Atualizar");
      System.out.println("5- Deletar");
      System.out.println("6- Voltar");
      opc = Console.readInt("Informe a opção: ");
      switch (opc) {
        case 1:
          incluirLivro();
          break;
        case 2:
          buscarLivro();
          break;
        case 3:
          listarLivros();
          break;
        case 4:
          atualizarLivro();
          break;
        case 5:
          deletarLivro();
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
  public void incluirLivro() {
    System.out.println("\n\n*****Cadastro de Livro*****");
    Livro objLivro = new Livro();
    LivroPersistencia objLivroPersistencia = new LivroPersistencia();

    objLivro.setTitulo(Console.readString("Informe o Título: "));

    try {
      ResultSet rsLivro = objLivroPersistencia.verificarLivro(objLivro);

      if (rsLivro.next()) {
        System.out.println("\n\nLivro já cadastrado!");
      } else {
        objLivro.setAutor(Console.readString("Informe o Autor: "));
        System.out.println("Livro cadastrado com sucesso!");
        objLivroPersistencia.cadastrarLivro(objLivro);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  public static void buscarLivro() {
    System.out.println("\n\n*****Buscar Livro*****");
    Livro objLivro = new Livro();
    LivroPersistencia objLivroPersistencia = new LivroPersistencia();
    objLivro.setTitulo(Console.readString("Informe o título desejado: "));

    try {
      ResultSet rsLivro = objLivroPersistencia.verificarLivro(objLivro);

      if (rsLivro.next()) {
        System.out.println("\n\nTítulo: " + rsLivro.getString("titulo"));
        System.out.println("Autor: " + rsLivro.getString("autor"));
      } else {
        System.out.println("Livro não encontrado!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  // ----------------------------------------------------------------------------------------------------------------------------
  public static void listarLivros() {
    LivroPersistencia objLivroPersistencia = new LivroPersistencia();
    System.out.println("Livros ---->");
    for (Livro itemLivro : objLivroPersistencia.listarLivros()) {
      System.out.println("\n--------------------");
      System.out.println("Id: " + itemLivro.getId());
      System.out.println("Título: " + itemLivro.getTitulo());
      System.out.println("Autor: " + itemLivro.getAutor());
    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------
  public void atualizarLivro() {
    System.out.println("\n\n*****Atualizar Livro*****");
    Livro objLivro = new Livro();
    LivroPersistencia objLivroPersistencia = new LivroPersistencia();

    objLivro.setTitulo(Console.readString("Informe o Título: "));

    try {
      ResultSet rsLivro = objLivroPersistencia.verificarLivro(objLivro);

      if (rsLivro.next()) {
        objLivro.setAutor(Console.readString("Informe o autor: "));
        System.out.println("\n\nLivro atualizado com sucesso!\n\n");
        objLivroPersistencia.alterarLivro(objLivro);
      } else {
        System.out.println("\n\nLivro não encontrado!\n\n");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
  // ----------------------------------------------------------------------------------------------------------------------------

  private static void deletarLivro() {
    System.out.println("\n\n*****Deletar Livro*****");
    Livro objLivro = new Livro();
    LivroPersistencia objLivroPersistencia = new LivroPersistencia();

    objLivro.setTitulo(Console.readString("Informe o titulo: "));

    try {
      ResultSet rsLivro = objLivroPersistencia.verificarLivro(objLivro);

      if (rsLivro.next()) {
        System.out.println("\n\nLivro deletado com sucesso!\n\n");
        objLivroPersistencia.deletarLivro(objLivro);
      } else {
        System.out.println("\n\nLivro não encontrado!\n\n");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  // ----------------------------------------------------------------------------------------------------------------------------

}
