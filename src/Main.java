import javax.swing.*;

public class Main {
    public static void main() {
        int opcaoMenu = 0;
        do {
            opcaoMenu = mostrarmenu();
            if (opcaoMenu == JOptionPane.CLOSED_OPTION) {
                break;
            }
            switch (opcaoMenu) {
                case 0:
                    Biblioteca.listarUsuarios();
                    break;
                case 1:
                    Biblioteca.listarLivros();
                    break;
                case 2:
                    Biblioteca.cadastrarLivro();
                    break;
                case 3:
                    Biblioteca.registrarAluguel();
                    break;
                case 4:
                    Biblioteca.devolverLivro();
                    break;
            }
        } while (opcaoMenu != 5);
    }

    public static int mostrarmenu() {
        String[] opcoes = {"Lista de usuários", "Lista de livros", "Registrar livro", "Registrar aluguel", "Devolver livro", "Sair"};
        return JOptionPane.showOptionDialog(null, "Bem-vindo ao Sistema da Biblioteca!\n\nO que você deseja fazer?", "Sistema da Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
    }
}