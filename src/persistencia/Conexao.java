package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
  public Connection conectaBD() {
    Connection conn = null;

    try {
      String url = "jdbc:mysql://localhost:3306/bibliotecaTeste?useSSL=false&user=root&password=positivo";
      conn = DriverManager.getConnection(url);
    } catch (Exception erro) {
      System.out.println("Conexao: " + erro.getMessage());
    }

    return conn;
  }
}