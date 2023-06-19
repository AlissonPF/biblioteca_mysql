CREATE TABLE emprestimo (
  id INT PRIMARY KEY AUTO_INCREMENT,
  livro_id INT,
  cliente_id INT,
  data_emprestimo DATE,
  data_devolucao DATE,
  FOREIGN KEY (livro_id) REFERENCES livro(id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
