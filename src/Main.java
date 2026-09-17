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
                    Usuarios.listarUsuarios(Biblioteca.getUsuario());
                    break;
                case 1:
                    Livros.listaLivros(Biblioteca.getLivro());
                    break;
                case 2:
                    Biblioteca.cadastrarLivro();
                    break;
                case 3:
                    Biblioteca.registrarAluguel();
                    break;
                case 4:
                    Usuarios.devolverLivro();
                    break;
            }
        } while (opcaoMenu != 5);
    }

    public static int mostrarmenu() {
        ImageIcon logo = new ImageIcon(Biblioteca.class.getResource("/Imagens/livro.png"));
        String[] opcoes = {"Lista de usuários", "Lista de livros", "Registrar livro", "Registrar aluguel", "Devolver livro", "Sair"};
        return JOptionPane.showOptionDialog(null, "Bem-vindo ao Sistema da Biblioteca!\nO que você deseja fazer?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, logo, opcoes, opcoes[0]);
    }
}
