package front;

public class Principal {
    public static void main(String[] args) {
        int opc = 0;
        do {
            System.out.println("\n\n**********MENU**********");
            System.out.println("1- Clientes");
            System.out.println("2- Livros");
            System.out.println("3- Empréstimos");
            System.out.println("4- Sair");
            opc = Console.readInt("Digite a opção: ");

            switch (opc) {
                case 1:
                    new AppCliente();
                    break;
                case 2:
                    // new AppLivro();
                    break;
                case 3:
                    // new AppEmprestimo();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opc != 4);

    }

}